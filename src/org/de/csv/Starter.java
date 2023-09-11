package org.de.csv;

import org.de.csv.interaction.ConsoleInput;
import org.de.csv.reader.CsvReader;

public class Starter {

    public static void main(String[] args) {

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

//zuerst manuell, danach per framework (jpa/hibernate)