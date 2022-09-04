package io.springbatch.study;

import io.springbatch.study.exception.SkippableException;
import org.springframework.batch.item.ItemProcessor;

public class SkipItemProcessor implements ItemProcessor<String, String> {

    private int cnt = 0;

    @Override
    public String process(String item) throws Exception {

        if ("6".equals(item) || "7".equals(item)) {
            System.out.println("ItemProcessor : " + item);
//            cnt++;
            throw new SkippableException("Process failed cnt:" + cnt);
        } else {
            System.out.println("ItemProcessor : " + item);
            return String.valueOf(Integer.valueOf(item) * -1);
        }
    }
}
