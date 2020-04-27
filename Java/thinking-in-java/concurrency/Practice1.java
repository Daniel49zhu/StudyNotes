public class Practice1 implements Runnable {
    public Practice1() {
        System.out.printf("Thread start %s\n", this.toString());
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("print message " + i);
            Thread.yield();
        }
        return;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Thread end %s\n", this.toString());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(new Practice1()).start();
        }
    }
}
