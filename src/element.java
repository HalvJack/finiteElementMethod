import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class element {
    List<Integer> ID = new ArrayList<>();

    public element() {
    }

    public element(List<Integer> ID) {
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
        return "element{" +
                "ID=" + ID +
                '}';
    }
}
