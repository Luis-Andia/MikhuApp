package pe.edu.upc.mikhuapp.dtos;

import pe.edu.upc.mikhuapp.entities.Difficulty;
import pe.edu.upc.mikhuapp.entities.Country;

public class RecipeDTOList {

    private Long idRecipe;
    private String recipeName;
    private Country country;
    private Integer calories;
    private Difficulty difficulty;
    private byte[] image;

    public Long getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Long idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}