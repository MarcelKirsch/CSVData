package org.de.csv.reader;

import org.de.csv.person.Person;
import org.de.csv.person.PersonBuilder;
import org.de.csv.utilities.OrdinalNumbers;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class CsvReader {
    private static final Logger log = Logger.getLogger(CsvReader.class.getName());

    private CsvReader() {}

    private static List<String[]> csvData = new ArrayList<>();

    static String[] kopfzeile;

    /*
    Lädt die CSV als Array in ArrayList
     */
    public static void loadCSV(String path) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path));
            sc.useDelimiter(";");

            kopfzeile = sc.nextLine().split(";");

            //Personeninfos
            while (sc.hasNextLine()) {
                csvData.add(sc.nextLine().split(";"));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            log.warning(e.getMessage());
        }
    }

    /*
    Filtert den User Input
     */
    public static List<String[]> filterEntry(String userInput) {

        boolean startsWith = userInput.toLowerCase().startsWith("*");
        boolean endsWith = userInput.toLowerCase().endsWith("*");

        List<String[]> result = new ArrayList<>();

        if (startsWith && endsWith) result = InputFilter.filterByStartAndEnd(userInput, csvData);
        if (startsWith)             result = InputFilter.filterByBeginning(userInput, csvData);
        if (endsWith)               result = InputFilter.filterByEnd(userInput, csvData);
        if (result.isEmpty())       result = InputFilter.filterByBirthday(userInput, csvData);

        return result;
    }

    /*
    gibt Ergebnis auf CommandLine aus
     */
    public static void printResult(List<String[]> result) {
        if (result.isEmpty()) {
            System.out.println("Leider wurde kein Ergebnis gefunden");
            return;
        }

        //Ausgabe der Infos
        System.out.println("****************************");
        System.out.println("Treffer: " + result.size());
        System.out.println("****************************");

        // Ausgabe
        for (int k = 0; k < result.size(); k++) {
            System.out.println(OrdinalNumbers.zahlenBuilder(k) + " Treffer:\n");
            for (int i = 0; i < kopfzeile.length; i++) {
                try {
                    //Telefonausgabe anpassen
                    if (kopfzeile[i].equals("Telefon-Nr.") &&
                            result.get(k)[i] != null && !result.get(k)[i].isEmpty()) {

                        result.get(k)[i] = result.get(k)[i].replace("/", ""); //empty character

                        //0123 123 123 12
                        result.get(k)[i] = result.get(k)[i].substring(0, 4) + " " +
                                result.get(k)[i].substring(4, 7) + " " +
                                result.get(k)[i].substring(7, 10) + " " +
                                result.get(k)[i].substring(10, result.get(k)[i].length());
                    }
                    //Spalte + PersonInfo
                    System.out.println(kopfzeile[i] + ": " + result.get(k)[i]);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                    //manchmal ist kein Adresszusatz enthalten, wird damit übersprungen
                }


            }
            System.out.println("\n");
        }

    }

    public static List<Person> loadCSVtoPerson(String path) {
        List<Person> personen = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter(";");
            sc.nextLine();  //Header ueberspringen

            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                personen.add(new PersonBuilder()
                        .setVorname(data[0])
                        .setNachname(data[1])
                        .setGeburtsDatum(dateFormat.parse(data[2]))
                        .setGeschlecht(data[3])
                        .setTelefonnr(data[4])
                        .setBundesland(data[5])
                        .setStadt(data[6])
                        .setPlz(data[7])
                        .setHausnummmer(data[8])
                        .setAdresszusatz(data[9])
                        .createPerson());
            }
        } catch (Exception e) {
            log.warning(e.getMessage());
        }

        return personen;
    }

    public List<String[]> getCSVData() { return csvData;}
}
