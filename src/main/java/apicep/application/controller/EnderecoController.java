package apicep.application.controller;

import apicep.application.core.dto.EnderecoDTO;
import apicep.application.core.service.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cep")
@Api(value = "API Endereço")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @ApiOperation(value = "Consulta endereço por cep")
    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoDTO> consultaEnderecoPorCep(@PathVariable @Valid String cep) {
        try {
            return ResponseEntity.ok().body(service.buscaEnderecoPeloCep(cep).get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
