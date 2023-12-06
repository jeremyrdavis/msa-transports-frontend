package io.arrogantprogrammer;

import io.arrogantprogrammer.domain.Film;
import io.arrogantprogrammer.graphql.GraphQLFilmService;
import io.arrogantprogrammer.proto.Empty;
import io.arrogantprogrammer.proto.GRPCFilmService;
import io.arrogantprogrammer.rest.RESTClient;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.core.Document;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static io.smallrye.graphql.client.core.Document.document;
import static io.smallrye.graphql.client.core.Field.field;
import static io.smallrye.graphql.client.core.Operation.operation;

@Path("transports-shootout")
public class TransportsShootoutResource {

    static final Logger LOGGER = LoggerFactory.getLogger(TransportsShootoutResource.class);

    @RestClient
    RESTClient restClient;

    @GrpcClient("grpc-film-client")
    GRPCFilmService grpcFilmServiceClient;

    @Inject
    @GraphQLClient("star-wars-dynamic")
    DynamicGraphQLClient dynamicClient;

    @Inject
    GraphQLFilmService typesafeClient;

    @GET
    @Path("/rest")
    public List<Film> allFilmsREST() {
        List<Film> films = restClient.allFilms();
        films.forEach(film -> {
            LOGGER.debug("film retrieved by rest client: {}", film);
        });
        return films;
    }

    @GET
    @Path("/grpc")
    public List<Film> allFilmsGRPC() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return grpcFilmServiceClient.allFilms(Empty.newBuilder().build())
                .onItem().transform(allFilmsProtos -> {
                    List<Film> films = new ArrayList<>();
                    allFilmsProtos.getFilmsList().forEach(filmProto -> {
                        Film film = new Film();
                        film.setTitle(filmProto.getTitle());
                        film.setEpisodeID(filmProto.getEpisodeId());
                        film.setDirector(filmProto.getDirector());
                        film.setReleaseDate(LocalDate.parse(filmProto.getReleaseDate(), formatter));
                        films.add(film);
                        LOGGER.debug("film retrieved by grpc client: {}", film);
                    });
                    return films;
                }).await().indefinitely();
    }

    @GET
    @Path("/graphql-dynamic")
    public List<Film> allFilms() throws ExecutionException, InterruptedException {

        //return dynamicClient.allFilms();
        Document query = document(
                operation(
                        field("allFilms",
                                field("director"),
                                field("episodeID"),
                                field("releaseDate"),
                                field("title")
                        )
                )
        );
        Response response = dynamicClient.executeSync(query);
        List<Film> films = response.getList(Film.class, "allFilms");
        LOGGER.debug("response: {}", response);
        films.forEach(film -> {
            LOGGER.debug("film retrieved by dynamic graphql client: {}", film);
        });
        return films;
    }

    @GET
    @Path("/graphql-typesafe")
    public List<Film> allFilmsTypesafe(){
        List<Film> films = typesafeClient.allFilms();
        films.forEach(film -> {
            LOGGER.debug("film retrieved by typesafe graphql client: {}", film);
        });
        return films;
    }

}
