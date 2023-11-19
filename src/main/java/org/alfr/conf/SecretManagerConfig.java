package org.alfr.conf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.alfr.dto.AwsSecret;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.util.HashMap;
import java.util.Map;

@Component
@Log
public class SecretManagerConfig {
    private final ObjectMapper objectMapper;
    private final Map<String, AwsSecret> secretStore;

    public SecretManagerConfig() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        secretStore = new HashMap<>();
    }

    /**
     * The functionality of getting aws secret values can be executed on the aws infrastructure or locally (when debugging for example)
     * Using credentialsProvider(ProfileCredentialsProvider.create()) will override the "default credentials provider" chain which is implemented by the DefaultCredentialsProvider class. It sequentially checks each place where you can set the default configuration for supplying temporary credentials, and then selects the first one you set.
     */
    public static SecretsManagerClient getDefaultSecretsManagerClient() {
        Region region = Region.US_EAST_1;
        return SecretsManagerClient.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create()) //The default behavior is implemented by DefaultCredentialsProvider, but we can select another one
                .build();

    }

    public AwsSecret getSecret(SecretsManagerClient secretsClient, String secretName) {
        if (secretStore.containsKey(secretName)) {
            return secretStore.get(secretName);
        }
        GetSecretValueRequest secretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();
        GetSecretValueResponse secretValueResponse = secretsClient.getSecretValue(secretValueRequest);
        String secret = secretValueResponse.secretString();
        if (secret != null) {
            try {
                AwsSecret awsSecret = objectMapper.readValue(secret, AwsSecret.class);
                secretStore.put(secretName, awsSecret);
                return awsSecret;
            } catch (JsonProcessingException e) {
                final String errorMessage = "Something went wrong while trying to read the secret value. Cause: " + e.getMessage();
                log.severe(errorMessage);
                throw new RuntimeException(errorMessage);
            }
        }
        throw new RuntimeException("Secret value not found");
    }

}
