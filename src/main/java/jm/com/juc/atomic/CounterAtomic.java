package jm.com.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterAtomic implements Counter {

    // JUC包 针对基本数据类型 --- 原子操作
    AtomicInteger i = new AtomicInteger(0); // 本质是修改内存中某一个变量的值

    public int incr() {
        return i.incrementAndGet();
    }

    public int decr() {
        return i.decrementAndGet();
    }

    @Override
    public int get() {
        return i.get();
    }
}
