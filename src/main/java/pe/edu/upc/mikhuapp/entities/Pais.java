package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;

@Entity
@Table(name="Pais")
public class Pais {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPais;

    @Column(name="codPais", length = 5, nullable = false)
    private String codPais;

    @Column(name="nomPais", length = 40, nullable = false)
    private String nomPais;

    // Constructor vacio
    public Pais() {
    }

    // Constructor de los atributos


    public Pais(Long idPais, String codPais, String nomPais) {
        this.idPais = idPais;
        this.codPais = codPais;
        this.nomPais = nomPais;
    }

    // Getter and Setter

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    public String getNomPais() {
        return nomPais;
    }

    public void setNomPais(String nomPais) {
        this.nomPais = nomPais;
    }
}
