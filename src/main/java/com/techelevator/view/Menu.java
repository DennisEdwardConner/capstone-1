package com.techelevator.view;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Menu {
	private static final DecimalFormat df = new DecimalFormat("0.00");
	private PrintWriter out;
	private Scanner in;
	private double currentMoney = 0;

	/**
	 * The method is used in the menu testing class to test the instantiated objects
	 * @return allItems map
	 */
	public Map<String, Item> getAllItems() {
		return allItems;
	}
	//local variables
	private Map<String, Item> allItems = new HashMap<>();
	private double selectedItemPrice;
	private int currentStock;

	/**
	 * Setter method used to amend the money provided
	 * @return void
	 */
	public void setCurrentMoney(double currentMoney) {
		this.currentMoney = currentMoney;
	}

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}


	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			if (options[i].equals("R")) {
				continue;
			}
			int optionNum = i + 1;

			out.println(optionNum + ") " + options[i]);
		}
		if (currentMoney > 0) {
			out.println("\n" + "Current Money Provided $ " + df.format(currentMoney));
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	/**
	 * Begins the feeding prompts the user to enter bills one at a time
	 * Each successful feed is logged using the VMLogs log method
	 * It allows them to continue to enter bills until they use a non-numeric entry, then they are returned to the
	 * Purchase screen. If they do not enter a valid dollar bill amount it prompts them accordingly
	 * @return void
	 * @throws CustomException and breaks the while loop when a non number is entered to return to the main menu
	 */
	public void feedMoney() throws CustomException {

		System.out.print("To exit this screen, enter any non-numeric key." + System.lineSeparator() + "Or insert whole dollar bills, one at a time >>>");
		try {
			String entry = in.nextLine();
			if (!entry.matches("^\\d+$")) {
				throw new CustomException();
			}
			int fedBill = Integer.parseInt(entry);
			if (fedBill == 50 || fedBill == 100) {
				System.out.println("Sorry... the Vendo_Matic 800 cannot take bills larger than a 20 for security purposes.");
			}
			if (fedBill == 1 || fedBill == 2 || fedBill == 5
					|| fedBill == 10 || fedBill == 20) {

				currentMoney += fedBill;

				System.out.println(System.lineSeparator() + "$" + df.format(currentMoney) + " provided successfully.");

				VMLog.log("FEED MONEY:  $" + fedBill + ".00  $" + df.format(currentMoney));
			}
		}
		catch (CustomException e) {
			throw e;
		}
	}

	/**
	 * Prompts the user to enter a slot location and uses that to iterate through the allItems map and perform the
	 * transaction.
	 * If the user doesn't enter a valid slot number they are prompted that the choice was invalid. If it
	 * is, it uses the input as the key to return the Item from map. Then using getter and setter methods from the
	 * item,to confirm that user has feed enough money for the purchase and that there is sufficient stock before
	 * completing the purchase. If either of those is untrue, the user is prompted accordingly. After the purchase is
	 * complete, the user's money provided is updated and the transaction is logged.
	 *
	 * @Return void
	 */
	public void selectProduct() {
		System.out.println("Please enter the slot location of the item you wish to purchase >>>");
		try {
			String choice2 = in.nextLine();
			choice2.toUpperCase(Locale.ROOT);
			while (!allItems.containsKey(choice2)) {
				out.println();
				System.out.println(System.lineSeparator() + "Please enter a valid selection...");
				choice2 = in.nextLine();
				choice2.toUpperCase(Locale.ROOT);
			}
			selectedItemPrice = allItems.get(choice2).getPriceItem();
			currentStock = allItems.get(choice2).getInventoryAmt();
			if (currentStock > 0) {
				if (currentMoney >= selectedItemPrice) {
					currentMoney -= selectedItemPrice;

					VMLog.log(allItems.get(choice2).getProductName() + " " + allItems.get(choice2).getSlotLocation() + " $" +
							df.format((currentMoney + selectedItemPrice)) + " $" + df.format(currentMoney));
					allItems.get(choice2).setInventoryAmt(currentStock - 1);
					System.out.println(allItems.get(choice2).getSound());
					System.out.println("Current Money Provided $ " + df.format(currentMoney));
				} else {
					System.out.println("There is not enough money provided to purchase the product.");
					System.out.println("Product price: " + selectedItemPrice + "     Current Money Provided $ " + df.format(currentMoney));
					System.out.println("Please feed more or finish transaction");
				}
			} else {
				out.println("Sorry...the product is sold out. :(");
				out.println("please select another item >>>");
			}
		} catch (InputMismatchException e) {
			out.println();
			System.out.println(System.lineSeparator() + "Please enter a valid selection...");
		}
	}

	/**
	 * Getter method to return the money provided
	 * @return double
	 */
	public double getMoneyProvided() {
		return currentMoney;
	}

	/**
	 * Iterates through the allItems map and uses the displayItemsToString method from the Item class to return the
	 * appropriate information to the command line interface
	 * @Returns void
	 */
	public void displayAllItems() {
		for (Map.Entry<String, Item> entry : allItems.entrySet()) {
			System.out.println(entry.getKey() + ") " + entry.getValue().displayItemsToString());
		}
	}

	/**
	 * Reads the csv file line by line, and splits the line into a temp array using the '|' as the delimiter. Then its
	 * confirms the item type and with that uses the appropriate constructors inherited item class to instantiate
	 * each Chip, Gum, Candy and Soda, then store it in the allItems map
	 * @Returns void
	 */
	public void stockMachine() {


		String filePath = "src/main/resources/vendingmachine.csv";
		File path = new File(filePath);

		try {
			Scanner scanner = new Scanner(path);


			while (scanner.hasNextLine()) {
				String currentLine = scanner.nextLine();
				String[] temp = currentLine.split("\\|");

				String slotNum = temp[0];
				String itemName = temp[1];
				double price = Double.parseDouble(temp[2]);
				String itemType = temp[3];

				if (itemType.matches("Chip")) {

					Chip chip = new Chip(slotNum, itemName, price);
					allItems.put(slotNum, chip);
				} else if (itemType.matches("Drink")) {
					Drink drink = new Drink(slotNum, itemName, price);
					allItems.put(slotNum, drink);
				} else if (itemType.matches("Candy")) {
					Candy candy = new Candy(slotNum, itemName, price);
					allItems.put(slotNum, candy);
				} else if (itemType.matches("Gum")) {
					Gum gum = new Gum(slotNum, itemName, price);
					allItems.put(slotNum, gum);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Iterates through the map allItems and uses the getter methods to get the names, prices and inventory to determine
	 * how many were sold and calculate the total sales. Then it prints it to a file with an individual time stamp and
	 * displays to the user that the report was created
	 * @throws FileNotFoundException
	 */
	public void printSalesReport() throws FileNotFoundException{
		PrintWriter printWriter = null;
		int startingStock = 5;
		double sales;
		double totalSales = 0;
		int numberSold;
		String fileName = "salesreport" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss'.txt'").format(new Date());

		try {
			printWriter = new PrintWriter(new FileOutputStream(fileName));
			for (Map.Entry<String, Item> entry : allItems.entrySet()) {
				numberSold = startingStock - entry.getValue().getInventoryAmt();
				printWriter.println(entry.getValue().getProductName() + "|" + numberSold);
				sales = numberSold * entry.getValue().getPriceItem();
				totalSales += sales;
			}
			printWriter.println(System.lineSeparator() + "TOTAL SALES : $" + df.format(totalSales));
			System.out.println("Sales Report Created...");
		} catch (FileNotFoundException e) {
			e.getCause();
		}
		assert printWriter != null;
		printWriter.flush();
	}
<<<<<<< HEAD
=======
	public void printReport(Object[] options){

		String messageReport = "";
		int lenthLine = 0;
		int amtSold = 0;
		double totalSales = 0.0;
		int j = 0;

		for (int i = 0; i < options.length; i++) {
			String newLine = (String) options[i];
			lenthLine = newLine.length();
			String logList = (newLine.substring(4, 19).trim());
			String costAmt = newLine.substring(lenthLine - 19, lenthLine - 11);
			double costItem = Double.parseDouble(costAmt);
			String amtOfInv = newLine.substring(lenthLine - 10, lenthLine);
			if (amtOfInv.contains("SOLD OUT")) {
				amtSold = 5;
				totalSales += (amtSold * costItem);
			} else {
				amtOfInv = amtOfInv.replace("in stock", "");
				j = Integer.parseInt(amtOfInv.trim());
				amtSold = 5 - j;
				totalSales += (amtSold * costItem);
			}
			messageReport += (logList.trim() + "|" + amtSold + "\n");
		}
		messageReport += ("\n\n **TOTAL SALES **  " + df.format(totalSales));
		TELog.reportLog(messageReport);
		//System.out.println(messageReport);
	}
	

>>>>>>> 59cf9ce862545ae11253932c51e76d9e5ff7803e
}





