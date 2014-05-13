package net.github.rtc.micro.user.resource;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import net.github.rtc.micro.user.dao.RoleDao;
import net.github.rtc.micro.user.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static com.google.common.base.Preconditions.checkArgument;


@Path(value = "/roles")
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource {
    private static Logger LOG = LoggerFactory.getLogger(RoleResource.class.getName());

    private final RoleDao dao;

    public RoleResource(RoleDao dao) {
        this.dao = dao;
    }

    @GET
    @UnitOfWork
    @Path("{id}")
    public Role get(@PathParam("id") IntParam id) {
        Role role = dao.get(id.get());
        if (role == null) {
            RuntimeException ex = new WebApplicationException(Response.Status.NOT_FOUND);
            LOG.error("Exception: ", ex);
            throw ex;
        }
        return role;
    }

    @GET
    @UnitOfWork
    public Collection<Role> findAll() {
        return dao.findAll();
    }


    @POST
    @UnitOfWork
    public Role create(Role role) {
        if (dao.exist(role)) {
            return null;
        }
        return dao.save(role);
    }


    @PUT
    @UnitOfWork
    @Path("{id}")
    public Role update(@PathParam("id") IntParam id, Role role) {
        Role oldRole = dao.get(id.get());
        if (oldRole != null) {
            role.setId(oldRole.getId());
        }
        checkArgument(id.get().equals(role.getId()));
        return dao.update(role);
    }


    @DELETE
    @UnitOfWork
    @Path("{id}")
    public void delete(@PathParam("id") IntParam id) {
        dao.delete(id.get());
    }


}
