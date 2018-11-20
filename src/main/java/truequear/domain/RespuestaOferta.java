package truequear.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A RespuestaOferta.
 */
@Entity
@Table(name = "respuesta_oferta")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "respuestaoferta")
public class RespuestaOferta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "aceptar", nullable = false)
    private Boolean aceptar;

    @OneToOne    @JoinColumn(unique = true)
    private Oferta generaRespuestas;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAceptar() {
        return aceptar;
    }

    public RespuestaOferta aceptar(Boolean aceptar) {
        this.aceptar = aceptar;
        return this;
    }

    public void setAceptar(Boolean aceptar) {
        this.aceptar = aceptar;
    }

    public Oferta getGeneraRespuestas() {
        return generaRespuestas;
    }

    public RespuestaOferta generaRespuestas(Oferta oferta) {
        this.generaRespuestas = oferta;
        return this;
    }

    public void setGeneraRespuestas(Oferta oferta) {
        this.generaRespuestas = oferta;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RespuestaOferta respuestaOferta = (RespuestaOferta) o;
        if (respuestaOferta.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), respuestaOferta.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RespuestaOferta{" +
            "id=" + getId() +
            ", aceptar='" + isAceptar() + "'" +
            "}";
    }
}
