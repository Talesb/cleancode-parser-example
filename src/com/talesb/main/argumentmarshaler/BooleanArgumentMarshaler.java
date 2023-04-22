package com.talesb.main.argumentmarshaler;

import java.util.Iterator;

import com.talesb.main.exception.ArgsException;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {
	private boolean booleanValue = false;

	public void set(Iterator<String> currentArgument) throws ArgsException {
		booleanValue = true;
		 }
	
	public void set(String s) {
		booleanValue = true;
	}

	public Object get() {
		return booleanValue;
	}
}
