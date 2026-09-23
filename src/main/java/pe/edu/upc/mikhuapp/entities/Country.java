package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Pais")
public class Country {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCountry;

    @Column(name = "codCountry", length = 5, nullable = false)
    private String countryCode;

    @Column(name = "nomCountry", length = 40, nullable = false)
    private String countryName;

    // Empty constructor
    public Country() {
    }

    // Attributes constructor
    public Country(Long idCountry, String countryCode, String countryName) {
        this.idCountry = idCountry;
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    // Getter and Setter
    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}