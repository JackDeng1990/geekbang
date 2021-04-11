import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ReentrantLock reentrantLock=new ReentrantLock();
        AtomicInteger result = new AtomicInteger();
        new Thread(() -> {
            //确保在进程中sum被锁在Thread中
            reentrantLock.lock();
            result.set(sum());
            reentrantLock.unlock();
            countDownLatch.countDown();
        }).start();
        //确保堵塞主进程等Thread.countDown()后再输出结果
        countDownLatch.await();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }
}
