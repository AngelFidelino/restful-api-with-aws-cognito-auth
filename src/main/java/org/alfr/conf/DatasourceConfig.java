package org.alfr.conf;

import lombok.extern.java.Log;
import org.alfr.dto.AwsSecret;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Log
public class DatasourceConfig {

    private SecretManagerConfig secretManagerConfig;

    public DatasourceConfig(SecretManagerConfig secretManagerConfig) {
        this.secretManagerConfig = secretManagerConfig;
    }

    @Bean(name = "h2DataSource")
    public DataSource h2DataSource() {
        final String h2SecretName = "dev/myApp/h2";
        AwsSecret h2Secret = secretManagerConfig.getSecret(SecretManagerConfig.getDefaultSecretsManagerClient(), h2SecretName);
        DataSourceBuilder dsBuilder = DataSourceBuilder.create();
        dsBuilder.driverClassName("org.h2.Driver");
        dsBuilder.url("jdbc:h2:mem:mydb;DATABASE_TO_UPPER=false");
        dsBuilder.username(h2Secret.getUsername());
        dsBuilder.password(h2Secret.getPassword());
        return dsBuilder.build();
    }


}
