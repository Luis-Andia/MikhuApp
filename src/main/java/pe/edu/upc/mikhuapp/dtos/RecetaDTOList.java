package pe.edu.upc.mikhuapp.dtos;

import jakarta.persistence.*;
import pe.edu.upc.mikhuapp.entities.Dificultad;
import pe.edu.upc.mikhuapp.entities.Pais;

public class RecetaDTOList {

    private Long idReceta;
    private String nomReceta;
    private Pais pais;
    private Integer calorias;
    private Dificultad dificultad;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
