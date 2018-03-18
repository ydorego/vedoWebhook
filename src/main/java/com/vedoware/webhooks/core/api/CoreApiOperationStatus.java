package com.vedoware.webhooks.core.api;

/**
 * 
 * Core API operation status.
 * 
 * @author yvdorego
 *
 */
public enum CoreApiOperationStatus {

    UNDEFINED(0), ACCEPTED(10), IN_PROGRESS(20), SUCCESS(30), FAIL(40), REJECTED(50);

    private int status;

    private CoreApiOperationStatus(int status) {
        this.status = status;
    }

    /**
     * @return the status
     */
    public int getOperationStatus() {
        return status;
    }

}
