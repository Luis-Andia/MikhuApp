package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecipe;

    @Column(name="Nom_Receta", length = 100, nullable = false)
    private String recipeName;

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

    public Recipe(Long idRecipe, String recipeName, Country country,
                  Integer calories, Difficulty difficulty, byte[] image) {
        this.idRecipe = idRecipe;
        this.recipeName = recipeName;
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

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}