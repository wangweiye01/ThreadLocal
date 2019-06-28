import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestThreadLocal implements Runnable {
    /*private static ThreadLocal<String> name = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };*/

    private static ThreadLocal<String> name = ThreadLocal.withInitial(() -> {
        return Thread.currentThread().getName();
    });

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static Date parse(String dateStr) throws ParseException {
        return TestThreadLocal.threadLocal.get().parse(dateStr);
    }

    public static String formate(Date date) {
        return TestThreadLocal.threadLocal.get().format(date);
    }


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
