import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.sqrt;

public abstract class MatrixMES {
    double[] x = new double[4];
    double[] y = new double[4];
    double alfa;
    double conductivity;
    double density;
    double specificHeat;
    double[] eta2 = {-1 / sqrt(3), -1 / sqrt(3), 1 / sqrt(3), 1 / sqrt(3)};
    double[] ksi2 = {-1 / sqrt(3), 1 / sqrt(3), -1 / sqrt(3), 1 / sqrt(3)}; // ksi to E
    double[] eta3 = {-sqrt(3.0 / 5), -sqrt(3.0 / 5), -sqrt(3.0 / 5), 0, 0, 0, sqrt(3.0 / 5), sqrt(3.0 / 5), sqrt(3.0 / 5)};
    double[] ksi3 = {-sqrt(3.0 / 5), 0, sqrt(3.0 / 5), -sqrt(3.0 / 5), 0, sqrt(3.0 / 5), -sqrt(3.0 / 5), 0, sqrt(3.0 / 5)};
    double[] eta4 = {-0.861136, -0.861136, -0.861136, -0.861136, -0.339981, -0.339981, -0.339981, -0.339981, 0.339981, 0.339981, 0.339981, 0.339981, 0.861136, 0.861136, 0.861136, 0.861136};
    double[] ksi4 = {-0.861136, -0.339981, 0.339981, 0.861136, -0.861136, -0.339981, 0.339981, 0.861136, -0.861136, -0.339981, 0.339981, 0.861136, -0.861136, -0.339981, 0.339981, 0.861136};
    double[] wages2 = {1.0, 1.0};
    double[] wages3 = {5.0 / 9.0, 8.0 / 9.0, 5.0 / 9.0};
    double[] wages4 = {0.347855, 0.652145, 0.652145, 0.347855};

    List<Function<Double, Double>> myFunctionsKsi = new ArrayList<>() {
        {
            add(aDouble -> 0.25 * (aDouble - 1));
            add(aDouble -> 0.25 * (-1 - aDouble));
            add(aDouble -> 0.25 * (aDouble + 1));
            add(aDouble -> 0.25 * (1 - aDouble));
        }
    };
    List<Function<Double, Double>> myFunctionsEta = new ArrayList<>() {
        {
            add(aDouble -> 0.25 * (aDouble - 1));
            add(aDouble -> 0.25 * (1 - aDouble));
            add(aDouble -> 0.25 * (aDouble + 1));
            add(aDouble -> 0.25 * (-aDouble - 1));
        }
    };
    //function we will be integrate after
    List<ShapeFunction<Double, Double, Double>> myShapeFunctions = new ArrayList<>() {
        { // funkcje ksztaltu
            add((ksi, eta) -> 0.25 * (1 - ksi) * (1 - eta));
            add((ksi, eta) -> 0.25 * (1 + ksi) * (1 - eta));
            add((ksi, eta) -> 0.25 * (1 + ksi) * (1 + eta));
            add((ksi, eta) -> 0.25 * (1 - ksi) * (1 + eta));
        }
    };

    /*ShapeFunction<Double,Double,Double> myShape = new ShapeFunction<Double, Double, Double>() {
        @Override
        public Double apply(Double ksi, Double eta) {
            return 0.25 * (1 - ksi) * (1 - eta);
        }
    };*/

    protected double[] calculateDetJ(double matrix[][]) {
        double[] array = new double[matrix.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = (matrix[i][0] * matrix[i][3] - matrix[i][1] * matrix[i][2]);
            //System.out.println(array[i]);
        }
        return array;
    }
    protected double[][] showTableEta(int size) { // nazwa tabeli, ale to tabelka z dn1/dE ( E to ksi )
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

    protected double[][] showTableKsi(int size) { // nazwa tabeli, ale to tabelka z dn1/dn ( n to eta )
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
}
