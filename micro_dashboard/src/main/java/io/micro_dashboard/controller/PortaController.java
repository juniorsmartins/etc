package io.micro_dashboard.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/servico")
public class PortaController {

    @GetMapping(value = "/porta")
    public String consultarPorta(@Value("${local.server.port}") String porta) {
        return String.format("Instância executada na porta: %s", porta);
    }
}
