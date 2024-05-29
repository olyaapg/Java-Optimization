public class Sock {
    private int size;

    public Sock() {
    }

    public Sock(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Sock [size=" + size + "]";
    }
}
