package jm.com.juc.atomic;

public class CounterBasic implements Counter {

    volatile int i = 0; // 本质是修改内存中某一个变量的值

    public int incr() {
        // 字节码
        // 1. 获取i的值 getfield #2 <com/tony/edu/juc/atomic/CounterBasic.i>
        int cuurent = i;

        // 2. 进行计算(+1) iadd
        int result = cuurent + 1;

        // 3. 赋值 putfield #2 <com/tony/edu/juc/atomic/CounterBasic.i>
        i = result;

        return result;
    }

    public int decr() {
        return i--;
    }

    @Override
    public int get() {
        return i;
    }
}
