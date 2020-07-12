package com.zero.ioc.beans.exception;

/**
 * @author zero
 */
public class CircularDependenceException extends RuntimeException {

    public CircularDependenceException(String msg) {
        super(msg);
    }
}
