package com.talesb.main.argumentmarshaler;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.talesb.main.exception.ArgsException;

public class StringArgumentMarshaler implements ArgumentMarshaler {
	private String stringValue = "";

	public void set(Iterator<String> currentArgument) throws ArgsException {
		try {
			stringValue = currentArgument.next();
		} catch (NoSuchElementException e) {
			throw new ArgsException(ArgsException.ErrorCode.MISSING_STRING,stringValue);
		}
	}

	public void set(String s) {
		stringValue = s;

	}

	public Object get() {
		return stringValue;
	}

}
