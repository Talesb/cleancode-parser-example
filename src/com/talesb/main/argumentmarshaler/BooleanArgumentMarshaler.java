package com.talesb.main.argumentmarshaler;

public class BooleanArgumentMarshaler extends ArgumentMarshaler {
	private boolean booleanValue = false;

	public void set(String s) {
		booleanValue = true;
	}

	public Object get() {
		return booleanValue;
	}
}
