package com.vedoware.webhooks.core.api.rest.notifications;

import java.util.Date;

public class Notification implements INotification {

	private String  type;
	private Date time;
	private String name;
	private String internalVnfInstanceId;	
	private String externalVnfInstanceId;	
	private String details;
	
	//Properties additionalParameters;
		
	
	public void setType(String type) {
		this.type = type;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInternalVnfInstanceId(String internalVnfInstanceId) {
		this.internalVnfInstanceId = internalVnfInstanceId;
	}

	public void setExternalVnfInstanceId(String externalVnfInstanceId) {
		this.externalVnfInstanceId = externalVnfInstanceId;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public Date getTime() {
		return time;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getInternalVnfInstanceId() {
		return internalVnfInstanceId;
	}

	@Override
	public String getExternalVnfInstanceId() {
		return externalVnfInstanceId;
	}

	@Override
	public String getDetails() {
		return details;
	}

}
