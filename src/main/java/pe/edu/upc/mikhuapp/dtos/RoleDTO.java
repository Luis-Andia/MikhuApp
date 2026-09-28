package pe.edu.upc.mikhuapp.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class RoleDTO {
    private Long idRol;

    @NotBlank(message = "El nombre del rol es obligatorio")
    private String rol;
    @Positive(message = "El id del usuario es obligatorio")
    private Long idUser;

    // Get and set
    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
