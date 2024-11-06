package com.example.practicahibernate1n_eduardodominguez.Model;


import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity  // Indica que esta clase representa una entidad que se mapea a una tabla en la base de datos.
    @Table(name = "multas") // Especifica el nombre de la tabla en la base de datos que esta clase representa
public class Coche {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "tipo")
    private String tipo;

    @OneToMany(mappedBy = "coche", cascade = CascadeType.ALL)
    private List<Multa> multas;//un arma puede ser utilizada  por muchos personajes


    // Constructores


    public Coche( String matricula, String marca, String modelo, String tipo) {
        super();
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public Coche() {
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Multa> getMultas() {
        return multas;
    }

    public void setMultas(List<Multa> multas) {
        this.multas = multas;
    }
}