package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecipe;

    @Column(name="Nom_Receta", length = 100, nullable = false)
    private String nomRecipe;

    @ManyToOne
    @JoinColumn(name="Id_Pais")
    private Country country;

    @Column(name="Calorias", nullable = false)
    private Integer calories;

    @ManyToOne
    @JoinColumn(name="Id_Dificultad")
    private Difficulty difficulty;

    public Recipe() {
    }

    public Recipe(Long idRecipe, String nomRecipe, Country country,
                  Integer calories, Difficulty difficulty, byte[] imagen) {
        this.idRecipe = idRecipe;
        this.nomRecipe = nomRecipe;
        this.country = country;
        this.calories = calories;
        this.difficulty = difficulty;
    }

    public Long getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Long idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getNomRecipe() {
        return nomRecipe;
    }

    public void setNomRecipe(String nomRecipe) {
        this.nomRecipe = nomRecipe;
    }

    public Country getPais() {
        return country;
    }

    public void setPais(Country country) {
        this.country = country;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Difficulty getDificultad() {
        return difficulty;
    }

    public void setDificultad(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}