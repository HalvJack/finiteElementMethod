import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        ReadFromFile readFromFile = new ReadFromFile();
        readFromFile.OverallFunction();
        GlobalData globalData = readFromFile.globalData;
        Grid grid = readFromFile.grid;
        Aggregation aggragatedMatrix = new Aggregation(grid, globalData);
        aggragatedMatrix.AggregatedGlobalMatrixH(aggragatedMatrix.createListOfHMatrices());
        /*for(int i = 0; i < grid.nEl; i++){
            for (int j = 0; j < grid.EL.get(0).nodes.size(); j++) {
                System.out.println(grid.EL.get(i).nodes.get(j));
            }
        }*/ //wypisywanie wezlow siatki elementow
    }
}
