package com.example.practicahibernate1n_eduardodominguez.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity  // Indica que esta clase representa una entidad que se mapea a una tabla en la base de datos.
@Table(name = "multas") // Especifica el nombre de la tabla en la base de datos que esta clase representa

public class Multa{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_multa")
    private int id_multa;

    @Column(name = "precio")
    private double precio;

    @Column(name = "fecha")
    private LocalDate fecha;

    // Relación ManyToOne con Coche, la clave foránea es "matricula"
    @ManyToOne
    @JoinColumn(name = "matricula",referencedColumnName = "matricula")
    private Coche coche;

    // CONSTRUCTORES:

    public Multa() {

    }

    public Multa(Coche coche, double precio, LocalDate fecha) {
        this.coche = coche;
        this.precio = precio;
        this.fecha = fecha;
    }

    public Multa(double precio, LocalDate fecha) {
        this.precio = precio;
        this.fecha = fecha;
    }

    // GETTERS Y SETTERS
    public int getId_multa() {
        return id_multa;
    }

    public void setId_multa(int id_multa) {
        this.id_multa = id_multa;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    // TO STRING

    @Override
    public String toString() {
        return "Multa{" +
                "id_multa=" + id_multa +
                ", precio=" + precio +
                ", fecha=" + fecha +
                ", coche=" + coche +
                '}';
    }
}