package io.abc.complaintportal;

import io.abc.complaintportal.api.Role;
import io.abc.complaintportal.api.User;
import io.abc.complaintportal.api.ComplaintPortalAuthenticator;
import io.abc.complaintportal.api.ComplaintPortalAuthorizer;
import io.abc.complaintportal.resource.ComplaintResource;
import io.abc.complaintportal.resource.PingResource;
import io.abc.complaintportal.resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jdbi.v3.core.Jdbi;

/**
 * <code>{@link ComplaintPortalApplication}</code> class to run the application
 *
 * @author aya
 * @since v1.0
 */
public class ComplaintPortalApplication extends Application<ComplaintPortalConfiguration> {

    private final static String DATABASE_NAME = "mysql";

    public static void main(String[] args) throws Exception {
        new ComplaintPortalApplication().run(args);
    }

    @Override
    public String getName() {
        return "complaint-portal";
    }

    @Override
    public void initialize(Bootstrap<ComplaintPortalConfiguration> bootstrap) {
    }

    @Override
    public void run(ComplaintPortalConfiguration config, Environment environment) {

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, config.getDataSourceFactory(), DATABASE_NAME);

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new ComplaintPortalAuthenticator(jdbi))
                        .setAuthorizer(new ComplaintPortalAuthorizer() {
                            @Override
                            public boolean authorize(User user, String role) {
                                return user.getName().equals(config.getAdminName()) && role.equals(Role.ADMIN.name());
                            }
                        })
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

        final PingResource pingResource = new PingResource();
        environment.jersey().register(pingResource);

        final UserResource userResource = new UserResource(jdbi);
        environment.jersey().register(userResource);

        final ComplaintResource complaintResource = new ComplaintResource(jdbi);
        environment.jersey().register(complaintResource);
    }
}
