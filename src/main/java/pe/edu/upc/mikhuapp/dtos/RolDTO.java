package pe.edu.upc.mikhuapp.dtos;


import jakarta.validation.constraints.NotBlank;

public class RolDTO {
    private Long idRol;

    @NotBlank(message = "El nombre del rol es obligatorio")
    private String nomRol;

    // Get and set
    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNomRol() {
        return nomRol;
    }

    public void setNomRol(String nomRol) {
        this.nomRol = nomRol;
    }
}
