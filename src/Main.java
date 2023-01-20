import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        ReadFromFile readFromFile = new ReadFromFile();
        readFromFile.OverallFunction();
        GlobalData globalData = readFromFile.globalData;
        Grid grid = readFromFile.grid;
        double[][][] matrixHBCList = new double[9][4][4];
        double[][] matrixHBCpom = new double[4][4];
        double[][] vectorPList = new double[9][4];
        for (int i = 0; i < grid.nEl; i++) {
            MatrixHBC matrixHBC = new MatrixHBC(grid.getEL().get(i), globalData.getAlfa(), globalData.getTot());
            double[] vectorPPom = matrixHBC.calculateVectorP(4);
            for (int j = 0; j < 4; j++) {
                vectorPList[i][j] = vectorPPom[j];
            }
            matrixHBCpom = matrixHBC.calculateMatrixHBC(4);
            for (int m = 0; m < 4; m++) {
                for (int j = 0; j < 4; j++) {
                    matrixHBCList[i][m][j] = matrixHBCpom[m][j];
                }
            }
        }
        Aggregation aggragatedMatrix = new Aggregation(grid, globalData);
        double[][][] myListOfMatricesH = aggragatedMatrix.createListOfHMatrices();
        double[][][] myListOfMatricesC = aggragatedMatrix.createListOfMatricesC();

        aggragatedMatrix.AggregatedGlobalMatrixH(myListOfMatricesH, matrixHBCList, vectorPList, myListOfMatricesC);
    }
}
