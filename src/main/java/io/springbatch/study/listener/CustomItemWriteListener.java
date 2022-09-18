package io.springbatch.study.listener;

import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class CustomItemWriteListener implements ItemWriteListener<String> {

    @Override
    public void beforeWrite(List<? extends String> items) {
        System.out.println(">> beforeWrite");
    }

    @Override
    public void afterWrite(List<? extends String> items) {
        System.out.println(">> afterWrite : "+ items);
    }

    @Override
    public void onWriteError(Exception exception, List<? extends String> items) {
        System.out.println(">> onWriteError : " + exception.getMessage());
        System.out.println(">> onWriteError : " + items);
    }
}
