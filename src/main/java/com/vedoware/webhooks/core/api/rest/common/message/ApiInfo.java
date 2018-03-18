package com.vedoware.webhooks.core.api.rest.common.message;

/**
 * Information related to the API being used by the client
 * 
 * @author yvdorego
 *
 */
public class ApiInfo {

    private final String apiType;
    private final String apiVersion;

    public ApiInfo(String apiType, String apiVersion) {
        super();
        this.apiType = apiType;
        this.apiVersion = apiVersion;
    }

    public String getApiType() {
        return apiType;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((apiType == null) ? 0 : apiType.hashCode());
        result = prime * result + ((apiVersion == null) ? 0 : apiVersion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ApiInfo other = (ApiInfo) obj;
        if (apiType == null) {
            if (other.apiType != null)
                return false;
        } else if (!apiType.equals(other.apiType))
            return false;
        if (apiVersion == null) {
            if (other.apiVersion != null)
                return false;
        } else if (!apiVersion.equals(other.apiVersion))
            return false;
        return true;
    }

}
