package io.abc.complaintportal;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <code>{@link ComplaintPortalConfiguration}</code> class to handle the configuration of the app
 *
 * @author aya
 * @since v1.0
 */
public class ComplaintPortalConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database;

    @NotEmpty
    private String adminName;

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory database) {
        this.database = database;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
