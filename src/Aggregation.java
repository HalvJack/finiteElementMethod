import java.util.ArrayList;
import java.util.List;

public class Aggregation {
    List<double[][]> listOfMatricesH = new ArrayList<>();
    Grid grid;
    GlobalData globalData;
    double[][] aggregatedMatrix =  new double[16][16];

    public Aggregation(Grid grid, GlobalData globalData) {
        this.grid = grid;
        this.globalData = globalData;
        //aggregatedMatrix = new double[grid.getnNd()][grid.getnNd()];
    }

    public Aggregation() {
    }
    public List<double[][]> createListOfHMatrices(){
        List<double[]> xList = new ArrayList<>();
        List<double[]> yList = new ArrayList<>();
        List<double[][]> matrixHList = new ArrayList<>();
        for (int i = 0; i < grid.nEl; i++) {
            double[] x = new double[4];
            double[] y = new double[4];
            for (int j = 0; j < 4; j++) {
                x[j] = grid.getEL().get(i).getNodes().get(j).getX();
                y[j] = grid.getEL().get(i).getNodes().get(j).getY();
//                System.out.println(j + " " + x[j]);
//                System.out.println(j + " " + y[j]);
            }
            xList.add(x);
            yList.add(y);
            MatrixH matrixH = new MatrixH(x, y, globalData.Conductivity);
            matrixHList.add(matrixH.calculateMatrixH(2));

        }
        return matrixHList;
    }
    public void AggregatedGlobalMatrixH(List<double[][]> matrixHList){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.println(grid.getEL().get(i).getID().get(j)-1);
                    System.out.println(grid.getEL().get(i).getID().get(k)-1);
                    aggregatedMatrix[grid.getEL().get(i).getID().get(j)-1][grid.getEL().get(i).getID().get(k)-1] +=listOfMatricesH.get(0)[j][k];

                }
            }
        }
        writeAggregatedMatrix(aggregatedMatrix);
    }

    private void writeAggregatedMatrix(double[][] aggregatedMatrix) {
        for (int i = 0; i <aggregatedMatrix.length ; i++) {
            for (int j = 0; j <aggregatedMatrix.length ; j++) {
                System.out.println(aggregatedMatrix[i][j]);
            }
        }

    }
}
