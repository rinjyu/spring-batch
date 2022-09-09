package io.springbatch.study.exception;

public class RetryableException extends RuntimeException {

    public RetryableException() {
        super();
    }

    public RetryableException(String msg) {
        super(msg);
    }
}
