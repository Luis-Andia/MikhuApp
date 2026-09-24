package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="countries")
public class Country {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCountry;

    @Column(name="codCountry", length = 5, nullable = false)
    private String codCountry;

    @Column(name="nomCountry", length = 40, nullable = false)
    private String nomCountry;

    // Constructor vacio
    public Country() {
    }

    // Constructor de los atributos
    public Country(Long idCountry, String codCountry, String nomCountry) {
        this.idCountry = idCountry;
        this.codCountry = codCountry;
        this.nomCountry = nomCountry;
    }

    // Getter and Setter
    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    public String getCodCountry() {
        return codCountry;
    }

    public void setCodCountry(String codCountry) {
        this.codCountry = codCountry;
    }

    public String getNomCountry() {
        return nomCountry;
    }

    public void setNomCountry(String nomCountry) {
        this.nomCountry = nomCountry;
    }
}
