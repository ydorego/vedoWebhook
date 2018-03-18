package com.vedoware.webhooks.core.api.exceptions;

public class InvalidArgumentsException extends EscCoreApiException {

    private static final long serialVersionUID = -4964303636097953382L;

    public InvalidArgumentsException() {
        super();
    }


    public InvalidArgumentsException(String message) {
        super(message);
    }

    public InvalidArgumentsException(String message, Throwable re) {
        super(message, re);
    }

}
