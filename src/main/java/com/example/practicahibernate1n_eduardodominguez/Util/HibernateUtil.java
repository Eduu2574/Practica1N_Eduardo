package com.example.practicahibernate1n_eduardodominguez.Util;

import com.example.practicahibernate1n_eduardodominguez.Model.Coche;
import com.example.practicahibernate1n_eduardodominguez.Model.Multa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    static SessionFactory factory = null;  // Se declara una variable estática de tipo SessionFactory, inicializada como nula.

    static {
        Configuration cfg = new Configuration();  // Se crea una nueva instancia de Configuration para configurar Hibernate.
        cfg.configure("Configuration/hibernate.cfg.xml");  // Se carga la configuración de Hibernate desde un archivo XML especificado.

        // AGREGO AMBAS CLASES:
        cfg.addAnnotatedClass(Coche.class);
        cfg.addAnnotatedClass(Multa.class);

        factory = cfg.buildSessionFactory();  // Se construye la SessionFactory a partir de la configuración proporcionada.
    }

    public static SessionFactory getSessionFactory() {  // Método estático para obtener la SessionFactory.
        return factory;  // Se retorna la instancia de SessionFactory.
    }

    public static Session getSession() {  // Método estático para abrir una nueva sesión de Hibernate.
        return factory.openSession();  // Se retorna una nueva sesión creada a partir de la SessionFactory.
    }
}