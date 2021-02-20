package io.abc.complaintportal.api;

import org.jvnet.hk2.annotations.Optional;

import javax.validation.constraints.NotEmpty;

/**
 * Bean <code>{@link Complaint}</code> class represents a complaint
 * @author aya
 * @since v1.0
 */
public class Complaint {

    private long complaintId;
    private long userId;
    @Optional
    private String title;
    @NotEmpty
    private String content;
    private ComplaintStatus status;
    private String userName;

    public Complaint() {
    }

    public Complaint(long complaintId, long userId, String title, @NotEmpty String content, ComplaintStatus status, String userName) {
        this.complaintId = complaintId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.status = status;
        this.userName = userName;
    }

    public long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(long complaintId) {
        this.complaintId = complaintId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintId=" + complaintId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", userName='" + userName + '\'' +
                '}';
    }
}
