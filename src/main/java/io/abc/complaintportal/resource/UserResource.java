package io.abc.complaintportal.resource;

import io.abc.complaintportal.api.User;
import io.abc.complaintportal.db.UserDAL;
import org.jdbi.v3.core.Jdbi;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * <code>{@link UserResource}</code> class to handle User operations
 *
 * @author aya
 * @since v1.0
 */
@Path("/user")
public class UserResource {

    private final static String SUCCESSFUL_LOGIN_RESPONSE = "User logged in successfully";
    private final static String SUCCESSFUL_SIGNUP_RESPONSE = "User signed up successfully";
    private final Jdbi jdbi;

    public UserResource(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    /**
     * Method to authorize user
     *
     * @return <code>{@link Response}</code>
     */
    @POST
    @Path("/login")
    @PermitAll
    public Response checkLogin() {
        return Response.ok(SUCCESSFUL_LOGIN_RESPONSE).build();
    }

    /**
     * Method to register new user
     *
     * @param user , <code>{@link User}</code>
     * @return <code>{@link Response}</code>
     */
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signup(@NotNull @Valid User user) {
        UserDAL.addUser(user, jdbi);
        return Response.ok(SUCCESSFUL_SIGNUP_RESPONSE).build();
    }
}
