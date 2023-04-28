package com.techelevator.view;

<<<<<<< HEAD
import com.techelevator.VendingMachineCLI;
=======
>>>>>>> 59cf9ce862545ae11253932c51e76d9e5ff7803e
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MenuTest {

    private ByteArrayOutputStream output;

    @Before
    public void setup() {
        output = new ByteArrayOutputStream();
    }

    @Test
    public void displays_a_list_of_menu_options_and_prompts_user_to_make_a_choice() {
        Object[] options = new Object[] { Integer.valueOf(3), "Blind", "Mice" };
        Menu menu = getMenuForTesting();

        menu.getChoiceFromOptions(options);

        String expected = System.lineSeparator() + "1) " + options[0].toString() + System.lineSeparator() + "2) " + options[1].toString() + System.lineSeparator() + "3) "
                + options[2].toString() + System.lineSeparator() + System.lineSeparator() + "Please choose an option >>> ";
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    public void returns_object_corresponding_to_user_choice() {
        Integer expected = Integer.valueOf(456);
        Integer[] options = new Integer[] { Integer.valueOf(123), expected, Integer.valueOf(789) };
        Menu menu = getMenuForTestingWithUserInput("2" + System.lineSeparator());

        Integer result = (Integer) menu.getChoiceFromOptions(options);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void redisplays_menu_if_user_does_not_choose_valid_option() {
        Object[] options = new Object[] { "Larry", "Curly", "Moe" };
        Menu menu = getMenuForTestingWithUserInput("4" + System.lineSeparator() + "1" + System.lineSeparator());

        menu.getChoiceFromOptions(options);

        String menuDisplay = System.lineSeparator() + "1) " + options[0].toString() + System.lineSeparator() + "2) " + options[1].toString() + System.lineSeparator() + "3) "
                + options[2].toString() + System.lineSeparator() + System.lineSeparator() + "Please choose an option >>> ";

        String expected = menuDisplay + System.lineSeparator() + "*** 4 is not a valid option ***" + System.lineSeparator() + System.lineSeparator() + menuDisplay;

        Assert.assertEquals(expected, output.toString());
    }

    @Test
    public void redisplays_menu_if_user_chooses_option_less_than_1() {
        Object[] options = new Object[] { "Larry", "Curly", "Moe" };
        Menu menu = getMenuForTestingWithUserInput("0" + System.lineSeparator() + "1" + System.lineSeparator());

        menu.getChoiceFromOptions(options);

        String menuDisplay = System.lineSeparator() + "1) " + options[0].toString() + System.lineSeparator() + "2) " + options[1].toString() + System.lineSeparator() + "3) "
                + options[2].toString() + System.lineSeparator() + System.lineSeparator() + "Please choose an option >>> ";

        String expected = menuDisplay + System.lineSeparator() + "*** 0 is not a valid option ***" + System.lineSeparator() + System.lineSeparator() + menuDisplay;

        Assert.assertEquals(expected, output.toString());
    }

    @Test
    public void redisplays_menu_if_user_enters_garbage() {
        Object[] options = new Object[] { "Larry", "Curly", "Moe" };
        Menu menu = getMenuForTestingWithUserInput("Mickey Mouse" + System.lineSeparator() + "1" + System.lineSeparator());

        menu.getChoiceFromOptions(options);

        String menuDisplay = System.lineSeparator() + "1) " + options[0].toString() + System.lineSeparator() + "2) " + options[1].toString() + System.lineSeparator() + "3) "
                + options[2].toString() + System.lineSeparator() + System.lineSeparator() + "Please choose an option >>> ";

        String expected = menuDisplay + System.lineSeparator() + "*** Mickey Mouse is not a valid option ***" + System.lineSeparator() + System.lineSeparator() + menuDisplay;

        Assert.assertEquals(expected, output.toString());
    }

    private Menu getMenuForTestingWithUserInput(String userInput) {
        ByteArrayInputStream input = new ByteArrayInputStream(String.valueOf(userInput).getBytes());
        return new Menu(input, output);
    }

<<<<<<< HEAD
	private Menu getMenuForTesting() {
		return getMenuForTestingWithUserInput("1" + System.lineSeparator());
	}

	/**
	 * @Tests the size of the Map and confirms the correct number of Items for the Machine
	 */
	@Test
    public void testStockMachineMapSize() {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		menu.stockMachine();
		System.out.println(System.lineSeparator() +"Test allItems Map Size");
		System.out.println("allItems map size: " + menu.getAllItems().size() + "   Expected: 16");
		Assert.assertEquals(menu.getAllItems().size(), 16);

	}

	/**
	 * @Tests Checks if each item instantiated has the correct name
	 */
	@Test
	public void testStockMachineItemName(){
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		menu.stockMachine();
		System.out.println(System.lineSeparator() + "Test allItems objects for name");

		// Slot A1
		System.out.println("Map Item at A1 Name: " + menu.getAllItems().get("A1").getProductName() + "    Expected: Potato Crisps");
		Assert.assertEquals("Potato Crisps", menu.getAllItems().get("A1").getProductName());

		// Slot A2
		System.out.println("Map Item at A2 Name: " + menu.getAllItems().get("A2").getProductName() + "    Expected: Stackers");
		Assert.assertEquals("Stackers", menu.getAllItems().get("A2").getProductName());

		// Slot A3
		System.out.println("Map Item at A3 Name: " + menu.getAllItems().get("A3").getProductName() + "    Expected: Grain Waves");
		Assert.assertEquals("Grain Waves", menu.getAllItems().get("A3").getProductName());

		// Slot A4
		System.out.println("Map Item at A4 Name: " + menu.getAllItems().get("A4").getProductName() + "    Expected: Cloud Popcorn");
		Assert.assertEquals("Cloud Popcorn", menu.getAllItems().get("A4").getProductName());

		// Slot B1
		System.out.println("Map Item at B1 Name: " + menu.getAllItems().get("B1").getProductName() + "    Expected: Moonpie");
		Assert.assertEquals("Moonpie", menu.getAllItems().get("B1").getProductName());

		// Slot B2
		System.out.println("Map Item at B2 Name: " + menu.getAllItems().get("B2").getProductName() + "    Expected: Cowtales");
		Assert.assertEquals("Cowtales", menu.getAllItems().get("B2").getProductName());

		// Slot B3
		System.out.println("Map Item at B3 Name: " + menu.getAllItems().get("B3").getProductName() + "    Expected: Wonka Bar");
		Assert.assertEquals("Wonka Bar", menu.getAllItems().get("B3").getProductName());

		// Slot B4
		System.out.println("Map Item at B4 Name: " + menu.getAllItems().get("B4").getProductName() + "    Expected: Crunchie");
		Assert.assertEquals("Crunchie", menu.getAllItems().get("B4").getProductName());

		// Slot C1
		System.out.println("Map Item at C1 Name: " + menu.getAllItems().get("C1").getProductName() + "    Expected: Cola");
		Assert.assertEquals("Cola", menu.getAllItems().get("C1").getProductName());

		// Slot C2
		System.out.println("Map Item at C2 Name: " + menu.getAllItems().get("C2").getProductName() + "    Expected: Dr. Salt");
		Assert.assertEquals("Dr. Salt", menu.getAllItems().get("C2").getProductName());

		// Slot C3
		System.out.println("Map Item at C3 Name: " + menu.getAllItems().get("C3").getProductName() + "    Expected: Mountain Melter");
		Assert.assertEquals("Mountain Melter", menu.getAllItems().get("C3").getProductName());

		// Slot C4
		System.out.println("Map Item at C4  Name: " + menu.getAllItems().get("C4").getProductName() + "    Expected: Heavy");
		Assert.assertEquals("Heavy", menu.getAllItems().get("C4").getProductName());

		// Slot D1
		System.out.println("Map Item at D1 Name: " + menu.getAllItems().get("D1").getProductName() + "    Expected: U-Chews");
		Assert.assertEquals("U-Chews", menu.getAllItems().get("D1").getProductName());

		// Slot D2
		System.out.println("Map Item at D2 Name: " + menu.getAllItems().get("D2").getProductName() + "    Expected: Little League Chew");
		Assert.assertEquals("Little League Chew", menu.getAllItems().get("D2").getProductName());

		// Slot D3
		System.out.println("Map Item at D3 Name: " + menu.getAllItems().get("D3").getProductName() + "    Expected: Chiclets");
		Assert.assertEquals("Chiclets", menu.getAllItems().get("D3").getProductName());

		// Slot D4
		System.out.println("Map Item at D4 Name: " + menu.getAllItems().get("D4").getProductName() + "    Expected: Triplemint");
		Assert.assertEquals("Triplemint", menu.getAllItems().get("D4").getProductName());
	}

	/**
	 * @Tests Checks if each item instantiated has the correct price
	 */
	@Test
	public void testStockMachineItemPrice(){
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		menu.stockMachine();
		System.out.println(System.lineSeparator() + "Test allItems objects for price");

		// Slot A1
		System.out.println("Map Item at A1 Price: " + menu.getAllItems().get("A1").getPriceItem() + "    Expected: 3.05");
		Assert.assertEquals (3.05, menu.getAllItems().get("A1").getPriceItem(), 0.00);

		// Slot A2
		System.out.println("Map Item at A2 Price: " + menu.getAllItems().get("A2").getPriceItem() + "    Expected: 1.45");
		Assert.assertEquals (1.45, menu.getAllItems().get("A2").getPriceItem(),0.00);

		// Slot A3
		System.out.println("Map Item at A3 Price: " + menu.getAllItems().get("A3").getPriceItem() + "    Expected: 2.75");
		Assert.assertEquals (2.75, menu.getAllItems().get("A3").getPriceItem(), 0.00);

		// Slot A4
		System.out.println("Map Item at A4 Price: " + menu.getAllItems().get("A4").getPriceItem() + "    Expected: 3.65");
		Assert.assertEquals (3.65, menu.getAllItems().get("A4").getPriceItem(),  0.00);

		// Slot B1
		System.out.println("Map Item at B1 Price: " + menu.getAllItems().get("B1").getPriceItem() + "    Expected: 1.80");
		Assert.assertEquals (1.80, menu.getAllItems().get("B1").getPriceItem(), 0.00);

		// Slot B2
		System.out.println("Map Item at B2 Price: " + menu.getAllItems().get("B2").getPriceItem() + "    Expected: 1.50");
		Assert.assertEquals (1.50, menu.getAllItems().get("B2").getPriceItem(), 0.00);

		// Slot B3
		System.out.println("Map Item at B3 Price: " + menu.getAllItems().get("B3").getPriceItem() + "    Expected: 1.50");
		Assert.assertEquals (1.50, menu.getAllItems().get("B3").getPriceItem(), 0.00);

		// Slot B4
		System.out.println("Map Item at B4 Price: " + menu.getAllItems().get("B4").getPriceItem() + "    Expected: 1.75");
		Assert.assertEquals (1.75, menu.getAllItems().get("B4").getPriceItem(), 0.00);

		// Slot C1
		System.out.println("Map Item at C1 Price: " + menu.getAllItems().get("C1").getPriceItem() + "    Expected: 1.25");
		Assert.assertEquals (1.25, menu.getAllItems().get("C1").getPriceItem(), 0.00);

		// Slot C2
		System.out.println("Map Item at C2 Price: " + menu.getAllItems().get("C2").getPriceItem() + "    Expected: 1.50");
		Assert.assertEquals (1.50, menu.getAllItems().get("C2").getPriceItem(), 0.00);

		// Slot C3
		System.out.println("Map Item at C3 Price: " + menu.getAllItems().get("C3").getPriceItem() + "    Expected: 1.50");
		Assert.assertEquals (1.50, menu.getAllItems().get("C3").getPriceItem(), 0.00);

		// Slot C4
		System.out.println("Map Item at C4  Price: " + menu.getAllItems().get("C4").getPriceItem() + "    Expected: 1.50");
		Assert.assertEquals (1.50, menu.getAllItems().get("C4").getPriceItem(), 0.00);

		// Slot D1
		System.out.println("Map Item at D1 Price: " + menu.getAllItems().get("D1").getPriceItem() + "    Expected: 0.85");
		Assert.assertEquals (0.85, menu.getAllItems().get("D1").getPriceItem(), 0.00);

		// Slot D2
		System.out.println("Map Item at D2 Price: " + menu.getAllItems().get("D2").getPriceItem() + "    Expected: 0.95");
		Assert.assertEquals (0.95, menu.getAllItems().get("D2").getPriceItem(), 0.00);

		// Slot D3
		System.out.println("Map Item at D3 Price: " + menu.getAllItems().get("D3").getPriceItem() + "    Expected: 0.75");
		Assert.assertEquals (0.75, menu.getAllItems().get("D3").getPriceItem(), 0.00);

		// Slot D4
		System.out.println("Map Item at D4 Price: " + menu.getAllItems().get("D4").getPriceItem() + "    Expected: 0.75");
		Assert.assertEquals (0.75, menu.getAllItems().get("D4").getPriceItem(), 0.00);
	}

	/**
	 * @Tests Checks if each item instantiated has the correct sound
	 */
	@Test
	public void testStockMachineItemSound() {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		menu.stockMachine();
		System.out.println(System.lineSeparator() + "Test allItems objects for sound");

		// Slot A1
		System.out.println("Map Item at A1 Sound: " + menu.getAllItems().get("A1").getSound() + "    Expected: Crunch Crunch, Yum");
		Assert.assertEquals("Crunch Crunch, Yum", menu.getAllItems().get("A1").getSound());

		// Slot A2
		System.out.println("Map Item at A2 Sound: " + menu.getAllItems().get("A2").getSound() + "    Expected: Crunch Crunch, Yum");
		Assert.assertEquals("Crunch Crunch, Yum", menu.getAllItems().get("A2").getSound());

		// Slot A3
		System.out.println("Map Item at A3 Sound: " + menu.getAllItems().get("A3").getSound() + "    Expected: Crunch Crunch, Yum");
		Assert.assertEquals("Crunch Crunch, Yum", menu.getAllItems().get("A3").getSound());

		// Slot A4
		System.out.println("Map Item at A4 Sound: " + menu.getAllItems().get("A4").getSound() + "    Expected: Crunch Crunch, Yum");
		Assert.assertEquals("Crunch Crunch, Yum", menu.getAllItems().get("A4").getSound());

		// Slot B1
		System.out.println("Map Item at B1 Sound: " + menu.getAllItems().get("B1").getSound() + "    Expected: Munch Munch, Yum");
		Assert.assertEquals("Munch Munch, Yum", menu.getAllItems().get("B1").getSound());

		// Slot B2
		System.out.println("Map Item at B2 Sound: " + menu.getAllItems().get("B2").getSound() + "    Expected: Munch Munch, Yum");
		Assert.assertEquals("Munch Munch, Yum", menu.getAllItems().get("B2").getSound());

		// Slot B3
		System.out.println("Map Item at B3 Sound: " + menu.getAllItems().get("B3").getSound() + "    Expected: Munch Munch, Yum");
		Assert.assertEquals("Munch Munch, Yum", menu.getAllItems().get("B3").getSound());

		// Slot B4
		System.out.println("Map Item at B4 Sound: " + menu.getAllItems().get("B4").getSound() + "    Expected: Munch Munch, Yum");
		Assert.assertEquals("Munch Munch, Yum", menu.getAllItems().get("B4").getSound());

		// Slot C1
		System.out.println("Map Item at C1 Sound: " + menu.getAllItems().get("C1").getSound() + "    Expected: Glug Glug, Yum");
		Assert.assertEquals("Glug Glug, Yum", menu.getAllItems().get("C1").getSound());

		// Slot C2
		System.out.println("Map Item at C2 Sound: " + menu.getAllItems().get("C2").getSound() + "    Expected: Glug Glug, Yum");
		Assert.assertEquals("Glug Glug, Yum", menu.getAllItems().get("C2").getSound());

		// Slot C3
		System.out.println("Map Item at C3 Sound: " + menu.getAllItems().get("C3").getSound() + "    Expected: Glug Glug, Yum");
		Assert.assertEquals("Glug Glug, Yum", menu.getAllItems().get("C3").getSound());

		// Slot C4
		System.out.println("Map Item at C4 Sound: " + menu.getAllItems().get("C4").getSound() + "    Expected: Glug Glug, Yum");
		Assert.assertEquals("Glug Glug, Yum", menu.getAllItems().get("C4").getSound());

		// Slot D1
		System.out.println("Map Item at D1 Sound: " + menu.getAllItems().get("D1").getSound() + "    Expected: Chew Chew, Yum");
		Assert.assertEquals("Chew Chew, Yum", menu.getAllItems().get("D1").getSound());

		// Slot D2
		System.out.println("Map Item at D2 Sound: " + menu.getAllItems().get("D2").getSound() + "    Expected: Chew Chew, Yum");
		Assert.assertEquals("Chew Chew, Yum", menu.getAllItems().get("D2").getSound());

		// Slot D3
		System.out.println("Map Item at D3 Sound: " + menu.getAllItems().get("D3").getSound() + "    Expected: Chew Chew, Yum");
		Assert.assertEquals("Chew Chew, Yum", menu.getAllItems().get("D3").getSound());

		// Slot D4
		System.out.println("Map Item at D4 Sound: " + menu.getAllItems().get("D4").getSound() + "    Expected: Chew Chew, Yum");
		Assert.assertEquals("Chew Chew, Yum", menu.getAllItems().get("D4").getSound());
	}

	/**
	 * @Tests Checks if each item instantiated has the correct inventory number
	 */
	@Test
	public void testStockMachineItemInventory(){
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		menu.stockMachine();
		System.out.println(System.lineSeparator() + "Test allItems Objects for Inventory");

		// Slot A1
		System.out.println("Map Item at A1 Inventory: " + menu.getAllItems().get("A1").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("A1").getInventoryAmt(), 0);

		// Slot A2
		System.out.println("Map Item at A2 Inventory: " + menu.getAllItems().get("A2").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("A2").getInventoryAmt(), 0);

		// Slot A3
		System.out.println("Map Item at A3 Inventory: " + menu.getAllItems().get("A3").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("A3").getInventoryAmt(), 0);

		// Slot A4
		System.out.println("Map Item at A4 Inventory: " + menu.getAllItems().get("A4").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("A4").getInventoryAmt(), 0);

		// Slot B1
		System.out.println("Map Item at B1 Inventory: " + menu.getAllItems().get("B1").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("B1").getInventoryAmt(), 0);

		// Slot B2
		System.out.println("Map Item at B2 Inventory: " + menu.getAllItems().get("B2").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("B2").getInventoryAmt(), 0);

		// Slot B3
		System.out.println("Map Item at B3 Inventory: " + menu.getAllItems().get("B3").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("B3").getInventoryAmt(), 0);

		// Slot B4
		System.out.println("Map Item at B4 Inventory: " + menu.getAllItems().get("B4").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("B4").getInventoryAmt(), 0);

		// Slot C1
		System.out.println("Map Item at C1 Inventory: " + menu.getAllItems().get("C1").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals( 5, menu.getAllItems().get("C1").getInventoryAmt(),0);

		// Slot C2
		System.out.println("Map Item at C2 Inventory: " + menu.getAllItems().get("C2").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("C2").getInventoryAmt(), 0);

		// Slot C3
		System.out.println("Map Item at C3 Inventory: " + menu.getAllItems().get("C3").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("C3").getInventoryAmt(), 0);

		// Slot C4
		System.out.println("Map Item at C4 Inventory: " + menu.getAllItems().get("C4").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("C4").getInventoryAmt(), 0);

		// Slot D1
		System.out.println("Map Item at D1 Inventory: " + menu.getAllItems().get("D1").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("D1").getInventoryAmt(), 0);

		// Slot D2
		System.out.println("Map Item at D2 Inventory: " + menu.getAllItems().get("D2").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("D2").getInventoryAmt(), 0);

		// Slot D3
		System.out.println("Map Item at D3 Inventory: " + menu.getAllItems().get("D3").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("D3").getInventoryAmt(), 0);

		// Slot D4
		System.out.println("Map Item at D4 Inventory: " + menu.getAllItems().get("D4").getInventoryAmt() + "    Expected: 5");
		Assert.assertEquals(5, menu.getAllItems().get("D4").getInventoryAmt(), 0);
	}
=======
    private Menu getMenuForTesting() {
        return getMenuForTestingWithUserInput("1" + System.lineSeparator());
    }
>>>>>>> 59cf9ce862545ae11253932c51e76d9e5ff7803e
}
