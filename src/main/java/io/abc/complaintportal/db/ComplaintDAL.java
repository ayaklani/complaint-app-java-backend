package io.abc.complaintportal.db;

import io.abc.complaintportal.api.Complaint;
import io.abc.complaintportal.api.ComplaintStatus;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * <code>{@link ComplaintDAL}</code> to handle complaint operations from database side
 * @author aya
 * @since v1.0
 */
public class ComplaintDAL {

    private static final String INSERT_COMPLAINT_QUERY = "INSERT INTO complaint_portal.complaint (title, content, status, user_id) VALUES (?, ?, ?, ?)";
    private static final String GET_USER_COMPLAINTS_QUERY = "SELECT complaint_id as complaintId, user_id as userId, title, content, status FROM complaint_portal.complaint where user_id = ?";
    private static final String GET_ALL_COMPLAINTS_QUERY = "SELECT complaint_id as complaintId, c.user_id as userId, user_name as userName,title, content, status FROM complaint_portal.complaint c INNER JOIN complaint_portal.user u ON c.user_id = u.user_id";
    private static final String UPDATE_COMPLAINT_STATUS_QUERY = "UPDATE complaint_portal.complaint SET status = ? WHERE complaint_id = ?";

    /**
     * Method to add <code>{@link Complaint}</code> to database
     * @param complaint , <code>{@link Complaint}</code>
     * @param jdbi , <code>{@link Jdbi}</code>
     */
    public static void addComplaint(Complaint complaint, Jdbi jdbi) {

        final String complaintTitle = isNotBlank(complaint.getTitle()) ? complaint.getTitle().trim() : null;
        jdbi.withHandle(handle -> handle.createUpdate(INSERT_COMPLAINT_QUERY)
                .bind(0, complaintTitle)
                .bind(1, complaint.getContent().trim())
                .bind(2, ComplaintStatus.NEW.getStatusName())
                .bind(3, complaint.getUserId())
                .execute());
    }

    /**
     * Method to get user <code>{@link Complaint}</code> list from DB based on userId
     * @param userId , <code>long</code>
     * @param jdbi , <code>{@link Jdbi}</code>
     * @return , <code>{@link List}</code> of <code>{@link Complaint}</code>
     */
    public static List<Complaint> getUserComplaints(long userId, Jdbi jdbi) {

        List<Complaint> complaints = jdbi.withHandle(handle -> handle.createQuery(GET_USER_COMPLAINTS_QUERY)
                .bind(0, userId).mapToBean(Complaint.class).list());
        return complaints;
    }

    /**
     * Method to get all <code>{@link Complaint}</code> list
     * @param jdbi , <code>{@link Jdbi}</code>
     * @return , <code>{@link List}</code> of <code>{@link Complaint}</code>
     */
    public static List<Complaint> getAllComplaints(Jdbi jdbi) {

        List<Complaint> complaints = jdbi.withHandle(handle -> handle.createQuery(GET_ALL_COMPLAINTS_QUERY)
                .mapToBean(Complaint.class).list());
        return complaints;
    }

    /**
     * Method to update <code>{@link ComplaintStatus}</code> based on complainId
     * @param compliantId , <code>long</code>
     * @param updatedStatus , <code>{@link ComplaintStatus}</code>
     * @param jdbi , <code>{@link Jdbi}</code>
     */
    public static void updateComplaintStatus(long compliantId, ComplaintStatus updatedStatus, Jdbi jdbi) {

        jdbi.withHandle(handle -> handle.createUpdate(UPDATE_COMPLAINT_STATUS_QUERY)
                .bind(0, updatedStatus.getStatusName())
                .bind(1, compliantId)
                .execute());
    }
}
