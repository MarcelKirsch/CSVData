package org.de.csv.database;

import org.de.csv.reader.CsvReader;

import java.sql.*;
import java.util.List;

public class manuelTransfer {

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(ConnectionURL.url);
             Statement statement = connection.createStatement()) {

            ResultSet rs = null;

            //Creates Table
            String createTablePerson = "EXEC Create_Table_Person";
            statement.addBatch(createTablePerson);

            //CSV Speichern
            String data = createDataString();
            statement.addBatch(data);

            //abschicken und beenden
            statement.executeBatch();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //String wird erstellt, der der DB geschickt wird und CSV Daten reinspeichert
    private static String createDataString() {
        List<String[]> csvData = CsvReader.getCsvData("input.csv"); //CSV laden
        String headerStatement = "EXEC Insert_Data_Person ";    //Prozedur zum Datenspeichern
        String insertDataString = null;
        //TODO wenn die csv nicht leer oder null ist
//        if (csvData.size() > 1) {   //mehr als 1 Eintrag
            StringBuilder sb = new StringBuilder();
            for (String[] s : csvData) {
                sb.append(headerStatement).append(
                        "@Vorname = " +         "'" + (s[0]) + "', " +
                        "@Nachname = " +        "'" + (s[1]) + "', " +
                        "@Geburtsdatum = " +    "'" + (s[2]) + "', " +
                        "@Geschlecht = " +      "'" + (s[3]) + "', " +
                        "@Telefonnummer = " +   "'" + (s[4]) + "', " +
                        "@Bundesland = " +      "'" + (s[5]) + "', " +
                        "@Stadt = " +           "'" + (s[6]) + "', " +
                        "@PLZ = " +             "'" + (s[7]) + "', " +
                        "@Strasse = " +         "'" + (s[8]) + "', " +
                        "@Hausnummer = " +      "'" + (s[9]) + "'");

                //Wenn Adresszusatz vorhanden ist
                if (s.length == 11)
                    sb.append(", @Adresszusatz = " + "'").append(s[10]).append("'").append("; \n");
                else
                    sb.append(", @Adresszusatz = " + "''" + "; \n");
            }
            insertDataString = sb.substring(0, sb.length() - 2);   //letzte Komma wird entfernt
//        }
        return insertDataString;
    }
}
