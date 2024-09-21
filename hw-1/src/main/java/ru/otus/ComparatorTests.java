package ru.otus;


import ru.otus.annotations.AfterSuite;
import ru.otus.annotations.BeforeSuite;
import ru.otus.annotations.Disabled;
import ru.otus.annotations.Test;
import ru.otus.util.CustomException;

public class ComparatorTests {

    @Disabled("This test class is disabled")
    public static class TestClass {}
    Comparator comparator = new Comparator();

    @BeforeSuite
    public void before() throws CustomException {
        Comparator comparator = new Comparator(12, 1);
        if (comparator != null) {
            System.out.println("Comparator exists");
        } else throw new CustomException("Comparator cannot be created");

    }

    @Test(priority = 1)
    @Disabled("test is disabled")
    public void greaterTest() throws CustomException {
        int a = 12;
        int b = 10;
        boolean greater = comparator.isGreater(a, b);
        if (!greater) {
            throw new CustomException("The number " + a + " is not greater than " + b);
        }
    }


    @Test(priority = 2)
    public void lessTest() throws CustomException {
        int a = 9;
        int b = 16;
        boolean less = comparator.isLess(a, b);
        if (!less) {
            throw new CustomException("The number " + a + " is not less than " + b);
        }
    }

    @Test(priority = 1)
    public void lessTest2() throws CustomException {
        int a = 90;
        int b = 1600;
        boolean less = comparator.isLess(a, b);
        if (!less) {
            throw new CustomException("The number " + a + " is not less than " + b);
        }
    }

    @AfterSuite
    public void after() {
        Comparator comparator = new Comparator();
        comparator = null;
        System.out.println("Comparator is destroyed");
    }


}
