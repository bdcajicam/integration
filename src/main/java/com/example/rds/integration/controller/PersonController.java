package com.example.rds.integration.controller;

import com.example.rds.integration.model.Person;
import com.example.rds.integration.service.PersonService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @QueryMapping
    Iterable<Person> people() { return personService.getPeople(); }

    @MutationMapping
    Person addPerson(@Argument PersonInput person) {
        return personService.createPerson(person.firstName, person.lastName, person.homeTel, person.email);
    }

    record PersonInput(String firstName, String lastName, String homeTel, String email) {}

}
