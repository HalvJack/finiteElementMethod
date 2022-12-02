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
        /*for (:
             ) {
            
        }*/

    }
}
