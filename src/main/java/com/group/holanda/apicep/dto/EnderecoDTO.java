package com.group.holanda.apicep.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;

@Data
public class EnderecoDTO implements Serializable {

    private String cep;
    private String logradouro;
    private String bairro;

    @JsonAlias(value = "localidade")
    private String cidade;

    @JsonAlias(value = "uf")
    private String estado;

    private String pais;
    private String codigoIbgeCidade;
    private String codigoIbgeEstado;

}
