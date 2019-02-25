package com.capita.calculator.exception;

public class InvalidTestCaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTestCaseException() {
		super();
	}

	public InvalidTestCaseException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidTestCaseException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidTestCaseException(String arg0) {
		super(arg0);
	}

	public InvalidTestCaseException(Throwable arg0) {
		super(arg0);
	}
}
