package com.talesb.main.argumentmarshaler;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.talesb.main.exception.ArgsException;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {

	private int intValue = 0;

	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter = null;
		try {
			parameter = currentArgument.next();
			intValue = Integer.parseInt(parameter);
		} catch (NoSuchElementException e) {
			 throw new ArgsException(ArgsException.ErrorCode.MISSING_INTEGER,parameter);
		} catch (NumberFormatException e) {
			throw e;
		}
	}

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
