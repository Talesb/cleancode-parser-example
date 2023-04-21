package com.talesb.main.argumentmarshaler;

import com.talesb.main.exception.ArgsException;

public class IntegerArgumentMarshaler extends ArgumentMarshaler {

	private int intValue=0;

	public void set(String s) throws ArgsException {
		try {
			intValue = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			throw new ArgsException();
		}
	}

	public Object get() {
		return intValue;
	}

}
