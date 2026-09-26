package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class RecipeDTOInsert {
    private Long idRecipe;
    @NotBlank(message = "El nombre de la receta es obligatorio")
    private String nomRecipe;
    @Positive(message = "El id del pais de la receta es obligatorio")
    private Long idCountry;
    @Positive(message = "Las calorias de la receta son obligatorias")
    private double calories;
    @Positive(message = "La dificultad es obligatoria")
    private int difficulty;

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

    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
