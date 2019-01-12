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
public class TresVariables {

    private String salida = "";
    private final int A[][] = new int[2][4];
    private final int marcado[][] = new int[2][4];

    public TresVariables(int[] val) {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                A[i][j] = val[count++];
                marcado[i][j] = 0;
            }
        }
    }

    public String resolver() {
        if (!buscar8()) {
                        salida = "1";
        } else {
        
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    if (A[i][j] == 1 && marcado[i][j] == 0) {
                        if (buscar4(i, j)) {
                            if (buscar2(i, j)) {
                                noagrupar(i, j);
                            }
                        }

                    }
                }
            }
        }
        return salida;
    }

    private boolean buscar8() {
        boolean buscargrupomenor = false;

        outer:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (A[i][j] == 1) {
                    buscargrupomenor = false;
                } else {
                    buscargrupomenor = true;
                    break outer;
                }
            }
        }
        return buscargrupomenor;
    }

    private boolean buscar4(int r, int c) {
        boolean buscargrupomenor = true;
        String local = "";

        if (A[r][0] == 1 && A[r][1] == 1 && A[r][2] == 1 && A[r][3] == 1) { // row fours
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
           
            marcado[r][0] = 1;
            marcado[r][1] = 1;
            marcado[r][2] = 1;
            marcado[r][3] = 1;
        } else if (A[0][c] == 1 && A[0][(c + 1) % 4] == 1 && A[1][c] == 1 && A[1][(c + 1) % 4] == 1) { // columns ++
            if (c == 0) {
                local = "B'";
            }
            if (c == 1) {
                local = "C";
            }
            if (c == 2) {
                local = "B";
            }
            if (c == 3) {
                local = "C'";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargrupomenor = false;

            marcado[0][c] = 1;
            marcado[0][(c + 1) % 4] = 1;
            marcado[1][c] = 1;
            marcado[1][(c + 1) % 4] = 1;
        } else if (A[0][c] == 1 && A[0][(4 + (c - 1)) % 4] == 1 && A[1][c] == 1 && A[1][(4 + (c - 1)) % 4] == 1) { // columns --
            if (c == 0) {
                local = "C'";
            }
            if (c == 1) {
                local = "B'";
            }
            if (c == 2) {
                local = "C";
            }
            if (c == 3) {
                local = "B";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargrupomenor = false;
      
            marcado[0][c] = 1;
            marcado[0][(4 + (c - 1)) % 4] = 1;
            marcado[1][c] = 1;
            marcado[1][(4 + (c - 1)) % 4] = 1;
        }
        return buscargrupomenor;
    }

 //buscar grupo de 2
    private boolean buscar2(int r, int c) {
        boolean buscargrupomenor = true;
        String local = "";

        if (A[r][c] == 1 && A[r][(c + 1) % 4] == 1) { // columns ++
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
                local = local + "C";
            }
            if (c == 2) {
                local = local + "B";
            }
            if (c == 3) {
                local = local + "C'";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargrupomenor = false;

            marcado[r][c] = 1;
            marcado[r][(c + 1) % 4] = 1;
        } else if (A[r][(4 + (c - 1)) % 4] == 1 && A[r][c] == 1) { 
            if (r == 0) {
                local = "A'";
            }
            if (r == 1) {
                local = "A";
            }
            if (c == 0) {
                local = local + "C'";
            }
            if (c == 1) {
                local = local + "B'";
            }
            if (c == 2) {
                local = local + "C";
            }
            if (c == 3) {
                local = local + "B";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargrupomenor = false;
           
            marcado[r][(4 + (c - 1)) % 4] = 1;
            marcado[r][c] = 1;
        } else if (A[r][c] == 1 && A[(r + 1) % 2][c] == 1) { 
            if (c == 0) {
                local = "B'C'";
            }
            if (c == 1) {
                local = "B'C";
            }
            if (c == 2) {
                local = "BC";
            }
            if (c == 3) {
                local = "BC'";
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
            local = local + "B'C'";
        }
        if (c == 1) {
            local = local + "B'C";
        }
        if (c == 2) {
            local = local + "BC";
        }
        if (c == 3) {
            local = local + "BC'";
        }

        if (salida.matches("")) {
            salida = salida + local;
        } else {
            salida = salida + " + " + local;
        }

        marcado[r][c] = 1;
    }
}
