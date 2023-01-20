import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MatrixHBC extends MatrixMES {
    public MatrixHBC(Element element, double alfa, double tot) {
        this.element = element;
        this.alfa = alfa;
        this.tot = tot;
    }

    Element element;
    double tot;
    double alfa;
    double[] eta2 = {-1, -1 / sqrt(3), 1 / sqrt(3), 1};
    double[] ksi2 = {-1, -1 / sqrt(3), 1 / sqrt(3), 1}; // ksi to E
    double[] eta3 = {-1, -sqrt(3.0 / 5), 0, sqrt(3.0 / 5), 1};
    double[] ksi3 = {-1, -sqrt(3.0 / 5), 0, sqrt(3.0 / 5), 1};
    double[] eta4 = {-1, -0.861136, -0.339981, 0.339981, 0.861136, 1};
    double[] ksi4 = {-1, -0.861136, -0.339981, 0.339981, 0.861136, 1};
    //wages are the same

    int numberOfIntegralPoints; // 2,3 lub 4
    double[] ksi; //wspol punktow calkowania dla
    double[] eta; //wspol punktow calkowania
    //double[]


    public double[][] showTableOneSide(int points, int whichSide) {
        double[][] table = new double[points][4];
        if (whichSide == 0) {
            if (points == 2) {
                table[0][0] = myShapeFunctions.get(0).apply(ksi2[1], eta2[0]);
                table[0][1] = myShapeFunctions.get(1).apply(ksi2[1], eta2[0]);
                table[1][0] = myShapeFunctions.get(0).apply(ksi2[2], eta2[0]);
                table[1][1] = myShapeFunctions.get(1).apply(ksi2[2], eta2[0]);

            }
            if (points == 3) {
                table[0][0] = myShapeFunctions.get(0).apply(ksi3[1], eta3[0]);
                table[0][1] = myShapeFunctions.get(1).apply(ksi3[1], eta3[0]);
                table[1][0] = myShapeFunctions.get(0).apply(ksi3[2], eta3[0]);
                table[1][1] = myShapeFunctions.get(1).apply(ksi3[2], eta3[0]);
                table[2][0] = myShapeFunctions.get(0).apply(ksi3[3], eta3[0]);
                table[2][1] = myShapeFunctions.get(1).apply(ksi3[3], eta3[0]);
            }
            if (points == 4) {
                table[0][0] = myShapeFunctions.get(0).apply(ksi4[1], eta4[0]);
                table[0][1] = myShapeFunctions.get(1).apply(ksi4[1], eta4[0]);
                table[1][0] = myShapeFunctions.get(0).apply(ksi4[2], eta4[0]);
                table[1][1] = myShapeFunctions.get(1).apply(ksi4[2], eta4[0]);
                table[2][0] = myShapeFunctions.get(0).apply(ksi4[3], eta4[0]);
                table[2][1] = myShapeFunctions.get(1).apply(ksi4[3], eta4[0]);
                table[3][0] = myShapeFunctions.get(0).apply(ksi4[4], eta4[0]);
                table[3][1] = myShapeFunctions.get(1).apply(ksi4[4], eta4[0]);
            }
        }
        if (whichSide == 1) {
            if (points == 2) {
                table[0][1] = myShapeFunctions.get(1).apply(ksi2[3], eta2[1]);
                table[0][2] = myShapeFunctions.get(2).apply(ksi2[3], eta2[1]);
                table[1][1] = myShapeFunctions.get(1).apply(ksi2[3], eta2[2]);
                table[1][2] = myShapeFunctions.get(2).apply(ksi2[3], eta2[2]);

            }
            if (points == 3) {
                table[0][1] = myShapeFunctions.get(1).apply(ksi3[4], eta3[1]);
                table[0][2] = myShapeFunctions.get(2).apply(ksi3[4], eta3[1]);
                table[1][1] = myShapeFunctions.get(1).apply(ksi3[4], eta3[2]);
                table[1][2] = myShapeFunctions.get(2).apply(ksi3[4], eta3[2]);
                table[2][1] = myShapeFunctions.get(1).apply(ksi3[4], eta3[3]);
                table[2][2] = myShapeFunctions.get(2).apply(ksi3[4], eta3[3]);
            }
            if (points == 4) {
                table[0][1] = myShapeFunctions.get(1).apply(ksi4[5], eta4[1]);
                table[0][2] = myShapeFunctions.get(2).apply(ksi4[5], eta4[1]);
                table[1][1] = myShapeFunctions.get(1).apply(ksi4[5], eta4[2]);
                table[1][2] = myShapeFunctions.get(2).apply(ksi4[5], eta4[2]);
                table[2][1] = myShapeFunctions.get(1).apply(ksi4[5], eta4[3]);
                table[2][2] = myShapeFunctions.get(2).apply(ksi4[5], eta4[3]);
                table[3][1] = myShapeFunctions.get(1).apply(ksi4[5], eta4[4]);
                table[3][2] = myShapeFunctions.get(2).apply(ksi4[5], eta4[4]);
            }
        }
        if (whichSide == 2) {
            if (points == 2) {
                table[0][2] = myShapeFunctions.get(2).apply(ksi2[1], eta2[3]);
                table[0][3] = myShapeFunctions.get(3).apply(ksi2[1], eta2[3]);
                table[1][2] = myShapeFunctions.get(2).apply(ksi2[2], eta2[3]);
                table[1][3] = myShapeFunctions.get(3).apply(ksi2[2], eta2[3]);

            }
            if (points == 3) {
                table[0][2] = myShapeFunctions.get(2).apply(ksi3[1], eta3[4]);
                table[0][3] = myShapeFunctions.get(3).apply(ksi3[1], eta3[4]);
                table[1][2] = myShapeFunctions.get(2).apply(ksi3[2], eta3[4]);
                table[1][3] = myShapeFunctions.get(3).apply(ksi3[2], eta3[4]);
                table[2][2] = myShapeFunctions.get(2).apply(ksi3[3], eta3[4]);
                table[2][3] = myShapeFunctions.get(3).apply(ksi3[3], eta3[4]);

            }
            if (points == 4) {
                table[0][2] = myShapeFunctions.get(2).apply(ksi4[1], eta4[5]);
                table[0][3] = myShapeFunctions.get(3).apply(ksi4[1], eta4[5]);
                table[1][2] = myShapeFunctions.get(2).apply(ksi4[2], eta4[5]);
                table[1][3] = myShapeFunctions.get(3).apply(ksi4[2], eta4[5]);
                table[2][2] = myShapeFunctions.get(2).apply(ksi4[3], eta4[5]);
                table[2][3] = myShapeFunctions.get(3).apply(ksi4[3], eta4[5]);
                table[3][2] = myShapeFunctions.get(2).apply(ksi4[4], eta4[5]);
                table[3][3] = myShapeFunctions.get(3).apply(ksi4[4], eta4[5]);
            }

        }
        if (whichSide == 3) {
            if (points == 2) {
                table[0][3] = myShapeFunctions.get(3).apply(ksi2[0], eta2[1]);
                table[0][0] = myShapeFunctions.get(0).apply(ksi2[0], eta2[1]);
                table[1][3] = myShapeFunctions.get(3).apply(ksi2[0], eta2[2]);
                table[1][0] = myShapeFunctions.get(0).apply(ksi2[0], eta2[2]);

            }
            if (points == 3) {
                table[0][3] = myShapeFunctions.get(3).apply(ksi3[0], eta3[1]);
                table[0][0] = myShapeFunctions.get(0).apply(ksi3[0], eta3[1]);
                table[1][3] = myShapeFunctions.get(3).apply(ksi3[0], eta3[2]);
                table[1][0] = myShapeFunctions.get(0).apply(ksi3[0], eta3[2]);
                table[2][3] = myShapeFunctions.get(3).apply(ksi3[0], eta3[3]);
                table[2][0] = myShapeFunctions.get(0).apply(ksi3[0], eta3[3]);

            }
            if (points == 4) {
                table[0][3] = myShapeFunctions.get(3).apply(ksi4[0], eta4[1]);
                table[0][0] = myShapeFunctions.get(0).apply(ksi4[0], eta4[1]);
                table[1][3] = myShapeFunctions.get(3).apply(ksi4[0], eta4[2]);
                table[1][0] = myShapeFunctions.get(0).apply(ksi4[0], eta4[2]);
                table[2][3] = myShapeFunctions.get(3).apply(ksi4[0], eta4[3]);
                table[2][0] = myShapeFunctions.get(0).apply(ksi4[0], eta4[3]);
                table[3][3] = myShapeFunctions.get(3).apply(ksi4[0], eta4[4]);
                table[3][0] = myShapeFunctions.get(0).apply(ksi4[0], eta4[4]);
            }
        }


        //System.out.println("Which side: " + whichSide);
        for (int i = 0; i < points; i++) {
            for (int j = 0; j < 4; j++) {
                //  System.out.print(table[i][j] + " ");
            }
            // System.out.println();
        }
        return table;
    }

    public double[] calculateVectorP(int points) {
        double[][] vectorP = new double[4][4];
        for (int i = 0; i < element.getSides().size(); i++) {
            //System.out.println("Ktora sciana : " + i);
            if (element.getSides().get(i).isSideBorderCondition()) {
                double[][] tableKsiEta = showTableOneSide(points, i);
                double detJ;
                detJ = calculateDetJ(element.getSides().get(i).getNodeA(), element.getSides().get(i).getNodeB());
                for (int j = 0; j < 4; j++) {
                    if (points == 2) {
                        vectorP[i][j] = alfa * detJ * tot * (wages2[0] * tableKsiEta[0][j] +
                                wages2[1] * tableKsiEta[1][j]);
                    }
                    if (points == 3) {
                        vectorP[i][j] = alfa * detJ * tot * (wages3[0] * tableKsiEta[0][j] +
                                wages3[1] * tableKsiEta[1][j] +
                                wages3[2] * tableKsiEta[2][j]);
                    }
                    if (points == 4) {
                        vectorP[i][j] += alfa * detJ * tot * (wages4[0] * tableKsiEta[0][j] +
                                wages4[1] * tableKsiEta[1][j] +
                                wages4[2] * tableKsiEta[2][j] +
                                wages4[3] * tableKsiEta[3][j]);
                    }
                    //System.out.print(vectorP[i][j] + " Vector P ");
                }
                //System.out.println();
            }
        }
        double[] vectorPLocal = new double[4];
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                vectorPLocal[j] += vectorP[k][j];
            }
            //    System.out.print(String.format("|%-5.5f|", vectorPLocal[j]));
        }
        return vectorPLocal;
    }

    public double[][] calculateMatrixHBC(int points) {
        double[][][] listOfSideMatrix = new double[4][4][4];
        for (int i = 0; i < element.getSides().size(); i++) {
            if (element.getSides().get(i).isSideBorderCondition()) {
                double[][] tableKsiEta = new double[points][4];
                tableKsiEta = showTableOneSide(points, i);
                double detJ;
                detJ = calculateDetJ(element.getSides().get(i).getNodeA(), element.getSides().get(i).getNodeB());

                for (int m = 0; m < 4; m++) {
                    for (int j = 0; j < 4; j++) {
                        if (points == 2)
                            listOfSideMatrix[i][m][j] = detJ * alfa * (wages2[0] * tableKsiEta[0][m] * tableKsiEta[0][j] +
                                    wages2[1] * tableKsiEta[1][m] * tableKsiEta[1][j]);
                        if (points == 3)
                            listOfSideMatrix[i][m][j] = detJ * alfa * (wages3[0] * tableKsiEta[0][m] * tableKsiEta[0][j] +
                                    wages3[1] * tableKsiEta[1][m] * tableKsiEta[1][j] +
                                    wages3[2] * tableKsiEta[2][m] * tableKsiEta[2][j]);
                        if (points == 4)
                            listOfSideMatrix[i][m][j] = detJ * alfa * (wages4[0] * tableKsiEta[0][m] * tableKsiEta[0][j] +
                                    wages4[1] * tableKsiEta[1][m] * tableKsiEta[1][j] +
                                    wages4[2] * tableKsiEta[2][m] * tableKsiEta[2][j] +
                                    wages4[3] * tableKsiEta[3][m] * tableKsiEta[3][j]);

                        //  System.out.print(listOfSideMatrix[i][m][j]);
                    }
                    //  System.out.println();
                }

            } else {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        listOfSideMatrix[i][j][k] = 0;
//                        System.out.print(listOfSideMatrix[i][j][k]);
                    }
//                    System.out.println();
                }
            }
//            System.out.println("Ktora powierzchnia : " + i);

        }
        double[][] matrixHBC = new double[4][4];
        //  System.out.println("MatrixHBC");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    matrixHBC[j][k] += listOfSideMatrix[i][j][k];

                }
            }
        }
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                System.out.print(String.format("|%-5.5f|", matrixHBC[j][k]));
            }
            System.out.println();
        }
        System.out.println();
        return matrixHBC;
    }

    //funkcja obliczająca jakobian z prezentacji generacja macierzy hbc, długość wektora,odcinka o koncach w dwoch node'ach
    public double calculateDetJ(Node node1, Node node2) {
        return sqrt(pow(node1.getX() - node2.getX(), 2) + pow(node1.getY() - node2.getY(), 2)) / 2.0;
    }


}
