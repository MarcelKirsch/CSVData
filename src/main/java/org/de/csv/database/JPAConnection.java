package org.de.csv.database;

import jakarta.persistence.*;
import org.de.csv.person.PersonEntity;
import org.de.csv.reader.CsvReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JpaTransfer {

    public static void main(String[] args) {

        EntityTransaction transaction = null;

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
             EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();


            //Tabellen erstellen
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("Create_Table_Person");
            query.execute();

            //CSV Data wird in Datenbank persistiert
            List<PersonEntity> pe = createPerson(CsvReader.getCsvData("input.csv"));
            for (PersonEntity p : pe) {
                try {

                    String sql = "SELECT * FROM Person WHERE vorname = :vorname AND nachname = :nachname AND geburtsdatum = :geburtsdatum";

                    List<PersonEntity> results = entityManager.createNativeQuery(sql)
                            .setParameter("vorname", p.getVorname())
                            .setParameter("nachname", p.getNachname())
                            .setParameter("geburtsdatum", p.getGeburtsdatum())
                            .getResultList();

                    if (results.isEmpty()) {
                        entityManager.persist(p);   //Daten persistieren
                        System.out.println(p.getVorname() + " " + p.getNachname() + " wurde erstellt.");
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            transaction.rollback();
        }
    }

    //Erstellt PersonEntities
    private static List<PersonEntity> createPerson(List<String[]> csvData) {
        List<PersonEntity> personen = new ArrayList<>();
        for (String[] s : csvData) {
            PersonEntity p = new PersonEntity();
            p.setVorname(s[0]);
            p.setNachname(s[1]);
            p.setGeburtsdatum(LocalDate.parse(s[2], DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            p.setGeschlecht(s[3]);
            p.setTelefonnummer(s[4]);
            p.setBundesland(s[5]);
            p.setStadt(s[6]);
            p.setPlz(s[7]);
            p.setStrasse(s[8]);
            p.setHausnummer(s[9]);
            if (s.length == 11)
                p.setAdresszusatz(s[10]);
            personen.add(p);
        }
        return personen;
    }
}

