package com.github.nk.klusterfuck.admin.model;

import javax.persistence.*;

/**
 * Created by nipunkumar on 27/05/17.
 */
@Entity
@Table(name = "kf_functions")
public class KFFunction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	private UserNamespace owner;

	private String name;

	private String displayName;

	private FunctionType type;

	private String gitUrl;

	private String namespace;

	private String service;

	private String deployment;

	private String commitId;

	private String ingressUrl;

	public Long getId() {
		return id;
	}

	public UserNamespace getOwner() {
		return owner;
	}

	public void setOwner(UserNamespace owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public FunctionType getType() {
		return type;
	}

	public void setType(FunctionType type) {
		this.type = type;
	}

	public String getGitUrl() {
		return gitUrl;
	}

	public void setGitUrl(String gitUrl) {
		this.gitUrl = gitUrl;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getDeployment() {
		return deployment;
	}

	public void setDeployment(String deployment) {
		this.deployment = deployment;
	}

	public String getCommitId() {
		return commitId;
	}

	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}

	public String getIngressUrl() {
		return ingressUrl;
	}

	public void setIngressUrl(String ingressUrl) {
		this.ingressUrl = ingressUrl;
	}
}
