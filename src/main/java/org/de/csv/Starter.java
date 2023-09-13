package org.de.csv;

import org.de.csv.database.manuelTransfer;

public class Starter {

    public static void main(String[] args) {

        manuelTransfer db = new manuelTransfer();   //Datenbank und CSV Speicherung manuell


        //Alte Anwendung
//        CsvReader.loadCSV("input.csv");
//        ConsoleInput.readUserInput();

    }
}

//MS SQL Treiber
//Verbindung aufbauen
//Tabelle erzeugen: Person
//Inhalte der CSV in DB importieren
//automatisch beim Start
//auto primary key