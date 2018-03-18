package com.vedoware.webhooks.core.api.rest.operations;

import java.util.Date;

import com.vedoware.webhooks.core.api.CoreApiOperationStatus;

public class Response {

    private Date date;
    private String operationName;

    private CoreApiOperationStatus status;
    private String message;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public CoreApiOperationStatus getStatus() {
        return status;
    }

    public void setStatus(CoreApiOperationStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((operationName == null) ? 0 : operationName.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        Response other = (Response) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (operationName == null) {
            if (other.operationName != null)
                return false;
        } else if (!operationName.equals(other.operationName))
            return false;
        if (status != other.status)
            return false;
        return true;
    }

}
