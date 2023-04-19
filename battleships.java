package jafa;

import java.util.Scanner;
import java.util.Random;

public class battleships {

    static char polje[][] = new char[10][10];
    static char polje2[][] = new char[10][10]; // polje za izpis in prikaz kje smo ugibali
    // NUJNO STATIC, DA LAHKO KLICEMO V DRUGIH METODAH!!!!
    static int dolzina = polje.length;
    // 0 1 2 3 4 5 6 7 8 9 (0-9)
    static int steviloLadjic = 18;
    static String abcd = "ABCDEFGHIJ";
    static int steviloPoiskusov = 0;
    static char starEmoji = '\u2B50'; // unikoda za star emoyi
    static int r; // kasnej deklariran
    static int c; // kasnej deklariran
    static Random rand = new Random();
    static boolean started = false;

    static void zacni() {
        Stopwatch.start();
        generirajPolje();
        generirajLadjice();
        izpis();
        ugibaj();

    }

    static void koncaj() {
        Stopwatch.stop();
        rezultat();
    }

    static void rezultat() {
        System.out.println("Cestitam koncali ste igor potaplanje ladjic");
        System.out.println("Igro ste zakljucili s " + steviloPoiskusov + " poiskusi");

        // izpis zvezdic ki ne dela:
        // zracuna povprecej in ga zaokrozi navzgor

        if (steviloPoiskusov <= 50)
            System.out.println("Zasluzite si: " + (starEmoji) + (starEmoji) + (starEmoji));
        else if (steviloPoiskusov <= 75)
            System.out.println("Zasluzite si: " + (starEmoji) + (starEmoji));
        else
            System.out.println("Zasluzite si " + (starEmoji));

        // V stevilopoiskusov ste zadeli stevilo ladjic
        // vasa igra je trajala x minut in sekund
        // zasluzili ste si *** zvezdice

    }

    static void generirajPolje() {

        for (int i = 0; i < dolzina; i++) {
            for (int j = 0; j < dolzina; j++) {
                polje[i][j] = 'O';
                polje2[i][j] = 'O';
            }
        }
    }

    static void izpisPolja() {

        for (int i = 0; i < dolzina; i++) {
            for (int j = 0; j < dolzina; j++) {
                System.out.print(polje[i][j]);
            }
            System.out.println();

        }
        System.out.println();

    }

    static void generirajLadjice() {
        int n = steviloLadjic;
        int i = 0;

        // generira 18 ladjic na radome poljih//
        while (i < n) {

            int rnd1 = rand.nextInt(10); // prva spremenljivka
            int rnd2 = rand.nextInt(10); // druga spremenljivka
            while (rnd2 == rnd1) { // preveri ce se ponovi
                rnd2 = rand.nextInt(10);
            }

            polje[rnd1][rnd2] = 'x'; // zapise x
            i++;
        }
    }

    static void izpis() {

        // izpis tabele za igralkaca
        // izpiše bolj ločljivo tabelo za igralca in vsebuje kje je že on ugibal
        System.out.println("    |A|B|C|D|E|F|G|H|I|J|");
        System.out.println("    ---------------------");

        for (int i = 0; i < dolzina; i++) {

            if (i == 9)
                System.out.print(i + 1 + ": |");
            else
                System.out.print(i + 1 + ":  |");
            for (int j = 0; j < dolzina; j++)
                System.out.print(polje2[i][j] + "|");

            // preveri v polju1 ali je je posikus pravilen in ce je v polje2 zapise x/@

            if (polje[c][r] == 'x')
                polje2[c][r] = 'X';
            else if (started)
                polje2[c][r] = '@';

            System.out.println();
        }
        System.out.println();
        started = true; // poskrbi da prvic ne izpise @ na c=0 in r=0

    }

    static void ugibaj() {

        // Vnos podatkov

        while (steviloLadjic != 0) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Izberite polje in stevilo na katerem mislite, da se nahaja ladjica (A 5): ");
            String s = scanner.nextLine();
            char row = s.charAt(0); // A
            char column = s.charAt(2); // 5
            String number = s.replaceAll("[^0-9]", ""); // izloči vse, kar ni številka
            c = Integer.parseInt(number) - 1;

            // c = Integer.parseInt(String.valueOf(column)) - 1;A

            // pretvori char v int ce je mozno '1' = 1

            for (int i = 0; i < abcd.length(); i++) { // iz Stringa ABCDEFGHIJ vcame index ki je ustrezen s igralnim
                                                      // poljem
                if (abcd.charAt(i) == row)
                    r = i;
            }

            // testni izpois poolji

            /*
             * System.out.print(r);
             * System.out.print(" ");
             * System.out.print(c);
             * System.out.println(" ");
             */

            // preverimo ali je igralec zadelo ladjico

            if (polje[c][r] == 'x') {
                System.out.println();
                System.out.println("Zadetek");
                System.out.println("Zadel iste ladjico, ki se nahaja na: " + row + column);

                steviloLadjic--;
                System.out.println("Preostalo je se: " + steviloLadjic);
            } else if (polje2[c][r] == 'X' || polje2[c][r] == '@') {
                System.out.println("To polje ste ze ugibali, ponovno poiskusite");
            }

            else {
                System.out.println();
                System.out.println("Na tem polju ni ladjice");
            }

            steviloPoiskusov++;
            System.out.println("To je bil vas " + steviloPoiskusov + ". poiskusi");
            System.out.println();

            izpis();
            ugibaj();
            scanner.close();
            koncaj();
        }

    }

    public static void main(String[] args) {

        zacni();

        /*
         * System.out.println("   |A|B|C|D|E|F|G|H|I|J|");
         * System.out.println("   ---------------------");
         * System.out.println("1: |O|O|O|O|O|O|O|O|O|O|");
         * System.out.println("2: |O|O|O|O|O|O|O|O|O|O|");
         * System.out.println("3: |O|O|O|O|O|O|O|O|O|O|");
         * System.out.println("4: |O|O|O|O|O|O|O|O|O|O|");
         * System.out.println("5: |O|O|O|O|O|O|O|O|O|O|");
         * System.out.println("6: |O|O|O|O|O|O|O|O|O|O|");
         * System.out.println("7: |O|O|O|O|O|O|O|O|O|O|");
         * System.out.println("8: |O|O|O|O|O|O|O|O|O|O|");
         * System.out.println("9: |O|O|O|O|O|O|O|O|O|O|");
         * System.out.println("   ---------------------");
         */

    }

}
