package com.example.rds.integration.model;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.sparqlbuilder.core.SparqlBuilder;
import org.eclipse.rdf4j.sparqlbuilder.core.Variable;
import org.eclipse.rdf4j.spring.demo.model.Artist;

import java.util.Objects;

@Getter
@Setter
//Basado en la clase Artist de rdf4j-spring-demo
public class Person {

    public static final Variable PERSON_ID = SparqlBuilder.var("person_id");
    public static final Variable PERSON_FIRST_NAME = SparqlBuilder.var("person_firstName");
    public static final Variable PERSON_LAST_NAME = SparqlBuilder.var("person_lastName");
    private IRI id;
    private String firstName;
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
