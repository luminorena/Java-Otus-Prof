package ru.otus;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Matryoshka matryoshka = new Matryoshka(List.of("red", "green", "blue", "magenta"));
        Matryoshka matryoshka1 = new Matryoshka(List.of("red", "green", "blue", "magenta"));
        Matryoshka matryoshka2 = new Matryoshka(List.of("red", "green", "blue", "magenta"));
        Matryoshka matryoshka3 = new Matryoshka(List.of("red", "green", "blue", "magenta"));
        Box box = new Box(matryoshka, matryoshka1, matryoshka2, matryoshka3);

        Iterator<String> firstIterator = box.getSmallFirstIterator();
        Iterator<String> secondIterator = box.getColorFirstIterator();


        while (firstIterator.hasNext()) {
            System.out.print(firstIterator.next());
        }
        System.out.println("\n -----------------------------------------");
        while (secondIterator.hasNext()) {
            System.out.print(secondIterator.next());
        }


    }
}
