package io.springbatch.study.listener;

import org.springframework.batch.core.ItemProcessListener;

public class CustomItemProcessListener implements ItemProcessListener<Integer, String> {

    @Override
    public void beforeProcess(Integer item) {
        System.out.println(">> beforeProcess");
    }

    @Override
    public void afterProcess(Integer item, String result) {
        System.out.println(">> afterProcess : "+ item);
        System.out.println(">> afterProcess : "+ result);
    }

    @Override
    public void onProcessError(Integer item, Exception e) {
        System.out.println(">> onProcessError : " + e.getMessage());
        System.out.println(">> onProcessError : " + item);
    }
}
