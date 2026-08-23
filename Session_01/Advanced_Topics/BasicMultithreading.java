/*
4. Explore multithreading in Java to perform multiple tasks concurrently.

Multithreading in Java
- Multithreading allows multiple threads (tasks) to run concurrently
- It helps improve performance and better CPU utilization

Ways to create threads:
1. Thread class → extend Thread and override run()
2. Runnable interface → implement Runnable 

Key Methods:
- start() → starts a new thread and calls run()
- run() → contains the code to be executed by the thread

Note:
- Threads run independently and may execute in any order

Example:
*/


class MyThread extends Thread {

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Count: " + i);

            try {
                Thread.sleep(500); // pause for half second
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class BasicMultithreading {
    public static void main(String[] args) {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start(); // thread 1 starts
        t2.start(); // thread 2 starts
    }
}