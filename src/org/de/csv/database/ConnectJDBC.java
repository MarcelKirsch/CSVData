package org.de.csv.database;

import org.de.csv.reader.CsvReader;

import java.sql.*;
import java.util.List;

public class ConnectJDBC {

    public static void main(String[] args) {

        String connectionUrl =
                "jdbc:sqlserver://192.168.2.107:1433;"
                        + "database=CSVData;"
                        + "user=mk;"
                        + "password=mkmkmk;"
                        + "encrypt=false;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            ResultSet rs = null;

            /* Prozedur in Datenbank */
            String createTablePerson = "EXEC Create_Table_Person";  //Creates Table
            statement.addBatch(createTablePerson);

            //CSV Speichern
            List<String[]> csvData = CsvReader.getCsvData("input.csv");
            String headerStatement = "EXEC Insert_Data_Person ";
            String insertPersonData = null;
            if (csvData.size() > 1) {   //mehr als 1 Eintrag
                StringBuilder sb = new StringBuilder();
                for (String[] s : csvData) {
                    sb.append(headerStatement).append(
                            "@Vorname = " +          "'" + (s[0]) + "', " +
                            "@Nachname = " +          "'" + (s[1]) + "', " +
                            "@Geburtsdatum = " +      "'" + (s[2]) + "', " +
                            "@Geschlecht = " +        "'" + (s[3]) + "', " +
                            "@Telefonnummer = " +     "'" + (s[4]) + "', " +
                            "@Bundesland = " +        "'" + (s[5]) + "', " +
                            "@Stadt = " +             "'" + (s[6]) + "', " +
                            "@PLZ = " +               "'" + (s[7]) + "', " +
                            "@Strasse = " +           "'" + (s[8]) + "', " +
                            "@Hausnummer = " +        "'" + (s[9]) + "'");

                    //Wenn Adresszusatz vorhanden ist
                    if (s.length == 11)
                        sb.append(", @Adresszusatz = " + "'" + (s[10]) + "'" + "; \n");
                    else
                        sb.append(", @Adresszusatz = " + "''" + "; \n");
                }
                insertPersonData = sb.toString();
                insertPersonData = sb.substring(0, sb.length() - 2);   //letzte Komma wird entfernt
            }

            statement.addBatch(insertPersonData);

            statement.executeBatch();

            statement.close();
            connection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
