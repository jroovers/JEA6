package rest;

import domain.model.Kweet;
import domain.model.UriLink;
import io.jsonwebtoken.Jwts;

import service.KweetService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import domain.model.User;
import java.net.URI;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Link;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import javax.ws.rs.core.UriBuilder;
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
        List<Kweet> kweets = kweetService.getKweetOverview();
        List<Link> linkArray = new ArrayList<>();
        kweets.forEach((k) -> initLinks(k, context, k.getId().toString()));
        Link self = Link.fromUriBuilder(context.getAbsolutePathBuilder())
                .rel("self").build();
        return Response
                .ok(kweetService.getKweetOverview(), MediaType.APPLICATION_JSON)
                .links(self)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getSingleKweet(@PathParam("id") String pathid) {
        Long id;
        try {
            id = Long.parseLong(pathid);
        } catch (NumberFormatException ex) {
            return Response.status(400).build();
        }
        Kweet k = kweetService.getKweetById(id);
        if (k == null) {
            return Response.status(404).build();
        } else {
            initLinks(k, context, null);
            return Response.ok(k).build();
        }
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

    private void initLinks(Kweet kweet, UriInfo uriInfo, String path) {
        //create self link
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        if(path != null){
            uriBuilder = uriBuilder.path(path);
        }
        String url = uriBuilder.build().toASCIIString();
        String rel = "self";
        //Link.Builder linkBuilder = Link.fromUriBuilder(uriBuilder);
        //Link selfLink = linkBuilder.rel("self").build();
        //also we can add other meta-data by using: linkBuilder.param(..),
        // linkBuilder.type(..), linkBuilder.title(..)
        kweet.setLinks(Arrays.asList(new UriLink(rel, url)));
    }

}
