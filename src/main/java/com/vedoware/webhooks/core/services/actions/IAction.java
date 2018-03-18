package com.vedoware.webhooks.core.services.actions;

import com.vedoware.webhooks.core.api.exceptions.EscCoreApiException;

/**
 * 
 * @author yvdorego
 *
 */
public interface IAction {

	String getNamespace();
	String getName();
	String getVersion();
	
	
	KeyValuePairs executeAction(KeyValuePairs input) throws EscCoreApiException;
	
}
