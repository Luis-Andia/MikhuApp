package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;

public class IngredientDTO {

    private Long idIngredient;

    @NotBlank(message = "Nombre de ingrediente obligatorio")
    private String nomIngredient;

    // Get and set
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
}