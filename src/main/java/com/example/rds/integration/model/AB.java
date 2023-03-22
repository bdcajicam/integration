package com.example.rds.integration.model;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Namespace;
import org.eclipse.rdf4j.model.impl.SimpleNamespace;
import org.eclipse.rdf4j.model.util.Values;

//Basado en la clase EX de rdf4j-spring-demo. Descibe los prefijos
public class AB {

    private static final Namespace base =
            new SimpleNamespace("ab", "http://learningsparql.com/ns/addressbook#");
    public static final IRI firstName = Values.iri(base, "firstName");
    public static final IRI lastName = Values.iri(base, "lastName");
    public static final IRI homeTel = Values.iri(base, "homeTel");
    public static final IRI email = Values.iri(base, "email");

    public static IRI of(String localName) { return Values.iri(base, localName); }

}
