package pe.edu.upc.mikhuapp.dtos;


import jakarta.validation.constraints.NotBlank;

public class RoleDTO {
    private Long idRole;

    @NotBlank(message = "El nombre del rol es obligatorio")
    private String rol;

    // Get and set

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
