public class Side {
    public Node nodeA;
    public Node nodeB;
    public int pointsOfIntegration; //2,3,4 w konstruktorze
    public boolean sideBorderCondition;

    public Side(Node nodeA, Node nodeB, boolean sideBorderCondition) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.sideBorderCondition = sideBorderCondition;
    }
}
