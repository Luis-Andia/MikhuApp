package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ConsumptionDTO {

    private Long idConsumo;

    @NotNull(message = "Id de Item obligatorio")
    private Long idItem;

    @NotNull(message = "Id de Receta obligatorio")
    private Long idReceta;

    @NotNull(message = "Fecha de consumo obligatoria")
    private LocalDateTime fechaConsumo;

    public Long getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(Long idConsumo) {
        this.idConsumo = idConsumo;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public LocalDateTime getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(LocalDateTime fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }
}