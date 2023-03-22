package com.example.rds.integration.model;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Namespace;
import org.eclipse.rdf4j.model.impl.SimpleNamespace;
import org.eclipse.rdf4j.model.util.Values;

//Basado en la clase EX de rdf4j-spring-demo. Descibe los prefijos
public class D {
    private static final Namespace base = new SimpleNamespace("d", "http://learningsparql.com/ns/data#");
    public static final IRI Person = Values.iri(base, "Person");

    public static IRI of(String localName) { return Values.iri(base, localName); }
}
