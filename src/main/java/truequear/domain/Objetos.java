package truequear.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import truequear.domain.enumeration.TipoObjetos;

/**
 * A Objetos.
 */
@Entity
@Table(name = "objetos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "objetos")
public class Objetos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "tipo_objeto", nullable = false)
    private String tipoObjeto;

    
    @Lob
    @Column(name = "agregar_archibo", nullable = false)
    private byte[] agregarArchibo;

    @Column(name = "agregar_archibo_content_type", nullable = false)
    private String agregarArchiboContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_objetos")
    private TipoObjetos tipoObjetos;

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @OneToOne    @JoinColumn(unique = true)
    private User nombre;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public Objetos tipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
        return this;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public byte[] getAgregarArchibo() {
        return agregarArchibo;
    }

    public Objetos agregarArchibo(byte[] agregarArchibo) {
        this.agregarArchibo = agregarArchibo;
        return this;
    }

    public void setAgregarArchibo(byte[] agregarArchibo) {
        this.agregarArchibo = agregarArchibo;
    }

    public String getAgregarArchiboContentType() {
        return agregarArchiboContentType;
    }

    public Objetos agregarArchiboContentType(String agregarArchiboContentType) {
        this.agregarArchiboContentType = agregarArchiboContentType;
        return this;
    }

    public void setAgregarArchiboContentType(String agregarArchiboContentType) {
        this.agregarArchiboContentType = agregarArchiboContentType;
    }

    public TipoObjetos getTipoObjetos() {
        return tipoObjetos;
    }

    public Objetos tipoObjetos(TipoObjetos tipoObjetos) {
        this.tipoObjetos = tipoObjetos;
        return this;
    }

    public void setTipoObjetos(TipoObjetos tipoObjetos) {
        this.tipoObjetos = tipoObjetos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Objetos descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public User getNombre() {
        return nombre;
    }

    public Objetos nombre(User user) {
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
        Objetos objetos = (Objetos) o;
        if (objetos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), objetos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Objetos{" +
            "id=" + getId() +
            ", tipoObjeto='" + getTipoObjeto() + "'" +
            ", agregarArchibo='" + getAgregarArchibo() + "'" +
            ", agregarArchiboContentType='" + getAgregarArchiboContentType() + "'" +
            ", tipoObjetos='" + getTipoObjetos() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
