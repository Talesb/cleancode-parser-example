package com.talesb.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.talesb.main.argumentmarshaler.ArgumentMarshaler;
import com.talesb.main.argumentmarshaler.BooleanArgumentMarshaler;
import com.talesb.main.argumentmarshaler.DoubleArgumentMarshaler;
import com.talesb.main.argumentmarshaler.IntegerArgumentMarshaler;
import com.talesb.main.argumentmarshaler.StringArgumentMarshaler;
import com.talesb.main.exception.ArgsException;

public class Args {

	private String schema;
	private boolean valid = true;
	private Map<Character, ArgumentMarshaler> marshalers = new HashMap<Character, ArgumentMarshaler>();
	private Set<Character> argsFound = new HashSet<Character>();
	private Iterator<String> currentArgument;
	private List<String> argsList;

	public Args(String schema, String[] args) throws ArgsException {
		this.schema = schema;
		argsList = Arrays.asList(args);
		parse();
	}

	private void parse() throws ArgsException {
		parseSchema();
		parseArguments();
	}

	private boolean parseSchema() throws ArgsException {
		for (String element : schema.split(",")) {
			if (element.length() > 0) {
				String trimmedElement = element.trim();
				parseSchemaElement(trimmedElement);
			}
		}
		return true;
	}

	private void parseSchemaElement(String element) throws ArgsException {
		char elementId = element.charAt(0);
		String elementTail = element.substring(1);
		validateSchemaElementId(elementId);
		if (elementTail.length() == 0)
			marshalers.put(elementId, new BooleanArgumentMarshaler());
		else if (elementTail.equals("*"))
			marshalers.put(elementId, new StringArgumentMarshaler());
		else if (elementTail.equals("#")) {
			marshalers.put(elementId, new IntegerArgumentMarshaler());
		} else if (elementTail.equals("##")) {
			marshalers.put(elementId, new DoubleArgumentMarshaler());
		} else {
			throw new ArgsException(String.format("Argument: %c has invalid format: %s.", elementId, elementTail));
		}
	}

	private void validateSchemaElementId(char elementId) throws ArgsException {
		if (!Character.isLetter(elementId)) {
			throw new ArgsException("Bad character:" + elementId + "in Args format: " + schema);
		}
	}

	private boolean parseArguments() throws ArgsException {
		for (currentArgument = argsList.iterator(); currentArgument.hasNext();) {
			String arg = currentArgument.next();
			parseArgument(arg);
		}
		return true;
	}

	private void parseArgument(String arg) throws ArgsException {
		if (arg.startsWith("-"))
			parseElements(arg);
	}

	private void parseElements(String arg) throws ArgsException {
		for (int i = 1; i < arg.length(); i++)
			parseElement(arg.charAt(i));
	}

	private void parseElement(char argChar) throws ArgsException {
		if (setArgument(argChar))
			argsFound.add(argChar);
		else {
			throw new ArgsException(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, argChar, null);
		}
	}

	private boolean setArgument(char argChar) throws ArgsException {
		ArgumentMarshaler m = marshalers.get(argChar);
		if (m == null) {
			return false;
		}
		try {
			m.set(currentArgument);
			return true;
		} catch (ArgsException e) {
			valid = false;
			e.setErrorArgumentId(argChar);
			throw e;
		}

	}

	public int cardinality() {
		return argsFound.size();
	}

	public String usage() {
		if (schema.length() > 0)
			return "-[" + schema + "]";
		else
			return "";
	}

	public String getString(char arg) {
		ArgumentMarshaler am = marshalers.get(arg);
		try {
			return am == null ? "" : (String) am.get();
		} catch (ClassCastException e) {
			return "";
		}
	}

	public int getInt(char arg) {
		ArgumentMarshaler am = marshalers.get(arg);
		try {
			return am == null ? 0 : (Integer) am.get();
		} catch (Exception e) {
			return 0;
		}
	}

	public double getDouble(char arg) {
		ArgumentMarshaler am = marshalers.get(arg);
		try {
			return am == null ? 0 : (Double) am.get();
		} catch (Exception e) {
			return 0.0;
		}
	}

	public boolean getBoolean(char arg) {
		ArgumentMarshaler am = marshalers.get(arg);
		boolean b = false;
		try {
			b = am != null && (Boolean) am.get();
		} catch (ClassCastException e) {
			b = false;
		}
		return b;
	}

	public boolean has(char arg) {
		return argsFound.contains(arg);
	}

	public boolean isValid() {
		return valid;
	}

}
