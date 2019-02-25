package com.capita.calculator.exception;

public class InvalidInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
		super();
	}

	public InvalidInputException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidInputException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidInputException(String arg0) {
		super(arg0);
	}

	public InvalidInputException(Throwable arg0) {
		super(arg0);
	}
}
