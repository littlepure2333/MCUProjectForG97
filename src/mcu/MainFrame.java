package mcu;

class MainFrame implements Runnable {
    private Thread t;
    @Override
    public void run() {
        while (true) {
            System.out.println("a");
        }
    }

    void start() {
        System.out.println("Starting ");
        t = new Thread (this);
        t.start ();
        t.interrupt();
    }
}
