package com.techelevator.view;

public class Chip extends Item {

    public Chip(String slotLocation, String productName, double priceItem) {
        super(slotLocation, productName, priceItem);
    }

    @Override
    public String getSound() {
        return "Crunch Crunch, Yum";
    }

}
