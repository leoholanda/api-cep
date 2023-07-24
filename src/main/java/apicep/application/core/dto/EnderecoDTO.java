package apicep.application.core.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO implements Serializable {

    private String cep;
    private String logradouro;
    private String bairro;

    @JsonAlias(value = "localidade")
    private String cidade;

    @JsonAlias(value = "uf")
    private String estado;

    private String pais;

    @JsonAlias(value = "ibge")
    private String codigoIbgeCidade;

}
