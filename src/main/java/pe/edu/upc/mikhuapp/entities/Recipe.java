package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecipe;

    @Column(name="nomRecipe", length = 100, nullable = false)
    private String nomRecipe;

    @ManyToOne
    @JoinColumn(name="idCountry")
    private Country country;

    @Column(name="calories", nullable = false)
    private double calories;

    @Column(name="difficulty", nullable = false)
    private int difficulty;

    public Recipe() {
    }

    public Recipe(Long idRecipe, String nomRecipe, Country country, double calories, int difficulty) {
        this.idRecipe = idRecipe;
        this.nomRecipe = nomRecipe;
        this.country = country;
        this.calories = calories;
        this.difficulty = difficulty;
    }

    // get and set

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}