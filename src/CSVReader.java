import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    static List<String[]> csvData = new ArrayList<>();  //lokale speicherung
    static String[] kopfzeile;

    /*
    Lädt die CSV als Array in ArrayList
     */
    public static void loadCSV(String path) {
        Scanner sc = null;
        String[] st = null;
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
                    if (kopfzeile[i].equals("Telefon-Nr.")) {
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
}
