package pe.edu.upc.mikhuapp.dtos;

import pe.edu.upc.mikhuapp.entities.Difficulty;
import pe.edu.upc.mikhuapp.entities.Country;

public class RecipeDTOList {

    private Long idReceta;
    private String nomReceta;
    private Country country;
    private Integer calorias;
    private Difficulty difficulty;
    private byte[] imagen;

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

    public Country getPais() {
        return country;
    }

    public void setPais(Country country) {
        this.country = country;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Difficulty getDificultad() {
        return difficulty;
    }

    public void setDificultad(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
