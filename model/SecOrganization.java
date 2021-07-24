package com.addon.bpsbackend.security1.model;


public class SecOrganization {
	private long id;
	private String orgName;
	private String logoName;

	public SecOrganization() {

	}

	public SecOrganization(long id, String orgName, String logoName) {
		super();
		this.id = id;
		this.orgName = orgName;
		this.logoName = logoName;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

}
