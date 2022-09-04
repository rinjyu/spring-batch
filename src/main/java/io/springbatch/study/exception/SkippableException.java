package io.springbatch.study.exception;

public class SkippableException extends Exception {

    public SkippableException() {
        super();
    }

    public SkippableException(String msg) {
        super(msg);
    }
}