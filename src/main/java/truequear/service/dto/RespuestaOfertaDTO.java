package truequear.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RespuestaOferta entity.
 */
public class RespuestaOfertaDTO implements Serializable {

    private Long id;

    @NotNull
    private Boolean aceptar;

    private Long generaRespuestasId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAceptar() {
        return aceptar;
    }

    public void setAceptar(Boolean aceptar) {
        this.aceptar = aceptar;
    }

    public Long getGeneraRespuestasId() {
        return generaRespuestasId;
    }

    public void setGeneraRespuestasId(Long ofertaId) {
        this.generaRespuestasId = ofertaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RespuestaOfertaDTO respuestaOfertaDTO = (RespuestaOfertaDTO) o;
        if (respuestaOfertaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), respuestaOfertaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RespuestaOfertaDTO{" +
            "id=" + getId() +
            ", aceptar='" + isAceptar() + "'" +
            ", generaRespuestas=" + getGeneraRespuestasId() +
            "}";
    }
}
