package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idUsuario;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Role> roles = new ArrayList<>();

    @Column(name="contrasena", length = 20, nullable = false)
    private String contrasena;

    @Column(name="nomUsuario", length = 40, nullable = false)
    private String nomUsuario;

    @Column(name="apePatUsuario", length = 15, nullable = false)
    private String apePatUsuario;

    @Column(name="edad", nullable = false)
    private int edad;

    @Column(name="correo", length = 30, nullable = false)
    private String correo;

    // Nueva columna
    @Column(nullable = false)
    private Boolean enabled = true;

    @ManyToOne
    @JoinColumn(name="idFamilia")
    private Family family;

    @ManyToOne
    @JoinColumn(name="idPais")
    private Country country;

    // Constructores

    public Users() {
    }

    public Users(Long idUsuario, List<Role> roles, String contrasena, String nomUsuario, String apePatUsuario, int edad, String correo, Boolean enabled, Family family, Country country) {
        this.idUsuario = idUsuario;
        this.roles = roles;
        this.contrasena = contrasena;
        this.nomUsuario = nomUsuario;
        this.apePatUsuario = apePatUsuario;
        this.edad = edad;
        this.correo = correo;
        this.enabled = enabled;
        this.family = family;
        this.country = country;
    }
    // Get an SET

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Family getFamilia() {
        return family;
    }

    public void setFamilia(Family family) {
        this.family = family;
    }

    public Country getPais() {
        return country;
    }

    public void setPais(Country country) {
        this.country = country;
    }
}