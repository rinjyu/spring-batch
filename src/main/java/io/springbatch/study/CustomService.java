package io.springbatch.study;

public class CustomService<T> {

    private int cnt = 0;

    public T joinCustomer() {
        return (T) ("item" + cnt++);
    }
}