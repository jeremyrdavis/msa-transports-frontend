package io.arrogantprogrammer.graphql;

import io.arrogantprogrammer.domain.Film;
import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;

import java.util.List;

@GraphQLClientApi(configKey = "star-wars-typesafe")
public interface GraphQLFilmService {

    List<Film> allFilms();

}
