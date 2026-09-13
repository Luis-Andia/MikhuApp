package pe.edu.upc.mikhuapp.dtos;


import jakarta.validation.constraints.NotBlank;

public class RolDTO {
    private Long Id_Rol;

    @NotBlank(message = "El nombre del rol es obligatorio")
    private String Nom_Rol;

    // Get and set

    public Long getId_Rol() {
        return Id_Rol;
    }

    public void setId_Rol(Long id_Rol) {
        Id_Rol = id_Rol;
    }

    public String getNom_Rol() {
        return Nom_Rol;
    }

    public void setNom_Rol(String nom_Rol) {
        Nom_Rol = nom_Rol;
    }
}
