package org.de.csv.reader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InputFilter {

    private InputFilter() {}

    public static List<String[]> filterByBeginning(String userInput, List<String[]> csvData) {
        List<String[]> result = new ArrayList<>();
        String userInputLowercase = userInput.toLowerCase();

        //Wildcard entfernen
        userInputLowercase = userInputLowercase.substring(1, userInputLowercase.length());

        for (String[] s : csvData) {
            boolean vornameEndsWithInput = s[0].toLowerCase().endsWith(userInputLowercase);
            boolean nachnameEndsWithInput = s[1].toLowerCase().endsWith(userInputLowercase);

            if (vornameEndsWithInput || nachnameEndsWithInput) {
                result.add(s);
            }
        }
        return result;
    }

    public static List<String[]> filterByEnd(String userInput, List<String[]> csvData) {
        List<String[]> result = new ArrayList<>();
        String userInputLowercase = userInput.toLowerCase();

        //Wildcard entfernen
        userInputLowercase = userInputLowercase.substring(0, userInputLowercase.length()-1);

        for (String[] s : csvData) {
            boolean vornameStartsWithInput = s[0].toLowerCase().startsWith(userInputLowercase);
            boolean nachnameStartsWithInput = s[1].toLowerCase().startsWith(userInputLowercase);

            if (vornameStartsWithInput || nachnameStartsWithInput) {
                result.add(s);
            }
        }
        return result;
    }

    public static List<String[]> filterByStartAndEnd(String userInput, List<String[]> csvData) {
        List<String[]> result = new ArrayList<>();
        String userInputLowercase = userInput.toLowerCase();

        //Wildcard entfernen
        userInputLowercase = userInputLowercase.substring(1, userInputLowercase.length()-1);

        for (String[] s : csvData) {
            boolean vornameContainsInput = s[0].toLowerCase().contains(userInputLowercase);
            boolean nachnameContainsInput = s[1].toLowerCase().contains(userInputLowercase);

            if (vornameContainsInput || nachnameContainsInput) {
                result.add(s);
            }
        }
        return result;
    }

    public static List<String[]> filterByBirthday (String userInput, List<String[]> csvData) {
        List<String[]> result = new ArrayList<>();
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
        } catch (ParseException ignored) {
            //Kein Geburtsdatum
        }
        return result;
    }

}
