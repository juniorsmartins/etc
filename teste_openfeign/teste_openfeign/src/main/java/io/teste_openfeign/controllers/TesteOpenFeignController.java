package io.teste_openfeign.controllers;

import io.teste_openfeign.dtos.ValorDTO;
import io.teste_openfeign.http.MicroDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/teste")
public class TesteOpenFeignController {

    @Autowired
    private MicroDashboard microDashboard;

    @PutMapping
    public List<String> incluirNaFila(@RequestBody ValorDTO dto) {
        return this.microDashboard.atualizaLista(dto.valor());
    }
}
