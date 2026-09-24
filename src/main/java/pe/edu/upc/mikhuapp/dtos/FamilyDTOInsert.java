package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;

public class FamilyDTOInsert {

    private Long idFamily;

    @NotBlank(message = "El nombre es obligatorio")
    private String nomFamily;

    @NotBlank(message = "La contraseña es obligatoria")
    private String passwordFamily;


    // Get and set

    public Long getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(Long idFamily) {
        this.idFamily = idFamily;
    }

    public String getNomFamily() {
        return nomFamily;
    }

    public void setNomFamily(String nomFamily) {
        this.nomFamily = nomFamily;
    }

    public String getPasswordFamily() {
        return passwordFamily;
    }

    public void setPasswordFamily(String passwordFamily) {
        this.passwordFamily = passwordFamily;
    }
}
