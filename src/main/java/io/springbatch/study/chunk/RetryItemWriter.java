package io.springbatch.study.chunk;

import io.springbatch.study.Customer;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class RetryItemWriter implements ItemWriter<Customer> {

    @Override
    public void write(List<? extends Customer> items) throws Exception {
        items.forEach(item -> System.out.println(item));
    }
}