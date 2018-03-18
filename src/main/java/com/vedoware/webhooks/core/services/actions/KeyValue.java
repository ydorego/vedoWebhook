package com.vedoware.webhooks.core.services.actions;

import java.util.ArrayList;
import java.util.List;

/**
 * KeyValue(s)
 * 
 * @author ydorego
 *
 */
public class KeyValue {

	private String key;
	private String value;
	private List<String> values;

	public KeyValue() {
		this.values = new ArrayList<>();
	}
	
	public KeyValue(String key, String value) {
		super();
		this.key = key;
		this.value = value;
		this.values = new ArrayList<>();
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
	
	
}
