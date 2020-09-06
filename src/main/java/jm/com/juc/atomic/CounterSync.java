package jm.com.juc.atomic;

public class CounterSync implements Counter {

    volatile int i = 0; // 本质是修改内存中某一个变量的值

    public synchronized int incr() {
        return i++;
    }

    public int decr() {
        return i--;
    }

    @Override
    public int get() {
        return i;
    }
}
