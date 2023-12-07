package io.arrogantprogrammer.rest;

import io.arrogantprogrammer.domain.Film;
import io.arrogantprogrammer.domain.Hero;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "rest-client")
public interface RESTClient {

    @GET
    public List<Film> allFilms();

    @GET
    @Path("/heroes")
    List<Hero> allHeroes();

}
