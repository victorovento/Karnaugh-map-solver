/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resolvermapas;

/**
 *
 * @author victoromar
 */
public class DosVariables {

    private String salida = "";
    private final int A[][] = new int[2][2];
    private final int marcado[][] = new int[2][2];

    public DosVariables(int[] val) {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                A[i][j] = val[count++];
                marcado[i][j] = 0;
            }
        }
    }

    public String resolver() {
        if (!buscar4()) {
            salida = "1";
        } else {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (A[i][j] == 1 && marcado[i][j] == 0) {
                        if (buscar2(i, j)) {
                            noagrupar(i, j);
                        }

                    }
                }
            }
        }
        return salida;
    }

    private boolean buscar4() {
        boolean buscarmenores = false;

        outer:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (A[i][j] == 1) {
                    buscarmenores = false;
                } else {
                    buscarmenores = true;
                    break outer;
                }
            }
        }
        return buscarmenores;
    }

    private boolean buscar2(int r, int c) {
        boolean buscargrupomenor = true;
        String local = "";

        if (A[r][c] == 1 && A[r][(c + 1) % 2] == 1) {
            if (r == 0) {
                local = "A'";
            }
            if (r == 1) {
                local = "A";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargrupomenor = false;
           
            marcado[r][c] = 1;
            marcado[r][(c + 1) % 2] = 1;
        } else if (A[r][c] == 1 && A[(r + 1) % 2][c] == 1) {  
            if (c == 0) {
                local = "B'";
            }
            if (c == 1) {
                local = "B";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargrupomenor = false;
            
            marcado[r][c] = 1;
            marcado[(r + 1) % 2][c] = 1;
        }
        return buscargrupomenor;
    }


    private void noagrupar(int r, int c) {
        String local = "";

        if (r == 0) {
            local = "A'";
        }
        if (r == 1) {
            local = "A";
        }
        if (c == 0) {
            local = local + "B'";
        }
        if (c == 1) {
            local = local + "B";
        }

        if (salida.matches("")) {
            salida = salida + local;
        } else {
            salida = salida + " + " + local;
        }

        marcado[r][c] = 1;
    }
}
