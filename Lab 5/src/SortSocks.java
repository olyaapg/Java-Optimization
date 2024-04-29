import java.util.List;

public class SortSocks {
    public static void sort(List<Sock> socks) {
        for (int i = 0; i < socks.size() - 1; i++) {
            for (int j = 0; j < socks.size() - 1 - i; j++) {
                if (socks.get(j).getSize() == socks.get(i).getSize()) {
                    Sock temp = socks.get(j);
                    socks.set(j, socks.get(i));
                    socks.set(i, temp);
                }
            }
        }
    }
}
