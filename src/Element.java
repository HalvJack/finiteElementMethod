import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Element {
    public List<Integer> ID = new ArrayList<>();
    public List<Node> nodes= new ArrayList<>();
    public Element() {
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Element(List<Integer> ID) {
        this.ID = ID;
    }

    public List<Integer> getID() {
        return ID;
    }

    public void setID(List<Integer> ID) {
        this.ID = ID;
    }


    @Override
    public String toString() {
        return "Element{" +
                "ID=" + ID +
                '}';
    }
}
