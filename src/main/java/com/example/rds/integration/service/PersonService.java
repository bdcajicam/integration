package com.example.rds.integration.service;

import com.example.rds.integration.dao.PersonDao;
import com.example.rds.integration.model.Person;
import org.eclipse.rdf4j.model.IRI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//Basado en la clase ArtService de rdf4j-spring-demo
public class PersonService {

    @Autowired
    private PersonDao personDao;

    //@Transactional
    public Person createPerson(String firstName, String lastName, String homeTel, String email) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setHomeTel(homeTel);
        person.setEmail(email);
        return personDao.save(person);
    }

    //@Transactional() //TODO revisar por qué falla cuando está descomentado. (Está presente en el ejemplo de ArtService)
    public List<Person> getPeople() {
        return personDao.list();
    }

    //@Transactional
    public IRI addPerson(Person person) {
        return personDao.saveAndReturnId(person);
    }

}
