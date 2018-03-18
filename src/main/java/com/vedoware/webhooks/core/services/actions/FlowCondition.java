package com.vedoware.webhooks.core.services.actions;

public class FlowCondition {

	private KeyValuePairs keyValues;
	private Operator operator;
	
	
	public KeyValuePairs getKeyValues() {
		return keyValues;
	}
	public void setKeyValues(KeyValuePairs keyValues) {
		this.keyValues = keyValues;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	
}
