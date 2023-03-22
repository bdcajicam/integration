package com.example.rds.integration.config;

import com.example.rds.integration.dao.PersonDao;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryProvider;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.eclipse.rdf4j.spring.RDF4JConfig;
import org.eclipse.rdf4j.spring.support.RDF4JTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RDF4JConfig.class)
public class IntegrationConfig {

    @Bean
    public PersonDao getMyPerson(@Autowired RDF4JTemplate template) {
        return new PersonDao(template);
    }

    /*@Bean
    public Repository createRepository() {
        return new SailRepository(new MemoryStore());
    }*/

    //Recomendado en stackoverflow pero no funciona
    //https://stackoverflow.com/questions/48289405/rdf4j-and-graphdb-repository-connection
    /*@Bean
    public Repository createRepository() {
        String server = "http://localhost:7200/repositories/";
        String repoId = "Training";
        return new HTTPRepository(server, repoId);
    }*/

    @Bean
    public Repository createRepository() {
        String sparqlEndpoint = "http://localhost:7200/repositories/Training";
        return new SPARQLRepository(sparqlEndpoint, sparqlEndpoint + "/statements");
    }

}

