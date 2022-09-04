package io.springbatch.study;

import io.springbatch.study.exception.SkippableException;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class SkipItemWriter implements ItemWriter<String> {

    private int cnt = 0;

    @Override
    public void write(List<? extends String> items) throws Exception {
        for (String item : items) {
            if ("-12".equals(item)) {
                System.out.println("ItemWriter : " + item);
//                cnt++;
                throw new SkippableException("Write failed cnt:" + cnt);
            } else {
                System.out.println("ItemWriter : " + item);
            }
        }
    }
}
