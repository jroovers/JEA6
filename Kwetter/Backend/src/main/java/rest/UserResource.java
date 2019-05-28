package rest;

import domain.model.Kweet;
import domain.model.UriLink;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import domain.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import rest.dto.UserProfileDTO;
import rest.jwt.filter.JWTTokenNeeded;
import service.KweetService;
import service.UserService;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import javax.ws.rs.core.UriBuilder;
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
     * @param username
     * @param password
     * @return
     */
    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username,
            @FormParam("password") String password) {
        try {
            logger.info("#### login/password : " + username + "/" + password);
            // Authenticate the user using the credentials provided
            User u = this.userService.login(username, password);
            if (u == null) {
                throw new NullPointerException("Login failed");
            }
            // Issue a token for the user
            String token = issueToken(u.getUsername());
            // Return the token on the response
            return Response.ok(u).header(AUTHORIZATION, "Bearer " + token).build();

        } catch (NullPointerException | InvalidKeyException ex) {
            logger.warning(ex.getMessage());
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
            initLinks(u, uriInfo, "/users/" + u.getUsername());
            List<Kweet> kweets = kweetService.getKweetsByUser(u);
            kweets.forEach(k -> initLinks(k, uriInfo, "/kweets/" + k.getId().toString()));
            List<User> followers = userService.getFollowersByUser(u);
            followers.forEach(f -> initLinks(f, uriInfo, "/users/" + f.getUsername()));
            List<User> following = userService.getUsersFollowedByUser(u);
            following.forEach(f -> initLinks(f, uriInfo, "/users/" + f.getUsername()));
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

    @POST
    @JWTTokenNeeded
    public Response updateUserProfile(@Context ContainerRequestContext requestContext, User user) {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer".length()).trim();
        Key key = keyGenerator.generateKey();
        String securedUsername = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        if (securedUsername.equalsIgnoreCase(user.getUsername())) {
            User updatedUser = this.userService.updateProfileDetails(user);
            return Response.accepted(updatedUser).build();
        } else {
            return Response.status(403).build();
        }
    }

    @POST
    @JWTTokenNeeded
    @Path("/follow")
    @Produces(value = {MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Response followUserByUsername(@Context ContainerRequestContext requestContext, JsonObject body) {
        String username = body.getString("username");
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer".length()).trim();
        Key key = keyGenerator.generateKey();
        String securedUsername = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        User follower = this.userService.getUserbyUsername(securedUsername);
        User toFollow = this.userService.getUserbyUsername(username);
        if (follower != null && toFollow != null) {
            JsonObject reply = Json.createObjectBuilder().add("message", "follow status changed").build();
            if (this.userService.followUser(follower, toFollow)) {
                return Response.ok(reply).build();
            } else {
                return Response.ok(reply).build();
            }
        }
        return Response.status(400, "Invalid username to follow").build();
    }

    @GET
    @JWTTokenNeeded
    @Path("/suggest")
    public Response getMutualFriends(@Context ContainerRequestContext requestContext) {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer".length()).trim();
        Key key = keyGenerator.generateKey();
        String securedUsername = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        return Response.ok(this.userService.getFriendSuggestions(securedUsername)).build();
    }

    private String issueToken(String login) throws InvalidKeyException {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(key)
                .compact();
        logger.info("#### generating token for a key : " + jwtToken + " - " + key);
        return jwtToken;
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private void initLinks(User user, UriInfo uriInfo, String path) {
        //create self link
        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        if (path != null) {
            uriBuilder = uriBuilder.path(path);
        }
        String url = uriBuilder.build().toASCIIString();
        String rel = "self";
        user.setLinks(Arrays.asList(new UriLink(rel, url)));
    }

    private void initLinks(Kweet kweet, UriInfo uriInfo, String path) {
        //create self link
        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        if (path != null) {
            uriBuilder = uriBuilder.path(path);
        }
        String url = uriBuilder.build().toASCIIString();
        String rel = "self";
        kweet.setLinks(Arrays.asList(new UriLink(rel, url)));
    }
}
