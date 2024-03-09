package com.jnw.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Person implements FieldsAsCsvWritable {

    private String name;
    private int age;

    @Override
    public List<String> getAllFieldsAsList() {
        return List.of(name, String.valueOf(age));
    }
}
