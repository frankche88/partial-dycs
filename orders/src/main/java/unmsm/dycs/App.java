package unmsm.dycs;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import unmsm.dycs.orders.infrastructure.application.bundles.CorsBundle;
import unmsm.dycs.orders.infrastructure.application.bundles.HbnBundle;
import unmsm.dycs.orders.infrastructure.application.bundles.SwitchableSwaggerBundle;
import unmsm.dycs.orders.infrastructure.application.guice.module.HbnModule;

public class App extends Application<AppConfiguration> {
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        
        
        bootstrap.addBundle(new MigrationsBundle<AppConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
        
        bootstrap.addBundle(new SwitchableSwaggerBundle());
        
        bootstrap.addBundle(new CorsBundle());
        

        final HbnBundle hibernate = new HbnBundle();
        
        // register hbn bundle before guice to make sure factory initialized before guice context start
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig("app")
                .modules(new HbnModule(hibernate))
                .build());

    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {

        environment.jersey().register(new InvalidCustomerExceptionMapper());
    }

}
