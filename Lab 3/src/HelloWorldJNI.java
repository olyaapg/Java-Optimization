public class HelloWorldJNI {
    static {
        System.loadLibrary("native");
    }

    // 1 пункт
    // запустила -> винда упала. Получила лишь hs_err_pid4876.log
    private void killMyComputer(HelloWorldJNI obj) {
        obj.eatMemory();
    }

    // 2 пункт
    // - Метод который аллоцирует 1 Кб памяти. Потом показать, как это видно из методов Runtime.xxxMemory()
    private void allocateMemory(Bean bean) {
        Runtime runtime = Runtime.getRuntime();

        var total = runtime.totalMemory();
        var free = runtime.freeMemory();
        var busyBefore = total - free;

        new HelloWorldJNI().allocate1KB(bean);

        total = runtime.totalMemory();
        free = runtime.freeMemory();
        var busyAfter = total - free;

        System.out.println("Busy before: " + busyBefore);
        System.out.println("Busy after: " + busyAfter);
        System.out.println("Difference = " + (busyAfter - busyBefore));
    }


    public static void main(String[] args) {
        var obj = new HelloWorldJNI();
        var newBean = obj.createNewBean("pip", true);
        newBean.setIntVar(100500);
        //System.out.println(newBean);
        obj.allocateMemory(newBean);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //obj.callMethods();

        assert obj.getStringLen("pip") == 3;

        var bean = obj.createNewBean("Hi from C", true);
        assert obj.beanToString(bean).equals(bean.toString());

        assert newBean.getBean() == null;
        obj.setBean(newBean, bean);
        assert newBean.getBean() == bean;
        long ptr = obj.allocateNativeStruct();
        assert obj.getStructValueInt(ptr) == 5;
        assert obj.getStructValueString(ptr).equals("Bobik");
        obj.freeStruct(ptr);
    }

    private native void eatMemory();

    private native void allocate1KB(Bean bean);

    private native void callMethods();

    private native int getStringLen(String string);

    private native String beanToString(Bean bean);  // получить объект и вызвать метод

    private native Bean createNewBean(String string, boolean bool); // создает новый бин с заданными значениями полей

    private native void setBean(Bean bean, Bean newValue);  // получает бин и присваивает новое значение полю

    private native long allocateNativeStruct();

    private native int getStructValueInt(long pointer);

    private native String getStructValueString(long pointer);

    private native void freeStruct(long pointer);
}
