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

        List<String[]> result = new ArrayList<>();
        for (int i = 0; i < csvData.size(); i++) {

            //Input ist in Array enthalten
            String dataEntryLowercase = csvData.get(i)[0].toLowerCase();
            String userInput = input.toLowerCase();

            if (dataEntryLowercase.contains(userInput)) {
                result.add(csvData.get(i));
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

        for (int k = 0; k < result.size(); k++) {
            System.out.println(OrdinalNumbers.zahlenBuilder(k) + " Treffer:\n");
            //nicht kopfzeile, sondern länge des arrays
            for (int i = 0; i < 10; i++) {
                //Spalte + PersonInfo
                System.out.println(kopfzeile[i] + ": " + result.get(k)[i]);
            }
            System.out.println("\n");
        }

    }
}
