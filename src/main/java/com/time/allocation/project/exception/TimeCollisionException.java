package com.time.allocation.project.exception;

import com.time.allocation.project.entity.Time;

public class TimeCollisionException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_BASE = "Time collision to time: %s";

    public TimeCollisionException(Time time) {
        super(String.format(MESSAGE_BASE, time));
    }
}
