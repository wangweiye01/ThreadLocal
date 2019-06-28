public class TestSync implements Runnable {
    private synchronized void print() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName());
        }
    }

    private void print1() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    @Override
    public void run() {
        try {
            print();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /*Thread thread1 = new Thread(new TestSync(), "name1");

        Thread thread2 = new Thread(new TestSync(), "name2");

        thread1.start();
        thread2.start();*/

        TestSync testSync = new TestSync();
        Thread thread3 = new Thread(testSync, "name1");

        Thread thread4 = new Thread(testSync, "name2");

        thread3.start();
        thread4.start();
    }
}
