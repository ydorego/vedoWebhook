package com.vedoware.webhooks.core.api.types;

public enum TerminationType {

    UNDEFINED(0), FORCEFUL(10), GRACEFUL(20)

    ;

    private int type;

    private TerminationType(int type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public int getTerminationType() {
        return type;
    }

}
