package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ItemDTOInsert {

    private Long idItem;

    @NotNull(message = "Id de Familia obligatorio")
    private Long idFamily;

    @NotNull(message = "Id de Ingrediente obligatorio")
    private Long idIngredient;

    @NotNull(message = "Cantidad disponible obligatoria")
    private Float amountAvailable;

    @NotNull(message = "Fecha de compra obligatoria")
    private LocalDate purchaseDate;

    @NotNull(message = "Fecha de vencimiento obligatoria")
    private LocalDate dueDate;

    @NotNull(message = "Stock minimo obligatorio")
    private int minimumStock;


    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Long getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(Long idFamily) {
        this.idFamily = idFamily;
    }

    public Long getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Long idIngredient) {
        this.idIngredient = idIngredient;
    }

    public Float getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Float amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(int minimumStock) {
        this.minimumStock = minimumStock;
    }
}