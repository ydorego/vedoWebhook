package com.vedoware.webhooks.core.services.actions.impl;

import com.vedoware.webhooks.core.services.actions.FlowCondition;
import com.vedoware.webhooks.core.services.actions.IAction;

public class ChainedAction {

	/**
	 * Incoming condition to execute the action
	 */
	private FlowCondition incoming;
	private IAction action;
	
	/**
	 * Outgoing condition to trigger the next action inline
	 */
	private FlowCondition outgoing;
	
	
	public IAction getAction() {
		return action;
	}
	public void setAction(IAction action) {
		this.action = action;
	}
	public FlowCondition getIncoming() {
		return incoming;
	}
	public void setIncoming(FlowCondition incoming) {
		this.incoming = incoming;
	}
	public FlowCondition getOutgoing() {
		return outgoing;
	}
	public void setOutgoing(FlowCondition outgoing) {
		this.outgoing = outgoing;
	}
	
	
	
}
