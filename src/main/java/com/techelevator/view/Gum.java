package com.techelevator.view;

public class Gum extends Item{

    public Gum(String slotLocation, String productName, double priceItem) {
        super(slotLocation, productName, priceItem);
    }

    @Override
    public String getSound() {
        return "Chew Chew, Yum";
    }

}
