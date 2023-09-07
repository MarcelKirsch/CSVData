import java.util.List;
import java.util.Scanner;

public class ConsoleInput {

    //Interation mit dem Benutzer
    public static void readUserInput() {
        boolean quit = false;
        Scanner sc = new Scanner(System.in);
        List<String[]> result;

        do {
            String userInput = null;
            result = null;

            System.out.println("Suche: ");
            userInput = sc.nextLine();
            result =  CSVReader.filterEntry(userInput); //Filtert Ergebnis
            CSVReader.printResult(result);              //Ausgabe CommandLine

        } while(!quit);
        sc.close();
    }
}
