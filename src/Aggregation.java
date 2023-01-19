import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;

public class Aggregation {
    Grid grid;
    GlobalData globalData;
    double[][] aggregatedMatrix;
    double[] aggregatedVectorP;

    public Aggregation(Grid grid, GlobalData globalData) {
        this.grid = grid;
        this.globalData = globalData;
        aggregatedMatrix = new double[grid.getnNd()][grid.getnNd()];
        aggregatedVectorP = new double[grid.getnNd()];
    }

    public double[][][] createListOfHMatrices() {
        double[][][] matrixHList = new double[9][4][4];
        double[][] matrixHpom = new double[4][4];
        for (int i = 0; i < 9; i++) {
            double[] x = new double[4];
            double[] y = new double[4];
            for (int j = 0; j < 4; j++) {
                x[j] = grid.getEL().get(i).getNodes().get(j).getX();
                y[j] = grid.getEL().get(i).getNodes().get(j).getY();
                System.out.println(j + " " + x[j]);
                System.out.println(j + " " + y[j]);
            }
            MatrixH matrixH = new MatrixH(x, y, globalData.Conductivity);
            matrixHpom = matrixH.calculateMatrixH(2);
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    matrixHList[i][j][k] = matrixHpom[j][k];
                }
            }
        }
        //writeHMatrices(matrixHList);
        return matrixHList;
    }
    public double[][][] createListOfMatricesC(){;
        double[][][] matrixCList = new double[9][4][4];
        double[][] matrixCpom = new double[4][4];
        for (int i = 0; i < 9; i++) {
            double[] x = new double[4];
            double[] y = new double[4];
            for (int j = 0; j < 4; j++) {
                x[j] = grid.getEL().get(i).getNodes().get(j).getX();
                y[j] = grid.getEL().get(i).getNodes().get(j).getY();
                System.out.println(j + " " + x[j]);
                System.out.println(j + " " + y[j]);
            }
            MatrixC matrixC = new MatrixC(x, y, globalData.getDensity(), globalData.getSpecificHeat());
            matrixCpom = matrixC.calculateMatrixC(2);
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    matrixCList[i][j][k] = matrixCpom[j][k];
                }
            }
        }
        writeCMatrices(matrixCList);
        return matrixCList;
    }

    private void writeCMatrices(double[][][] matrixCList) {
        System.out.println("MATRICES C HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(matrixCList[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private void writeHMatrices(double matrixHList[][][]) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(matrixHList[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

    }

    public void AggregatedGlobalMatrixH(double[][][] matrixHList, double[][][] matrixHBCList, double[][] vectorPList, double[][][] myLIstOfMatricesC) {
        double[][] aggreagatedMatrixC = new double[16][16];
        //double[][] matrixHplusHBC = new double[16][16];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    aggreagatedMatrixC[grid.getEL().get(i).getID().get(j) - 1][grid.getEL().get(i).getID().get(k) - 1] += myLIstOfMatricesC[i][j][k];
                    //matrixHplusHBC[grid.getEL().get(i).getID().get(j) - 1][grid.getEL().get(i).getID().get(k) - 1] += myLIstOfMatricesC[i][j][k] + matrixHBCList[i][j][k];
                }
            }
        }
        //writeH(matrixHList);
        //writeVectorPList(vectorPList);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                aggregatedVectorP[grid.getEL().get(i).getID().get(j) - 1] += vectorPList[i][j];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    matrixHList[i][j][k] += matrixHBCList[i][j][k];
                    aggregatedMatrix[grid.getEL().get(i).getID().get(j) - 1][grid.getEL().get(i).getID().get(k) - 1] += matrixHList[i][j][k];
                }
            }
        }

        //writeHBC(matrixHBCList);
        System.out.println("HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        writeAggregatedMatrix(aggregatedMatrix);
        //writeVectorP(aggregatedVectorP);
        //writeAggregatedHplusHBC(matrixHList);
        //writeAggregatedMatrixC(aggreagatedMatrixC);
        for (int i = 0; i < grid.getnNd(); i++) {
            for (int j = 0; j < grid.getnNd(); j++) {
                aggregatedMatrix[i][j] += aggreagatedMatrixC[i][j] / globalData.getSimulationStepTime(); //borderConditionMatrix jako h plus hbc plus vector c dalem
            }
        }
        double[] globalVectorPCopy = new double[aggregatedVectorP.length];
        for (int i = 0; i < globalVectorPCopy.length; i++) {
            globalVectorPCopy[i] = aggregatedVectorP[i];
        }
        double[][] matrixHplusHBCCopy = new double[grid.getnNd()][grid.getnNd()];
        for (int i = 0; i < grid.getnNd(); i++) {
            for (int j = 0; j < grid.getnNd(); j++) {
                matrixHplusHBCCopy[i][j] = aggregatedMatrix[i][j];
            }
        }
        double[] temperatureVector = new double[grid.getnNd()];
        for (int i = 0; i < grid.getnNd(); i++) {
            temperatureVector[i] = globalData.getInitialTemp();
        }
        for (int i = 0; i < globalData.getSimulationTime(); i+= globalData.getSimulationStepTime()) {
            for (int j = 0; j < grid.getnNd(); j++) {
                for (int k = 0; k < grid.getnNd(); k++) {
                    aggregatedVectorP[j] += (aggreagatedMatrixC[j][k] / globalData.getSimulationStepTime()) *temperatureVector[k];
                }
            }
            temperatureVector = GaussianElimination.lsolve(aggregatedMatrix, aggregatedVectorP);
            double min = Arrays.stream(temperatureVector).summaryStatistics().getMin();
            double max = Arrays.stream(temperatureVector).summaryStatistics().getMax();
            System.out.println("Minimum " + min);
            System.out.println("Maksimum " + max);
            for (int j = 0; j < temperatureVector.length; j++) {
                System.out.print(temperatureVector[j] + " ");
            }
            System.out.println();
            for (int j = 0; j < aggregatedVectorP.length; j++) {
                aggregatedVectorP[j] = globalVectorPCopy[j];
            }

            for (int m = 0; m < grid.getnNd(); m++) {
                for (int j = 0; j < grid.getnNd(); j++) {
                    aggregatedMatrix[m][j] = matrixHplusHBCCopy[m][j];
                }
            }
        }
    }

    private void writeAggregatedMatrixC(double[][] aggreagatedMatrixC) {
        System.out.println("MACIERZ C ZAGREAGOWANA!!!!!!");
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(aggreagatedMatrixC[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void writeVectorPList(double vectorPList[][]) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(vectorPList[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void writeH(double matrixHList[][][]) {
        for (int i = 0; i < 9; i++) {
            System.out.println("Element " + i + 1);
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(matrixHList[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private void writeHBC(double matrixHBCList[][][]) {
        for (int i = 0; i < 9; i++) {
            System.out.println("Element " + i + 1);
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(matrixHBCList[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private void writeVectorP(double[] aggregatedVectorP) {
        for (int i = 0; i < aggregatedVectorP.length; i++) {
            System.out.println(aggregatedVectorP[i]);
        }
    }

    private void writeAggregatedMatrix(double[][] aggregatedMatrix) {
        DecimalFormat df = new DecimalFormat("0.00");
        //df.format(
        for (int i = 0; i < aggregatedMatrix.length; i++) {
            for (int j = 0; j < aggregatedMatrix.length; j++) {
                System.out.print(String.format("|%-5.2f|", aggregatedMatrix[i][j]));
            }
            System.out.println();
        }

    }

    private void writeAggregatedHplusHBC(double matrixHList[][][]) {
        for (int i = 0; i < 9; i++) {
            System.out.println("Element " + i + 1);
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(matrixHList[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
