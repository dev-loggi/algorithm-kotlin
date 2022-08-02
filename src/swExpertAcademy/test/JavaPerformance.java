package swExpertAcademy.test;

import java.util.ArrayList;
import java.util.LinkedList;

public class JavaPerformance {

    private final int SIZE = 1_000_000;
    private int tmp;
    private int[] arr;
    private ArrayList<Integer> arrayList;

    public void init1() {
        arrayList = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            arrayList.add(i);
        }
        System.out.println("initialize1()");
    }

    public void test1() {
        for (int i = 0; i < arrayList.size(); i++) {
            tmp = arrayList.get(i);
        }
    }

    public void test2() {
        for (int n : arrayList) {
            tmp = n;
        }
    }

    public void init2() {
        arr = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = i;
        }
        System.out.println("initialize2()");
    }

    public void test3() {
        for (int i = 0; i < arr.length; i++) {
            tmp = arr[i];
        }
    }

    public void test4() {
        for (int n : arr) {
            tmp = n;
        }
    }

    public static class Insertion {
        private static final int SIZE = 1_000_000;

        private ArrayList<Integer> arrayList;
        private LinkedList<Integer> linkedList;

        public void test1() {
            arrayList = new ArrayList<>(SIZE);
            for (int i=0; i<SIZE; i++) arrayList.add(i);
        }

        public void test2() {
            linkedList = new LinkedList<>();
            for (int i=0; i<SIZE; i++) linkedList.add(i);
        }

        public void release() {
            if (arrayList != null) {
                arrayList.clear();
                arrayList = null;
            }
            if (linkedList != null) {
                linkedList.clear();
                linkedList = null;
            }
        }
    }

    private void logLn(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}