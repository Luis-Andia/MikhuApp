package pe.edu.upc.mikhuapp.dtos;

import jakarta.persistence.Column;

public class FamiliaDTOList {
    private Long idFamilia;
    private String nomFamilia;

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
}
