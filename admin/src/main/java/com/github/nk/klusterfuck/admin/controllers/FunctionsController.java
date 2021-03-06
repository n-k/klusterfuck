package com.github.nk.klusterfuck.admin.controllers;

import com.github.nk.klusterfuck.admin.controllers.objects.CreateFunctionRequest;
import com.github.nk.klusterfuck.admin.model.KFFunction;
import com.github.nk.klusterfuck.admin.services.FunctionsService;
import com.github.nk.klusterfuck.admin.services.Version;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for /api/v1/functions/** endpoints
 */
@Api("Functions")
@RestController()
@RequestMapping("/api/v1/functions")
public class FunctionsController {

	@Autowired
	private FunctionsService fnService;
	@Autowired
	private DefaultKubernetesClient client;

	/**
	 *
	 * @return
	 */
	@ApiOperation(value = "list")
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public List<KFFunction> list() {
		return fnService.list();
	}

	/**
	 *
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "create")
	@RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
	public KFFunction create(@RequestBody CreateFunctionRequest req) throws Exception {
		return fnService.create(req);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "get")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public KFFunction get(@ApiParam @PathVariable("id") String id) {
		long idL = Long.parseLong(id);
		return fnService.list().stream()
				.filter(f -> f.getId().equals(idL))
				.findAny()
				.get();
	}

	/**
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "getVersions")
	@RequestMapping(value = "/{id}/versions", method = RequestMethod.GET)
	public List<Version> getVersions(@ApiParam @PathVariable("id") String id) throws Exception {
		return fnService.getCommits(id);
	}

	/**
	 * Get a single version
	 *
	 * This is mostly here so swagger code gen creates a type for Version
	 *
	 * @param id
	 * @param versionId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "getVersion")
	@RequestMapping(value = "/{id}/versions/{versionId}", method = RequestMethod.GET)
	public Version getVersion(@ApiParam @PathVariable("id") String id,
	                          @ApiParam @PathVariable("versionId") String versionId) throws Exception {
		return getVersions(id).stream()
				.filter(v -> v.getId().equals(versionId))
				.findFirst()
				.get();
	}

	/**
	 *
	 * @param id
	 * @param versionId
	 */
	@ApiOperation(value = "setVersion")
	@RequestMapping(value = "/{id}/versions/{versionId}", method = RequestMethod.PUT)
	public Map<String, String> setVersion(@ApiParam @PathVariable("id") String id,
	                                      @ApiParam @PathVariable("versionId") String versionId) {
		fnService.setVersion(id, versionId);
		Map<String, String> result = new HashMap<>();
		result.put("status", "OK");
		return result;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "getAddress")
	@RequestMapping(value = "/{id}/service", method = RequestMethod.GET)
	public Service getService(@ApiParam @PathVariable("id") String id) {
		KFFunction fn = get(id);
		if (fn.getNamespace() == null || fn.getService() == null) {
			throw new RuntimeException("No k8s service for function");
		}
		Service service = client.inNamespace(fn.getNamespace())
				.services()
				.withName(fn.getService())
				.get();
		return service;
	}

	/**
	 * Delete the k8s artifacts for a function, this will NOT delete the git repo
	 * @param id
	 */
	@ApiOperation(value = "delete", produces = "text/plain")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@ApiParam @PathVariable("id") String id) throws Exception {
		fnService.delete(id);
	}

	@ApiOperation(value = "proxy", produces = "text/plain")
	@RequestMapping(value = "/{id}/proxy", method = RequestMethod.POST)
	public ProxyResponse proxy(
			@ApiParam @PathVariable("id") String id,
			@RequestBody String payload,
			Principal principal) throws Exception {
		KFFunction function = get(id);
		HttpPost post = new HttpPost("http://" + function.getIngressUrl() + "/");
		post.setEntity(new StringEntity(payload));
		post.setHeader("Content-Type", "text/plain");
		if (principal instanceof KeycloakAuthenticationToken) {
			KeycloakAuthenticationToken kat = (KeycloakAuthenticationToken) principal;
			KeycloakSecurityContext ksc = kat.getAccount().getKeycloakSecurityContext();
			String tokenString = ksc.getTokenString();
			post.setHeader("Authorization", "Bearer " + tokenString);
		}

		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			HttpResponse response = client.execute(post);
			ProxyResponse proxyResponse = new ProxyResponse();
			proxyResponse.setCode(response.getStatusLine().getStatusCode());
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			proxyResponse.setBody(result.toString());
			return proxyResponse;
		}
	}

	public static class ProxyResponse {
		private int code;
		private String body;

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}
	}
}
