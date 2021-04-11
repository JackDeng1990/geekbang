import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        long start=System.currentTimeMillis();

        CyclicBarrier cyclicBarrier=new CyclicBarrier(2);
        ReentrantLock reentrantLock=new ReentrantLock();
        AtomicInteger result = new AtomicInteger();
        new Thread(() -> {
            //确保在进程中sum被锁在Thread中
            reentrantLock.lock();
            result.set(sum());
            reentrantLock.unlock();
            try {
                //确保main进程也await()后，大家都可以往下继续执行
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        //因为new CyclicBarrier(2)，所以子线程Thread不执行await()将会一直堵塞
        cyclicBarrier.await();
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
