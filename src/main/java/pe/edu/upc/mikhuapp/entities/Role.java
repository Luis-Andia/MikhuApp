package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"idUser", "rol"})
        })
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @Column(name="rol", length = 30, nullable = false)
    private String rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser", nullable = false)
    private Users user;

    // Constructores

    public Role() {
    }

    public Role(Long idRol, String rol, Users user) {
        this.idRol = idRol;
        this.rol = rol;
        this.user = user;
    }

    // Get an SET

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
