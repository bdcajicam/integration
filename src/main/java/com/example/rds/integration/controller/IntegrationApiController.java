package com.example.rds.integration.controller;

import com.example.rds.integration.model.Person;
import com.example.rds.integration.service.PersonService;
import org.eclipse.rdf4j.model.IRI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/person")
public class IntegrationApiController {

    private final PersonService personService;

    @Autowired
    public IntegrationApiController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    ResponseEntity<List<Person>> getPeople() {
        return new ResponseEntity<>(personService.getPeople(), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<IRI> addPerson(@RequestBody Person person) {
        return new ResponseEntity<>(personService.addPerson(person), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return new ResponseEntity<>(personService.createPerson(person.getFirstName(),
                                                                person.getLastName(),
                                                                person.getHomeTel(),
                                                                person.getEmail()), HttpStatus.OK);
    }

}
