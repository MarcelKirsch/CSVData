public class OrdinalNumbers {

    private static String[] ordinalNumbers = {"Erster", "Zweiter", "Dritter", "Vierter", "F\u00FCnfter",
            "Sechster", "Siebter", "Achter", "Neunter", "Zehnter" +
            "Elfter", "Zw\u00F6lfter", "Dreizehnter", "Vierzehnter", "F\u00FCnfzehnter", "Sechszehnter",
            "Siebzehnter", "Achtzehnter", "Neunzehnter", "Zwanzigster", "Einundzwangzigster",
            "Zweiundzwangzigster", "Dreiundzwangzigster", "Vierundzwangzigster", "F\u00FCnfundzwangzigster",
            "Sechsundzwangzigster", "Siebenundzwangzigster", "Achtundzwangzigster", "Neunundzwangzigster",
            "Drei\u00DFigster", "Einunddrei\u00DFigster", "Zweiunddrei\u00DFigster", "Dreiunddrei\u00DFigster",
            "Vierunddrei\u00DFigster", "F\u00FCnfunddrei\u00DFigster", "Sechsunddrei\u00DFigster",
            "Siebenunddrei\u00DFigster", "Achtunddrei\u00DFigster", "Neununddrei\u00DFigster", "Vierzigster",
            "Einundvierzigster", "Zweiundvierzigster", "Dreiundvierzigster", "Vierundvierzigster", "F\u00FCnfundvierzigster",
            "Sechsundvierzigster", "Siebenundvierzigster", "Achtundvierzigster", "Neunundvierzigster", "F\u00FCnfzigster",
            "Einundf\u00FCnfzigster", "Zweiundf\u00FCnfzigster", "Dreiundf\u00FCnfzigster", "Vierundf\u00FCnfzigster", "Sechsundf\u00FCnfzigster",
            "Siebenundf\u00FCnfzigster", "Achtundf\u00FCnfzigster", "Neunundf\u00FCnfzigster", "Sechszig", "Einundsechszig"};

    private static String[] einstelligeZahlen = {"", "Erster", "Zweiter", "Dritter", "Vierter", "F\u00FCnfter",
            "Sechster", "Siebter", "Achter", "Neunter", "Zehnter", "Elfter", "Zw\u00F6lfter"};

    private static String[] einstellig = {"", "Ein", "Zwei", "Drei", "Vier", "F\u00FCnf", "Sechs", "Sieben", "Acht", "Neun"};
    private static String[] zehner = {"", "Zehn", "Zwanzig", "Drei\u00DFig", "Vierzig", "F\u00FCnfzig", "Sechszig", "Siebzig", "Achtzig", "Neunzig"};

    public static String zahlenBuilder(int number) {
        String result = "";
        if (number <= 12) return einstelligeZahlen[number];
        else {
            int einserStelle = number % 10;                 //58 % 10       -> 8
            int zehnerStelle = number / 10;                 //34 / 10       -> 3
            int hundertStelle = number / 100;               //205 / 100     -> 2
            int hundertZehnerStelle = number / 10 % 10;     //407 / 10 % 10 -> 0

            StringBuilder sb = new StringBuilder();
            String zehnerLowercase = zehner[zehnerStelle].toLowerCase();
            String hundert = "hundert";


            /* HUNDERTER */
            if (hundertStelle > 0) {

                //Ein Hundert ster, Zwei Hundert ster, Drei Hundert ster, Fünf Hundert ster
                if (hundertZehnerStelle == 0) {
                    result = sb.append(einstellig[hundertStelle]).append(hundert).append("ster").toString();


                //Ein Hundert Drei, Fünf Hundert Sechs, Drei Hundert Sieben
                } else if (einserStelle != 0 ) {
                    result = sb.append(einstellig[hundertStelle]).append(hundert).append(einserStelle).append("ster").toString();


                //Drei Hundert Zwei und Dreißig, Vier Hundert Sechs und Vierzig
                } else {
                    result = sb.append(einstellig[hundertStelle]).append(hundert).append(einserStelle).append("und").append(zehnerLowercase).append("ster").toString();
                }

            /* ZEHNER */
            } else {
                //bis 20
                if (number < 20) {
                    result = sb.append(einstellig[einserStelle]).append(zehnerLowercase).append("ter").toString();


                //alles ab 20
                } else {
                    //volle zehner (30, 40, 50, 60)
                    if (number % 10 == 0) {
                        result = sb.append(einstellig[einserStelle]).append(zehner[zehnerStelle]).append("ster").toString();
                    } else {
                        //zehner mit zahl (32, 45, 58, 93)
                        result = sb.append(einstellig[einserStelle]).append("und").append(zehnerLowercase).append("ster").toString();
                    }
                }
            }
        }
        return result;
    }
}
