import java.util.ArrayList;
import java.util.List;

// zgodnie z zadaniem, powinnismy tu umiescic
// ilosc punktow calkowania po powierzchni tj 2,3 lub 4
// wspolrzedne tych punktow calkowania
// na koniec wartosci funkcji ksztaltu w punktach calkowania
public class MatrixHBC implements ShapeFunction {
    int numberOfIntegralPoints; // 2,3 lub 4
    double[] ksi; //wspol punktow calkowania dla
    double[] eta; //wspol punktow calkowania
    //double[]

    List<ShapeFunction<Double, Double, Double>> myShapeFunctions = new ArrayList<>(){
        { // funkcje ksztaltu
            add((ksi, eta) -> 0.25 * (1 - ksi) * ( 1 -eta));
            add((ksi, eta) -> 0.25 * (1 + ksi) * ( 1 -eta));
            add((ksi, eta) -> 0.25 * (1 + ksi) * ( 1 +eta));
            add((ksi, eta) -> 0.25 * (1 - ksi) * ( 1 +eta));
        }
    };

}
