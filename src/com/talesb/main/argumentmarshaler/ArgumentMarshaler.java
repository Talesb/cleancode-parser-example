package com.talesb.main.argumentmarshaler;

public class ArgumentMarshaler {
	private boolean booleanValue = false;
	private String stringValue;;

	public boolean getBoolean() {
		return booleanValue;
	}

	public void setBoolean(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public void setString(String s) {
		stringValue = s;
	}

	public String getString() {
		return stringValue == null ? "" : stringValue;
	}

}
