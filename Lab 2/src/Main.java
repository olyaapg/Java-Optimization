import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    int n = 3;

    List<Thread> threads = new ArrayList<>(n);
    List<Bean> beans = new ArrayList<>(n);
    Bean previousBean = null;

    for (int i = 0; i < n; i++) {
      Bean currentBean = new Bean();
      currentBean.setBean(previousBean);
      previousBean = currentBean;
      beans.add(currentBean);

      SomeRunnable runnable = new SomeRunnable(currentBean);
      threads.add(new Thread(runnable));
    }
    beans.get(0).setBean(previousBean);

    for (Thread thread : threads) {
      thread.start();
    }

    if (args.length > 0 && args[0].equals("startThread")) {
      Thread harmfulThread = new Thread(new HarmfulRunnable());
      harmfulThread.start();
    }
  }
}