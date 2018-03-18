package com.vedoware.webhooks.core.api.types;

public enum StopType {

    UNDEFINED(0), FORCEFUL(10), GRACEFUL(20)

    ;

    private int type;

    private StopType(int type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public int getStopType() {
        return type;
    }

}
