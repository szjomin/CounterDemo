package jm.com.juc.atomic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

@RestController
public class Demo1_CounterTest {

    Counter counter = new CounterBasic();

    int limit = 3000;

    @RequestMapping("/hello")
    public void hello() {

        counter.incr();
        // 请求超过限制
        if (counter.get() > limit) {
            // 限流
            return;
        }

        try {
            // 业务逻辑
        } finally {
            // 处理完毕， 数量减一
            counter.decr();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Counter ct = new CounterCAS();

        //模拟多线程场景
        CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                long begin = System.nanoTime();
                for (int j = 0; j < 10000; j++) {
                    ct.incr();
                }
                System.out.println("done...运算时间： " + (System.nanoTime() - begin));
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println("计数器最终结果: " + ct.get());
        // 预期结果应该 --- 20000
    }
}
