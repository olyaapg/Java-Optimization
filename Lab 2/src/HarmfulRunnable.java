import java.util.ArrayList;
import java.util.List;

public class HarmfulRunnable implements Runnable {
  static class BigObject {
    int[] array1 = new int[1000000];
  }
  @Override
  public void run() {
    List<BigObject> list = new ArrayList<>();

    while (true) {
      list.add(new BigObject());
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
