package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;

public class FamiliaDTOInsert {

    private Long Id_Familia;

    @NotBlank(message = "El nombre es obligatorio")
    private String Nom_Familia;

    @NotBlank(message = "La contraseña es obligatoria")
    private String Contrasena_Familia;

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

    public String getContrasena_Familia() {
        return Contrasena_Familia;
    }

    public void setContrasena_Familia(String contrasena_Familia) {
        Contrasena_Familia = contrasena_Familia;
    }
}
