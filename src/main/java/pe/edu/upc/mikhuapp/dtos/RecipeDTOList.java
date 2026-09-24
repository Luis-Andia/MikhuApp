package pe.edu.upc.mikhuapp.dtos;

import pe.edu.upc.mikhuapp.entities.Country;

public class RecipeDTOList {

    private Long idReceta;
    private String nomReceta;
    private Country country;
    private Double calorias;
    private int difficulty;

    // Get and set

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public String getNomReceta() {
        return nomReceta;
    }

    public void setNomReceta(String nomReceta) {
        this.nomReceta = nomReceta;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Double getCalorias() {
        return calorias;
    }

    public void setCalorias(Double calorias) {
        this.calorias = calorias;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
