package com.proyecto.frontend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IntegrantesService {

    private final RestTemplate restTemplate;
    private final String backendUrl;

    public IntegrantesService(RestTemplate restTemplate, @Value("${backend.api.url}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.backendUrl = backendUrl;
    }

    public List<String> obtenerIntegrantes() {
        String url = backendUrl + "/get-integrantes";
        return List.of(restTemplate.getForObject(url, String[].class));
    }
}
