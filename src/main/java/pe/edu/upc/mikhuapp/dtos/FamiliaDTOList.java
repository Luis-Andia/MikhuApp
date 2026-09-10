package pe.edu.upc.mikhuapp.dtos;

import jakarta.persistence.Column;

public class FamiliaDTOList {
    private Long Id_Familia;
    private String Nom_Familia;

    public Long getId_Familia() {
        return Id_Familia;
    }

    public void setId_Familia(Long id_Familia) {
        Id_Familia = id_Familia;
    }

    public String getNom_Familia() {
        return Nom_Familia;
    }

    public void setNom_Familia(String nom_Familia) {
        Nom_Familia = nom_Familia;
    }
}
