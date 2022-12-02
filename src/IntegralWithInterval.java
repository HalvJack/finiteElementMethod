import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class IntegralWithInterval {
    public void OverallFunction() {

        System.out.println("Function to integrate:");
        System.out.println("3. A(x) = pow(x,2) + 3*x +2");
        Scanner scanner = new Scanner(System.in);
        double result;
        double chooseA = 0, chooseB = 0;
        int chooseNodeNumber = 0;
        double w2[] = {1, 1}; // wartosc dla liczby node'ow 2
        double w3[] = {0.555, 0.888, 0.555}; // wartosci dla liczby node'ow 3
        List<Double> pc;
        System.out.println("Type the interval ( a and b");
        try {
            chooseA = scanner.nextInt();
            chooseB = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Not a number");
        }
        System.out.print("Type in the number of node: 2 or 3: ");
        try {
            chooseNodeNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Not a number");
        }
        pc = calculatePc(chooseNodeNumber, chooseA, chooseB);
        if (chooseNodeNumber == 2) {
            result = Calculate(pc, w2, chooseNodeNumber, chooseA, chooseB);
            System.out.println(result);
        } else if (chooseNodeNumber == 3) {
            result = Calculate(pc, w2, chooseNodeNumber, chooseA, chooseB);
            System.out.println(result);
        } else {
            System.out.println("Wrong number");
        }

    }

    public static double Calculate(List<Double> pc, double[] w, int chooseNodeNumber, double chooseA, double chooseB) {
        double sum = 0;
        for (int i = 0; i < chooseNodeNumber; i++) {
            sum += (f(pc.get(i)) * w[i]);
        }
        sum *= detJ(chooseA, chooseB);
        return sum;

    }

    public static double detJ(double a, double b) {
        return abs((a - b)) / 2;
    }

    public static double f(double pc) {
        return Math.pow(pc, 2) + 3 * pc + 2;
    }

    public static List<Double> calculatePc(int chooseNodeNumber, double chooseA, double chooseB) {
        List<Double> pc = new ArrayList<>();
        if (chooseNodeNumber == 2) {
            pc.add(n1Calculate(-1 / sqrt(3)) * chooseA + n2Calculate(-1 / sqrt(3)) * chooseB);
            pc.add(n1Calculate(1 / sqrt(3)) * chooseA + n2Calculate(1 / sqrt(3)) * chooseB);
            return pc;
        } else if (chooseNodeNumber == 3) {
            return pc;
        }
        throw new IllegalArgumentException("Not an option"); // moze cos innego jakby byl blad czy cos zamiast return
    }

    public static double n1Calculate(double x1) {
        return (1 - (x1)) / 2;
    }

    public static double n2Calculate(double x2) {
        return (x2 + 1) / 2;
    }
}
