package pe.edu.upc.mikhuapp.dtos;

import jakarta.persistence.*;
import pe.edu.upc.mikhuapp.entities.Item;
import pe.edu.upc.mikhuapp.entities.Recipe;

import java.time.LocalDate;

public class ConsumptionDTODate {
    private Long idConsumption;
    private LocalDate consumptionDate;
    private String nomIngredient;

    public Long getIdConsumption() {
        return idConsumption;
    }

    public void setIdConsumption(Long idConsumption) {
        this.idConsumption = idConsumption;
    }

    public LocalDate getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(LocalDate consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public String getNomIngredient() {
        return nomIngredient;
    }

    public void setNomIngredient(String nomIngredient) {
        this.nomIngredient = nomIngredient;
    }
}
