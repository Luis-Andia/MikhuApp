package pe.edu.upc.mikhuapp.dtos;

public class RecipeDTOList {
    private Long idRecipe;
    private String nomRecipe;
    private Long idCountry;
    private Double calories;
    private int difficulty;

    // Get and set
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

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
