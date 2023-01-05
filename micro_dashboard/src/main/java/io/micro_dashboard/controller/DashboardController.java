package io.micro_dashboard.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {

    private static List<String> lista = new ArrayList<>();

    @GetMapping(value = "/porta")
    public String consultarPorta(@Value("${local.server.port}") String porta) {
        return String.format("Instância executada na porta: %s", porta);
    }

    @PutMapping
    public List<String> mostrar(@RequestBody String valor) {
        lista.add(valor);
        lista.forEach(System.out::println);
        return lista;
    }
}
