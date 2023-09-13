package org.de.csv.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.de.csv.person.PersonEntity;

import java.time.LocalDate;

public class JpaTransfer {

    //Verbindung aufbauen
    //Tabelle erzeugen
    //CSV Daten als Entity persistieren

    public static void main(String[] args) {

        EntityTransaction transaction = null;

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
             EntityManager entityManager = emf.createEntityManager()) {
            Class c = Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            transaction = entityManager.getTransaction();
            transaction.begin();

            PersonEntity student = new PersonEntity();
            student.setVorname("Alex");
            student.setNachname("Minova");
            student.setGeburtsdatum(LocalDate.parse("2015-02-20"));
            student.setGeschlecht("m");
            student.setTelefonnummer("0176 123 123 32");

            entityManager.persist(student);

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            transaction.rollback();
        }
    }

}

