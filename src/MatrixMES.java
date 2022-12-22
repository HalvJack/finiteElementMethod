import static java.lang.Math.sqrt;

public abstract class MatrixMES {
    double[] x = new double[4];
    double[] y = new double[4];
    double conductivity = 0;

    double[] eta2 = {-1 / sqrt(3), -1 / sqrt(3), 1 / sqrt(3), 1 / sqrt(3)};
    double[] ksi2 = {-1 / sqrt(3), 1 / sqrt(3), -1 / sqrt(3), 1 / sqrt(3)}; // ksi to E
    double[] eta3 = {-sqrt(3.0 / 5), -sqrt(3.0 / 5), -sqrt(3.0 / 5), 0, 0, 0, sqrt(3.0 / 5), sqrt(3.0 / 5), sqrt(3.0 / 5)};
    double[] ksi3 = {-sqrt(3.0 / 5), 0, sqrt(3.0 / 5), -sqrt(3.0 / 5), 0, sqrt(3.0 / 5), -sqrt(3.0 / 5), 0, sqrt(3.0 / 5)};
    double[] eta4 = {-0.861136, -0.861136, -0.861136, -0.861136, -0.339981, -0.339981, -0.339981, -0.339981, 0.339981, 0.339981, 0.339981, 0.339981, 0.861136, 0.861136, 0.861136, 0.861136};
    double[] ksi4 = {-0.861136, -0.339981, 0.339981, 0.861136, -0.861136, -0.339981, 0.339981, 0.861136, -0.861136, -0.339981, 0.339981, 0.861136, -0.861136, -0.339981, 0.339981, 0.861136};
    double[] wages2 = {1.0, 1.0};
    double[] wages3 = {5.0 / 9.0, 8.0 / 9.0, 5.0 / 9.0};
    double[] wages4 = {0.347855, 0.652145, 0.652145, 0.347855};

    protected double[] calculate1DivideByDet(double matrix[][]) {
        double[] array = new double[matrix.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1 / (matrix[i][0] * matrix[i][3]);
            //System.out.println(array[i]);
        }
        return array;
    }
}
