package io.springbatch.study;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {

    @Override
    public Customer mapFieldSet(FieldSet fieldSet) {

        if (fieldSet == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setName(fieldSet.readString("name"));
        customer.setYear(fieldSet.readString("age"));
        customer.setAge(fieldSet.readInt("year"));

        return customer;
    }
}