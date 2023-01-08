public class Side {
    public Node nodeA;
    public Node nodeB;
    public boolean sideBorderCondition;

    public Side(Node nodeA, Node nodeB, boolean sideBorderCondition) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.sideBorderCondition = sideBorderCondition;
    }

    public Node getNodeA() {
        return nodeA;
    }

    public void setNodeA(Node nodeA) {
        this.nodeA = nodeA;
    }

    public Node getNodeB() {
        return nodeB;
    }

    public void setNodeB(Node nodeB) {
        this.nodeB = nodeB;
    }

    public boolean isSideBorderCondition() {
        return sideBorderCondition;
    }

    public void setSideBorderCondition(boolean sideBorderCondition) {
        this.sideBorderCondition = sideBorderCondition;
    }
}
