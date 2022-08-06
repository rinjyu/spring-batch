package io.springbatch.study;

import lombok.Data;

@Data
public class Customer {

    private final long id;
    private final String name;
    private final int age;

}