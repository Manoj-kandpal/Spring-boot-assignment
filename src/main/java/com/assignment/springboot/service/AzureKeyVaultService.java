package com.assignment.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.springboot.utils.AzureUtils;

@Service
public class AzureKeyVaultService {

    @Autowired
    private AzureUtils azureUtils;

    public String getSecret() {
        azureUtils.fetchSecret();
        return azureUtils.getSecret();
    }
}
