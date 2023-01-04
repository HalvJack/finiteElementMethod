import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

// zgodnie z zadaniem, powinnismy tu umiescic
// ilosc punktow calkowania po powierzchni tj 2,3 lub 4
// wspolrzedne tych punktow calkowania
// na koniec wartosci funkcji ksztaltu w punktach calkowania
public class MatrixHBC extends MatrixMES implements ShapeFunction {
    public MatrixHBC(Node node1, Node node2, double[] x, double[] y, double conductivity) {
        myNode1 = node1;
        myNode2 = node2;
        this.x = x;
        this.y = y;
        this.conductivity = conductivity;
    }

    Node myNode1;
    Node myNode2;
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

    //function we will be integrate after
    List<ShapeFunction<Double, Double, Double>> myShapeFunctions = new ArrayList<>() {
        { // funkcje ksztaltu
            add((ksi, eta) -> 0.25 * (1 - ksi) * (1 - eta));
            add((ksi, eta) -> 0.25 * (1 + ksi) * (1 - eta));
            add((ksi, eta) -> 0.25 * (1 + ksi) * (1 + eta));
            add((ksi, eta) -> 0.25 * (1 - ksi) * (1 + eta));
        }
    };

    public double[][] showTableOneSide(int points) {
        double[][] table = new double[points][4];
        for (int i = 0; i < points; i++) {
            for (int j = 0; j < 4; j++) {
                if (points == 2) table[i][j] = myShapeFunctions.get(j).apply(eta2[i], ksi2[i]);
                if (points == 3) table[i][j] = myShapeFunctions.get(j).apply(eta3[i], ksi3[i]);
                if (points == 4) table[i][j] = myShapeFunctions.get(j).apply(eta4[i], ksi4[i]);
            }
        }
        return table;
    }

    public double[][] calculateMatrixHBC(int points) {
        double[][] matrixHBC = new double[4][4];
        double[][] tableKsiEta = new double[points][4];
        double[][] firstByDet = new double[points][4];
        //firstByDet = calculate1DivideByDet();
        tableKsiEta = showTableOneSide(points);
        double detJ = calculateDetJ(myNode1, myNode2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (points == 2)
                    matrixHBC[i][j] = detJ * conductivity * (wages2[0] * tableKsiEta[0][i] * tableKsiEta[0][j] +
                            wages2[1] * tableKsiEta[1][i] * tableKsiEta[1][j]);
                if (points == 3)
                    matrixHBC[i][j] = detJ * conductivity * (wages3[0] * tableKsiEta[0][i] * tableKsiEta[0][j] +
                            wages3[1] * tableKsiEta[1][i] * tableKsiEta[1][j] +
                            wages3[2] * tableKsiEta[2][i] * tableKsiEta[2][j]);
                if (points == 4)
                    matrixHBC[i][j] = detJ * conductivity * (wages4[0] * tableKsiEta[0][i] * tableKsiEta[0][j] +
                            wages4[1] * tableKsiEta[1][i] * tableKsiEta[1][j] +
                            wages4[2] * tableKsiEta[2][i] * tableKsiEta[2][j] +
                            wages4[3] * tableKsiEta[3][i] * tableKsiEta[3][j]);
            }
        }
        return matrixHBC;
    }

    //funkcja obliczająca jakobian z prezentacji generacja macierzy hbc, długość wektora,odcinka o koncach w dwoch node'ach
    public double calculateDetJ(Node node1, Node node2) {
        return sqrt(pow(node1.getX() - node2.getX(), 2) + pow(node1.getY() - node2.getY(), 2));
    }


}
