package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Historial_Consumo")
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsumption;

    @ManyToOne
    @JoinColumn(name = "Id_Item", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "Id_Receta", nullable = false)
    private Recipe recipe;

    @Column(name = "Fecha_Consumo", nullable = false)
    private LocalDateTime consumptionDate;

    public Consumption() {
    }

    public Consumption(Long idConsumption, Item item, Recipe recipe,
                       LocalDateTime consumptionDate) {
        this.idConsumption = idConsumption;
        this.item = item;
        this.recipe = recipe;
        this.consumptionDate = consumptionDate;
    }

    public Long getIdConsumption() {
        return idConsumption;
    }

    public void setIdConsumption(Long idConsumption) {
        this.idConsumption = idConsumption;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public LocalDateTime getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(LocalDateTime consumptionDate) {
        this.consumptionDate = consumptionDate;
    }
}