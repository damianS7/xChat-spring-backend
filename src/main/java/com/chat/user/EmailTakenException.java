package com.chat.user;

public class EmailTakenException extends Exception {
    public EmailTakenException(String s) {
        super(s);
    }
}
