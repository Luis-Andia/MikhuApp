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
    private int amountAvailable;

    @Column(name="purchaseDate", nullable = false)
    private LocalDate purchaseDate;

    @Column(name="dueDate", nullable = false)
    private LocalDate dueDate;

    @Column(name="minimumStock", nullable = false)
    private int minimumStock;

    // Constructores

    public Item() {
    }

    public Item(Long idItem, Family family, Ingredient ingredient, int amountAvailable, LocalDate purchaseDate, LocalDate dueDate, int minimumStock) {
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

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(int amountAvailable) {
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