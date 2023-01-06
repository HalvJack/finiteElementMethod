import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ReadFromFile {
    Grid grid;
    GlobalData globalData;

    public ReadFromFile() {
        this.grid = new Grid();
        this.globalData = new GlobalData();
    }

    public void OverallFunction() {
        //System.out.println("Hello, choose ( by inserting the value from 1 to 3 the file ");
        //Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            //choice = scanner.nextInt();
            choice = 1; // na razie na sztywno
            switch (choice) {
                case 1 -> readFile(new File("Test1_4_4.txt"));
                case 2 -> readFile(new File("Test2_4_4_MixGrid.txt"));
                case 3 -> readFile(new File("Test3_31_31_kwadrat.txt"));
                default -> throw new IllegalArgumentException("There is not choice like that");
            }
        } catch (InputMismatchException i) {
            throw new InputMismatchException("That's not a number");
//            System.err.println("Error");
        }
    }

    private void readFile(File file) {
        try {
            Scanner readFile = new Scanner(file);
            List<Node> fileNodes = new ArrayList<>();

            String line;
            String[] s;
            while (readFile.hasNextLine()) {
                line = readFile.nextLine();
                if (line.contains("SimulationTime")) {
                    s = line.split(" ");
                    globalData.setSimulationTime(Integer.parseInt(s[1].trim()));
                }
                if (line.contains("SimulationStepTime")) {
                    s = line.split(" ");
                    globalData.setSimulationStepTime(Integer.parseInt(s[1].trim()));
                }
                if (line.contains("Conductivity")) {
                    s = line.split(" ");
                    globalData.setConductivity(Integer.parseInt(s[1].trim()));
                }
                if (line.contains("Alfa")) {
                    s = line.split(" ");
                    globalData.setAlfa(Integer.parseInt(s[1].trim()));
                }
                if (line.contains("Tot")) {
                    s = line.split(" ");
                    globalData.setTot(Integer.parseInt(s[1].trim()));
                }
                if (line.contains("InitialTemp")) {
                    s = line.split(" ");
                    globalData.setInitialTemp(Integer.parseInt(s[1].trim()));
                }
                if (line.contains("Density")) {
                    s = line.split(" ");
                    globalData.setDensity(Integer.parseInt(s[1].trim()));
                }
                if (line.contains("SpecificHeat")) {
                    s = line.split(" ");
                    globalData.setSpecificHeat(Integer.parseInt(s[1].trim()));
                }
                if (line.contains("Nodes number")) {
                    s = line.split(" ");
                    int nNd = Integer.parseInt(s[2].trim());
                    grid.setnNd(nNd);
                }
                if (line.contains("Elements number")) {
                    s = line.split(" ");
                    grid.setnEl(Integer.parseInt(s[2].trim()));
                }

                if (line.contains("*Node")) {
                    for (int i = 0; i < grid.getnNd(); i++) {
                        line = readFile.nextLine();
                        s = line.split(",");
                        float x = Float.parseFloat(s[1].trim());
                        float y = Float.parseFloat(s[2].trim());
                        Node node = new Node(x, y);
                        fileNodes.add(node);


                    }
                    grid.setND(fileNodes);
                }
                if (line.contains("*Element, type=DC2D4")) {

                    List<Element> elements = new ArrayList<>();
                    for (int i = 0; i < grid.nEl; i++) {
                        line = readFile.nextLine();
                        s = line.split(",");
                        List<Integer> ID = new ArrayList<>();
                        ID.add(Integer.parseInt(s[1].trim()));
                        ID.add(Integer.parseInt(s[2].trim()));
                        ID.add(Integer.parseInt(s[3].trim()));
                        ID.add(Integer.parseInt(s[4].trim()));
                        elements.add(new Element(ID));
                        for (int j = 0; j < 4; j++) {
                            elements.get(i).nodes.add(fileNodes.get((elements.get(i).ID.get(j))-1));
                            //dla listy elementow, wez element i, wez liste wezlow i dodaj do niej:
                            // z listy nodes powyzej ktore odczytalismy, wez node o indeksie:
                            //z listy elements wez element o indeksie 'i' i wez z listy ID nr j
                        }

                        grid.EL.add(elements.get(i));
                    }
                    //grid.setEL(elements);

                }
                if (line.contains("*BC")) {
                    line = readFile.nextLine();
                    s = line.split(",");
                    List<Integer> BC = new ArrayList<>();
                    for (int i = 0; i < s.length; i++) {
                        grid.ND.get(Integer.parseInt(s[i].trim()) - 1).setBC(true);
                    }

                }

            }
            readFile.close();
            for (int i = 0; i < grid.getnEl(); i++) {
                grid.getEL().get(i).setSides();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
    public void writeData(){
        System.out.println("Simulation time: " + globalData.getSimulationTime());
        System.out.println("Simulation Step Time: " + globalData.getSimulationStepTime());
        System.out.println("Conductivity: " + globalData.getConductivity());
        System.out.println("Alfa: " + globalData.getAlfa());
        System.out.println("Tot: " + globalData.getTot());
        System.out.println("Initial Temp: " + globalData.getInitialTemp());
        System.out.println("Density: " + globalData.getDensity());
        System.out.println("Specific Heat: " + globalData.getSpecificHeat());
        System.out.println("Nodes number: " + grid.getnNd());
        System.out.println("Elements number: " + grid.getnEl());

        List<Node> ND = grid.getND();
        for (Node node : ND) {
            System.out.println(node.toString());
        }
        for (Element element : grid.getEL()) {
            System.out.println(element.toString());
        }
    }
}

