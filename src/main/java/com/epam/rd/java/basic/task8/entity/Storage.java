package com.epam.rd.java.basic.task8.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Storage {

    private String name;
    private List<Product> products;

    public  Storage(){
        this.products = new ArrayList<>();
    }

    public Storage(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }




    public void addProduct(Product product) {
        products.add(product);
    }

    public void sortBy(Comparator<Product> comparator){
        products.sort(comparator);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
