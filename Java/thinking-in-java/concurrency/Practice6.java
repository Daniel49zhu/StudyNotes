import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Practice6 implements Runnable {
    @Override
    public void run() {
        int timeSleep = new Random().nextInt(10) + 1;
        try {
            TimeUnit.MILLISECONDS.sleep(timeSleep * 1000);
            System.out.printf("Thread sleep %d seconds\n", timeSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            exec.execute(new Practice6());
        }
        exec.shutdown();
    }
}
