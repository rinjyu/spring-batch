package io.springbatch.study.retryListener.exception;

public class CustomRetryException extends Exception {

    public CustomRetryException() {
        super();
    }

    public CustomRetryException(String message) {
        super(message);
    }
}
