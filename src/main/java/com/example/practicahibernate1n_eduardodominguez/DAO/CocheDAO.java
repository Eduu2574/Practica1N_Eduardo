package com.example.practicahibernate1n_eduardodominguez.DAO;

import com.example.practicahibernate1n_eduardodominguez.Model.Coche;
import com.example.practicahibernate1n_eduardodominguez.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CocheDAO implements CocheDAOImpl {

    public boolean insertarCoche(Coche coche) {
        boolean insertado = false; // Variable para verificar si el coche fue insertado exitosamente
        Transaction transaction = null; // Inicializa la transacción como nula para asegurar que pueda controlarse su estado

        try (Session session = HibernateUtil.getSession()) { // Hago try-catch para abrir una sesión de Hibernate, que se cerrará automáticamente al salir del bloque
            transaction = session.beginTransaction(); // Inicio una nueva transacción
            session.save(coche); // Guardo el objeto coche en la base de datos
            transaction.commit(); // Si la inserción es exitosa, se confirma la transacción (commit)
            insertado = true; // Marca la variable insertado como verdadera, indicando que la operación fue exitosa
        } catch (Exception e) { // Captura cualquier excepción que pueda ocurrir durante la inserción
            if (transaction != null)
                transaction.rollback(); // Si ocurre alguna excepción, realiza un rollback para deshacer la transacción
            System.err.println("No se ha podido insertar el coche" + e.getMessage()); // Imprime un mensaje de error con el detalle de la excepción
        }
        return insertado; // Retorna el estado de la inserción
    }

    public boolean borrarCoche(Coche coche) {
        boolean eliminado = false; // Variable para verificar si el coche fue eliminado correctamente
        Transaction transaction = null; // Inicializa la transacción como nula para poder controlarla luego

        try (Session session = HibernateUtil.getSession()) { // Hago try-catch para abrir una sesión de Hibernate, que se cerrará automáticamente al salir del bloque
            transaction = session.beginTransaction(); // Inicia una nueva transacción en la sesión actual
            session.delete(coche); // Elimina el objeto coche de la base de datos
            transaction.commit(); // Si la eliminación es exitosa, confirma la transacción (commit)
            eliminado = true; // Marca la variable eliminado como verdadera, indicando que la operación fue exitosa
        } catch (Exception e) { // Captura cualquier excepción que ocurra durante la eliminación
            if (transaction != null)
                transaction.rollback(); // Si ocurre una excepción, realiza un rollback para deshacer la transacción
            System.err.println("No se ha podido eliminar el coche " + e.getMessage()); // Imprime un mensaje de error con los detalles de la excepción
        }
        return eliminado; // Retorna el estado de la eliminación (true si fue exitosa, false en caso contrario)
    }

    public boolean actualizarCoche(Coche coche) {
        boolean actualizado = false; // Variable para verificar si el coche fue actualizado correctamente
        Transaction transaction = null; // Inicializa la transacción como nula para poder controlarla más adelante

        try (Session session = HibernateUtil.getSession()) { // Hago try-catch para abrir una sesión de Hibernate, que se cerrará automáticamente al salir del bloque
            transaction = session.beginTransaction(); // Inicia una transacción en la sesión actual
            session.saveOrUpdate(coche); // Actualiza el objeto coche en la base de datos con los valores actuales
            transaction.commit(); // Si la actualización es exitosa, confirma la transacción (commit)
            actualizado = true; // Marca la variable actualizado como verdadera, indicando que la operación fue exitosa
        } catch (Exception e) { // Captura cualquier excepción que ocurra durante la actualización
            if (transaction != null)
                transaction.rollback(); // Si ocurre una excepción, realiza un rollback para deshacer la transacción
            System.err.println("No se ha podido actualizar el coche" + e.getMessage()); // Imprime un mensaje de error con los detalles de la excepción
        }
        return actualizado; // Retorna el estado de la actualización (true si fue exitosa, false en caso contrario)
    }

    public List<Coche> obtenerCoche() {

        Transaction transaction = null; // Inicializa la transacción como nula para el manejo de errores
        List<Coche> coches = new ArrayList<>(); // Crea una lista vacía para almacenar los coches obtenidos

        try (Session session = HibernateUtil.getSession()) { // Abre una sesión de Hibernate que se cierra automáticamente al finalizar
            transaction = session.beginTransaction(); // Inicia una transacción para asegurar la atomicidad de las operaciones
            coches = session.createQuery("from Coche", Coche.class).list(); // Ejecuta una consulta para obtener todos los objetos Coche de la base de datos y los almacena en la lista 'coches'
            transaction.commit(); // Confirma la transacción si la consulta se ejecuta sin errores
        } catch (Exception e) { // Captura cualquier excepción que pueda ocurrir durante la operación
            if (transaction != null) { // Verifica si la transacción fue iniciada
                transaction.rollback(); // Revierte la transacción en caso de error
                System.err.println("Error al mostrar coches: " + e.getMessage()); // Imprime un mensaje de error con los detalles de la excepción
            }
        }
        return coches; // Retorna la lista de coches obtenida (puede estar vacía si hubo un error)
    }

    public boolean verificarExisteMatricula(String matricula) { // Método que verifica si una matrícula ya existe en la base de datos
        boolean existe = false; // Inicializo la variable para indicar si la matrícula existe como falsa
        Transaction transaction = null; // Inicializo la transacción como nula
        try (Session session = HibernateUtil.getSession()) { // Abro una nueva sesión utilizando HibernateUtil
            transaction = session.beginTransaction(); // Inicio la transacción

            // Creo una consulta para buscar coches con la matrícula proporcionada
            Query<Coche> query = session.createQuery("from Coche where matricula = :matricula", Coche.class);
            query.setParameter("matricula", matricula); // Asigno el valor del parámetro "matricula" en la consulta

            // Verifico si la lista de resultados no está vacía, lo que indicaría que la matrícula existe
            existe = !query.list().isEmpty();

            transaction.commit(); // Confirmo la transacción
        } catch (Exception e) { // Capturo cualquier excepción que ocurra
            if (transaction != null) {
                transaction.rollback(); // Deshago los cambios si hubo un error
                System.err.println("Error al verificar matrícula: " + e.getMessage()); // Imprimo un mensaje de error
            }
        }
        return existe; // Retorno el estado de existencia de la matrícula
    }

}