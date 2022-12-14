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
        Aggregation aggragatedMatrix = new Aggregation(grid, globalData);
        aggragatedMatrix.AggregatedGlobalMatrixH(aggragatedMatrix.createListOfHMatrices());
        /*for(int i = 0; i < grid.nEl; i++){
            for (int j = 0; j < grid.EL.get(0).nodes.size(); j++) {
                System.out.println(grid.EL.get(i).nodes.get(j));
            }
        }*/ //wypisywanie wezlow siatki elementow
        //MatrixH matrixH = new MatrixH(grid.getEL().get());
        //Scanner scanner = new Scanner(System.in);
        //int choice = scanner.nextInt();
    }
}
