package com.example.exam.exception;

public class BadRequestAlertException extends RuntimeException {

	private static final long serialVersionUID = -9013158367116764178L;

	public BadRequestAlertException(String message) {
        super(message);
    }
}
