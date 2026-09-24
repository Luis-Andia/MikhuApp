package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pe.edu.upc.mikhuapp.entities.Country;

public class RecipeDTOInsert {

    @NotNull(message = "El id de la receta es obligatorio")
    private Long idRecipe;
    @NotBlank(message = "El nombre de la receta es obligatorio")
    private String nomRecipe;
    @NotBlank(message = "El pais de la receta es obligatoria")
    private Country country;
    @NotNull(message = "Las calorias de la receta son obligatorias")
    private Integer calories;

    // Get y set

    public Long getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Long idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getNomRecipe() {
        return nomRecipe;
    }

    public void setNomRecipe(String nomRecipe) {
        this.nomRecipe = nomRecipe;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
}
