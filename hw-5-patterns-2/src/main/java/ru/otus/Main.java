package ru.otus;

public class Main {

    public static void main(String[] args) {
        ItemsServiceProxy itemsServiceProxy = new ItemsServiceProxy();
        itemsServiceProxy.addNewItemsTran();
        itemsServiceProxy.increasePriceAndUpdateTran();

    }
}


