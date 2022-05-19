package com.epam.rd.java.basic.task8.entity;

public class Product {
    private int id;
    private String title;
    private String note;
    private int quantity;
    private String units;
    private Double price;

    public Product(int id, String title, String note, int quantity, String units, double price) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.quantity = quantity;
        this.units = units;
        this.price = price;
    }
    public Product(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", quantity=" + quantity +
                ", units='" + units + '\'' +
                ", price=" + price +
                '}';
    }
}
