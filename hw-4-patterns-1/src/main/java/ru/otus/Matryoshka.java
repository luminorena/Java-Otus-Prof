package ru.otus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Matryoshka {
    private final List<String> items;


    public Matryoshka(List<String> items) {
        this.items = items;
    }

    public String getColor(int index) {
        return items.get(index);
    }

    @Override
    public String toString() {
        return String.valueOf(items);
    }
}

final class Box {
    private final Matryoshka color1;
    private final Matryoshka color2;
    private final Matryoshka color3;
    private final Matryoshka color4;
    private final int COUNTCOLORS = 10;

    public Box(Matryoshka color1, Matryoshka color2, Matryoshka color3, Matryoshka color4) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
    }

    public Iterator<String> getSmallFirstIterator() {
        List<String> colors = new ArrayList<>();
        List<String> commaFree = new ArrayList<>();

        for (int i = 0; i < COUNTCOLORS; i++) {
            colors.add("\"" + color1.getColor(0) + i + "\"");
            colors.add("\"" + color2.getColor(1) + i + "\"");
            colors.add("\"" + color3.getColor(2) + i + "\"");
            colors.add("\"" + color4.getColor(3) + i + "\"");
        }
        String commaSeparated = String.join(", ", colors);
        commaFree.add(commaSeparated);
        return commaFree.iterator();
    }

    public Iterator<String> getColorFirstIterator() {
        List<String> colors = new ArrayList<>();
        List<String> commaFree = new ArrayList<>();

        for (int i = 0; i < COUNTCOLORS; i++) {
            colors.add("\"" + color1.getColor(0) + i + "\"");
        }
        for (int i = 0; i < COUNTCOLORS; i++) {
            colors.add("\"" + color2.getColor(1) + i + "\"");
        }
        for (int i = 0; i < COUNTCOLORS; i++) {
            colors.add("\"" + color3.getColor(2) + i + "\"");
        }

        for (int i = 0; i < COUNTCOLORS; i++) {
            colors.add("\"" + color4.getColor(3) + i + "\"");
        }

        String commaSeparated = String.join(", ", colors);
        commaFree.add(commaSeparated);
        return commaFree.iterator();
    }
}