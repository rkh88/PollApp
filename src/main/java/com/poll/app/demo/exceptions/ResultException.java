package com.poll.app.demo.exceptions;

public class ResultException extends RuntimeException {


    public ResultException() {
        super("Something is wrong with json");
    }
}
