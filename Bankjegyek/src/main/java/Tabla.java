import java.util.Arrays;

public class Tabla {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private int[][] tabla;

    public Tabla(int Nsor, int Noszlop) {
        tabla = new int[Nsor+1][Noszlop+1];

        for (int sor = 0; sor < Nsor; sor++) {
            for (int oszlop = 0; oszlop < Noszlop; oszlop++)
                tabla[sor][oszlop] = 0;
        }

        tabla[2][5] = 8;
        tabla[3][5] = 9;
        tabla[4][5] = 10;
        tabla[5][1] = 5;
        tabla[5][2] = 9;
        tabla[5][4] = 7;
    }

    public void print() {
        for (int sor = 0; sor < tabla.length-1; sor++) {
            for (int oszlop = 0; oszlop < tabla.length-1; oszlop++)
                System.out.printf("%2d ", tabla[sor][oszlop]);
            System.out.println(ANSI_YELLOW + tabla[sor][tabla.length-1] + ANSI_RESET);
        }
        for (int oszlop = 0; oszlop < tabla.length-1; oszlop++) {
            System.out.print(ANSI_YELLOW);
            System.out.printf("%2d ", tabla[tabla.length - 1][oszlop]);
            System.out.print(ANSI_RESET);
        }
        System.out.println();
    }

    public boolean check() {
        // sor check
        for (int sor = 0; sor < tabla.length-2; sor++) {
            if (tabla[sor][tabla.length-1] > 0) {
                int sum = 0;

                for (int oszlop = 0; oszlop < tabla.length-2; oszlop++) {
                    sum += tabla[sor][oszlop];
                }

                if (sum != tabla[sor][tabla.length-1]) return false;
            }
        }

        // oszlop check
        for (int oszlop = 0; oszlop < tabla.length-2; oszlop++) {
            if (tabla[tabla.length-1][oszlop] > 0) {
                int sum = 0;

                for (int sor = 0; sor < tabla.length-2; sor++) {
                    sum += tabla[sor][oszlop];
                }

                if (sum != tabla[tabla.length-1][oszlop]) return false;
            }
        }

        return true;
    }

}
