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
    private String difficultyLevel;

    // Constructores
    public Difficulty() {
    }

    public Difficulty(Long idDifficulty, String difficultyLevel) {
        this.idDifficulty = idDifficulty;
        this.difficultyLevel = difficultyLevel;
    }

    public Long getIdDifficulty() {
        return idDifficulty;
    }

    public void setIdDifficulty(Long idDifficulty) {
        this.idDifficulty = idDifficulty;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}