import java.util.Arrays;

public class MatrixH extends MatrixMES {
    public MatrixH(double[] x, double[] y, double conductivity) {
        this.x = x;
        this.y = y;
        this.conductivity = conductivity;
    }



    /*private double[] calculate1DivideByDet(double matrix[][]) {e
        double[] array = new double[matrix.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1 / (matrix[i][0] * matrix[i][3]);
            //System.out.println(array[i]);
        }
        return array;
    }*/

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
        double[] detJ = new double[size];
        detJ = calculateDetJ(matrixFirst);
        double[][] matrixFirstReversedJakobian = new double[size][4];
        for (int i = 0; i < matrixFirst.length; i++) {
            matrixFirstReversedJakobian[i][0]=(1/detJ[i])*matrixFirst[i][3];
            matrixFirstReversedJakobian[i][1]=(1/detJ[i])*(-matrixFirst[i][1]);
            matrixFirstReversedJakobian[i][2]=(1/detJ[i])*(-matrixFirst[i][2]);
            matrixFirstReversedJakobian[i][3]=(1/detJ[i])*matrixFirst[i][0];
        }
        double[][] tableDx = new double[size][4];
        double[][] tableDy = new double[size][4];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                tableDx[i][j] = matrixFirstReversedJakobian[i][0] * tableEta[i][j] + matrixFirstReversedJakobian[i][1] * tableKsi[i][j]; //slajd 13, tabelka z dx
                tableDy[i][j] = matrixFirstReversedJakobian[i][2] * tableEta[i][j] + matrixFirstReversedJakobian[i][3] * tableKsi[i][j]; //slajd 13, tabelka z dy
               // System.out.print(i + " " + j + " " + tableDx[i][j]);
                //System.out.print(i + " " + j + " " + tableDy[i][j]);
            }
            //System.out.println();
        }
        double[] oneByDetJ = new double[size];
        for (int i = 0; i < size; i++) {
            oneByDetJ[i] = 1 / detJ[i]; // TUTAJ DOLICZAM JESZCZE DETJ BO WYZEJ OD RAZU MIALEM POLICZONE 1/DETJ
        }
        double[][][] matricesH = new double[size][4][4]; // MACIERZ H DLA 4 PUNKTOW CALKOWANIA
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    matricesH[i][j][k] = conductivity * (tableDx[i][j] * tableDx[i][k] + tableDy[i][j] * tableDy[i][k]) * detJ[i];
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

