package rest;

import controller.KweetService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import model.Kweet;
import model.PersonGroup;
import model.Person;

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
    public Response find(Person user) {
        return Response.ok(kweetService.getKweetOverviewForUser(user), MediaType.APPLICATION_JSON_TYPE).build();
    }

    @PUT
    @Path(value = "/create")
    public Response create() {
        Person u = new Person();
        u.setUsername("FOOUSER");
        Kweet k = new Kweet();
        k.setAuthor(u);
        k.setBody("bar kweet content donald trump 2022");
        kweetService.createKweet(u, k);
        return Response.status(Response.Status.CREATED).build();
    }

}
