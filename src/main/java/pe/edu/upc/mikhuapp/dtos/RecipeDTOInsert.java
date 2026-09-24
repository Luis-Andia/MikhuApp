package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pe.edu.upc.mikhuapp.entities.Country;

public class RecipeDTOInsert {

    @NotNull(message = "El id de la receta es obligatorio")
    private Long idReceta;
    @NotBlank(message = "El nombre de la receta es obligatorio")
    private String nomReceta;
    @NotBlank(message = "El pais de la receta es obligatoria")
    private Country country;
    @NotNull(message = "Las calorias de la receta son obligatorias")
    private Integer calorias;

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public String getNomReceta() {
        return nomReceta;
    }

    public void setNomReceta(String nomReceta) {
        this.nomReceta = nomReceta;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

}
