package org.example;

import java.util.List;

public class ThreadCalc extends Thread {
    private final List<Integer> threadList;
    private int sum;

    private int startIndex;
    private int endIndex;

    public ThreadCalc(List<Integer> list, int startIndex, int endIndex) {
        this.threadList = list;
        this.sum = 0;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        for (int i = startIndex; i <= endIndex -1; i++) {
            sum += threadList.get(i);
        }
    }

    public int getSum() {
        return sum;
    }
}
