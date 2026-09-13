package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id_Usuario;

    @ManyToOne
    @JoinColumn(name="Id_Rol")
    private Rol rol;

    @Column(name="Contrasena", length = 20, nullable = false)
    private String Contrasena;

    @Column(name="Nom_Usuario", length = 40, nullable = false)
    public String Nom_Usuario;

    @Column(name="Ape_Pat_Usuario", length = 15, nullable = false)
    public String Ape_Pat_Usuario;

    @Column(name="Edad", nullable = false)
    private int Edad;

    @Column(name="Correo", length = 30, nullable = false)
    private String Correo;

    @ManyToOne
    @JoinColumn(name="Id_Familia")
    private Familia familia;

    @ManyToOne
    @JoinColumn(name="Id_Pais")
    private Pais pais;

    // Constructores
    public Usuario() {
    }

    public Usuario(Long id_Usuario, Rol rol, String contrasena, String nom_Usuario, String ape_Pat_Usuario, int edad, String correo, Familia familia, Pais pais) {
        Id_Usuario = id_Usuario;
        this.rol = rol;
        Contrasena = contrasena;
        Nom_Usuario = nom_Usuario;
        Ape_Pat_Usuario = ape_Pat_Usuario;
        Edad = edad;
        Correo = correo;
        this.familia = familia;
        this.pais = pais;
    }
    // Get an SET

    public Long getId_Usuario() {
        return Id_Usuario;
    }

    public void setId_Usuario(Long id_Usuario) {
        Id_Usuario = id_Usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public String getNom_Usuario() {
        return Nom_Usuario;
    }

    public void setNom_Usuario(String nom_Usuario) {
        Nom_Usuario = nom_Usuario;
    }

    public String getApe_Pat_Usuario() {
        return Ape_Pat_Usuario;
    }

    public void setApe_Pat_Usuario(String ape_Pat_Usuario) {
        Ape_Pat_Usuario = ape_Pat_Usuario;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
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