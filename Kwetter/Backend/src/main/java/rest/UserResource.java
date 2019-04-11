package rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.User;
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
    public Response getUserByUsername(@PathParam(value = "username") String username) {
        //TODO return proper representation object
        User u = userService.getUserbyUsername(username);
        return Response
                .ok(u, MediaType.APPLICATION_JSON_TYPE)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
}
