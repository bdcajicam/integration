package com.example.rds.integration.dao;

import com.example.rds.integration.model.AB;
import com.example.rds.integration.model.D;
import com.example.rds.integration.model.Person;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.sparqlbuilder.core.query.Queries;
import org.eclipse.rdf4j.spring.dao.SimpleRDF4JCRUDDao;
import org.eclipse.rdf4j.spring.dao.support.bindingsBuilder.MutableBindings;
import org.eclipse.rdf4j.spring.dao.support.sparql.NamedSparqlSupplier;
import org.eclipse.rdf4j.spring.support.RDF4JTemplate;
import org.eclipse.rdf4j.spring.util.QueryResultUtils;
import org.springframework.stereotype.Component;

import static com.example.rds.integration.model.Person.*;
import static org.eclipse.rdf4j.sparqlbuilder.rdf.Rdf.iri;

@Component
//Basado en la clase ArtistDao de rdf4j-spring-demo
public class PersonDao extends SimpleRDF4JCRUDDao<Person, IRI>  {

    public PersonDao(RDF4JTemplate rdf4JTemplate) { super(rdf4JTemplate); }

    @Override
    //Este método es llamado por la superclase para vincular el id al realizar una query de SPARQL
    protected void populateIdBindings(MutableBindings bindingsBuilder, IRI iri) {
        bindingsBuilder.add(PERSON_ID, iri);
    }

    @Override
    //Este método es llamado por la superclase para vincular las variables (que no hagan parte del id) al realizar un update
    protected void populateBindingsForUpdate(MutableBindings bindingsBuilder, Person person) {
        bindingsBuilder
                .add(PERSON_FIRST_NAME, person.getFirstName())
                .add(PERSON_LAST_NAME, person.getLastName())
                .addMaybe(PERSON_HOME_TEL, person.getHomeTel()) //Valor opcional
                .addMaybe(PERSON_EMAIL, person.getEmail()); //Valor opcional
    }

    @Override
    //Convierte el parámetro querySolution en una instancia de la entidad
    protected Person mapSolution(BindingSet querySolution) {
        Person person = new Person();
        person.setId(QueryResultUtils.getIRI(querySolution, PERSON_ID));
        person.setFirstName(QueryResultUtils.getString(querySolution, PERSON_FIRST_NAME));
        person.setLastName(QueryResultUtils.getString(querySolution, PERSON_LAST_NAME));
        //Valores opcionales
        if (querySolution.hasBinding(PERSON_HOME_TEL.getVarName()))
            person.setHomeTel(QueryResultUtils.getString(querySolution, PERSON_HOME_TEL));
        if (querySolution.hasBinding(PERSON_EMAIL.getVarName()))
            person.setEmail(QueryResultUtils.getString(querySolution, PERSON_EMAIL));
        return person;
    }

    @Override
    //Este método retorna la query de SPARQL que trae los "registros" de una entidad
    //Nota: los nombres de la variable deben ser los mismos usados en el método mapSolution
    protected String getReadQuery() {
        return "PREFIX ab: <http://learningsparql.com/ns/addressbook#> " +
                "PREFIX d: <http://learningsparql.com/ns/data#> " +
                "SELECT ?person_id ?person_firstName ?person_lastName ?person_homeTel ?person_email " +
                "WHERE " +
                "{ " +
                "   ?person_id a d:Person ; " +
                "           ab:firstName ?person_firstName ; " +
                "           ab:lastName ?person_lastName . " +
                "    OPTIONAL { ?person_id ab:homeTel ?person_homeTel . } " +
                "    OPTIONAL { ?person_id ab:email ?person_email . } " +
                "}";
    }

    @Override
    //Este método provee la query de SPARQL para insertar una nueva instancia
    protected NamedSparqlSupplier getInsertSparql(Person person) {
        return NamedSparqlSupplier.of("insert", () -> Queries.INSERT(PERSON_ID.isA(iri(D.Person))
                .andHas(iri(AB.firstName), PERSON_FIRST_NAME)
                .andHas(iri(AB.lastName), PERSON_LAST_NAME)
                .andHas(iri(AB.homeTel), PERSON_HOME_TEL)
                .andHas(iri(AB.email), PERSON_EMAIL))
                .getQueryString());
    }

    //TODO revisar getUpdateSparql(ENTITY)

    @Override
    //Genera el id de una entidad antes de ser insertada en caso de que no exista
    protected IRI getInputId(Person person) {
        if (person.getId() == null) {
            return getRdf4JTemplate().getNewUUID();
        }
        return person.getId();
    }

    @Override
    //Ayuda a manejar queries más específicas
    //Nota: revisar el ejemplo en ArtistDao
    protected NamedSparqlSupplierPreparer prepareNamedSparqlSuppliers(NamedSparqlSupplierPreparer preparer) {
        return null; //TODO
    }

}
