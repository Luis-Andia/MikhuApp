package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConsumptionDTO {

    private Long idConsumption;

    @NotNull(message = "Id de Item obligatorio")
    private Long idItem;

    @NotNull(message = "Id de Receta obligatorio")
    private Long idReceta;

    @NotNull(message = "Fecha de consumo obligatoria")
    private LocalDate consumptionDate;

    // get y set

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

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public LocalDate getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(LocalDate consumptionDate) {
        this.consumptionDate = consumptionDate;
    }
}