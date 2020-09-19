import java.util.Arrays;

public class Tabla {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private int[][] tabla;

    public Tabla(int N) {
        tabla = new int[N][N]; // N+1 a sum

        for (int[] sor:tabla)
            Arrays.fill(sor, 0);


        /*
        for (int sor = 0; sor < N; sor++) {
            for (int oszlop = 0; oszlop < N; oszlop++)
                tabla[sor][oszlop] = 0;
        }
         */

        /*
        tabla[2][N] = 8;
        tabla[3][N] = 9;
        tabla[4][N] = 10;
        tabla[N][1] = 5;
        tabla[N][2] = 9;
        tabla[N][4] = 7;
         */

        /*
        tabla[2][0] = 1;
        tabla[3][0] = 1;
        tabla[4][0] = 1;

        tabla[0][2] = 2;
        tabla[1][2] = 2;
        tabla[2][2] = 2;

        tabla[3][2] = 3;
        tabla[3][3] = 3;
        tabla[3][4] = 3;

        tabla[4][2] = 4;
        tabla[4][3] = 4;
        tabla[4][4] = 4;

        tabla[2][1] = 5;
        tabla[3][1] = 5;
        tabla[4][1] = 5;
         */
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





    static int k = 1;

    static void printSolution(Tabla tabla, int N) {
        System.out.printf("%d. megoldas: \n", k++);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf(" %d ", tabla.getTabla()[i][j]);
            System.out.printf("\n");
        }
        System.out.printf("\n");
    }

    static boolean checkAtfedes(Tabla tabla, int N, int row, int col) {
        int i, j;
        /*
        for (i = 0; i < col; i++)
            if (tabla.getTabla()[row][i] == 1)
                return false;
         */
        return true;
    }

    static boolean tablaUtil(Tabla tabla, int N, int row, int col, int val) {

        if (col == N-2 || row == N-2) {
            printSolution(tabla, N);
            return true;
        }

/*
        boolean res = false;
        for (int i = 0; i < N; i++) {
            if ( checkAtfedes(tabla, N, i, col) ) {
                tabla.getTabla()[i][col] = 1;
                res = tablaUtil(tabla, N,col + 1) || res;
                tabla.getTabla()[i][col] = 0; // BACKTRACK
            }
        }
        return res;
         */


        boolean res = false;
        /*
        for (int r=row; r<N; r++) {
            tabla.getTabla()[r][col] = val;
            tabla.getTabla()[r][col+1] = val;
            tabla.getTabla()[r][col+2] = val;

            res = tablaUtil(tabla, N, r,col + 3, val) || res;

            tabla.getTabla()[r][col] = 0;
            tabla.getTabla()[r][col+1] = 0;
            tabla.getTabla()[r][col+2] = 0;
        }
         */



        for (int bj = 1; bj < 6; bj++) {
            for (int i=0; i<N; i++) {
                for (int j = 0; j < N - 2; j++) {
                    tabla.getTabla()[i][j] = bj;
                    tabla.getTabla()[i][j + 1] = bj;
                    tabla.getTabla()[i][j + 2] = bj;
                    bj++;
                }
            }
            printSolution(tabla, N);
        }
        res = true;

        return res;
    }


    static void solveTabla(int N) {
        Tabla tabla = new Tabla(5);

        if (tablaUtil(tabla,tabla.getTabla().length,0, 0, 1) == false) {
            System.out.printf(ANSI_RED + "\nNincs megoldas\n" + ANSI_RESET);
        }
    }

}
