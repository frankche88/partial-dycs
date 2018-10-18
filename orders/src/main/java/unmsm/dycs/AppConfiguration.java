package unmsm.dycs;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import unmsm.dycs.application.security.JwtOrderConfiguration;

public class AppConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;
    
    @JsonProperty("jwt")
    private JwtOrderConfiguration jwtConfig;

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }

    public void setSwaggerBundleConfiguration(SwaggerBundleConfiguration swaggerBundleConfiguration) {
        this.swaggerBundleConfiguration = swaggerBundleConfiguration;
    }

    public JwtOrderConfiguration getJwtConfig() {
        return jwtConfig;
    }

    public void setJwtConfig(JwtOrderConfiguration jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

}
