package io.abc.complaintportal.resource;

import io.abc.complaintportal.api.Complaint;
import io.abc.complaintportal.api.User;
import io.abc.complaintportal.db.ComplaintDAL;
import io.dropwizard.auth.Auth;
import org.jdbi.v3.core.Jdbi;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * <code>{@link ComplaintResource}</code> class to handle complaint management operations
 *
 * @author aya
 * @since v1.0
 */
@Path("/complaint")
public class ComplaintResource {

    private final Jdbi jdbi;
    private final static String ADD_COMPLAINT_SUCCESSFUL_RESPONSE = "Complaint created successfully";
    private final static String UPDATE_COMPLAINT_SUCCESSFUL_RESPONSE = "Complaint status updated successfully";

    public ComplaintResource(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    /**
     * Method to add compliant
     *
     * @param user      , <code>{@link User}</code>
     * @param complaint , <code>{@link Complaint}</code>
     * @return <code>{@link Response}</code>
     */
    @POST
    @PermitAll
    @Path("/add-complaint")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComplaint(@Auth User user, @NotNull @Valid Complaint complaint) {

        complaint.setUserId(user.getUserId());
        ComplaintDAL.addComplaint(complaint, jdbi);
        return Response.ok(ADD_COMPLAINT_SUCCESSFUL_RESPONSE).build();
    }

    /**
     * Method to get user complaints
     *
     * @param user , <code>{@link User}</code>
     * @return <code>{@link Response}</code>
     */
    @GET
    @PermitAll
    @Path("/user-complaints")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserComplaints(@Auth User user) {

        List<Complaint> userComplaints = ComplaintDAL.getUserComplaints(user.getUserId(), jdbi);
        return Response.ok(userComplaints).build();
    }

    /**
     * Method to get all the complaints
     *
     * @return <code>{@link Response}</code>
     */
    @GET
    @RolesAllowed("ADMIN")
    @Path("/all-complaints")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComplaints() {

        List<Complaint> allComplaints = ComplaintDAL.getAllComplaints(jdbi);
        return Response.ok(allComplaints).build();
    }

    /**
     * Method to update complaint status
     *
     * @param updatedComplaint , <code>{@link Complaint}</code>
     * @return <code>{@link Response}</code>
     */
    @PUT
    @RolesAllowed("ADMIN")
    @Path("/update-complaint-status")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCompliantStatus(@NotNull Complaint updatedComplaint) {
        ComplaintDAL.updateComplaintStatus(updatedComplaint.getComplaintId(), updatedComplaint.getStatus(), jdbi);
        return Response.ok(UPDATE_COMPLAINT_SUCCESSFUL_RESPONSE).build();
    }

}
