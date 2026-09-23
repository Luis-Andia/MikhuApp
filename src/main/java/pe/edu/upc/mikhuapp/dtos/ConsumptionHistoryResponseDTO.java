package pe.edu.upc.mikhuapp.dtos;

import java.time.LocalDateTime;

public class ConsumptionHistoryResponseDTO {

    private Long idConsumption;
    private Long idItem;
    private String ingredientName;
    private Long idRecipe;
    private String recipeName;
    private LocalDateTime consumptionDate;

    public ConsumptionHistoryResponseDTO() {
    }

    public Long getIdConsumption() {
        return idConsumption;
    }

    public void setIdConsumption(Long idConsumption) {
        this.idConsumption = idConsumption;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

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

    public LocalDateTime getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(LocalDateTime consumptionDate) {
        this.consumptionDate = consumptionDate;
    }
}