package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;

public class PaisDTO {
    private Long idPais;
    @NotBlank(message = "El codigo del pais no puede estar vacio")
    private String codPais;
    @NotBlank(message = "El nombre del pais no puede estar vacio")
    private String nomPais;

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    public String getNomPais() {
        return nomPais;
    }

    public void setNomPais(String nomPais) {
        this.nomPais = nomPais;
    }
}
