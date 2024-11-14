package com.example.practicahibernate1n_eduardodominguez.DAO;

import com.example.practicahibernate1n_eduardodominguez.Model.Coche;
import com.example.practicahibernate1n_eduardodominguez.Model.Multa;

import java.util.List;

public interface MultasDAOImpl {
    public List<Multa> obtenerMulta(Coche coche); // Método que obtiene y retorna una lista de objetos coche desde la base de datos

    public boolean insertarMulta(Multa multa); // Método para insertar un nuevo coche en la base de datos

    public boolean actualizarMulta(Multa multa); // Método para actualiza un coche en la base de datos

    public boolean borrarMulta(Multa multa); // Método para elimina un coche de la base de datos
}