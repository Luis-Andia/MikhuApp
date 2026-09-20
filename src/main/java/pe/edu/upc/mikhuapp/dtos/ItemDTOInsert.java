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
    private Float cantidadDisposicion;

    @NotNull(message = "Fecha de compra obligatoria")
    private LocalDate fechaCompra;

    @NotNull(message = "Fecha de vencimiento obligatoria")
    private LocalDate fechaVencimiento;

    @NotNull(message = "Stock minimo obligatorio")
    private Integer stockMinimo;


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

    public Float getCantidadDisposicion() {
        return cantidadDisposicion;
    }

    public void setCantidadDisposicion(Float cantidadDisposicion) {
        this.cantidadDisposicion = cantidadDisposicion;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}