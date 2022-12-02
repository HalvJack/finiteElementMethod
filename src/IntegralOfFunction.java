import java.util.InputMismatchException;
import java.util.Scanner;

public class IntegralOfFunction {
    public void OverallFunction() {
        System.out.println("Type which function's integral you want to calculate:");
        System.out.println("1. A(x,y) = -5*pow(x,2)*y + 2*x*pow(y,2) + 10");
        System.out.println("2. A(x) = 2*pow(x,2) + 3*x -8");
        Scanner scanner = new Scanner(System.in);
        int chooseFunction = 0;
        int chooseNodeNumber = 0;
        int chooseA = 0;
        int chooseB = 0;
        double pc2[] = {-0.577, 0.577}; // dla liczba wezlow 2
        double pc3[] = {-0.77, 0.0, 0.77}; // dla liczba wezlow 3
        double w2[] = {1, 1}; // wartosc dla liczby wezlow 2
        double w3[] = {0.555, 0.888, 0.555};//wartosci dla liczby wezlow 3
        double result = 0;


        try {
            chooseFunction = scanner.nextInt();
            if (chooseFunction <= 0 || chooseFunction > 2) {
                System.out.println("Not a proper choice");
            }
        } catch (InputMismatchException e) {
            System.out.println("Not a number");
        }
        System.out.println("Type the amount of nodes: 2 or 3");
        try {
            chooseNodeNumber = scanner.nextInt();
            if (chooseFunction < 1 || chooseFunction > 2) {
                System.out.println("Not a proper choice");
            }
        } catch (InputMismatchException e) {
            System.out.println("Not a number");
        }

        if (chooseFunction == 1 && chooseNodeNumber == 2) {
            result = Calculate(pc2, w2, true, chooseNodeNumber);
            System.out.println(result);
        } else if (chooseFunction == 1 && chooseNodeNumber == 3) {
            result = Calculate(pc3, w3, true, chooseNodeNumber);
            System.out.println(result);
        } else if (chooseFunction == 2 && chooseNodeNumber == 2) {
            result = Calculate(pc2, w2, false, chooseNodeNumber);
            System.out.println(result);
        } else if (chooseFunction == 2 && chooseNodeNumber == 3) {
            result = Calculate(pc3, w3, false, chooseNodeNumber);
            System.out.println(result);
        }

    }

    public static double Calculate(double[] pc, double[] w, boolean dimension, int chooseNodeNumber) {
        double sum = 0;
        // dimension false
        if (dimension) { //dla 2d 2 petle
            for (int i = 0; i < chooseNodeNumber; i++) {
                for (int j = 0; j < chooseNodeNumber; j++) {
                    sum += f1(pc[i], pc[j]) * w[i] * w[j];
                }
            }
            return sum;
        } else // calka funkcji 1d
        {
            for (int i = 0; i < chooseNodeNumber; i++) {
                //sum+= f2(pc[i]) * w[i];
                sum += f2(pc[i]) * w[i];
            }
            return sum;
        }
    }

    public static double f1(double pci, double pcj) {

        return -5 * Math.pow(pci, 2) * pcj + 2 * pci * Math.pow(pcj, 2) + 10;
    }

    public static double f2(double pc) {
        return 2 * Math.pow(pc, 2.0) + 3 * pc - 8;
    }

}
