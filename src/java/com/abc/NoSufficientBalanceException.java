package com.abc;

public class NoSufficientBalanceException extends RuntimeException {

	public NoSufficientBalanceException(String msg) {
		super(msg);		
	}	
}
