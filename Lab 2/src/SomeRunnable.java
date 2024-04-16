public class SomeRunnable implements Runnable {

  private final Bean bean;
  private final Singleton singleton;

  public SomeRunnable(Bean bean) {
    this.bean = bean;
    this.singleton = Singleton.getInstance();
  }

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
