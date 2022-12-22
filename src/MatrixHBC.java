import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

// zgodnie z zadaniem, powinnismy tu umiescic
// ilosc punktow calkowania po powierzchni tj 2,3 lub 4
// wspolrzedne tych punktow calkowania
// na koniec wartosci funkcji ksztaltu w punktach calkowania
public class MatrixHBC extends MatrixMES implements ShapeFunction  {
    public MatrixHBC(double[] x, double[] y, double conductivity) {
        this.x = x;
        this.y = y;
        this.conductivity = conductivity;
    }
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
    public double[][] showTableOneSide(int size){
        size/=size;
        double[][] table = new double[size][4];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                if(size == 2) table[i][j] = myShapeFunctions.get(j).apply(eta2[i], ksi2[i]);
                if(size == 3) table[i][j] = myShapeFunctions.get(j).apply(eta3[i], ksi3[i]);
                if(size == 4) table[i][j] = myShapeFunctions.get(j).apply(eta4[i], ksi4[i]);
            }
        }
        return table;
    }





}
