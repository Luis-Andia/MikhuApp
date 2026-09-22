package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDTOList {
    private Long idUsuario;
    private Long idRol;
    public String nomUsuario;
    public String apePatUsuario;
    private String correo;
    private int edad;
    private Long idFamilia;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() { return edad; }

    public void setEdad(int edad) { this.edad = edad; }

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
