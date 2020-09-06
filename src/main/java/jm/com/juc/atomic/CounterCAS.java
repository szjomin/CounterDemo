package jm.com.juc.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CounterCAS implements Counter {

    volatile int i = 0; // 本质是修改内存中某一个变量的值

    static Unsafe unsafe;

    private static long valueOffSet;

    static {
        try {
            // 反射获取属性
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            Field fieldi = CounterCAS.class.getDeclaredField("i");
            valueOffSet = unsafe.objectFieldOffset(fieldi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int incr() {
        for (; ; ) {
            int current = i;
            int update = current + 1;
            if(unsafe.compareAndSwapInt(this,valueOffSet, current, update)){
                return update;// CAS 直接操作到内存层面
            }

        }
    }

    public int decr() {
        for (; ; ) {
            // 1. 获取i的值
            int current = i;
            // 2. 进行计算
            int update = current - 1;
            // CAS 近似硬件层面的操作（JDK和JVM 做的封装）
            if (unsafe.compareAndSwapInt(this, valueOffSet, current, update)) {
                return update;
            }
        }
    }

    @Override
    public int get() {
        return i;
    }
}
