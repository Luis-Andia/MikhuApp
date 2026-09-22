package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;

public class FamilyDTOInsert {

    private Long idFamilia;

    @NotBlank(message = "El nombre es obligatorio")
    private String nomFamilia;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasenaFamilia;


    // Get and set

    public Long getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Long idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getNomFamilia() {
        return nomFamilia;
    }

    public void setNomFamilia(String nomFamilia) {
        this.nomFamilia = nomFamilia;
    }

    public String getContrasenaFamilia() {
        return contrasenaFamilia;
    }

    public void setContrasenaFamilia(String contrasenaFamilia) {
        this.contrasenaFamilia = contrasenaFamilia;
    }
}
