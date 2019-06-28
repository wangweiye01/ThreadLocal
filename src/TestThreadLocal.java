import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestThreadLocal {
    /*private static ThreadLocal<String> name = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };*/

    private static ThreadLocal<String> name = ThreadLocal.withInitial(() -> {
        return Thread.currentThread().getName();
    });

    /**
     * 由于SimpleDateFormat不是线程安全的，所以考虑把它放到ThreadLocal中
     */
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


    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> System.out.println(name.get()), "线程1");
        Thread thread2 = new Thread(() -> System.out.println(name.get()), "线程2");

        thread1.start();
        thread2.start();

        System.out.println(name.get());
    }
}
