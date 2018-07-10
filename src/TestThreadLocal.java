public class TestThreadLocal implements Runnable {
    private static ThreadLocal<String> name = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };


    @Override
    public void run() {
        System.out.println(name.get());
    }

    public static void main(String[] args) {
        TestThreadLocal runnable = new TestThreadLocal();

        Thread thread1 = new Thread(runnable, "线程1");
        Thread thread2 = new Thread(runnable, "线程2");

        thread1.start();
        thread2.start();

        System.out.println(name.get());
    }
}
