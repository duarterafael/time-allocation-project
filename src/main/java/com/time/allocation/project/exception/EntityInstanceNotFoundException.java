package com.time.allocation.project.exception;

import javax.persistence.EntityNotFoundException;

public class EntityInstanceNotFoundException extends EntityNotFoundException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_BASE = "Instance not found to ID \"%s\" of entity \"%s\"";

    public <T> EntityInstanceNotFoundException(Class<T> entityClass, Object entityId) {
        super(String.format(MESSAGE_BASE, entityClass, entityId));
    }
}
