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
    private Family family;

    @ManyToOne
    @JoinColumn(name="idIngrediente")
    private Ingredient ingredient;

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

    public Item(Long idItem, Family family, Ingredient ingredient,
                Float cantidadDisposicion, LocalDate fechaCompra,
                LocalDate fechaVencimiento, Integer stockMinimo) {
        this.idItem = idItem;
        this.family = family;
        this.ingredient = ingredient;
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

    public Family getFamilia() {
        return family;
    }

    public void setFamilia(Family family) {
        this.family = family;
    }

    public Ingredient getIngrediente() {
        return ingredient;
    }

    public void setIngrediente(Ingredient ingredient) {
        this.ingredient = ingredient;
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