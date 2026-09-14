package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Receta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceta;

    @Column(name="Nom_Receta", length = 100, nullable = false)
    private String nomReceta;

    @ManyToOne
    @JoinColumn(name="Id_Pais")
    private Pais pais;

    @Column(name="Calorias", nullable = false)
    private Integer calorias;

    @ManyToOne
    @JoinColumn(name="Id_Dificultad")
    private Dificultad dificultad;

    @Column(name="Imagen")
    private byte[] imagen;

    public Receta() {
    }

    public Receta(Long idReceta, String nomReceta, Pais pais,
                  Integer calorias, Dificultad dificultad, byte[] imagen) {
        this.idReceta = idReceta;
        this.nomReceta = nomReceta;
        this.pais = pais;
        this.calorias = calorias;
        this.dificultad = dificultad;
        this.imagen = imagen;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public String getNomReceta() {
        return nomReceta;
    }

    public void setNomReceta(String nomReceta) {
        this.nomReceta = nomReceta;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}