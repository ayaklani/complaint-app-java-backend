package io.abc.complaintportal.api;

/**
 *  <code>{@link Role}</code> enum represents the complaint app user's role
 * @author aya
 * @since v1.0
 */
public enum Role {
    ADMIN(1l), CUSTOMER(2l);

    private long roleId;

    Role(long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }
}
