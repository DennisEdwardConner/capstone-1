package com.techelevator.view;

public class Candy extends Item {

    public Candy(String slotLocation, String productName, double priceItem) {
        super(slotLocation, productName, priceItem);
    }

    @Override
    public String getSound() {
        return "Munch Munch, Yum";
    }

}
