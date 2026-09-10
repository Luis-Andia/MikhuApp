package pe.edu.upc.mikhuapp.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class PaisDTOInsert {
    private Long Id_Pais;
    @NotBlank(message = "El codigo del pais no puede estar vacio")
    private String Cod_Pais;
    @NotBlank(message = "El nombre del pais no puede estar vacio")
    private String Nom_Pais;

    public Long getId_Pais() {
        return Id_Pais;
    }

    public void setId_Pais(Long id_Pais) {
        Id_Pais = id_Pais;
    }

    public String getCod_Pais() {
        return Cod_Pais;
    }

    public void setCod_Pais(String cod_Pais) {
        Cod_Pais = cod_Pais;
    }

    public String getNom_Pais() {
        return Nom_Pais;
    }

    public void setNom_Pais(String nom_Pais) {
        Nom_Pais = nom_Pais;
    }
}
