public class MatrixC extends MatrixMES {
    public MatrixC(double[] x, double[] y, double density, double specificHeat) {
        this.x = x;
        this.y = y;
        this.density = density;
        this.specificHeat = specificHeat;
    }

    public double[][] calculateMatrixC(int points) {
        int size = points * points;
        double[][] tableKsiEta = new double[size][4];
        double[] detJ = new double[size];
        detJ = calculateArrayUsefulToCalculateDetJ(points);
        tableKsiEta = showTableKsiEta(size);
        double[][] matrixC = showMatrixC(points, tableKsiEta, detJ);
        return matrixC;
    }

    private double[] calculateArrayUsefulToCalculateDetJ(int points) {
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
        return detJ;
    }

    private double[][] showMatrixC(int points, double[][] tableKsiEta, double[] detJ) {
        int size = points * points;
        double[] wages = new double[points];
        double[][][] matricesC = new double[size][4][4];
        double[][] matrixC = new double[4][4];
        if (points == 2) wages = wages2;
        if (points == 3) wages = wages3;
        if (points == 4) wages = wages4;
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    matricesC[k][i][j] = tableKsiEta[k][i] * tableKsiEta[k][j];
                }
            }
        }
        matrixC = sumMatricesC(matricesC, points, wages, detJ);
        writeMatrixC(matrixC);
        return matrixC;
    }

    private void writeMatrixC(double[][] matrixC) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matrixC[i][j] + " ");
            }
            System.out.println();
        }
    }

    private double[][] sumMatricesC(double[][][] matricesC, int points, double[] wages, double[] detJ) {
        double[][] matrixC = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (points == 2) {
                    matrixC[i][j] += specificHeat * density * (detJ[0] * matricesC[0][i][j] * wages[0] * wages[0] +
                            detJ[1] * matricesC[1][i][j] * wages[1] *
                                    wages[0] + detJ[2] * matricesC[2][i][j] * wages[0] * wages[1] + detJ[3] *
                            matricesC[3][i][j] * wages[1] * wages[1]);
                }
                if (points == 3) {
                    matrixC[i][j] += specificHeat * density * (detJ[0] * matricesC[0][i][j] * wages[0] * wages[0] +
                            detJ[1] * matricesC[1][i][j] * wages[0] * wages[1] +
                            detJ[2] * matricesC[2][i][j] * wages[0] * wages[2] +
                            detJ[3] * matricesC[3][i][j] * wages[1] * wages[0] +
                            detJ[4] * matricesC[4][i][j] * wages[1] * wages[1] +
                            detJ[5] * matricesC[5][i][j] * wages[1] * wages[2] +
                            detJ[6] * matricesC[6][i][j] * wages[2] * wages[0] +
                            detJ[7] * matricesC[7][i][j] * wages[2] * wages[1] +
                            detJ[8] * matricesC[8][i][j] * wages[2] * wages[2]);
                }
                if (points == 4) {
                    matrixC[i][j] = specificHeat * density * (detJ[0] * matricesC[0][i][j] * wages[0] *
                            wages[0] + detJ[1] * matricesC[1][i][j] * wages[0] * wages[1] +
                            detJ[2] * matricesC[2][i][j] * wages[0] * wages[2] +
                            detJ[3] * matricesC[3][i][j] * wages[0] * wages[3] +
                            detJ[4] * matricesC[4][i][j] * wages[1] * wages[0] +
                            detJ[5] * matricesC[5][i][j] * wages[1] * wages[1] +
                            detJ[6] * matricesC[6][i][j] * wages[1] * wages[2] +
                            detJ[7] * matricesC[7][i][j] * wages[1] * wages[3] +
                            detJ[8] * matricesC[8][i][j] * wages[2] * wages[0] +
                            detJ[9] * matricesC[9][i][j] * wages[2] * wages[1] +
                            detJ[10] * matricesC[10][i][j] * wages[2] * wages[2] +
                            detJ[11] * matricesC[11][i][j] * wages[2] * wages[3] +
                            detJ[12] * matricesC[12][i][j] * wages[3] * wages[0] +
                            detJ[13] * matricesC[13][i][j] * wages[3] * wages[1] +
                            detJ[14] * matricesC[14][i][j] * wages[3] * wages[2] +
                            detJ[15] * matricesC[15][i][j] * wages[3] * wages[3]);
                }
            }
        }
        return matrixC;
    }

    private double[][] showTableKsiEta(int size) // ksi i eta sa brane z matrixMes gdzie mamy tabele dla pkt calkowania
    {
        double[][] table = new double[size][4];
        double[] ksi = new double[size];
        double[] eta = new double[size];
        if (size == 4) {
            ksi = ksi2;
            eta = eta2;
        }
        if (size == 9) {
            ksi = ksi3;
            eta = eta3;
        }
        if (size == 16) {
            ksi = ksi4;
            eta = eta4;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                table[i][j] = myShapeFunctions.get(j).apply(ksi[i], eta[i]);
            }
        }
        return table;
    }
}
