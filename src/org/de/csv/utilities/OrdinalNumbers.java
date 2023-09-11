package org.de.csv.utilities;

public class OrdinalNumbers {
    private static String[] ordinalZahlen = {"", "Erster", "Zweiter", "Dritter", "Vierter", "F\u00FCnfter",
            "Sechster", "Siebter", "Achter", "Neunter", "Zehnter", "Elfter", "Zw\u00F6lfter"};

    private static String[] einstelligeZahlen = {"", "Ein", "Zwei", "Drei", "Vier", "F\u00FCnf", "Sechs", "Sieben", "Acht", "Neun"};
    private static String[] zehnerZahlen = {"", "Zehn", "Zwanzig", "Drei\u00DFig", "Vierzig", "F\u00FCnfzig", "Sechszig", "Siebzig", "Achtzig", "Neunzig"};

    private OrdinalNumbers() {}
    public static String zahlenBuilder(int number) {
        String result = "";
        number += 1;

        /* Zahlen bis 12 */
        if (number < ordinalZahlen.length) return ordinalZahlen[number];

        if (String.valueOf(number).length() == 3)   //Hundert
            return processHundret(number);

        if (String.valueOf(number).length() < 3)    //Zehner
            return processTens(number);

        return result;
    }


    private static String processHundret (int number) {
        StringBuilder sb = new StringBuilder();
        String result = "";
        String hundert = "hundert";

        int hundertStelle = number / 100;               //205 / 100     -> 2
        int hundertZehnerStelle = number / 10 % 10;     //123 / 10 % 10 -> 2
        int einserStelle = number % 10;                 //58 % 10       -> 8

        char atHundret = String.valueOf(number).charAt(0);
        char atTen = String.valueOf(number).charAt(1);
        char atOne = String.valueOf(number).charAt(2);

        //Ein Hundert ster, Zwei Hundert ster, Drei Hundert ster, Fünf Hundert ster
        String einserStelleLowercase = ordinalZahlen[einserStelle].toLowerCase();   //zweiter, vierter

        //200, 500, 600
        //Volle Hunderter
        if (atHundret != '0' && atTen == '0' && atOne == '0') {
            result = sb.append(einstelligeZahlen[hundertStelle])
                    .append(hundert)
                    .append("ster").toString();

            //450, 120, 690, 410
            //Alles ohne Einser
        } else if (atHundret != '0' && atTen != '0' && atOne == '0') {
            result = sb.append(einstelligeZahlen[hundertStelle])
                    .append(hundert)
                    .append(zehnerZahlen[hundertZehnerStelle].toLowerCase())
                    .append("ster").toString();

            //103, 506, 409, 504
            //Alles ohne Zehner
        } else if (atHundret != '0' && atTen == '0' && atOne != '0') {
            result = sb.append(einstelligeZahlen[hundertStelle])
                    .append(hundert).append("und")
                    .append(einserStelleLowercase).toString();

            //Drei Hundert Zwölf ter, Zwei Hundert Elf ter
            //Elf und Zwölf Sonderfälle
        } else if (number % 100 <= 12 && number % 100 >= 11) {
            int dezimalZahl = number % 100;
            result = sb.append(einstelligeZahlen[hundertStelle])
                    .append(hundert)
                    .append(ordinalZahlen[dezimalZahl].toLowerCase()).toString();

            // Hundert Drei Zehn ter, Hundert Fünf Zehn ter, Hundert Sieb Zehn ter
            //Zahlen von Hunder Drei Zehn ter bis Neun Zehn ter
        } else if (number % 100 > 12 && number % 100 < 20) {
            result = sb.append(einstelligeZahlen[hundertStelle])
                    .append(hundert)
                    .append(einstelligeZahlen[einserStelle].toLowerCase())
                    .append(zehnerZahlen[hundertZehnerStelle].toLowerCase())
                    .append("ter").toString();

            //Drei Hundert Zwei und Dreißig ster, Vier Hundert Sechs und Vierzig ster
            //323, 674, 215
        } else {
            result = sb.append(einstelligeZahlen[hundertStelle])
                    .append(hundert)
                    .append(einstelligeZahlen[einserStelle].toLowerCase())
                    .append("und")
                    .append(zehnerZahlen[hundertZehnerStelle].toLowerCase())
                    .append("ter").toString();
        }
        return result;
    }

    private static String processTens(int number) {
        String result = "";
        StringBuilder sb = new StringBuilder();

        int zehnerStelle = number / 10;    //34 / 10 -> 3
        int einserStelle = number % 10;    //58 % 10 -> 8

        String zehnerLowercase = zehnerZahlen[zehnerStelle].toLowerCase(); //zehnter, dreißigster

        //bis 20
        if (number < 20) {
            result = sb.append(einstelligeZahlen[einserStelle])
                    .append(zehnerLowercase)
                    .append("ter").toString();

            //alles ab 20
        } else {
            //volle zehner (30, 40, 50, 60)
            if (number % 10 == 0) {
                result = sb.append(einstelligeZahlen[einserStelle])
                        .append(zehnerZahlen[zehnerStelle])
                        .append("ster").toString();
            } else {
                //zehner mit zahl (32, 45, 58, 93)
                result = sb.append(einstelligeZahlen[einserStelle])
                        .append("und")
                        .append(zehnerLowercase)
                        .append("ster").toString();
            }
        }
        return result;
    }
}
