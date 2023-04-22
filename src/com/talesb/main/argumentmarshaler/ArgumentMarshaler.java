package com.talesb.main.argumentmarshaler;

import java.util.Iterator;

import com.talesb.main.exception.ArgsException;

public interface ArgumentMarshaler {
	
	 public abstract void set(Iterator<String> currentArgument)
			 throws ArgsException;
	
	public abstract void set(String s) throws ArgsException;
	
	public abstract Object get();


}
