package com.techelevator.view;

import java.text.DecimalFormat;

public class Item {


    private String slotLocation;
    private String productName;
    private double priceItem;
    private int inventoryAmount;
    private static final DecimalFormat df = new DecimalFormat("0.00");



    public Item(String slotLocation, String productName, double priceItem){
        this.setSlotLocation(slotLocation);
        this.setProductName(productName);
        this.setPriceItem(priceItem);
        this.setInventoryAmt(5);
    }



    public void purchaseItem() {
        if (inventoryAmount > 0) {
            inventoryAmount -= 1;
        }
    }
    public String getSlotLocation() {
        return slotLocation;
    }

    public  String getProductName() {
        return productName;
    }

    public  double getPriceItem() {
        return priceItem;
    }

    public int getInventoryAmt() {
        return inventoryAmount;
    }
    public String getInventoryToString(){return inventoryAmount + "";}

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPriceItem(double priceItem) {
        this.priceItem = priceItem;
    }

    public void setInventoryAmt(int inventoryAmount) {
        this.inventoryAmount = inventoryAmount;
    }



    public String displayItemsToString(){
        String str1 = String.format("%-20s", productName);
        String str2 = String.format("%5s", df.format(priceItem));
        String anotherLine = (str1 +"\t"+ "$"+ str2  + "\t  " +
                ((inventoryAmount > 0) ? inventoryAmount + " in stock" : "SOLD OUT"));

        return anotherLine;
    }

    public String getSound(){
        return null;
    }
}
