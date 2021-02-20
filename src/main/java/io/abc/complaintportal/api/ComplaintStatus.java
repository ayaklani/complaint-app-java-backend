package io.abc.complaintportal.api;

import io.abc.complaintportal.resource.ComplaintResource;

/**
 * <code>{@link ComplaintStatus }</code> enum represents the complaint status
 *
 * @author aya
 * @since v1.0
 */
public enum ComplaintStatus {
    NEW("new"), RESOLVED("resolved"), PENDING("pending"), DISMISSED("dismissed");

    private String statusName;

    ComplaintStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
