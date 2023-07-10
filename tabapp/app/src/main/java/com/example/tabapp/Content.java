package com.example.tabapp;

public class Content {
    private String name;
    private String description;
    private double protein;
    public Content(String name, double protein) {
        this.name = name;
       this.protein  = protein;
    }

    public String getName() {
        return name;
    }

    public double getProtein() {
        return protein;
    }
}
