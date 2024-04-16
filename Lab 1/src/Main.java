import java.util.ArrayList;
import java.util.List;

public class Main {

  static class BigObject {

    int[] array1 = new int[1000000];
    long[] array2 = new long[10000];
    char[] array3 = new char[100000];
  }

  public static void main(String[] args) {
    List<BigObject> list = new ArrayList<>();
    Runtime runtime = Runtime.getRuntime();
    int k = 0;

    while (true) {
      if (k % 100 == 0) {
        var busyMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println(
            runtime.maxMemory() + " " + runtime.totalMemory() + " " + runtime.freeMemory() + " "
                + busyMemory);
      }
      k++;
      list.add(new BigObject());
    }
  }
}
