package com.vedoware.webhooks.core.api.rest.notifications.payloads;

//import com.vedoware.webhooks.core.model.common.Properties;

/**
 * 
 * The definition of the general "ProblemDetails" data structure from IETF RFC
 * 7807 is reproduced in table 4.3.5.3-1. Compared to the general framework
 * defined in IETF RFC 7807, the "status" and "detail" attributes are mandated
 * to be included by the present specification, to ensure that the response
 * contains additional textual information about an error. IETF RFC 7807
 * foresees extensibility of the "ProblemDetails" type. It is possible that
 * particular APIs in the present document, or particular implementations,
 * define extensions to define additional attributes that provide more
 * information about the error.
 * 
 * @author yvdorego
 *
 */
public class NotificationProblemDetails {

    private String message;
//    private Properties additionalAttributes;
//
//    public Properties getAdditionalAttributes() {
//        return additionalAttributes;
//    }
//
//    public void setAdditionalAttributes(Properties additionalAttributes) {
//        this.additionalAttributes = additionalAttributes;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
