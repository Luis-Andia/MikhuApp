package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Historial_Consumo")
public class HistorialConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsumo;

    @ManyToOne
    @JoinColumn(name = "Id_Item", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "Id_Receta", nullable = false)
    private Receta receta;

    @Column(name = "Fecha_Consumo", nullable = false)
    private LocalDateTime fechaConsumo;

    public HistorialConsumo() {
    }

    public HistorialConsumo(Long idConsumo, Item item, Receta receta,
                            LocalDateTime fechaConsumo) {
        this.idConsumo = idConsumo;
        this.item = item;
        this.receta = receta;
        this.fechaConsumo = fechaConsumo;
    }

    public Long getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(Long idConsumo) {
        this.idConsumo = idConsumo;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public LocalDateTime getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(LocalDateTime fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }
}