package io.abc.complaintportal.api;

/**
 * Bean code>{@link PingResult}</code> class represents ping result
 *
 * @author aya
 * @since v1.0
 */
public class PingResult {

    private final String status;

    public PingResult(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
