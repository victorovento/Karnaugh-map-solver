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
public class CuatroVariables {

    private String salida = "";
    private final int A[][] = new int[4][4];
    private final int marcados[][] = new int[4][4];

    /*
    constructor 
    */
    public CuatroVariables(int val[]) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                A[i][j] = val[count++];
                marcados[i][j] = 0;
            }
        }
    }
    
    
    public String resolver() {
        if (!probar16()) {
            salida = "1"; // esto es que todos son 1
        } else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (A[i][j] == 1 && marcados[i][j] == 0) {
                        if (probar8(i, j)) {
                            if (probar4(i, j)) {
                                if (probar2(i, j)) {
                                    noAgrupar(i, j);
                                }
                            }
                        }
                    }
                }
            }
        }
        return salida;
    }

    /*
    este prueba si hay grupos de 16
    */
    private boolean probar16() {
        boolean buscar_Grupomenor = false;

        outer:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (A[i][j] == 1) {
                    buscar_Grupomenor = false;
                } else {
                    //cerrar el ciclo y se va a 
                    //buscar si hay grupos mas pequeños
                    buscar_Grupomenor = true;
                    break outer;
                }
            }
        }
        return buscar_Grupomenor;
    }

    // buscar grupos de 8
    private boolean probar8(int r, int c) {
        boolean buscargruposmenores = true;
        String local = "";

        if (A[r][0] == 1 && A[r][1] == 1 && A[r][2] == 1 && A[r][3] == 1 && A[(r + 1) % 4][0] == 1 && A[(r + 1) % 4][1] == 1
                && A[(r + 1) % 4][2] == 1 && A[(r + 1) % 4][3] == 1) { // rows ++
            if (r == 0) {
                local = "A'";
            }
            if (r == 1) {
                local = "B";
            }
            if (r == 2) {
                local = "A";
            }
            if (r == 3) {
                local = "B'";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargruposmenores = false;
            // make checked
            marcados[r][0] = 1;
            marcados[r][1] = 1;
            marcados[r][2] = 1;
            marcados[r][3] = 1;
            marcados[(r + 1) % 4][0] = 1;
            marcados[(r + 1) % 4][1] = 1;
            marcados[(r + 1) % 4][2] = 1;
            marcados[(r + 1) % 4][3] = 1;
        } else if (A[r][0] == 1 && A[r][1] == 1 && A[r][2] == 1 && A[r][3] == 1 && A[(4 + (r - 1)) % 4][0] == 1
                && A[(4 + (r - 1)) % 4][1] == 1 && A[(4 + (r - 1)) % 4][2] == 1 && A[(4 + (r - 1)) % 4][3] == 1) { // rows --
            if (r == 0) {
                local = "B'";
            }
            if (r == 1) {
                local = "A'";
            }
            if (r == 2) {
                local = "B";
            }
            if (r == 3) {
                local = "A";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargruposmenores = false;
            
            marcados[r][0] = 1;
            marcados[r][1] = 1;
            marcados[r][2] = 1;
            marcados[r][3] = 1;
            marcados[(4 + (r - 1)) % 4][0] = 1;
            marcados[(4 + (r - 1)) % 4][1] = 1;
            marcados[(4 + (r - 1)) % 4][2] = 1;
            marcados[(4 + (r - 1)) % 4][3] = 1;
        } else if (A[0][c] == 1 && A[1][c] == 1 && A[2][c] == 1 && A[3][c] == 1 && A[0][(c + 1) % 4] == 1 && A[1][(c + 1) % 4] == 1
                && A[2][(c + 1) % 4] == 1 && A[3][(c + 1) % 4] == 1) { // columns ++
            if (c == 0) {
                local = "C'";
            }
            if (c == 1) {
                local = "D";
            }
            if (c == 2) {
                local = "C";
            }
            if (c == 3) {
                local = "D'";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargruposmenores = false;
            
            marcados[0][c] = 1;
            marcados[1][c] = 1;
            marcados[2][c] = 1;
            marcados[3][c] = 1;
            marcados[0][(c + 1) % 4] = 1;
            marcados[1][(c + 1) % 4] = 1;
            marcados[2][(c + 1) % 4] = 1;
            marcados[3][(c + 1) % 4] = 1;
        } else if (A[0][c] == 1 && A[1][c] == 1 && A[2][c] == 1 && A[3][c] == 1 && A[0][(4 + (c - 1)) % 4] == 1
                && A[1][(4 + (c - 1)) % 4] == 1 && A[2][(4 + (c - 1)) % 4] == 1 && A[3][(4 + (c - 1)) % 4] == 1) { // columns --
            if (c == 0) {
                local = "D'";
            }
            if (c == 1) {
                local = "C'";
            }
            if (c == 2) {
                local = "D";
            }
            if (c == 3) {
                local = "C";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargruposmenores = false;
            

            marcados[0][c] = 1;
            marcados[1][c] = 1;
            marcados[2][c] = 1;
            marcados[3][c] = 1;
            marcados[0][(4 + (c - 1)) % 4] = 1;
            marcados[1][(4 + (c - 1)) % 4] = 1;
            marcados[2][(4 + (c - 1)) % 4] = 1;
            marcados[3][(4 + (c - 1)) % 4] = 1;
        }
        return buscargruposmenores;
    }

    // buscar  grupos de 4
    private boolean probar4(int r, int c) {
        boolean buscargrupomenores = true;
        String local = "";

        if (A[r][0] == 1 && A[r][1] == 1 && A[r][2] == 1 && A[r][3] == 1) { // row fours
            if (r == 0) {
                local = "A'B'";
            }
            if (r == 1) {
                local = "A'B";
            }
            if (r == 2) {
                local = "AB";
            }
            if (r == 3) {
                local = "AB'";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargrupomenores = false;
            
            marcados[r][0] = 1;
            marcados[r][1] = 1;
            marcados[r][2] = 1;
            marcados[r][3] = 1;
        } else if (A[0][c] == 1 && A[1][c] == 1 && A[2][c] == 1 && A[3][c] == 1) { // column fours
            if (c == 0) {
                local = "C'D'";
            }
            if (c == 1) {
                local = "C'D";
            }
            if (c == 2) {
                local = "CD";
            }
            if (c == 3) {
                local = "CD'";

            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargrupomenores = false;
            
            marcados[0][c] = 1;
            marcados[1][c] = 1;
            marcados[2][c] = 1;
            marcados[3][c] = 1;
        } else if (A[r][c] == 1 && A[r][(c + 1) % 4] == 1 && A[(r + 1) % 4][c] == 1 && A[(r + 1) % 4][(c + 1) % 4] == 1) {
           
            if (r == 0) {
                local = "A'";
            }
            if (r == 1) {
                local = "B";
            }
            if (r == 2) {
                local = "A";
            }
            if (r == 3) {
                local = "B'";
            }
            if (c == 0) {
                local = local + "C'";
            }
            if (c == 1) {
                local = local + "D";
            }
            if (c == 2) {
                local = local + "C";
            }
            if (c == 3) {
                local = local + "D'";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargrupomenores = false;
            
            marcados[r][c] = 1;
            marcados[r][(c + 1) % 4] = 1;
            marcados[(r + 1) % 4][c] = 1;
            marcados[(r + 1) % 4][(c + 1) % 4] = 1;
        } else if (A[r][(4 + (c - 1)) % 4] == 1 && A[r][c] == 1 && A[(r + 1) % 4][(4 + (c - 1)) % 4] == 1 && A[(r + 1) % 4][c] == 1) {
            
            if (r == 0) {
                local = "A'";
            }
            if (r == 1) {
                local = "B";
            }
            if (r == 2) {
                local = "A";
            }
            if (r == 3) {
                local = "B'";
            }
            if (c == 0) {
                local = local + "D'";
            }
            if (c == 1) {
                local = local + "C'";
            }
            if (c == 2) {
                local = local + "D";
            }
            if (c == 3) {
                local = local + "C'";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargrupomenores = false;
            
            marcados[r][(4 + (c - 1)) % 4] = 1;
            marcados[r][c] = 1;
            marcados[(r + 1) % 4][(4 + (c - 1)) % 4] = 1;
            marcados[(r + 1) % 4][c] = 1;

        } else if (A[(4 + (r - 1)) % 4][(4 + (c - 1)) % 4] == 1 && A[(4 + (r - 1)) % 4][c] == 1 && A[r][(4 + (c - 1)) % 4] == 1 && A[r][c] == 1) {
            
            if (r == 0) {
                local = "B'";
            }
            if (r == 1) {
                local = "A'";
            }
            if (r == 2) {
                local = "B";
            }
            if (r == 3) {
                local = "A";
            }
            if (c == 0) {
                local = local + "D'";
            }
            if (c == 1) {
                local = local + "C'";
            }
            if (c == 2) {
                local = local + "D";
            }
            if (c == 3) {
                local = local + "C'";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargrupomenores = false;
            
            marcados[(4 + (r - 1)) % 4][(4 + (c - 1)) % 4] = 1;
            marcados[(4 + (r - 1)) % 4][c] = 1;
            marcados[r][(4 + (c - 1)) % 4] = 1;
            marcados[r][c] = 1;
        } else if (A[(4 + (r - 1)) % 4][c] == 1 && A[(4 + (r - 1)) % 4][(c + 1) % 4] == 1 && A[r][c] == 1 && A[r][(c + 1) % 4] == 1) {
            
            if (r == 0) {
                local = "B'";
            }
            if (r == 1) {
                local = "A'";
            }
            if (r == 2) {
                local = "B";
            }
            if (r == 3) {
                local = "A";
            }
            if (c == 0) {
                local = local + "C'";
            }
            if (c == 1) {
                local = local + "D";
            }
            if (c == 2) {
                local = local + "C";
            }
            if (c == 3) {
                local = local + "D'";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargrupomenores = false;
            
            marcados[(4 + (r - 1)) % 4][c] = 1;
            marcados[(4 + (r - 1)) % 4][(c + 1) % 4] = 1;
            marcados[r][c] = 1;
            marcados[r][(c + 1) % 4] = 1;
        }
        return buscargrupomenores;
    }

    // buscar grupos de 2
    private boolean probar2(int r, int c) {
        boolean buscargruposmenores = true;
        String local = "";

        if (A[r][c] == 1 && A[r][(c + 1) % 4] == 1) {
            if (r == 0) {
                local = "A'B'";
            }
            if (r == 1) {
                local = "A'B";
            }
            if (r == 2) {
                local = "AB";
            }
            if (r == 3) {
                local = "AB'";
            }
            if (c == 0) {
                local = local + "C'";
            }
            if (c == 1) {
                local = local + "D";
            }
            if (c == 2) {
                local = local + "C";
            }
            if (c == 3) {
                local = local + "D'";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargruposmenores = false;
          
            marcados[r][c] = 1;
            marcados[r][(c + 1) % 4] = 1;

        } else if (A[r][(4 + (c - 1)) % 4] == 1 && A[r][c] == 1) { 
            if (r == 0) {
                local = "A'B'";
            }
            if (r == 1) {
                local = "A'B";
            }
            if (r == 2) {
                local = "AB";
            }
            if (r == 3) {
                local = "AB'";
            }
            if (c == 0) {
                local = local + "D'";
            }
            if (c == 1) {
                local = local + "C'";
            }
            if (c == 2) {
                local = local + "D";
            }
            if (c == 3) {
                local = local + "C";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargruposmenores = false;
        
            marcados[r][(4 + (c - 1)) % 4] = 1;
            marcados[r][c] = 1;
        } else if (A[r][c] == 1 && A[(r + 1) % 4][c] == 1) { 
            if (r == 0) {
                local = "A'";
            }
            if (r == 1) {
                local = "B";
            }
            if (r == 2) {
                local = "A";
            }
            if (r == 3) {
                local = "B'";
            }
            if (c == 0) {
                local = local + "C'D'";
            }
            if (c == 1) {
                local = local + "C'D";
            }
            if (c == 2) {
                local = local + "CD";
            }
            if (c == 3) {
                local = local + "CD'";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargruposmenores = false;
            
            marcados[r][c] = 1;
            marcados[(r + 1) % 4][c] = 1;
        } else if (A[r][c] == 1 && A[(4 + (r - 1)) % 4][c] == 1) { 
            if (r == 0) {
                local = "B'";
            }
            if (r == 1) {
                local = "A'";
            }
            if (r == 2) {
                local = "B";
            }
            if (r == 3) {
                local = "A";
            }
            if (c == 0) {
                local = local + "C'D'";
            }
            if (c == 1) {
                local = local + "C'D";
            }
            if (c == 2) {
                local = local + "CD";
            }
            if (c == 3) {
                local = local + "CD'";
            }

            if (salida.matches("")) {
                salida = salida + local;
            } else {
                salida = salida + " + " + local;
            }

            buscargruposmenores = false;
          
            marcados[r][c] = 1;
            marcados[(4 + (r - 1)) % 4][c] = 1;
        }
        return buscargruposmenores;
    }

    //no agrupar
    private void noAgrupar(int r, int c) {
        String local = "";
        if (r == 0) {
            local = "A'B'";
        }
        if (r == 1) {
            local = "A'B";
        }
        if (r == 2) {
            local = "AB";
        }
        if (r == 3) {
            local = "AB'";
        }
        if (c == 0) {
            local = local + "C'D'";
        }
        if (c == 1) {
            local = local + "C'D";
        }
        if (c == 2) {
            local = local + "CD";
        }
        if (c == 3) {
            local = local + "CD'";
        }

        if (salida.matches("")) {
            salida = salida + local;
        } else {
            salida = salida + " + " + local;
        }

        marcados[r][c] = 1;
    }

}
