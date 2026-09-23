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
    private Long idRole;

    @Column(name="rol", length = 30, nullable = false)
    private String roleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser", nullable = false)
    private Users user;

    // Constructores

    public Role() {
    }

    public Role(Long idRole, String roleName, Users user) {
        this.idRole = idRole;
        this.roleName = roleName;
        this.user = user;
    }

    // Get an SET

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}