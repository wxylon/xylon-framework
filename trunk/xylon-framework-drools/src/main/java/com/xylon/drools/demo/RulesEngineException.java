
package com.xylon.drools.demo;

public class RulesEngineException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RulesEngineException(String message) {
        super(message);
    }

    public RulesEngineException(String message, Throwable cause) {
        super(message, cause);
    }

}
