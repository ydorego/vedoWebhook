package com.vedoware.webhooks.core.services.actions.impl;

import com.vedoware.webhooks.core.api.exceptions.VedoHookApiException;
import com.vedoware.webhooks.core.services.actions.IAction;
import com.vedoware.webhooks.core.services.actions.KeyValue;
import com.vedoware.webhooks.core.services.actions.KeyValuePairs;

public class IcmpPing implements IAction {

	@Override
	public String getNamespace() {
		return "default";
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String getVersion() {
		return "0.0.1";
	}	
	
	@Override
	public KeyValuePairs executeAction(KeyValuePairs input) throws VedoHookApiException {

		KeyValuePairs keypairs = new KeyValuePairs();
		
		KeyValue pair = new KeyValue("vm-name", "mock-vm");
		keypairs.addPair(pair);

		pair = new KeyValue("vm-uuid", "123456");
		keypairs.addPair(pair);

		pair = new KeyValue("vm-reachability-state", "reachable");
		keypairs.addPair(pair);

		return keypairs;
	}

}
