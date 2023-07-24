package apicep.application.integracao.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrasilCepResponse implements Serializable {

    private String cep;

    @JsonAlias(value = "street")
    private String logradouro;

    @JsonAlias(value = "neighborhood")
    private String bairro;

    @JsonAlias(value = "city")
    private String cidade;

    @JsonAlias(value = "state")
    private String estado;

}
