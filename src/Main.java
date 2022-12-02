import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        /*MatrixH matrixH = new MatrixH();
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        matrixH.calculateMatrixH(i);*/
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
        }*/

    }
}
