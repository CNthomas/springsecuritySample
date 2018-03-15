package com.test.thomas.config.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by thomas on 2018/3/2.
 */
public class MyUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String validCode;
    public MyUsernamePasswordAuthenticationToken(String principal, String credentials, String validCode) {
        super(principal, credentials);
        this.validCode = validCode;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
}
