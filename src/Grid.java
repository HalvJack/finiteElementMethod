import java.util.ArrayList;
import java.util.List;

public class Grid {
    int nEl; // liczba elementow
    int nNd; //liczba wezlow
    List<Node> ND = new ArrayList<>();// tablica node'ow tj. wezlow
    List<Element> EL = new ArrayList<>(); //tablica elementów

    public Grid() {
    }

    public Grid(int nEl, int nNd, List<Node> ND, List<Element> EL) {
        this.nEl = nEl;
        this.nNd = nNd;
        this.ND = ND;
        this.EL = EL;
    }

    public int getnEl() {
        return nEl;
    }

    public void setnEl(int nEl) {
        this.nEl = nEl;
    }

    public int getnNd() {
        return nNd;
    }

    public void setnNd(int nNd) {
        this.nNd = nNd;
    }

    public List<Node> getND() {
        return ND;
    }

    public void setND(List<Node> ND) {
        this.ND = ND;
    }

    public List<Element> getEL() {
        return EL;
    }

    public void setEL(List<Element> EL) {
        this.EL = EL;
    }
}
