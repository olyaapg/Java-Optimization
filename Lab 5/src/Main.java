import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Sock> socks = new ArrayList<>(10);
        for (int i = 35; i < 45; i++) {
            socks.add(new Sock(i));
        }
        SortSocks.sort(socks);
        System.out.println(socks);
    }
}