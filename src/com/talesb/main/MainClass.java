package com.talesb.main;

import com.talesb.main.exception.ArgsException;

public class MainClass {
	public static void main(String[] args) {
		try {
			
			Args arg = new Args("l,p#,d*", args);
			boolean logging = arg.getBoolean('l');
			int port = arg.getInt('p');
			String directory = arg.getString('d');
			
			 System.out.println(logging);
			 System.out.println(port);
			 System.out.println(directory);
			
		
		} catch (ArgsException e) {
			System.out.printf("Argument error: %s\n", e.getMessage());
		}
		
		
	}

}
