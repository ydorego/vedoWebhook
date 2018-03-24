package com.vedoware.webhooks.core.api.exceptions;

@SuppressWarnings("serial")
public class VedoHookApiException extends Exception {

    private int statusCode;

    public VedoHookApiException() {
        super();
        this.statusCode = -1;
    }

    public VedoHookApiException(String message) {
        super(message);
        this.statusCode = -1;
    }

    public VedoHookApiException(String message, Throwable ex) {
        super(message, ex);
    }

    public VedoHookApiException(int status, String message) {
        super(message);
        this.statusCode = status;
    }

    public VedoHookApiException(int status, String message, Throwable ex) {
        super(message, ex);
        this.statusCode = status;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
