package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        List<Integer> list = new ArrayList<>();

        // Seed the list with random numbers
        for (int i = 0; i < 200_000_000; i++) {
            list.add(rand.nextInt(11));
        }

        System.out.println("List size: " + list.size());

        long startTime = System.nanoTime();
        int singleThreadSum = 0;

        for (Integer i : list) {
            singleThreadSum += i;
        }
        long endTime = System.nanoTime();
        System.out.println("Single thread sum: " + singleThreadSum);
        System.out.println("Single thread time: " + (endTime - startTime) / 1_000_000 + " ms");

        long startTime2 = System.nanoTime();
        List<Integer> multiThreadResults = new ArrayList<>();

        ThreadCalc thread1 = new ThreadCalc(list, 0, 100_000_000);
        ThreadCalc thread2 = new ThreadCalc(list, 100_000_000, 200_000_000);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        multiThreadResults.add(thread1.getSum());
        multiThreadResults.add(thread2.getSum());

        int multiThreadSum = 0;
        for (int i : multiThreadResults) {
            multiThreadSum += i;
        }
        long endTime2 = System.nanoTime();

        System.out.println("Multi thread sum: " + multiThreadSum);
        System.out.println("Multi thread time: " + (endTime2 - startTime2) / 1_000_000 + " ms");
    }
}