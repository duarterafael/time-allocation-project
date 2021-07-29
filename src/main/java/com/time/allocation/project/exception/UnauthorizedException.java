package com.time.allocation.project.exception;

public class UnauthorizedException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_BASE = "Usuário não autorizado";

    public UnauthorizedException() {
        super(MESSAGE_BASE);
    }
}
