package rest;

import io.jsonwebtoken.Jwts;

import service.KweetService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import domain.model.Kweet;
import domain.model.User;
import java.security.Key;
import javax.ws.rs.FormParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import rest.jwt.filter.JWTTokenNeeded;
import rest.jwt.util.KeyGenerator;
import service.UserService;

/**
 * REST Web Service
 *
 * @author Jeroen Roovers
 */
@Path(value = "/kweets")
@Stateless
public class KweetResource {

    @Inject
    private KweetService kweetService;

    @Inject
    private KeyGenerator keyGenerator;

    @Inject
    private UserService userService;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of KweetResource
     */
    public KweetResource() {
    }

    @GET
    public Response getKweetOverview() {
        User u = null;
        return Response
                .ok(kweetService.getKweetOverview(), MediaType.APPLICATION_JSON)
                .build();
    }

    @POST
    @JWTTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getKweetOverviewByUser(@Context ContainerRequestContext requestContext, User user) {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer".length()).trim();
        Key key = keyGenerator.generateKey();
        String username = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        User savedUser = this.userService.getUserbyUsername(username);
        return Response
                .ok(kweetService.getKweetOverviewForUser(savedUser), MediaType.APPLICATION_JSON)
                .build();
    }

    @POST
    @JWTTokenNeeded
    @Path(value = "/create")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response create(@Context ContainerRequestContext requestContext, @FormParam("body") String body) {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer".length()).trim();
        Key key = keyGenerator.generateKey();
        String username = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        User author = this.userService.getUserbyUsername(username);
        this.kweetService.createKweet(author, body);
        return Response.status(Response.Status.CREATED).build();
    }

}
