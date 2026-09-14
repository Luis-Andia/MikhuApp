package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Item")
public class Item {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @ManyToOne
    @JoinColumn(name="idFamilia")
    private Familia familia;

    @ManyToOne
    @JoinColumn(name="idIngrediente")
    private Ingrediente ingrediente;

    @Column(name="Cantidad_Disposicion", nullable = false)
    private Float cantidadDisposicion;

    @Column(name="Fecha_Compra", nullable = false)
    private LocalDate fechaCompra;

    @Column(name="Fecha_Vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name="Stock_Minimo", nullable = false)
    private Integer stockMinimo;

    // Constructores

    public Item() {
    }

    public Item(Long idItem, Familia familia, Ingrediente ingrediente,
                Float cantidadDisposicion, LocalDate fechaCompra,
                LocalDate fechaVencimiento, Integer stockMinimo) {
        this.idItem = idItem;
        this.familia = familia;
        this.ingrediente = ingrediente;
        this.cantidadDisposicion = cantidadDisposicion;
        this.fechaCompra = fechaCompra;
        this.fechaVencimiento = fechaVencimiento;
        this.stockMinimo = stockMinimo;
    }


    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
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