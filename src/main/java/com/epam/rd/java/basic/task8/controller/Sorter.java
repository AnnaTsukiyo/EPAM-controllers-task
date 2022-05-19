package com.epam.rd.java.basic.task8.controller;

import com.epam.rd.java.basic.task8.entity.Product;
import com.epam.rd.java.basic.task8.entity.Storage;

import java.util.Collections;
import java.util.Comparator;

public class Sorter {

    private Sorter() {}

    public static final void sortProductsByTitle(Storage storage) {
        Collections.sort(storage.getProducts(), Comparator.comparing(Product::getTitle));
    }

    public static final void sortProductsByYear(Storage storage) {
        Collections.sort(storage.getProducts(), Comparator.comparingDouble(Product::getPrice));
    }

    public static final void sortProductsByRating(Storage storage) {
        Collections.sort(storage.getProducts(), Comparator.comparingInt(Product::getQuantity));
    }
}
