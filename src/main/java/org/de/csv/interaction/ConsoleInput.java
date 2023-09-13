package org.de.csv.interaction;

import org.de.csv.reader.CsvReader;

import java.util.List;
import java.util.Scanner;

public class ConsoleInput {

    private ConsoleInput() {}

    //Interation mit dem Benutzer
    public static void readUserInput() {
        boolean quit = true;
        Scanner sc = new Scanner(System.in);
        List<String[]> result;

        do {
            String userInput = null;

            System.out.println("Suche: ");
            userInput = sc.nextLine();
            result =  CsvReader.filterEntry(userInput); //Filtert Ergebnis
            CsvReader.printResult(result);              //Ausgabe CommandLine

            System.out.println("Weitere Suche? j/n");
            userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("j")) quit = false;
            else quit = true;

        } while (!quit);
        sc.close();
    }
}
