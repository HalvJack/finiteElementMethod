import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MatrixH extends MatrixMES {
    public MatrixH(double[] x, double[] y, double conductivity) {
        this.x = x;
        this.y = y;
        this.alfa = conductivity;
    }
    List<Function<Double, Double>> myFunctionsKsi = new ArrayList<>() { // ksi, ma byc tablica ksi
        {
            add(aDouble -> 0.25 * (aDouble - 1));
            add(aDouble -> 0.25 * (-1 - aDouble));
            add(aDouble -> 0.25 * (aDouble + 1));
            add(aDouble -> 0.25 * (1 - aDouble));
        }
    };
    List<Function<Double, Double>> myFunctionsEta = new ArrayList<>() { // Eta, w sensie tez ma byc tablica eta
        {
            add(aDouble -> 0.25 * (aDouble - 1));
            add(aDouble -> 0.25 * (1 - aDouble));
            add(aDouble -> 0.25 * (aDouble + 1));
            add(aDouble -> 0.25 * (-aDouble - 1));
        }
    };


    /*private double[] calculate1DivideByDet(double matrix[][]) {e
        double[] array = new double[matrix.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1 / (matrix[i][0] * matrix[i][3]);
            //System.out.println(array[i]);
        }
        return array;
    }*/

    private double[][] showTableEta(int size) { // nazwa tabeli, ale to tabelka z dn1/dE ( E to ksi )
        double[][] table = new double[size][4];
        double[] eta = new double[size];
        if (size == 4) eta = eta2;
        if (size == 9) eta = eta3;
        if (size == 16) eta = eta4;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                table[i][j] = myFunctionsEta.get(j).apply(eta[i]);
            }
        }
        /*for (int i = 0; i < size; i++) {
            for (int i1 = 0; i1 < 4; i1++) {
                System.out.print(table[i][i1] + " || ");
            }
            System.out.println();
        }*/
        return table;
    }

    private double[][] showTableKsi(int size) { // nazwa tabeli, ale to tabelka z dn1/dn ( n to eta )
        double[][] table = new double[size][4];
        double[] ksi = new double[size];
        if (size == 4) ksi = ksi2;
        if (size == 9) ksi = ksi3;
        if (size == 16) ksi = ksi4;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                table[i][j] = myFunctionsKsi.get(j).apply(ksi[i]);
            }
        }
        /*for (int i = 0; i < size; i++) {
            for (int i1 = 0; i1 < 4; i1++) {
                System.out.print(table[i][i1] + " || ");
            }
            System.out.println();
        }*/
        return table;
    }

    private double[][] showMatrixH(int points, double matricesH[][][]) {
        double[][] matrixH = new double[4][4];
        double[] wages = new double[points];
        if (points == 2) wages = wages2;
        if (points == 3) wages = wages3;
        if (points == 4) wages = wages4;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (points == 2) {
                    matrixH[i][j] = matricesH[0][i][j] * wages[0] * wages[0] + matricesH[1][i][j] * wages[1] *
                            wages[0] + matricesH[2][i][j] * wages[0] * wages[1] + matricesH[3][i][j] * wages[1] * wages[1];
                }
                if (points == 3) {
                    matrixH[i][j] = matricesH[0][i][j] * wages[0] * wages[0] + matricesH[1][i][j] * wages[0] * wages[1] +
                            matricesH[2][i][j] * wages[0] * wages[2] + matricesH[3][i][j] * wages[1] * wages[0] +
                            matricesH[4][i][j] * wages[1] * wages[1] + matricesH[5][i][j] * wages[1] * wages[2] +
                            matricesH[6][i][j] * wages[2] * wages[0] + matricesH[7][i][j] * wages[2] * wages[1] +
                            matricesH[8][i][j] * wages[2] * wages[2];
                }
                if (points == 4) {
                    matrixH[i][j] = matricesH[0][i][j] * wages[0] * wages[0] + matricesH[1][i][j] * wages[0] * wages[1] +
                            matricesH[2][i][j] * wages[0] * wages[2] + matricesH[3][i][j] * wages[0] * wages[3] +
                            matricesH[4][i][j] * wages[1] * wages[0] + matricesH[5][i][j] * wages[1] * wages[1] +
                            matricesH[6][i][j] * wages[1] * wages[2] + matricesH[7][i][j] * wages[1] * wages[3] +
                            matricesH[8][i][j] * wages[2] * wages[0] + matricesH[9][i][j] * wages[2] * wages[1] +
                            matricesH[10][i][j] * wages[2] * wages[2] + matricesH[11][i][j] * wages[2] * wages[3] +
                            matricesH[12][i][j] * wages[3] * wages[0] + matricesH[13][i][j] * wages[3] * wages[1] +
                            matricesH[14][i][j] * wages[3] * wages[2] + matricesH[15][i][j] * wages[3] * wages[3];
                }
                System.out.print(matrixH[i][j]);
            }
            System.out.println();
        }
        return matrixH;
    }

    public double[][] calculateMatrixH(int points) {
        int size = points * points;
        double[][] tableEta = new double[size][4];
        double[][] tableKsi = new double[size][4];
        tableEta = showTableEta(size);
        tableKsi = showTableKsi(size);
        double[][] matrixFirst = new double[size][4];
        for (int i = 0; i < size; i++) { // dla pc1, pc2, pc3, pc4 zrobienie to co na slajdzie 10,11,12
            matrixFirst[i][0] = tableEta[i][0] * x[0] + tableEta[i][1] * x[1] + tableEta[i][2] * x[2] + tableEta[i][3] * x[3];
            matrixFirst[i][1] = tableEta[i][0] * y[0] + tableEta[i][1] * y[1] + tableEta[i][2] * y[2] + tableEta[i][3] * y[3];
            matrixFirst[i][2] = tableKsi[i][0] * x[0] + tableKsi[i][1] * x[1] + tableKsi[i][2] * x[2] + tableKsi[i][3] * x[3];
            matrixFirst[i][3] = tableKsi[i][0] * y[0] + tableKsi[i][1] * y[1] + tableKsi[i][2] * y[2] + tableKsi[i][3] * y[3];
        }
        double[] firstByDet = new double[size];
        firstByDet = calculate1DivideByDet(matrixFirst);
        double[][] matrixPom = new double[size][4];
        matrixPom = matrixFirst;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                matrixPom[i][j] *= firstByDet[i]; // ZGADZA SIE WYNOSI 80 0 0 80 kazda macierz wiec jest jak na slajdzie 12
            }
        }
        double[][] tableDx = new double[size][4];
        double[][] tableDy = new double[size][4];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                tableDx[i][j] = matrixFirst[i][0] * tableEta[i][j] + matrixFirst[i][1] * tableKsi[i][j]; //slajd 13, tabelka z dx
                tableDy[i][j] = matrixFirst[i][1] * tableEta[i][j] + matrixFirst[i][0] * tableKsi[i][j]; //slajd 13, tabelka z dy
               // System.out.print(i + " " + j + " " + tableDx[i][j]);
                //System.out.print(i + " " + j + " " + tableDy[i][j]);
            }
            //System.out.println();
        }
        double[] detJ = new double[size];
        for (int i = 0; i < size; i++) {
            detJ[i] = 1 / firstByDet[i]; // TUTAJ DOLICZAM JESZCZE DETJ BO WYZEJ OD RAZU MIALEM POLICZONE 1/DETJ
        }
        double[][][] matricesH = new double[size][4][4]; // MACIERZ H DLA 4 PUNKTOW CALKOWANIA
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    matricesH[i][j][k] = alfa * (tableDx[i][j] * tableDx[i][k] + tableDy[i][j] * tableDy[i][k]) * detJ[i];
                   // System.out.print(matricesH[i][j][k] + " ");
                }
                //System.out.println();
            }
            //System.out.println();
        }
        double[][] macierzH = new double[4][4];

        macierzH = showMatrixH(points, matricesH);

        return macierzH;
    }
}

