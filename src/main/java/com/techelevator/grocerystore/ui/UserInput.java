package com.techelevator.grocerystore.ui;

import com.techelevator.grocerystore.application.DeliItem;
import com.techelevator.grocerystore.application.ShoppingCart;
import com.techelevator.grocerystore.application.GroceryItem;

import java.util.Map;
import java.util.Scanner;

public class UserInput {

    private final static Scanner scanner = new Scanner(System.in);

    public static String mainMenuChoice() {
        String choice;

        do {
            UserOutput.displayMainMenu();
            choice = scanner.nextLine();

            try {
                if (!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") ||
                        choice.equals("5") || choice.equals("6") || choice.toLowerCase().contains("walk") ||
                        choice.toLowerCase().contains("aisle") || choice.toLowerCase().contains("deli") ||
                        choice.toLowerCase().contains("remove") || choice.toLowerCase().contains("checkout") ||
                        choice.equals("7") || choice.equals("8") || choice.equals("9") || choice.equals("10"))) {
                    throw new IllegalArgumentException("Enter a menu option to continue");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") ||
                choice.equals("5") || choice.equals("6") || choice.toLowerCase().contains("walk") ||
                choice.toLowerCase().contains("aisle") || choice.toLowerCase().contains("deli") ||
                choice.toLowerCase().contains("remove") || choice.toLowerCase().contains("checkout") ||
                choice.equals("7") || choice.equals("8") || choice.equals("9") || choice.equals("10")));

        return choice;
    }

    public static String itemToAddToGroceryList() {

        System.out.println("\nWhat would you like to add to your grocery list?");
        System.out.print(">>> ");
        return scanner.nextLine();
    }

    public static boolean addAnotherItemToGroceryList() {

        return getBooleanFromYNQuestion("\nWould you like to add another item to your grocery list? (Y/N)");
    }

    public static String itemToRemoveFromGroceryList() {
        System.out.println("\nWhat would you like to remove from your grocery list?");
        System.out.print(">>> ");
        return scanner.nextLine();
    }

    public static boolean removeAnotherItemFromGroceryList() {

        return getBooleanFromYNQuestion("\nWould you like to remove another item from your grocery list? (Y/N)");
    }

    public static String itemToAddToCart() {
        System.out.println("\nWhat item would you like to add to your cart? " +
                "(e.g. for \"Bag of Celery Hearts\" type \"Celery\")");
        System.out.print(">>> ");
        return scanner.nextLine();
    }

    public static String deliItemToAddToCart() {
        System.out.println("\nWhat deli item would you like the deli employee to package for you? " +
                "(e.g. for \"Macaroni and Cheese\" type \"Macaroni\")");
        System.out.print(">>> ");
        return scanner.nextLine();
    }

    public static String getWeightToAddToCart(DeliItem deliItem) {
        System.out.println("\nHow many lbs of " + deliItem.getName() +
                " would you like the deli employee to package for you? Type \"cancel\" to cancel.");
        System.out.print(">>> ");
        return scanner.nextLine();
    }

    public static String itemToRemoveFromCart() {
        System.out.println("\nWhat item would you like to remove from your cart?");
        System.out.print(">>> ");
        return scanner.nextLine();
    }

    public static String getQuantityToAdd(Map.Entry<GroceryItem, Integer> item) {
        System.out.println("\nHow many " + item.getKey().getPluralName() +
                " would you like to add to your cart? Type \"cancel\" to cancel.");
        System.out.print(">>> ");
        return scanner.nextLine();
    }

    public static boolean removeDeliItemFromCart() {

        return getBooleanFromYNQuestion("\nAre you sure you want to remove this deli item from your cart? (Y/N)");
    }

    public static String getQuantityToRemove(Map.Entry<GroceryItem, Integer> item) {
        System.out.println("\nHow many " + item.getKey().getPluralName() +
                " would you like to remove from your cart? Type \"cancel\" to cancel");
        System.out.print(">>> ");
        return scanner.nextLine();
    }

    public static boolean addAllToCart(Map.Entry<GroceryItem, Integer> item,
                                      Map<GroceryItem, Integer> groceryInventory) {

        return getBooleanFromYNQuestion("\nThere " + (item.getValue() == 1 ? "is" : "are") +
                " only " + item.getValue() + " " +
                item.getKey().getSingularOrPluralName(item.getKey(), groceryInventory) +
                " in stock. Would you like to add all of them to your cart? (Y/N)");
    }

    public static boolean addAllDeliItemToCart(DeliItem deliItem) {


        return getBooleanFromYNQuestion("\nThere is only " + deliItem.getWeight() + " lbs of " + deliItem.getName() +
                " available. Would you like to add all of it to your cart? (Y/N)");
    }

    public static boolean removeAllFromCart(Map.Entry<GroceryItem, Integer> item,
                                            ShoppingCart cart) {

        return getBooleanFromYNQuestion("\nThere " + (item.getValue() == 1 ? "is" : "are") + " only " + item.getValue() +
                " " + item.getKey().getSingularOrPluralName(item.getKey(), cart.getCartItems()) +
                " in your cart. Would you like remove all of them from your cart? (Y/N)");
    }

    public static boolean addAnotherItemToCart() {

        return getBooleanFromYNQuestion("\nWould you like to add another item to your cart? (Y/N)");
    }

    public static boolean removeAnotherItemFromCart() {

        return getBooleanFromYNQuestion("\nWould you like to remove another item from your cart? (Y/N)");
    }

    public static boolean addADifferentItemToCart(String item) {

        return getBooleanFromYNQuestion("\nSorry, there isn't any " + item + " in stock. Would you like to try " +
                "to add a different item to your cart? (Y/N)");
    }

    public static boolean removeADifferentItemFromCart(String item) {

        return getBooleanFromYNQuestion("\nSorry, there aren't any " + item + " in your cart. " +
                "Would you like to try to remove a different item to your cart? (Y/N)");
    }

    public static String handleEncounteringHighSchoolAcquaintance() {
        String choice;

        do {
            System.out.println("\nYou turned onto the aisle and see an acquaintance from high school. They haven't" +
                    " seen you yet. What would you like to do?\n\n[1] Stop to chat\n[2] Pretend like you didn't" +
                    " see them and go to the next aisle\n[3] Abandon your cart and leave the store");
            System.out.print(">>> ");
            choice = scanner.nextLine();

            try {
                if (!(choice.equals("1") || choice.equals("2") || choice.equals("3")
                        || choice.toLowerCase().contains("chat") || choice.toLowerCase().contains("pretend") ||
                        choice.toLowerCase().contains("abandon") || choice.toLowerCase().contains("leave"))) {
                    throw new IllegalArgumentException("Enter a valid option to continue.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!(choice.equals("1") || choice.equals("2") || choice.equals("3")
                || choice.toLowerCase().contains("chat") || choice.toLowerCase().contains("pretend") ||
                choice.toLowerCase().contains("abandon") || choice.toLowerCase().contains("leave")));

        return choice;
    }

    public static String deliMenu() {
        String choice;

        do {
            System.out.println("\n\tWhat would you like to do?\n[1] View deli items\n[2] Add deli item to cart\n" +
                    "[3] Leave the deli counter");
            System.out.print(">>> ");
            choice = scanner.nextLine();

            try {
                if (!(choice.equals("1") || choice.equals("2") || choice.equals("3"))) {
                    throw new IllegalArgumentException("Enter a valid option to continue.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!(choice.equals("1") || choice.equals("2") || choice.equals("3")));

        return choice;
    }

    public static void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static boolean getBooleanFromYNQuestion(String question) {
        String choice = "";

        do {
            try {
                System.out.println(question);
                System.out.print(">>> ");
                choice = scanner.nextLine();

                if (!(choice.equalsIgnoreCase("y") ||
                        choice.equalsIgnoreCase("n"))) {
                    throw new IllegalArgumentException("Enter \"Y\" or \"N\" to continue");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!(choice.equalsIgnoreCase("y") ||
                choice.equalsIgnoreCase("n")));

        return (choice.equalsIgnoreCase("y"));
    }


}



