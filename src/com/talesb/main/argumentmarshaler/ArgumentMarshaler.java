package com.talesb.main.argumentmarshaler;

public class ArgumentMarshaler {
	private boolean booleanValue = false;
	private String stringValue;
	private int integerValue;

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

	public void setInteger(int i) {
		integerValue = i;
	}

	public int getInteger() {
		return integerValue;
	}

}
