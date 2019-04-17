package rest;

import domain.model.Kweet;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import domain.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import rest.dto.UserProfileDTO;
import rest.jwt.filter.JWTTokenNeeded;
import service.KweetService;
import service.UserService;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import javax.ws.rs.core.UriInfo;
import rest.jwt.util.KeyGenerator;

/**
 * REST Web Service
 *
 * @author Jeroen Roovers
 */
@Stateless
@Path("/users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class UserResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private transient Logger logger;

    @Inject
    private UserService userService;

    @Inject
    private KweetService kweetService;

    @Inject
    private KeyGenerator keyGenerator;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    /**
     *
     * @param login
     * @param password
     * @return
     */
    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("login") String login,
            @FormParam("password") String password) {
        try {
            logger.info("#### login/password : " + login + "/" + password);
            // Authenticate the user using the credentials provided
            User u = this.userService.login(login, password);
            // Issue a token for the user
            String token = issueToken(u.getUsername());
            // Return the token on the response
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    /**
     * Retrieve an user by username
     *
     * @param username
     * @return an instance of model.Kweet
     */
    @GET
    @Path(value = "/{username}")
    public Response getUserProfileByUsername(@PathParam(value = "username") String username) {
        User u = userService.getUserbyUsername(username);
        if (u != null) {
            List<Kweet> kweets = kweetService.getKweetsByUser(u);
            List<User> followers = userService.getFollowersByUser(u);
            List<User> following = userService.getUsersFollowedByUser(u);
            UserProfileDTO reply = new UserProfileDTO(u, kweets, following, followers);
            return Response
                    .ok(reply)
                    .build();
        } else {
            return Response
                    .status(404)
                    .build();
        }
    }

    /**
     *
     * @param username
     * @return
     */
    @GET
    @Path(value = "/jwt/{username}")
    @JWTTokenNeeded
    public Response updateUserProfile(@PathParam(value = "username") String username) {
        User u = userService.getUserbyUsername(username);
        if (u != null) {
            List<Kweet> kweets = kweetService.getKweetsByUser(u);
            List<User> followers = userService.getFollowersByUser(u);
            List<User> following = userService.getUsersFollowedByUser(u);
            UserProfileDTO reply = new UserProfileDTO(u, kweets, following, followers);
            return Response
                    .ok(reply)
                    .build();
        } else {
            return Response
                    .status(404)
                    .build();
        }
    }

    private String issueToken(String login) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
