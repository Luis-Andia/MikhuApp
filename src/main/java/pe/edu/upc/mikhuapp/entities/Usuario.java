package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id_Usuario;

    @Column(name="Id_Rol", nullable = false)
    private int Id_Rol;

    @Column(name="Contrasena", length = 20, nullable = false)
    private String Contrasena;

    @Column(name="Nom_Usuario", length = 40, nullable = false)
    public String Nom_Usuario;

    @Column(name="Ape_Pat_Usuario", length = 15, nullable = false)
    public String Ape_Pat_Usuario;

    @Column(name="Edad", nullable = false)
    private int Edad;

    @Column(name="Id_Rol", length = 30, nullable = false)
    private String Correo;

    @Column(name="Id_Familia", nullable = true)
    private int Id_Familia;

    @Column(name="Id_Pais", nullable = false)
    private int Id_Pais;

    // Constructor vacio
    public Usuario() {
    }

    // Constructor de los atributos de la clase
    public Usuario(long id_Usuario, int id_Rol, String contrasena, String nom_Usuario, String ape_Pat_Usuario, int edad, String correo, int id_Familia, int id_Pais) {
        Id_Usuario = id_Usuario;
        Id_Rol = id_Rol;
        Contrasena = contrasena;
        Nom_Usuario = nom_Usuario;
        Ape_Pat_Usuario = ape_Pat_Usuario;
        Edad = edad;
        Correo = correo;
        Id_Familia = id_Familia;
        Id_Pais = id_Pais;
    }

    // Get y Set
    public long getId_Usuario() {
        return Id_Usuario;
    }

    public void setId_Usuario(long id_Usuario) {
        Id_Usuario = id_Usuario;
    }

    public int getId_Rol() {
        return Id_Rol;
    }

    public void setId_Rol(int id_Rol) {
        Id_Rol = id_Rol;
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

    public int getId_Familia() {
        return Id_Familia;
    }

    public void setId_Familia(int id_Familia) {
        Id_Familia = id_Familia;
    }

    public int getId_Pais() {
        return Id_Pais;
    }

    public void setId_Pais(int id_Pais) {
        Id_Pais = id_Pais;
    }
}