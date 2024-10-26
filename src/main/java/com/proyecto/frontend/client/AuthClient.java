package com.proyecto.frontend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-client", url = "${backend.api.url}")
public interface AuthClient {

    @PostMapping("/autenticar")
    boolean autenticar(@RequestParam String codigo, @RequestParam String password);
}
