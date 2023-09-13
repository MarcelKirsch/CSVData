package org.de.csv.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.de.csv.person.PersonEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JdbcTest {
    private final static String URL = "jdbc:sqlserver://192.168.2.107:1433;database=CSVData;user=mk;password=mkmkmk;encrypt=false;trustServerCertificate=false;loginTimeout=30;";

    public JdbcTest() throws SQLException {
        Connection c = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver found");
            System.out.println("Connecting...");
            c = DriverManager.getConnection(URL);
            System.out.println("Verbindung hergestellt");

            EntityTransaction transaction = null;
            try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
                 EntityManager entityManager = emf.createEntityManager()) {
                Class b = Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                transaction = entityManager.getTransaction();
                transaction.begin();

                PersonEntity student = new PersonEntity();
                student.setVorname("Alex");
                student.setNachname("Minova");
                student.setGeburtsdatum(LocalDate.parse("2015-02-20"));
                student.setGeschlecht("m");
                student.setTelefonnummer("0176 123 123 32");

//                entityManager.persist(student);

                transaction.commit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                transaction.rollback();
            }

        } catch (Exception e) {
            System.out.println("Cannot connect the database");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public static void main(String[] args) {

        try {
            JdbcTest t = new JdbcTest();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
