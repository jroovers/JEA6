package rest;

import domain.model.Kweet;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import domain.model.User;
import java.util.List;
import rest.dto.UserProfileDTO;
import service.KweetService;
import service.UserService;

/**
 * REST Web Service
 *
 * @author Jeroen Roovers
 */
@Path("/users")
@Stateless
public class UserResource {

    @Inject
    private UserService userService;

    @Inject
    private KweetService kweetService;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
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
                    .ok(reply, MediaType.APPLICATION_JSON_TYPE)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response
                    .status(404)
                    .build();
        }
    }
}
