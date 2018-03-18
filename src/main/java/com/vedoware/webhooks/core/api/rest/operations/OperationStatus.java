package com.vedoware.webhooks.core.api.rest.operations;

public enum OperationStatus {

	SUCCESS(10),
	FAIL(20),
	ACCEPTED(30),
	REJECTED(40);

	private int type;

	private OperationStatus(int type) {
		this.type = type;
	}

	/**
	 * @return the payload type
	 */
	public int getOperationStatus() {
		return type;
	}

}
