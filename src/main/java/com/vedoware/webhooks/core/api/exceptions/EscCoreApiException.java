package com.vedoware.webhooks.core.api.exceptions;

@SuppressWarnings("serial")
public class EscCoreApiException extends Exception {

    private int statusCode;

    public EscCoreApiException() {
        super();
        this.statusCode = -1;
    }

    public EscCoreApiException(String message) {
        super(message);
        this.statusCode = -1;
    }

    public EscCoreApiException(String message, Throwable ex) {
        super(message, ex);
    }

    public EscCoreApiException(int status, String message) {
        super(message);
        this.statusCode = status;
    }

    public EscCoreApiException(int status, String message, Throwable ex) {
        super(message, ex);
        this.statusCode = status;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
