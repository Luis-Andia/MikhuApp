package pe.edu.upc.mikhuapp.dtos;

import java.time.LocalDate;

public class ItemDTOList {

    private Long idItem;
    private Long idFamilia;
    private Long idIngrediente;
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

    public Long getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Long idFamilia) {
        this.idFamilia = idFamilia;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
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