package io.abc.complaintportal.api;

import org.jvnet.hk2.annotations.Optional;

import javax.validation.constraints.NotEmpty;
import java.security.Principal;

/**
 * Bean <code>{@link User}</code> class represent the user of the complaint app
 * @author aya
 * @since v1.0
 */
public class User implements Principal {
    private long userId;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String email;
    private String token;
    private String joinDate;
    @Optional
    private String phone;
    private Role roleName;
    @NotEmpty
    private String password;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, Role roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }

    public User(long userId, String name, String email, String phone, Role roleName) {
        this.userId = userId;
        this.userName = name;
        this.email = email;
        this.phone = phone;
        this.roleName = roleName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRoleName() {
        return roleName;
    }

    public void setRoleName(Role roleName) {
        this.roleName = roleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object another) {
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", joinDate='" + joinDate + '\'' +
                ", phone='" + phone + '\'' +
                ", roleName=" + roleName +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
