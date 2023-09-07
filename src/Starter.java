public class Starter {

    public static void main(String[] args) {

        CSVReader.loadCSV("input.csv");
        ConsoleInput.readUserInput();
//        testNummer();

    }

    private static void testNummer() {
        System.out.println(OrdinalNumbers.zahlenBuilder(1));
        System.out.println(OrdinalNumbers.zahlenBuilder(5));
        System.out.println(OrdinalNumbers.zahlenBuilder(9));
        System.out.println(OrdinalNumbers.zahlenBuilder(10));
        System.out.println(OrdinalNumbers.zahlenBuilder(11));
        System.out.println(OrdinalNumbers.zahlenBuilder(12));
        System.out.println(OrdinalNumbers.zahlenBuilder(13));
        System.out.println(OrdinalNumbers.zahlenBuilder(19));
        System.out.println(OrdinalNumbers.zahlenBuilder(20));
        System.out.println(OrdinalNumbers.zahlenBuilder(21));
        System.out.println(OrdinalNumbers.zahlenBuilder(25));
        System.out.println(OrdinalNumbers.zahlenBuilder(29));
        System.out.println(OrdinalNumbers.zahlenBuilder(30));
        System.out.println(OrdinalNumbers.zahlenBuilder(31));
        System.out.println(OrdinalNumbers.zahlenBuilder(35));
        System.out.println(OrdinalNumbers.zahlenBuilder(39));
        System.out.println(OrdinalNumbers.zahlenBuilder(40));
        System.out.println(OrdinalNumbers.zahlenBuilder(90));
        System.out.println(OrdinalNumbers.zahlenBuilder(96));
        System.out.println(OrdinalNumbers.zahlenBuilder(99));
        System.out.println(OrdinalNumbers.zahlenBuilder(100));
        System.out.println(OrdinalNumbers.zahlenBuilder(101));
        System.out.println(OrdinalNumbers.zahlenBuilder(111));
        System.out.println(OrdinalNumbers.zahlenBuilder(300));
        System.out.println(OrdinalNumbers.zahlenBuilder(301));
        System.out.println(OrdinalNumbers.zahlenBuilder(302));
        System.out.println(OrdinalNumbers.zahlenBuilder(305));
        System.out.println(OrdinalNumbers.zahlenBuilder(309));
        System.out.println(OrdinalNumbers.zahlenBuilder(311));
        System.out.println(OrdinalNumbers.zahlenBuilder(319));
        System.out.println(OrdinalNumbers.zahlenBuilder(320));
        System.out.println(OrdinalNumbers.zahlenBuilder(322));
        System.out.println(OrdinalNumbers.zahlenBuilder(360));
        System.out.println(OrdinalNumbers.zahlenBuilder(120));
        System.out.println(OrdinalNumbers.zahlenBuilder(312));
        System.out.println(OrdinalNumbers.zahlenBuilder(313));
        System.out.println(OrdinalNumbers.zahlenBuilder(321));
        System.out.println(OrdinalNumbers.zahlenBuilder(900));
        System.out.println(OrdinalNumbers.zahlenBuilder(901));
        System.out.println(OrdinalNumbers.zahlenBuilder(998));
        System.out.println(OrdinalNumbers.zahlenBuilder(999));
    }
}