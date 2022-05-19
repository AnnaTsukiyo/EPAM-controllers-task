package com.epam.rd.java.basic.task8.controller;

public enum XML {
    STORAGE("storage"),
    PRODUCT("product"),
    ID("id"),
    TITLE("title"),
    NOTE("note"),
    QUANTITY("quantity"),
    UNITS("units"),
    PRICE("price");

    private String value;

    XML(String value) {
        this.value = value;
    }

    public boolean equalsTo(String name) {
        return value.equals(name);
    }

    public String value() {
        return value;
    }
}