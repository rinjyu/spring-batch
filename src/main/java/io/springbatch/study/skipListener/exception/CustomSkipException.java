package io.springbatch.study.skipListener.exception;

public class CustomSkipException extends Exception {

    public CustomSkipException() {
        super();
    }

    public CustomSkipException(String message) {
        super(message);
    }
}
