package com.techelevator;

import com.techelevator.view.CustomException;
import com.techelevator.view.Menu;
import com.techelevator.view.VMLog;

import java.io.FileNotFoundException;
<<<<<<< HEAD
import java.text.DecimalFormat;
=======
import java.io.IOException;
import java.util.Scanner;



>>>>>>> 59cf9ce862545ae11253932c51e76d9e5ff7803e

public class VendingMachineCLI {

	//Formatting
	private static final DecimalFormat df = new DecimalFormat("0.00");
	private static final String RED_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_ITALICS = "\033[3m";
	private static final String ITALICS_RESET = "\033[0m";

	//Main Menu Array setup
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "R";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
	MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};

	//Purchase Menu Array setup
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
<<<<<<< HEAD
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};


	private Menu menu;

=======
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };


	//List<Items> allItems;
	Object[] options;

	private Menu menu;


	public void stockMachine() throws IOException {

		//allItems = new ArrayList<Items>(16);
		options = new Object[16];
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
					String str1 = String.format("%-15s", res[1]);
					String str2 = String.format("%5s", res[2]);
					String anotherLine = (res[0]+ "\t " + str1 +"\t"+ str2  + "\t  " + amt +" in stock");
					options[i] = anotherLine;
					//allItems.set(res[0]);
					//items.setProductName(res[2]);
					//items.setItemType(res[3]);
					//items.setPriceItem(Double.parseDouble(res[4]));
					//items.setInventoryAmt(5);

				}
				i++;
			}
		}catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		}

	}

>>>>>>> 59cf9ce862545ae11253932c51e76d9e5ff7803e
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;

	}

<<<<<<< HEAD
	//Starts by displaying the main menu and then based off of the entry guiding you to each screen.
	public void run() {
=======


	public void run() throws IOException {

		double balance = 0;
>>>>>>> 59cf9ce862545ae11253932c51e76d9e5ff7803e
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				menu.displayAllItems();
			}
			else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						while (true) {
							try {
								menu.feedMoney();
							} catch (CustomException e) {
								break;
							}
						}
					} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						menu.displayAllItems();
						menu.selectProduct();
						break;
					} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {

<<<<<<< HEAD
						//Stores the remaining money provided in a variable and them clears it
						double returnAmount = menu.getMoneyProvided();
						menu.setCurrentMoney(0.00);

						//Logs the changes return
						VMLog.log("GIVE CHANGE: $" + df.format(returnAmount) + " $" + df.format(menu.getMoneyProvided()));

						//Figures out the correct change using the smallest number of coins
						double change = (int) (Math.ceil(returnAmount * 100));
						int quarters = ((int) change / 25);
						change = change % 25;
						int dimes = ((int) change / 10);
						change = change % 10;
						int nickels = ((int) change / 5);

						//Prints the number of the coins returned if it is greater than 0
						System.out.println("Dispensing your change...");
						if (quarters > 0) {System.out.println("Quarters: " + quarters);}
						if (dimes > 0) {System.out.println("Dimes: " + dimes);}
						if(nickels > 0) {System.out.println("Nickels: " + nickels);}

						//Returns to main menu
=======
					if (choice2.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						balance = menu.feedMoney();

					}else if (choice2.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
						//menu.getChoiceFromOptions(options);
						menu.purchaseItem(options);
					}else if (choice2.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)){
						menu.finishTransaction();
>>>>>>> 59cf9ce862545ae11253932c51e76d9e5ff7803e
						break;
					}
				}
			}
			else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				//Thanks the user Vendo-Matic by umbrella corp and then displays both of umbrella corps mottos
				System.out.println("Thank you for choosing Vendo-Matic 800 by Umbrella Corp." +
								System.lineSeparator() + "\"Our Business is life itself.\"");
						System.out.println(ANSI_ITALICS + ANSI_RED +
								"\"Obedience Breeds Discipline, Discipline Breeds Unity, Unity Breeds Power, Power is Life\""
								+ RED_RESET + ITALICS_RESET);
						//Exits
						System.exit(0);
					}
			else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)){
				try {
					menu.printSalesReport();
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
<<<<<<< HEAD
		//Added the stockMachine method the run method is invoked
		menu.stockMachine();
=======

		cli.stockMachine();
>>>>>>> 59cf9ce862545ae11253932c51e76d9e5ff7803e
		cli.run();
	}
}

