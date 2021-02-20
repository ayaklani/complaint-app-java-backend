package io.abc.complaintportal.api;

import io.dropwizard.auth.Authorizer;

/**
 * <code>{@link ComplaintPortalAuthorizer}</code> abstract for user authorization process
 * @author aya
 * @since v1.0
 */
public abstract class ComplaintPortalAuthorizer implements Authorizer<User> {}
