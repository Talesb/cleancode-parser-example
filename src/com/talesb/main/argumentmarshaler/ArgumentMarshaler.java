package com.talesb.main.argumentmarshaler;

import com.talesb.main.exception.ArgsException;

public abstract class ArgumentMarshaler {
	
	public abstract void set(String s) throws ArgsException;
	
	public abstract Object get();


}
