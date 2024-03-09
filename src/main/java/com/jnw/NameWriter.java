package com.jnw;

import com.jnw.dto.Person;

import java.util.List;
import java.util.stream.Collectors;

import static com.jnw.FileResourceUtils.writeItemOnEachLine;
import static com.jnw.FileResourceUtils.writeObjectFieldsAsCsvOnEachLine;

//TODO take this code and transform it into a professional project with the following expectations:
/*
- all initial TODOs are resolved
- project is configured with gradle or maven
- code is clean and maintainable
- TODOs must be added where something is not clear and must be discussed
- feel free to fix the code which doesn't look right
 */


public class NameWriter {

    public static final List<String> NAMES = List.of("Mary", "Alina", "John", "Nicole", "Mike");

    public static final List<Person> PERSONS = List.of(
            Person.builder().name("Mary").age(25).build(),
            Person.builder().name("Alina").age(35).build(),
            Person.builder().name("John").age(47).build(),
            Person.builder().name("Nicole").age(22).build(),
            Person.builder().name("Mike").age(55).build()
    );


    public static void main(String[] args) {

        //TODO adapt the code to receive this list either from stdin or as a static list
        //TODO all files must be stored in a configurable folder --> FileResourceUtils.getFileFromResource()
        writeItemOnEachLine(NAMES, true);

        //TODO - log the count of names with length 4
        writeItemOnEachLine(List.of(
                String.format("Count of names with length 4 --> %s", getCountOfNamesWithLengthOfFour())
        ), true);

        //TODO - update the above code to receive the name and age for each person and save them as CSV in b.txt file
        writeObjectFieldsAsCsvOnEachLine(PERSONS, true);

        //TODO - implement a way to write in the b.txt only persons over a specified age
        writePersonsWithAgeOver(40);
    }

    private static long getCountOfNamesWithLengthOfFour() {
        return NAMES.stream()
                .filter(name -> name.length() == 4)
                .count();
    }

    private static void writePersonsWithAgeOver(int specifiedAge) {
        writeObjectFieldsAsCsvOnEachLine(
                PERSONS.stream()
                        .filter(person -> person.getAge() > specifiedAge)
                        .collect(Collectors.toList()),
                true
        );
    }
}
