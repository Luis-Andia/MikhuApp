package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name="idRol")
    private Rol rol;

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

    @ManyToOne
    @JoinColumn(name="idFamilia")
    private Familia familia;

    @ManyToOne
    @JoinColumn(name="idPais")
    private Pais pais;

    // Constructores

    public Usuario() {
    }

    public Usuario(Long idUsuario, Rol rol, String contrasena, String nomUsuario, String apePatUsuario, int edad, String correo, Familia familia, Pais pais) {
        this.idUsuario = idUsuario;
        this.rol = rol;
        this.contrasena = contrasena;
        this.nomUsuario = nomUsuario;
        this.apePatUsuario = apePatUsuario;
        this.edad = edad;
        this.correo = correo;
        this.familia = familia;
        this.pais = pais;
    }

    // Get an SET

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}