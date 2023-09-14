package org.de.csv;

import org.de.csv.interaction.ConsoleInput;
import org.de.csv.reader.CsvReader;

public class Starter {

    public static void main(String[] args) {

//        manuelTransfer db = new manuelTransfer();   //Datenbank und CSV Speicherung manuell
//        JpaTransfer jpadb = new JpaTransfer();      //Speicherung über JPA


        //Alte Anwendung
        CsvReader.loadCSV("input.csv");
        ConsoleInput.readUserInput();

    }
}

//MS SQL Treiber
//Verbindung aufbauen
//Tabelle erzeugen: Person
//Inhalte der CSV in DB importieren
//automatisch beim Start
//auto primary key