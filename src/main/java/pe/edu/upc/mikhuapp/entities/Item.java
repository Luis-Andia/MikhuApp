package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="items")
public class Item {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @ManyToOne
    @JoinColumn(name="idFamily")
    private Family family;

    @ManyToOne
    @JoinColumn(name="idIngredient")
    private Ingredient ingredient;

    @Column(name="amountAvailable", nullable = false)
    private Float amountAvailable;

    @Column(name="purchaseDate", nullable = false)
    private LocalDate purchaseDate;

    @Column(name="dueDate", nullable = false)
    private LocalDate dueDate;

    @Column(name="minimumStock", nullable = false)
    private Integer minimumStock;

    // Constructores

    public Item() {
    }

    public Item(Long idItem, Family family, Ingredient ingredient,
                Float amountAvailable, LocalDate purchaseDate,
                LocalDate dueDate, Integer minimumStock) {
        this.idItem = idItem;
        this.family = family;
        this.ingredient = ingredient;
        this.amountAvailable = amountAvailable;
        this.purchaseDate = purchaseDate;
        this.dueDate = dueDate;
        this.minimumStock = minimumStock;
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

    public Float getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Float cantidadDisposicion) {
        this.amountAvailable = cantidadDisposicion;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate fechaCompra) {
        this.purchaseDate = fechaCompra;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate fechaVencimiento) {
        this.dueDate = fechaVencimiento;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Integer stockMinimo) {
        this.minimumStock = stockMinimo;
    }
}