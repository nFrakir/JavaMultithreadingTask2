package com.gmail.seliverstova.hanna;

public class ArraySumCalculatorThread implements Runnable {
    private int[] array;
    private int begin;
    private int end;
    private int sum = 0;

    public ArraySumCalculatorThread(int[] array, int begin, int end) {
        super();
        this.array = array;
        this.begin = begin;
        this.end = end;
    }

    public ArraySumCalculatorThread() {
        super();
    }

    public int[] getArray() {
        return array;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        for (int i = begin; i < end; i += 1) {
            this.sum += array[i];
        }
    }
}
