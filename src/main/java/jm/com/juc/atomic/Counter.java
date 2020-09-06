package jm.com.juc.atomic;

public interface Counter {
    /**
     * increase 1
     * @return
     */
    int incr();

    /**
     * decrease 1
     * @return
     */
    int decr();

    /**
     * get current num
     * @return
     */
    int get();
}
