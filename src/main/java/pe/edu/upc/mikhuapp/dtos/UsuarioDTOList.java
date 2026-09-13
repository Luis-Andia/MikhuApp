package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDTOList {
    private Long Id_Usuario;
    private Long Id_Rol;
    public String Nom_Usuario;
    public String Ape_Pat_Usuario;
    private String Correo;
    private Long Id_Familia;
    private Long Id_Pais;

    public Long getId_Usuario() {
        return Id_Usuario;
    }

    public void setId_Usuario(Long id_Usuario) {
        Id_Usuario = id_Usuario;
    }

    public Long getId_Rol() {
        return Id_Rol;
    }

    public void setId_Rol(Long id_Rol) {
        Id_Rol = id_Rol;
    }

    public String getNom_Usuario() {
        return Nom_Usuario;
    }

    public void setNom_Usuario(String nom_Usuario) {
        Nom_Usuario = nom_Usuario;
    }

    public String getApe_Pat_Usuario() {
        return Ape_Pat_Usuario;
    }

    public void setApe_Pat_Usuario(String ape_Pat_Usuario) {
        Ape_Pat_Usuario = ape_Pat_Usuario;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public Long getId_Familia() {
        return Id_Familia;
    }

    public void setId_Familia(Long id_Familia) {
        Id_Familia = id_Familia;
    }

    public Long getId_Pais() {
        return Id_Pais;
    }

    public void setId_Pais(Long id_Pais) {
        Id_Pais = id_Pais;
    }
}
