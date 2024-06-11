package com.learn.junit.exception;

public class NotExistsException extends Exception {

	private static final long serialVersionUID = 2224821747455419408L;

	public NotExistsException()
	{
		super();
	}

	public NotExistsException(String message)
	{
		super(message); 
	}
	
	
}
