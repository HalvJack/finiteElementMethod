import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        ReadFromFile readFromFile = new ReadFromFile();
        readFromFile.OverallFunction();
        //readFromFile.writeData();
        Grid grid = new Grid();
        GlobalData globalData = new GlobalData();
        globalData = readFromFile.globalData;
        grid = readFromFile.grid;
        /*for(int i = 0; i < grid.nEl; i++){
            for (int j = 0; j < grid.EL.get(0).nodes.size(); j++) {
                System.out.println(grid.EL.get(i).nodes.get(j));
            }
        }*/ //wypisywanie wezlow siatki elementow
        //MatrixH matrixH = new MatrixH(grid.getEL().get());
        //Scanner scanner = new Scanner(System.in);
        //int choice = scanner.nextInt();
        List<double[]> xList = new ArrayList<>();
        List<double[]> yList = new ArrayList<>();
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
            //MatrixH matrixH = new MatrixH(x, y);
            //matrixH.calculateMatrixH(2);

        }
        MatrixH matrixH = new MatrixH(xList.get(5), yList.get(5));
        matrixH.calculateMatrixH(2);
    }
}
