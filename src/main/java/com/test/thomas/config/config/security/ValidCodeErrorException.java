package com.test.thomas.config.config.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by thomas on 2018/3/2.
 */
public class ValidCodeErrorException extends AuthenticationException {
    public ValidCodeErrorException(String msg) {
        super(msg);
    }

    public ValidCodeErrorException(String msg, Throwable t) {
        super(msg, t);
    }
}
