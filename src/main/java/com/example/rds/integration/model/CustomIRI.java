package com.example.rds.integration.model;

import org.eclipse.rdf4j.model.IRI;

public class CustomIRI implements IRI {

    private final String iri;

    private int split;

    public CustomIRI(String iri) {
        this.iri = iri;
        this.split = 0;
    }

    public CustomIRI(String namespace, String localName) {
        this.iri = namespace + localName;
        this.split = namespace.length();
    }

    @Override
    public String stringValue() {
        return iri;
    }

    @Override
    public String getNamespace() {
        return iri.substring(0, split());
    }

    @Override
    public String getLocalName() {
        return iri.substring(split());
    }

    private int split() {
        if (split > 0) {

            return split;

        } else if ((split = iri.indexOf('#') + 1) > 0) {

            return split;

        } else if ((split = iri.lastIndexOf('/') + 1) > 0) {

            return split;

        } else if ((split = iri.lastIndexOf(':') + 1) > 0) {

            return split;

        } else {

            return 0; // unexpected: colon presence already tested in factory methods

        }
    }

}
