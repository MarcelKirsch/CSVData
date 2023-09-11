import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
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
    public static List<String[]> filterEntry(String userInput) {
        List<String[]> result = new ArrayList<>();
        String userInputLowercase = userInput.toLowerCase();

        //Wildcard Anfang & Ende
        if (userInputLowercase.startsWith("*") && userInputLowercase.endsWith("*")) {
            //Wildcard entfernen
            userInputLowercase = userInputLowercase.substring(1, userInputLowercase.length()-1);

            for (String[] s : csvData) {
                if (s[0].toLowerCase().contains(userInputLowercase)) {
                    result.add(s);
                }
            }


        //Wildcard am Anfang
        } else if (userInputLowercase.startsWith("*")) {
            //Wildcard entfernen
            userInputLowercase = userInputLowercase.substring(1, userInputLowercase.length());

            for (String[] s : csvData) {
                if (s[0].toLowerCase().endsWith(userInputLowercase) ||
                        s[1].toLowerCase().endsWith(userInputLowercase)) {
                    result.add(s);
                }
            }
        //Wildcard am Ende
        } else if (userInputLowercase.endsWith("*")) {
            //Wildcard entfernen
            userInputLowercase = userInputLowercase.substring(0, userInputLowercase.length()-1);

            for (String[] s : csvData) {
                if (s[0].toLowerCase().startsWith(userInputLowercase) ||
                        s[1].toLowerCase().startsWith(userInputLowercase)) {
                    result.add(s);
                }
            }
        //Geburtsdatum
        } else {
            try {
                //Datum parsen
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date userInputDate = dateFormat.parse(userInput);   //parse userInput to DateFormat

                //Datum einfügen
                for (String[] s : csvData) {
                    Date csvDate = dateFormat.parse(s[2]);  //csvDate zu Date
                    if (csvDate.equals(userInputDate)) {
                        result.add(s);
                    }
                }

            } catch (ParseException ignored) {}
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
