package io.abc.complaintportal.api;

import io.abc.complaintportal.db.UserDAL;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.jdbi.v3.core.Jdbi;

import java.util.Optional;

/**
 * <code>{@link ComplaintPortalAuthenticator}</code> class to handle user authentication process
 * @author aya
 * @since v1.0
 */
public class ComplaintPortalAuthenticator implements Authenticator<BasicCredentials, User> {

    private final Jdbi jdbi;

    public ComplaintPortalAuthenticator(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) {

        User user = UserDAL.checkLogin(credentials, jdbi);
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
