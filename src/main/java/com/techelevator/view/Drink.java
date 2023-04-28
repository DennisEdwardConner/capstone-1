package com.techelevator.view;

public class Drink extends Item{


    public Drink(String slotLocation, String productName, double priceItem) {
        super(slotLocation, productName, priceItem);
    }

    @Override
    public String getSound() {
        return "Glug Glug, Yum";
    }



}
