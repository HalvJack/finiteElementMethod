public class GaussianElimination {
    private static final double EPSILON = 1e-10;

    // Gaussian elimination with partial pivoting
    public static double[] lsolve(double[][] matrixHGlobal, double[] vectorP) {
        int n = vectorP.length;

        for (int p = 0; p < n; p++) {

            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(matrixHGlobal[i][p]) > Math.abs(matrixHGlobal[max][p])) {
                    max = i;
                }
            }
            double[] temp = matrixHGlobal[p]; matrixHGlobal[p] = matrixHGlobal[max]; matrixHGlobal[max] = temp;
            double   t    = vectorP[p]; vectorP[p] = vectorP[max]; vectorP[max] = t;

            // singular or nearly singular
            if (Math.abs(matrixHGlobal[p][p]) <= EPSILON) {
                throw new ArithmeticException("Matrix is singular or nearly singular");
            }

            // pivot within A and b
            for (int i = p + 1; i < n; i++) {
                double alpha = matrixHGlobal[i][p] / matrixHGlobal[p][p];
                vectorP[i] -= alpha * vectorP[p];
                for (int j = p; j < n; j++) {
                    matrixHGlobal[i][j] -= alpha * matrixHGlobal[p][j];
                }
            }
        }

        // back substitution
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += matrixHGlobal[i][j] * x[j];
            }
            x[i] = (vectorP[i] - sum) / matrixHGlobal[i][i];
        }
        return x;
    }
}