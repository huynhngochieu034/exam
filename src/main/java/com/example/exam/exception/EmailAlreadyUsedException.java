package com.example.exam.exception;

public class EmailAlreadyUsedException extends RuntimeException {
	
	private static final long serialVersionUID = 3521772641445265430L;

	public EmailAlreadyUsedException() {
        super("Email is already in used!");
    }
}
