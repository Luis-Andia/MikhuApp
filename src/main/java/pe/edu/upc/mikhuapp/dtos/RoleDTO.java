package pe.edu.upc.mikhuapp.dtos;


import jakarta.validation.constraints.NotBlank;

public class RoleDTO {
    private Long idRol;

    @NotBlank(message = "El nombre del rol es obligatorio")
    private String rol;

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
}
