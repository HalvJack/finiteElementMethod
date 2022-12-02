public class Node {
    float x;
    float y;
    float t;
    boolean BC;


    public Node(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Node(float x, float y, float t, boolean BC) {
        this.x = x;
        this.y = y;
        this.t = t;
        this.BC = BC;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getT() {
        return t;
    }

    public void setT(float t) {
        this.t = t;
    }

    public boolean isBC() {
        return BC;
    }

    public void setBC(boolean BC) {
        this.BC = BC;
    }

    @Override
    public String toString() {
        return "node{" +
                "x=" + x +
                ", y=" + y +
                ", t=" + t +
                ", BC=" + BC +
                '}';
    }
}
