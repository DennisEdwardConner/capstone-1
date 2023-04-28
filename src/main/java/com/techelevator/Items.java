package com.techelevator;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Items {


    private String slotLocation;
    private String productName;
    private double priceItem;
    private String itemType;
    private int inventoryAmt;

    public Items(String slotLocation, String productName, double priceItem, String itemType, int inventoryAmt){
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.priceItem = priceItem;
        this.itemType = itemType;
        this.inventoryAmt = 5;
    }

    List<Items> allItems;  // = new ArrayList<>();
    Object[] options;



    public String getSlotLocation() {
        return slotLocation;
    }

    public  String getProductName() {
        return productName;
    }

    public  double getPriceItem() {
        return priceItem;
    }

    public String getItemType() {return itemType;}

    public int getInventoryAmt() {
        return inventoryAmt;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPriceItem(double priceItem) {
        this.priceItem = priceItem;
    }

    public void setItemType(String itemType) {this.itemType = itemType;}

    public void setInventoryAmt(int inventoryAmt) {
        this.inventoryAmt = inventoryAmt;
    }



    public void stockMachine2() {
        //options = new Object[16];
        allItems = new ArrayList<Items>(16);

        String amt = "5";

        String filePath = "src/main/resources/vendingmachine.csv";
        File path = new File(filePath);

        try {
            Scanner scanner = new Scanner(path);
            int i = 0;

            while (scanner.hasNextLine()) {

                String currentLine = scanner.nextLine();

                String newLine = currentLine.replaceAll("\\|", " ");


                String[] res = currentLine.split("\\|");
                for (String out : res) {
                    if (!"".equals(out)) {

                    }
                    // String str1 = String.format("%-15s", res[1]);
                    // String str2 = String.format("%5s", res[2]);
                    // String anotherLine = (res[0]+ "\t " + str1 +"\t"+ str2  + "\t  " + amt +" in stock");
                    // options[i] = anotherLine;
                    slotLocation=res[0];
                    productName = res[1];
                    priceItem = Double.parseDouble(res[2]);
                    itemType = res[3];
                    setSlotLocation(slotLocation);
                    setProductName(productName);
                    setItemType(itemType);
                    setPriceItem(priceItem);
                    setInventoryAmt(inventoryAmt);

                }
                i++;
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }







}
