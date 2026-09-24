package pe.edu.upc.mikhuapp.dtos;

import java.time.LocalDate;

public class ItemResponseDTO {

    private Long idItem;
    private String nombreIngrediente;
    private Float cantidadDisposicion;
    private LocalDate fechaCompra;
    private LocalDate fechaVencimiento;
    private Integer stockMinimo;

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
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