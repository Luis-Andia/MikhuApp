package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;


public class UserDTOInsert {
    private Long idUser;

    @NotNull(message = "Id de Rol obligatorio")
    private List<Long> idRol;
    @NotBlank(message = "Contraseña obligatoria")
    private String password;
    @NotBlank(message = "Nombre de usuario obligatorio")
    public String nomUser;
    @NotBlank(message = "Apellido paterno obligatorio")
    public String lastNameUser;
    @Positive(message = "Edad invalida")
    private int age;
    @Email(message = "Formato de correo obligatorio")
    private String email;
    private Long idFamily;
    @NotNull(message = "Id de Pais obligatorio")
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
