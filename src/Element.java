import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Element {
    public List<Integer> ID = new ArrayList<>();
    public List<Node> nodes = new ArrayList<>();
    public List<Side> sides = new ArrayList<>();

    public Element() {

    }

    public Element(List<Integer> ID) {
        this.ID = ID;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }


    public List<Integer> getID() {
        return ID;
    }

    public void setID(List<Integer> ID) {
        this.ID = ID;
    }

    public List<Side> getSides() {
        return sides;
    }

    public void setSides(List<Side> sides) {
        this.sides = sides;
    }

    @Override
    public String toString() {
        return "Element{" +
                "ID=" + ID +
                '}';
    }

    public void setSides(){
        for (int i = 0; i < nodes.size(); i++) {
            if(!(i+1 == nodes.size()))
            {
                if(nodes.get(i).BC && nodes.get(i+1).BC){
                    Side side = new Side(nodes.get(i), nodes.get(i+1), true);
                    sides.add(side);
                }
                else
                {
                    Side side = new Side(nodes.get(i), nodes.get(i+1), false);
                    sides.add(side);
                }
            }
            else
            {
                if(nodes.get(i).BC && nodes.get(0).BC){
                    Side side = new Side(nodes.get(i), nodes.get(0), true);
                    sides.add(side);
                }
                else{
                    Side side = new Side(nodes.get(i), nodes.get(0), false);
                    sides.add(side);
                }
            }
        }
    }
}
