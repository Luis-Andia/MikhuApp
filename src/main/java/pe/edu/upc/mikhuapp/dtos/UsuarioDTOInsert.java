package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class UsuarioDTOInsert {
    private Long idUsuario;

    @NotNull(message = "Id de Rol obligatorio")
    private Long idRol;
    @NotBlank(message = "Contraseña obligatoria")
    private String contrasena;
    @NotBlank(message = "Nombre de usuario obligatorio")
    public String nomUsuario;
    @NotBlank(message = "Apellido paterno obligatorio")
    public String apePatUsuario;
    @NotNull(message = "Edad obligatoria")
    private int edad;
    @NotNull(message = "Correo obligatorio")
    private String correo;
    @NotNull(message = "Id de Familia obligatorio")
    private Long idFamilia;
    @NotNull(message = "Id de Pais obligatorio")
    private Long idPais;

    // Get and set
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getApePatUsuario() {
        return apePatUsuario;
    }

    public void setApePatUsuario(String apePatUsuario) {
        this.apePatUsuario = apePatUsuario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Long idFamilia) {
        this.idFamilia = idFamilia;
    }

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }
}
