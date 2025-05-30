package mx.unam.aragon.ico.te.DeportesMVC.modelos;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "Teams")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // @Column(name ="name", nullable = false )
    private String nombre;
    private String apodo;
    private String estadio;
    private int titulosLiga;
    private String imagen;

    public Equipo() {
    }

    public Equipo(int id, String nombre, String apodo, String estadio, int titulosLiga, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.apodo = apodo;
        this.estadio = estadio;
        this.titulosLiga = titulosLiga;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public int getTitulosLiga() {
        return titulosLiga;
    }

    public void setTitulosLiga(int titulosLiga) {
        this.titulosLiga = titulosLiga;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return id == equipo.id && titulosLiga == equipo.titulosLiga && Objects.equals(nombre, equipo.nombre) && Objects.equals(apodo, equipo.apodo) && Objects.equals(estadio, equipo.estadio) && Objects.equals(imagen, equipo.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apodo, estadio, titulosLiga, imagen);
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apodo='" + apodo + '\'' +
                ", estadio='" + estadio + '\'' +
                ", titulosLiga=" + titulosLiga +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
