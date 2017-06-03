package com.github.nk.klusterfuck.admin.services;

import io.fabric8.kubernetes.api.model.EnvVar;
import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.ServicePort;
import io.fabric8.kubernetes.api.model.extensions.Deployment;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by nipunkumar on 28/05/17.
 */
@Service
public class KubeService {

    @Value("${AGENT_IMAGE}")
    private String agentImage;
    @Value("${NAMESPACE}")
    private String namespace;

    @Autowired
    private DefaultKubernetesClient client;
    @Autowired
    private IdService idService;

    // @formatter:off
    public KubeDeployment createFnService(String gitUrl, String user, String password) {
        String name = idService.newId();
        io.fabric8.kubernetes.api.model.Service service = client.services().createNew()
                .withNewMetadata()
                    .withName(name)
                    .withNamespace(namespace)
                    .withLabels(new HashMap<String, String>() {{put("app", name);}})
                .endMetadata()
                .withNewSpec()
                    .withSelector(new HashMap<String, String>() {{
                        put("app", name);
                    }})
                    .withPorts(new ServicePort("http", null, 80, "TCP", new IntOrString(5000)))
                .endSpec()
                .done();
        Deployment deployment = client.extensions().deployments().createNew()
                .withNewMetadata()
                    .withName(name)
                    .withNamespace(namespace)
                    .withLabels(new HashMap<String, String>() {{put("app", name);}})
                .endMetadata()
                .withNewSpec()
                    .withReplicas(1)
                    .withNewSelector()
                        .addToMatchLabels("app", name)
                    .endSelector()
                    .withNewTemplate()
                        .withNewMetadata()
                            .withLabels(new HashMap<String, String>() {{put("app", name);}})
                        .endMetadata()
                        .withNewSpec()
                            .withContainers()
                                .addNewContainer()
                                    .withName("meh")
                                    .withImage(agentImage)
                                    .withImagePullPolicy("IfNotPresent")
                                    .withEnv()
                                        .withEnv(
                                                new EnvVar("WORK_DIR", "/app/repo", null),
                                                new EnvVar("GIT_URL", gitUrl, null),
                                                new EnvVar("GOGS_USER", user, null),
                                                new EnvVar("GOGS_PASSWORD", password, null))
                                    .withPorts()
                                        .addNewPort().withProtocol("TCP").withContainerPort(5000).endPort()
                                .endContainer()
                        .endSpec()
                    .endTemplate()
                .endSpec()
                .done();
        KubeDeployment kd = new KubeDeployment();
        kd.setNamespace(deployment.getMetadata().getNamespace());
        kd.setService(service.getMetadata().getName());
        kd.setDeployment(deployment.getMetadata().getName());
        return kd;
    }
    // @formatter:on
}