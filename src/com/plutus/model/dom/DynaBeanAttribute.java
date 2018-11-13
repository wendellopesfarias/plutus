package com.plutus.model.dom;

public class DynaBeanAttribute {
	
	private String name;
	private Class type;
	private Object value;
	
	public DynaBeanAttribute(String name, Class type) {
		super();
		this.name = name;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class getType() {
		return type;
	}
	public void setType(Class type) {
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		if(type.isAssignableFrom(value.getClass())) {
			this.value = value;
		}else {
			throw new ClassCastException("Type incompatible "+type+" "+value.getClass());
		}
	}
	
	

}
