package com.proyecto.frontend.controller;

import com.proyecto.frontend.client.AuthClient;
import com.proyecto.frontend.service.IntegrantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private AuthClient authClient;  // Inyección del cliente Feign para autenticación

    @Autowired
    private IntegrantesService integrantesService;  // Servicio para obtener integrantes

    // Muestra la página de autenticación
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    // Maneja la lógica de autenticación
    @PostMapping("/authenticate")
    public String authenticate(
            @RequestParam("codigo") String codigo,
            @RequestParam("password") String password,
            Model model) {

        boolean authenticated = authClient.autenticar(codigo, password);  // Llama al backend para autenticación

        if (authenticated) {
            return "redirect:/detalle";  // Redirige a la pantalla de detalle si autenticación exitosa
        } else {
            model.addAttribute("error", "Problemas en la autenticación");
            return "login";  // Muestra la pantalla de login con un mensaje de error
        }
    }

    // Muestra la página de detalle
    @GetMapping("/detalle")
    public String showDetallePage(Model model) {
        model.addAttribute("integrantes", integrantesService.obtenerIntegrantes());  // Obtiene los integrantes y los envía a la vista
        return "detalle";
    }
}
