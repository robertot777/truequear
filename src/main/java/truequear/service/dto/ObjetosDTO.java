package truequear.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import truequear.domain.enumeration.TipoObjetos;

/**
 * A DTO for the Objetos entity.
 */
public class ObjetosDTO implements Serializable {

    private Long id;

    @NotNull
    private String tipoObjeto;

    
    @Lob
    private byte[] agregarArchibo;
    private String agregarArchiboContentType;

    private TipoObjetos tipoObjetos;

    @NotNull
    private String descripcion;

    private Long nombreId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public byte[] getAgregarArchibo() {
        return agregarArchibo;
    }

    public void setAgregarArchibo(byte[] agregarArchibo) {
        this.agregarArchibo = agregarArchibo;
    }

    public String getAgregarArchiboContentType() {
        return agregarArchiboContentType;
    }

    public void setAgregarArchiboContentType(String agregarArchiboContentType) {
        this.agregarArchiboContentType = agregarArchiboContentType;
    }

    public TipoObjetos getTipoObjetos() {
        return tipoObjetos;
    }

    public void setTipoObjetos(TipoObjetos tipoObjetos) {
        this.tipoObjetos = tipoObjetos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getNombreId() {
        return nombreId;
    }

    public void setNombreId(Long userId) {
        this.nombreId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ObjetosDTO objetosDTO = (ObjetosDTO) o;
        if (objetosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), objetosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ObjetosDTO{" +
            "id=" + getId() +
            ", tipoObjeto='" + getTipoObjeto() + "'" +
            ", agregarArchibo='" + getAgregarArchibo() + "'" +
            ", tipoObjetos='" + getTipoObjetos() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", nombre=" + getNombreId() +
            "}";
    }
}
