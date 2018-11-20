package truequear.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import truequear.domain.enumeration.TipoObjeto;

/**
 * A Oferta.
 */
@Entity
@Table(name = "oferta")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "oferta")
public class Oferta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "generar_oferta", nullable = false)
    private String generarOferta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_objeto")
    private TipoObjeto tipoObjeto;

    
    @Lob
    @Column(name = "genera_oferta", nullable = false)
    private byte[] generaOferta;

    @Column(name = "genera_oferta_content_type", nullable = false)
    private String generaOfertaContentType;

    @OneToOne    @JoinColumn(unique = true)
    private User nombre;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenerarOferta() {
        return generarOferta;
    }

    public Oferta generarOferta(String generarOferta) {
        this.generarOferta = generarOferta;
        return this;
    }

    public void setGenerarOferta(String generarOferta) {
        this.generarOferta = generarOferta;
    }

    public TipoObjeto getTipoObjeto() {
        return tipoObjeto;
    }

    public Oferta tipoObjeto(TipoObjeto tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
        return this;
    }

    public void setTipoObjeto(TipoObjeto tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public byte[] getGeneraOferta() {
        return generaOferta;
    }

    public Oferta generaOferta(byte[] generaOferta) {
        this.generaOferta = generaOferta;
        return this;
    }

    public void setGeneraOferta(byte[] generaOferta) {
        this.generaOferta = generaOferta;
    }

    public String getGeneraOfertaContentType() {
        return generaOfertaContentType;
    }

    public Oferta generaOfertaContentType(String generaOfertaContentType) {
        this.generaOfertaContentType = generaOfertaContentType;
        return this;
    }

    public void setGeneraOfertaContentType(String generaOfertaContentType) {
        this.generaOfertaContentType = generaOfertaContentType;
    }

    public User getNombre() {
        return nombre;
    }

    public Oferta nombre(User user) {
        this.nombre = user;
        return this;
    }

    public void setNombre(User user) {
        this.nombre = user;
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
        Oferta oferta = (Oferta) o;
        if (oferta.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), oferta.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Oferta{" +
            "id=" + getId() +
            ", generarOferta='" + getGenerarOferta() + "'" +
            ", tipoObjeto='" + getTipoObjeto() + "'" +
            ", generaOferta='" + getGeneraOferta() + "'" +
            ", generaOfertaContentType='" + getGeneraOfertaContentType() + "'" +
            "}";
    }
}
