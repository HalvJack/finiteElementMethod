public class GlobalData {
    int SimulationTime;
    int SimulationStepTime;
    int Conductivity;
    int Alfa;
    int Tot;
    int InitialTemp;
    int Density;
    int SpecificHeat;

    public GlobalData() {
    }


    public int getSimulationTime() {
        return SimulationTime;
    }

    public void setSimulationTime(int simulationTime) {
        SimulationTime = simulationTime;
    }

    public int getSimulationStepTime() {
        return SimulationStepTime;
    }

    public void setSimulationStepTime(int simulationStepTime) {
        SimulationStepTime = simulationStepTime;
    }

    public int getConductivity() {
        return Conductivity;
    }

    public void setConductivity(int conductivity) {
        Conductivity = conductivity;
    }

    public int getAlfa() {
        return Alfa;
    }

    public void setAlfa(int alfa) {
        Alfa = alfa;
    }

    public int getTot() {
        return Tot;
    }

    public void setTot(int tot) {
        Tot = tot;
    }

    public int getInitialTemp() {
        return InitialTemp;
    }

    public void setInitialTemp(int initialTemp) {
        InitialTemp = initialTemp;
    }

    public int getDensity() {
        return Density;
    }

    public void setDensity(int density) {
        Density = density;
    }

    public int getSpecificHeat() {
        return SpecificHeat;
    }

    public void setSpecificHeat(int specificHeat) {
        SpecificHeat = specificHeat;
    }
}

