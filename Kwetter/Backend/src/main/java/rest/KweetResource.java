package rest;

import controller.KweetService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import model.Kweet;
import model.Role;
import model.User;

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

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of KweetResource
     */
    public KweetResource() {
    }

    @POST
    @Path(value = "/timeline")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response find(User user) {
        return Response.ok(kweetService.getKweetOverviewForUser(user), MediaType.APPLICATION_JSON_TYPE).build();
    }

    @PUT
    @Path(value = "/create")
    public Response create() {
        User u = new User("foouser", new Role(true, true, true, true));
        kweetService.createKweet(u, new Kweet(u, "bar kweet content donald trump 2022"));
        return Response.status(Response.Status.CREATED).build();
    }

}
