package com.assignment.springboot.utils;

import org.springframework.stereotype.Service;

import com.azure.identity.AzureCliCredential;
import com.azure.identity.AzureCliCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AzureUtils {
    private final SecretClient secretClient;
    private String secret;
    AzureCliCredential cliCredential;

    public AzureUtils() {
        this.cliCredential = new AzureCliCredentialBuilder().tenantId("05d75c05-fa1a-42e7-9cf1-eb416c396f2d").build();
        this.secretClient = new SecretClientBuilder()
                .vaultUrl("https://test-jaspreet-kv.vault.azure.net/")
                .credential(cliCredential)
                .buildClient();
    }

    public void fetchSecret() {
        secret = secretClient.getSecret("env").getValue();
        log.info("Secret Value from key vault :{}",secret);
    }

    public String getSecret() {
        return secret;
    }
}
