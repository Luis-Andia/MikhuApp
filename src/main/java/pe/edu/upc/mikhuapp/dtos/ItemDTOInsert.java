package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class ItemDTOInsert {

    private Long idItem;

    @Positive(message = "Id de Familia obligatorio")
    private Long idFamily;

    @Positive(message = "Id de Ingrediente obligatorio")
    private Long idIngredient;

    @Positive(message = "Cantidad disponible obligatoria")
    private double amountAvailable;

    @NotNull(message = "Fecha de compra obligatoria")
    private LocalDate purchaseDate;

    @NotNull(message = "Fecha de vencimiento obligatoria")
    private LocalDate dueDate;

    @Positive(message = "Stock minimo obligatorio")
    private int minimumStock;


   // get y set

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

    public double getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(double amountAvailable) {
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