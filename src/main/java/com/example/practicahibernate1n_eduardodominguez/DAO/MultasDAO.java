package com.example.practicahibernate1n_eduardodominguez.DAO;

import com.example.practicahibernate1n_eduardodominguez.Model.Coche;
import com.example.practicahibernate1n_eduardodominguez.Model.Multa;
import com.example.practicahibernate1n_eduardodominguez.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MultasDAO implements MultasDAOImpl {

    @Override
    public List<Multa> obtenerMulta(Coche coche) {
        // Declaro la transacción que se utilizará para gestionar la operación en la base de datos.
        Transaction transaction = null;

        // Inicializo una lista vacía de 'Multa' que será devuelta al final del método.
        List<Multa> multas = new ArrayList<>();

        try (Session session = HibernateUtil.getSession()) {
            // Abro una nueva transacción con la sesión de Hibernate.
            transaction = session.beginTransaction();

            // Realizo una consulta para obtener las multas que tiene un coche filtrando por la matrícula del coche
            multas = session.createQuery("FROM Multa WHERE matricula = :matricula", Multa.class)
                    .setParameter("matricula", coche.getMatricula())
                    .list();

            // Si la consulta se ejecuta correctamente, confirmo la transacción.
            transaction.commit();

        } catch (Exception e) {
            // Si ocurre algún error durante la consulta o la transacción, hago un rollback para deshacer cualquier cambio.
            if (transaction != null) {
                transaction.rollback();
            }
            // Muestro un mensaje de error de la excpción
            System.err.println("Error al mostrar las multas: " + e.getMessage());
        }
        // Devuelvo la lista de multas (puede estar vacía si no se encontraron multas).
        return multas;
    }

    @Override
    public boolean insertarMulta(Multa multa) {
        boolean insertado = false; // Variable para verificar si la multa fue insertada exitosamente
        Transaction transaction = null; // Inicializa la transacción como nula para asegurar que pueda controlarse su estado

        try (Session session = HibernateUtil.getSession()) { // Hago try-catch para abrir una sesión de Hibernate, que se cerrará automáticamente al salir del bloque
            transaction = session.beginTransaction(); // Inicio una nueva transacción
            session.save(multa); // Guardo el objeto multa en la base de datos
            transaction.commit(); // Si la inserción es exitosa, se confirma la transacción (commit)
            insertado = true; // Marca la variable insertado como verdadera, indicando que la operación fue exitosa
        } catch (Exception e) { // Captura cualquier excepción que pueda ocurrir durante la inserción
            if (transaction != null)
                transaction.rollback(); // Si ocurre alguna excepción, realiza un rollback para deshacer la transacción
            System.err.println("No se ha podido insertar la multa" + e.getMessage()); // Muestro un mensaje de error con el detalle de la excepción
        }
        return insertado; // Retorna el estado de la inserción
    }

    public boolean actualizarMulta(Multa multa) {
        boolean actualizado = false; // Variable para verificar si la multa fue actualizada correctamente
        Transaction transaction = null; // Inicializa la transacción como nula para poder controlarla más adelante

        try (Session session = HibernateUtil.getSession()) { // Hago try-catch para abrir una sesión de Hibernate, que se cerrará automáticamente al salir del bloque
            transaction = session.beginTransaction(); // Inicia una transacción en la sesión actual
            session.saveOrUpdate(multa); // Actualiza el objeto multa en la base de datos con los valores nuevos
            transaction.commit(); // Si la actualización es exitosa, confirma la transacción (commit)
            actualizado = true; // Marca la variable actualizado como verdadera, indicando que la operación fue exitosa
        } catch (Exception e) { // Captura cualquier excepción que ocurra durante la actualización
            if (transaction != null)
                transaction.rollback(); // Si ocurre una excepción, realiza un rollback para deshacer la transacción
            System.err.println("No se ha podido actualizar la multa" + e.getMessage()); // Imprime un mensaje de error con los detalles de la excepción
        }
        return actualizado; // Devuelve el estado de la actualización
    }

    @Override
    public boolean borrarMulta(Multa multa) {
        boolean eliminado = false; // Variable para verificar si el coche fue eliminado correctamente
        Transaction transaction = null; // Inicializa la transacción como nula para poder controlarla luego

        try (Session session = HibernateUtil.getSession()) { // Hago try-catch para abrir una sesión de Hibernate, que se cerrará automáticamente al salir del bloque
            transaction = session.beginTransaction(); // Inicia una nueva transacción en la sesión actual
            session.delete(multa); // Elimina el objeto multa de la base de datos
            transaction.commit(); // Si la eliminación es exitosa, confirma la transacción (commit)
            eliminado = true; // Marca la variable eliminado como verdadera, indicando que la operación fue exitosa
        } catch (Exception e) { // Captura cualquier excepción que ocurra durante la eliminación
            if (transaction != null)
                transaction.rollback(); // Si ocurre una excepción, realiza un rollback para deshacer la transacción
            System.err.println("No se ha podido eliminar la multa" + e.getMessage()); // Imprime un mensaje de error con los detalles de la excepción
        }
        return eliminado; // Retorna el estado de la eliminación
    }

}