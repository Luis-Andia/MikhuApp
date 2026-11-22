package pe.edu.upc.mikhuapp.dtos;

import java.util.List;

public class UserDTOList {
    private Long idUser;
    private List<Long> idRol;
    public String nomUser;
    public String lastNameUser;
    private String email;
    private Long idFamily;
    private Long idCountry;

    // Get and set
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public List<Long> getIdRol() {
        return idRol;
    }

    public void setIdRol(List<Long> idRol) {
        this.idRol = idRol;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(Long idFamily) {
        this.idFamily = idFamily;
    }

    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }
}
