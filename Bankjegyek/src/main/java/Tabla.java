import java.util.Arrays;

public class Tabla {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private int[][] tabla;

    public Tabla(int N) {
        tabla = new int[N+1][N+1]; // N+1 a sum

        for (int[] sor:tabla)
            Arrays.fill(sor, 0);
    }

    public int[][] getTabla() {
        return tabla;
    }

    public void setTabla(int[][] tabla) {
        this.tabla = tabla;
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

    public boolean checkSum() { // legyen private, egyelore Bankjegyek main()-ben hasznalom

        int[] bankjegy = new int[5];

        // sor check
        for (int sor = 0; sor < tabla.length-1; sor++) {
            if (tabla[sor][tabla.length-1] > 0) {
                Arrays.fill(bankjegy,0);
                int sum = 0;

                for (int oszlop = 0; oszlop < tabla.length-1; oszlop++) {
                    if (tabla[sor][oszlop] != 0) {
                        if (bankjegy[tabla[sor][oszlop] - 1] == 0) { // minden bankjegyet csak 1x számoljunk
                            sum += tabla[sor][oszlop];
                            bankjegy[tabla[sor][oszlop] - 1]++;
                        }
                    }
                }

                if (sum != tabla[sor][tabla.length-1]) return false;
            }
        }

        // oszlop check
        for (int oszlop = 0; oszlop < tabla.length-1; oszlop++) {
            if (tabla[tabla.length-1][oszlop] > 0) {
                Arrays.fill(bankjegy,0);
                int sum = 0;

                for (int sor = 0; sor < tabla.length-1; sor++) {
                    if (tabla[sor][oszlop] != 0) {
                        if (bankjegy[tabla[sor][oszlop] - 1] == 0) { // minden bankjegyet csak 1x számoljunk
                            sum += tabla[sor][oszlop];
                            bankjegy[tabla[sor][oszlop] - 1]++;
                        }
                    }
                }

                if (sum != tabla[tabla.length-1][oszlop]) return false;
            }
        }

        return true;
    }

    public void calcSums(int N) {
        int[] bankjegy = new int[5];

        for (int i=0; i<N; i++) {
            Arrays.fill(bankjegy,0);
            for (int j = 0; j < N; j++)
                this.tabla[N][j] += this.tabla[i][j];
        }

        // sor check
        for (int sor = 0; sor < tabla.length-1; sor++) {
            if (tabla[sor][tabla.length-1] > 0) {
                Arrays.fill(bankjegy,0);
                int sum = 0;

                for (int oszlop = 0; oszlop < tabla.length-1; oszlop++) {
                    if (tabla[sor][oszlop] != 0) {
                        if (bankjegy[tabla[sor][oszlop] - 1] == 0) { // minden bankjegyet csak 1x számoljunk
                            sum += tabla[sor][oszlop];
                            bankjegy[tabla[sor][oszlop] - 1]++;
                        }
                    }
                }

                this.tabla[sor][N] = sum;
            }
        }


    }


    public void feltoltTabla(int N) {
        int[][] tabla = {
                {0, 0, 2, 0, 0},
                {0, 0, 2, 0, 0},
                {1, 5, 2, 0, 0},
                {1, 5, 3, 3, 3},
                {1, 5, 4, 4, 4}
        };

        for (int i=0; i<N; i++) {
            for (int j = 0; j < N; j++)
                this.tabla[i][j] = tabla[i][j];
        }

        calcSums(N);

    }



}
