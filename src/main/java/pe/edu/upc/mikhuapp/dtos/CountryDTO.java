package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;

public class CountryDTO {
    private Long idCountry;
    @NotBlank(message = "El codigo del pais no puede estar vacio")
    private String codCountry;
    @NotBlank(message = "El nombre del pais no puede estar vacio")
    private String nomCountry;

    // Getter y Setter
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
