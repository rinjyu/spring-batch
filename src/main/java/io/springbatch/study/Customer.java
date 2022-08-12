package io.springbatch.study;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data
@Entity
public class Customer {

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;
    private String firstname;
    private String lastname;
    private String birthdate;

}
