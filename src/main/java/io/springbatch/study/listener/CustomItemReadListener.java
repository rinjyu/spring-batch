package io.springbatch.study.listener;

import org.springframework.batch.core.ItemReadListener;

public class CustomItemReadListener implements ItemReadListener {

    @Override
    public void beforeRead() {
        System.out.println(">> beforeRead");
    }

    @Override
    public void afterRead(Object item) {
        System.out.println(">> afterRead : "+ item);
    }

    @Override
    public void onReadError(Exception e) {
        System.out.println(">> onReadError : " + e.getMessage());
    }
}