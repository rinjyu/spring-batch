package io.springbatch.study.skipListener.listener;

import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

@Component
public class CustomSkipListener implements SkipListener {

    @Override
    public void onSkipInRead(Throwable t) {
        System.out.println(">> onSkipInRead : " + t.getMessage());
    }

    @Override
    public void onSkipInWrite(Object item, Throwable t) {
        System.out.println(">> onSkipInWrite : " + item);
    }

    @Override
    public void onSkipInProcess(Object item, Throwable t) {
        System.out.println(">> onSkipInProcess : " + item);
        System.out.println(">> onSkipInProcess : " + t.getMessage());
    }
}
