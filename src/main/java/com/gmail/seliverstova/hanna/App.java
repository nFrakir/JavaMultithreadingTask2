package com.gmail.seliverstova.hanna;

public class App {
    public static void main(String[] args) {
        int[] array = new int[200_000_000];

        for(int i = 0; i < array.length; i+=1) {
            array[i] = (int) (Math.random() * 10);
        }

        System.out.println("Multithread calculation.");
        long start = System.currentTimeMillis();
        int totalSum = multithreadCalculate(array);
        long end = System.currentTimeMillis();

        System.out.println("Total array sum: " + totalSum);
        System.out.println("Elapsed time: " + (end - start));
        System.out.println();

        System.out.println("Single thread calculation.");
        start = System.currentTimeMillis();
        totalSum = singlethreadCalculate(array);
        end = System.currentTimeMillis();

        System.out.println("Total array sum: " + totalSum);
        System.out.println("Elapsed time: " + (end - start));
    }

    private static int singlethreadCalculate(int[] array) {
        int totalSum = 0;
        for (int i = 0; i < array.length; i += 1) {
            totalSum += array[i];
        }
        return totalSum;
    }

    private static int multithreadCalculate(int[] array) {
        Thread[] thrArray = new Thread[4];
        ArraySumCalculatorThread[] ascArray = new ArraySumCalculatorThread[4];

        int step = array.length / ascArray.length;
        for(int i = 0; i < thrArray.length; i+=1) {
            ascArray[i] = new ArraySumCalculatorThread(array, (step * i), step * (i+1));
            thrArray[i] = new Thread(ascArray[i]);
        }

        for(int i = 0; i < thrArray.length; i+=1) {
            thrArray[i].start();
        }
        for(int i = 0; i < thrArray.length; i+=1) {
            try {
                thrArray[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int totalSum = 0;
        for(int i = 0; i < thrArray.length; i+=1) {
            totalSum += ascArray[i].getSum();
        }
        return totalSum;
    }
}
