package com.example.practicahibernate1n_eduardodominguez.DAO;

import com.example.practicahibernate1n_eduardodominguez.Model.Coche;

import java.util.List;

public interface CocheDAOImpl {
    public List<Coche> obtenerCoche(); // Método que obtiene y retorna una lista de objetos coche desde la base de datos

    public boolean insertarCoche(Coche coche); // Método para insertar un nuevo coche en la base de datos

    public boolean borrarCoche(Coche coche); // Método para elimina un coche de la base de datos

    public boolean actualizarCoche(Coche coche); // Método para actualiza un coche en la base de datos

    public boolean verificarExisteMatricula(String matricula); // Método que verifica si una matrícula ya existe en la base de datos
}