package com.techelevator.grocerystore.ui;

import com.techelevator.grocerystore.application.DeliItem;
import com.techelevator.grocerystore.application.ShoppingCart;
import com.techelevator.grocerystore.application.GroceryItem;
import com.techelevator.grocerystore.application.GroceryList;

import java.util.List;
import java.util.Map;

public class UserOutput {

    private static String mainMenu = "[1] View your grocery list\n[2] Add an item to your grocery list\n[3] Remove " +
            "an item from your grocery list\n[4] Walk the aisles and browse the inventory\n[5] Go to the deli counter" +
            "\n[6] Add an item to your cart\n[7] View your cart\n[8] Remove an item from your cart\n[9] Checkout" +
            "\n[10] Abandon your cart\n>>> ";

    public static void displayHomeScreen() {
        System.out.println("*************************************************************");
        System.out.println("*************** Welcome to J & P Supermarket! ***************");
        System.out.println("*************************************************************\n");
    }

    public static void displayMainMenu() {

        System.out.println("\n\tWhat would you like to do?");
        System.out.print(mainMenu);
    }

    public static void walkAisles() {
        System.out.println("----------------------------------------------------------");
        System.out.println("         |               |            |             |");
        System.out.println("       C |               |            |             |");
        System.out.println("      /\\_________        |            |             |");
        System.out.println("==== /    |######| =======================================");
        System.out.println("    |\\    |#####/        |            |             |   ");
        System.out.println("    / |   |<_____        |            |             | ");
        System.out.println("=== ` `   o      o =======================================");
    }

    public static void displayInventory(Map<GroceryItem, Integer> inventory) {
        String output = "";

        for (Map.Entry<GroceryItem, Integer> inventoryItem : inventory.entrySet()) {
            output += inventoryItem.getValue() + " " +
                    inventoryItem.getKey().getSingularOrPluralName(inventoryItem.getKey(), inventory) + " -- $" +
                    inventoryItem.getKey().getPrice() + "\n";
        }

        System.out.println(output);
    }

    public static void displayDeliInventory(List<DeliItem> deliItems) {
        String output = "";

        for (DeliItem deliItem : deliItems) {
            output += deliItem.getName() + " -- " + deliItem.getWeight() + " lbs @ " + deliItem.getPrice() +
                    " per lb\n";
        }

        System.out.println(output);
    }

    public static void viewGroceryList() {
        System.out.println(GroceryList.viewGroceryList());
    }

    public static void viewCart(ShoppingCart cart) {
        System.out.println(cart);
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void cartIsEmpty() {
        System.out.println("\nYou need to add items to your cart before you can checkout!");
    }

    public static void abandonCart() {
        System.out.println("You leave your cart in the middle of the produce section and walk out of the " +
                "store. Hopefully there are enough groceries at home!");
    }

    public static void itemAddedToGroceryList(String itemToAdd) {
        System.out.println("\n" + itemToAdd + " successfully added to your grocery list!");
    }

    public static void itemRemovedFromGroceryList(String itemRemoved, int numberRemoved) {
        System.out.println("Removed " + numberRemoved + (numberRemoved == 1 ? " item" : " items") + " that matched "
                + itemRemoved);
    }

    public static void chatWithHighSchoolAcquaintance() {
        System.out.println("\nYou catch up and reminisce about the good old days...");
    }

    public static void backToBrowsing() {
        System.out.println("\nYou take a lull in the conversation as an opportunity to get back" +
                " to shopping.\nYou you finish browsing the aisles," +
                " and you've seen it has the following items in stock: ");
    }

    public static void sneakPastHighSchoolAcquaintance() {
        System.out.println("\nIt took a little longer to see what was in stock, since you had to double back" +
                " to see the aisle that you skipped, but here are the items in stock: ");
    }

    public static void runAwayFromHighSchoolAcquaintance() {
        System.out.println("\nYou abandon your cart in the aisle, turn around, and head back to your car to" +
                " go back home. You'll have complete your shopping trip another day.");
    }

    public static void browseAisles() {
        System.out.println("\nYou walked around the entire store," +
                " and you've seen it has the following items in stock:");
    }

    public static void enterPositiveNumberToAdd(Map.Entry<GroceryItem, Integer> item) {
        System.out.println("Please enter a positive number of " +
                item.getKey().getSingularOrPluralName(item.getKey(), 2)
                + " to add to your cart.");
    }

    public static void enterPositiveWeightToAdd(DeliItem deliItem) {
        System.out.println("Please enter a positive weight of " + deliItem.getName() + " to add to your cart.");
    }

    public static void enterPositiveNumberToRemove(Map.Entry<GroceryItem, Integer> item) {
        System.out.println("\nPlease enter a positive number of " +
                item.getKey().getSingularOrPluralName(item.getKey(), 2) +
                " to remove from your cart.");
    }

    public static void itemNotAddedToCart() {
        System.out.println("\nThe item wasn't added to your cart. Please enter a number " +
                "for quantity.");
    }

    public static void deliItemNotAddedToCart() {
        System.out.println("\nThe item wasn't added to your cart. Please enter a number " +
                "for weight");
    }

    public static void itemNotRemovedFromCart() {
        System.out.println("\nThe item wasn't removed to your cart. Please enter a number " +
                "for quantity.");
    }

}
