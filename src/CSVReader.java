import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    static List<String[]> csvData = new ArrayList<>();

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
            System.out.println(e.getMessage());
        }
    }

    /*
    Filtert den User Input
     */
    public static List<String[]> filterEntry(String input) {
        //Input ist in Array enthalten
        String userInput = input.toLowerCase();

        List<String[]> result = new ArrayList<>();
        for (int i = 0; i < csvData.size(); i++) {
            String dataEntryLowercase = csvData.get(i)[0].toLowerCase();

            if (input.contains("*")) {
                //WILDCARD
                //TODO nachname und geburtsdatum hinzufügen
                //TODO  % an % Wildcard vorne und hinten
                userInput = input.substring(0, input.length()-1);
                if (dataEntryLowercase.contains(userInput)) {
                    result.add(csvData.get(i));
                }
            } else {
                //Keine Wildcard
                if (dataEntryLowercase.equalsIgnoreCase(userInput)) {
                    result.add(csvData.get(i));
                }
            }
        }
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
                    if (kopfzeile[i].equals("Telefon-Nr.") && !result.get(k)[i].isEmpty()) {
                        result.get(k)[i] = result.get(k)[i].replace("/", ""); //empty character
                        //0123 123 123 12
                        result.get(k)[i] = result.get(k)[i].substring(0, 4) + " " +
                                result.get(k)[i].substring(4, 7) + " " +
                                result.get(k)[i].substring(7, 10) + " " +
                                result.get(k)[i].substring(10, result.get(k)[i].length());
                    }
                    //Spalte + PersonInfo
                    System.out.println(kopfzeile[i] + ": " + result.get(k)[i]);
                } catch (ArrayIndexOutOfBoundsException ignored) {}

            }
            System.out.println("\n");
        }

    }

    public static List<Person> loadCSVtoPerson(String path) {
        List<Person> personen = new ArrayList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path));
            sc.useDelimiter(";");
            sc.nextLine();  //Header ueberspringen

            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");

                try {
                    String vorname = data[0];
                    String nachname = data[1];
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    Date geburtstag = dateFormat.parse(data[2]);
                    String geschlecht = data[3];
                    String telefon = data[4];
                    String bundesland = data[5];
                    String stadt = data[6];
                    String plz = data[7];
                    String hausnummer = data[8];
                    String addresszusatz = data[9];

                    personen.add(new Person(vorname, nachname, geburtstag, geschlecht, telefon, bundesland, stadt, plz, hausnummer, addresszusatz));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return personen;
    }
}
