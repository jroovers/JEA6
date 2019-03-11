package rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.Role;

/**
 * REST Web Service
 *
 * @author Jeroen Roovers
 */
@Path("roles")
public class RoleResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RoleResource
     */
    public RoleResource() {
    }

    /**
     * Retrieves representation of an instance of rest.RoleResource
     *
     * @return an instance of model.Role
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Role getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RoleResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(Role content) {
    }
}
