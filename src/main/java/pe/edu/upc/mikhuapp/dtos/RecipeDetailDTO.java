package pe.edu.upc.mikhuapp.dtos;

public class RecipeDetailDTO {
    private Long idRecipe;
    private String nomRecipe;
    private Double calories;
    private Integer difficulty;
    private Long idIngredient;
    private String nomIngredient;
    private Integer requestedQuantity;

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

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Long getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Long idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getNomIngredient() {
        return nomIngredient;
    }

    public void setNomIngredient(String nomIngredient) {
        this.nomIngredient = nomIngredient;
    }

    public Integer getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(Integer requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }
}
