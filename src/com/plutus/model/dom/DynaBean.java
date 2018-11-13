package com.plutus.model.dom;

import java.util.HashMap;

public class DynaBean {
	
	private String name;
	private HashMap<String,DynaBeanAttribute> attributes;
	
	public DynaBean(String name, HashMap<String, DynaBeanAttribute> attributes) {
		super();
		this.name = name;
		this.attributes = attributes;
	}
	
	public void setAttribute(String attribute, Object value) {
		try {
			attributes.get(attribute).setValue(value);
		} catch (ClassCastException e) {
			throw e;
		}	
	}
	
	public Object getAttribute(String attribute) {
		return attributes.get(attribute).getValue();
	}

	public String getName() {
		return name;
	}

}
