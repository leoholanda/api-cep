package apicep.application.integracao.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CepResponse implements Serializable {

    @JsonAlias(value = "code")
    private String cep;

    @JsonAlias(value = "address")
    private String logradouro;

    @JsonAlias(value = "district")
    private String bairro;

    @JsonAlias(value = "city")
    private String cidade;

    @JsonAlias(value = "state")
    private String estado;

    private Integer status;

}
