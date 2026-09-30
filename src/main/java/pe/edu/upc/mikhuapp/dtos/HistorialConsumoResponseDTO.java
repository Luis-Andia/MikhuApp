package pe.edu.upc.mikhuapp.dtos;

import java.time.LocalDate;

public class HistorialConsumoResponseDTO {

    private Long idConsumption;
    private Long idItem;
    private String nomIngredient;
    private Long idRecipe;
    private String nomRecipe;
    private LocalDate consumptionDate;

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

    public String getNomIngredient() {
        return nomIngredient;
    }

    public void setNomIngredient(String nomIngredient) {
        this.nomIngredient = nomIngredient;
    }

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

    public LocalDate getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(LocalDate consumptionDate) {
        this.consumptionDate = consumptionDate;
    }
}