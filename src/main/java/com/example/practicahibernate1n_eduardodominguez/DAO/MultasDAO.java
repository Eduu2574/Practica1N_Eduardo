package com.example.practicahibernate1n_eduardodominguez.DAO;

import com.example.practicahibernate1n_eduardodominguez.Model.Coche;
import com.example.practicahibernate1n_eduardodominguez.Model.Multa;
import com.example.practicahibernate1n_eduardodominguez.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MultasDAO{
    /*

    // Variable estática que almacena la única instancia de la clase CocheDAO
    private static MultasDAO instance = new MultasDAO();

    // Constructor privado para evitar la creación de nuevas instancias desde fuera de la clase
    private MultasDAO() {
    }

    // Método público estático para acceder a la instancia única de la clase
    public static MultasDAO getInstance() {
        // Devuelve la instancia creada de CocheDAO
        return instance;
    }

    @Override
    public List<Multa> obtenerMulta() {
        Transaction transaction = null;
        List<Multa> multas = new ArrayList<>();
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            multas = session.createQuery("from multas", Multa.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.err.println("Error al mostrar coches: " + e.getMessage());
            }
        }
        return multas;
    }

    @Override
    public boolean insertarMulta(Multa multa) {
        return false;
    }

    @Override
    public boolean actualizarMulta(Multa multa) {
        return false;
    }

    @Override
    public boolean borrarMulta(Multa multa) {
        return false;
    }

     */
}