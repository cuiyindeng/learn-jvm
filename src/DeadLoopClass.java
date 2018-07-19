public class DeadLoopClass {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                LoadedClass deadLoopClass = new LoadedClass();
                System.out.println(Thread.currentThread() + "run over");
            }
        };
        Thread thread1 = new Thread(runnable, "thread1----");
        Thread thread2 = new Thread(runnable, "thread2----");
        thread1.start();
        thread2.start();
    }
}

class LoadedClass {
    static {
        if (true) {
            System.out.println(Thread.currentThread() + "init DeadLoopClass");
            while (true) {

            }
        }
    }
}
