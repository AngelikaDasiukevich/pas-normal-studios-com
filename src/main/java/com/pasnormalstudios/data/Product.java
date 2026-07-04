package com.pasnormalstudios.data;

public class Product {
    private String name;
    private String color;
    private String size;
    private String price;

    public Product(String name, String color, String size, String price) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getPrice() {
        return price;
    }
}
