package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;

public class IngredienteDTO {

    private Long idIngrediente;

    @NotBlank(message = "Nombre de ingrediente obligatorio")
    private String nomIngrediente;

    // Get and set

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNomIngrediente() {
        return nomIngrediente;
    }

    public void setNomIngrediente(String nomIngrediente) {
        this.nomIngrediente = nomIngrediente;
    }
}