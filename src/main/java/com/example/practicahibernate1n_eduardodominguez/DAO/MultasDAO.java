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
            multas = session.createQuery("from Multa", Multa.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.err.println("Error al mostrar las multas" + e.getMessage());
            }
        }
        return multas;
    }

    @Override
    public boolean insertarMulta(Multa multa) {
        boolean insertado = false; // Variable para verificar si el coche fue insertado exitosamente
        Transaction transaction = null; // Inicializa la transacción como nula para asegurar que pueda controlarse su estado

        try (Session session = HibernateUtil.getSession()) { // Hago try-catch para abrir una sesión de Hibernate, que se cerrará automáticamente al salir del bloque
            transaction = session.beginTransaction(); // Inicio una nueva transacción
            session.save(multa); // Guardo el objeto coche en la base de datos
            transaction.commit(); // Si la inserción es exitosa, se confirma la transacción (commit)
            insertado = true; // Marca la variable insertado como verdadera, indicando que la operación fue exitosa
        } catch (Exception e) { // Captura cualquier excepción que pueda ocurrir durante la inserción
            if (transaction != null) transaction.rollback(); // Si ocurre alguna excepción, realiza un rollback para deshacer la transacción
            System.err.println("No se ha podido insertar la multa" + e.getMessage()); // Imprime un mensaje de error con el detalle de la excepción
        }

        return insertado; // Retorna el estado de la inserción
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

    @Override
    public boolean actualizarMulta(Multa multa) {
        return false;
    }

    @Override
    public boolean borrarMulta(Multa multa) {
        return false;
    }


}