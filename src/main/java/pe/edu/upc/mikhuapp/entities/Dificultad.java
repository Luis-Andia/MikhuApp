package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Dificultad")
public class Dificultad {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDificultad;

    @Column(name="Nivel_dificultad", length = 20, nullable = false)
    private String nivelDificultad;

    // Constructores

    public Dificultad() {
    }

    public Dificultad(Long idDificultad, String nivelDificultad) {
        this.idDificultad = idDificultad;
        this.nivelDificultad = nivelDificultad;
    }


    public Long getIdDificultad() {
        return idDificultad;
    }

    public void setIdDificultad(Long idDificultad) {
        this.idDificultad = idDificultad;
    }

    public String getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(String nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }
}