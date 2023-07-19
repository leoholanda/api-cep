package com.group.holanda.apicep.controller;

import com.group.holanda.apicep.dto.EnderecoDTO;
import com.group.holanda.apicep.service.CepService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class CepController {

    @Autowired
    private CepService service;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoDTO> consultaEnderecoPorCep(@PathVariable @Valid String cep) {

        try {
            return ResponseEntity.ok().body(service.buscaEnderecoPeloCep(cep));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
