package org.de.csv;

import org.de.csv.interaction.ConsoleInput;
import org.de.csv.reader.CsvTools;

public class Starter {

    public static void main(String[] args) {

        //Datenbank Speicherung
//        manuelTransfer db = new manuelTransfer();   //Manuelle Speicherung
//        JpaTransfer jpadb = new JpaTransfer();      //JPA Speicherung


        //Personen Suche
        CsvTools.loadCSV("input.csv");
        ConsoleInput.readUserInput();

    }
}