package io.abc.complaintportal.db;

import io.abc.complaintportal.Utils;
import io.abc.complaintportal.api.Role;
import io.abc.complaintportal.api.User;
import io.dropwizard.auth.basic.BasicCredentials;
import org.jdbi.v3.core.Jdbi;

/**
 * <code>{@link UserDAL}</code> to handle the operations on user operations from database side
 * @author aya
 * @since v1.0
 */
public class UserDAL {

    private static final String LOGIN_QUERY = "SELECT user_id as userId, user_name as userName, email, phone, join_date as joinDate, role_name as roleName " +
            "FROM complaint_portal.user u INNER JOIN complaint_portal.role r ON u.role_id = r.role_id where lower(user_name) = ? AND password = ?";
    private static final String INSERT_USER_QUERY = "INSERT INTO complaint_portal.user (user_name, password, email, phone, role_id) VALUES (?, ?, ?, ?, ?)";

    /**
     * Method to check if the user is authenticated or not form DB side
     * @param credentials , <code>{@link BasicCredentials}</code>
     * @param jdbi , <code>{@link Jdbi}</code>
     * @return <code>{@link User}</code>
     */
    public static User checkLogin(BasicCredentials credentials, Jdbi jdbi) {

        String hashedPassword = Utils.generateHashedPassword(credentials.getPassword());
        if (hashedPassword == null || hashedPassword.isEmpty()) {
            return null;
        }

        User user = jdbi.withHandle(handle -> handle.createQuery(
                LOGIN_QUERY)
                .bind(0, credentials.getUsername())
                .bind(1, hashedPassword)
                .mapToBean(User.class).findFirst().orElse(null));
        return user;
    }

    /**
     * Method to add user to database
     * @param user ,  <code>{@link User}</code>
     * @param jdbi , <code>{@link Jdbi}</code>
     */
    public static void addUser(User user, Jdbi jdbi) {

        jdbi.withHandle(handle -> handle.createUpdate(INSERT_USER_QUERY)
                .bind(0, user.getName().trim())
                .bind(1, Utils.generateHashedPassword(user.getPassword()))
                .bind(2, user.getEmail().trim())
                .bind(3, user.getPhone().trim())
                .bind(4, Role.CUSTOMER.getRoleId())
                .execute());
    }
}
