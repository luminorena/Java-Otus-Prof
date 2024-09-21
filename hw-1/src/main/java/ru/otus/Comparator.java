package ru.otus;

public class Comparator {
    int a;
    int b;

    public Comparator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Comparator() {
    }

    public boolean isGreater(int a, int b) {
        return a > b;
    }

    public boolean isLess(int a, int b) {
        return a < b;
    }


}