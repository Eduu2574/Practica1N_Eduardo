package com.example.practicahibernate1n_eduardodominguez.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "multas")
public class Multa implements Serializable {
    @Id
    @Column(name = "id_multa")
    @GeneratedValue(strategy = IDENTITY)
    private int id_multa;

    @Column(name = "precio")
    private double precio;

    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "matricula")
    private String matricula;

    // Relación ManyToOne con Coche, la clave foránea es "matricula"
    @ManyToOne
    @JoinColumn(name = "matricula",referencedColumnName = "id")
    private Coche coche;

    // CONSTRUCTORES:

    // Constructor vacío. Hibernate puede mostrar algún error si no está implementado
    public Multa() {}

    public Multa(double precio, LocalDate fecha, Coche coche) {
        this.precio = precio;
        this.fecha = fecha;
        this.coche = coche;
    }

    public Multa(String matricula, double precio, LocalDate fecha) {
        this.matricula = matricula;
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