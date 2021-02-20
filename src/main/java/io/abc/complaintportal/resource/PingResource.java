package io.abc.complaintportal.resource;

import com.codahale.metrics.annotation.Timed;
import io.abc.complaintportal.api.PingResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <code>{@link PingResource}</code> class to check if the server is running
 *
 * @author aya
 * @since v1.0
 */
@Path("/ping")
@Produces({MediaType.APPLICATION_JSON})
public class PingResource {

    private static final String PING_SUCCESSFUL_RESPONSE = "Running";

    /**
     * Method to checks if the server is up and running
     * If this endpoint returns any value other than 200
     * it indicates that service is down.
     *
     * @return String "Running"
     */
    @GET
    @Timed
    public PingResult pingServer() {
        return new PingResult(PING_SUCCESSFUL_RESPONSE);
    }
}
