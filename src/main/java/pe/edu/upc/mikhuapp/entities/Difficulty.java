package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Difficulty")
public class Difficulty {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDifficulty;

    @Column(name="Nivel_dificultad", length = 20, nullable = false)
    private String difficultylevel;

    // Constructores
    public Difficulty() {
    }

    public Difficulty(Long idDifficulty, String difficultylevel) {
        this.idDifficulty = idDifficulty;
        this.difficultylevel = difficultylevel;
    }


    public Long getIdDifficulty() {
        return idDifficulty;
    }

    public void setIdDifficulty(Long idDifficulty) {
        this.idDifficulty = idDifficulty;
    }

    public String getDifficultylevel() {
        return difficultylevel;
    }

    public void setDifficultylevel(String difficultylevel) {
        this.difficultylevel = difficultylevel;
    }
}