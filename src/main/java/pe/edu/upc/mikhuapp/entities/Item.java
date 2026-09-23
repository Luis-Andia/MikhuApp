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
    private Float availableQuantity;

    @Column(name="Fecha_Compra", nullable = false)
    private LocalDate purchaseDate;

    @Column(name="Fecha_Vencimiento", nullable = false)
    private LocalDate expirationDate;

    @Column(name="Stock_Minimo", nullable = false)
    private Integer minimumStock;

    // Constructores

    public Item() {
    }

    public Item(Long idItem, Family family, Ingredient ingredient,
                Float availableQuantity, LocalDate purchaseDate,
                LocalDate expirationDate, Integer minimumStock) {
        this.idItem = idItem;
        this.family = family;
        this.ingredient = ingredient;
        this.availableQuantity = availableQuantity;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;
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

    public Float getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Float availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Integer minimumStock) {
        this.minimumStock = minimumStock;
    }
}