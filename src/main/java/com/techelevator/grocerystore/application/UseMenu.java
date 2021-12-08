package com.techelevator.grocerystore.application;

import com.techelevator.grocerystore.ui.UserInput;
import com.techelevator.grocerystore.ui.UserOutput;

import java.util.List;
import java.util.Map;

public class UseMenu {

    public static void run(ShoppingCart cart, Register register, Shopper shopper,
                           Map<GroceryItem, Integer> groceryInventory, List<DeliItem> deliItems) {

        while (true) {
            String choice = UserInput.mainMenuChoice();

            if (choice.equals("1")) {
                UserOutput.viewGroceryList();
            }

            if (choice.equals("2")) {
                GroceryList.addItemToGroceryList();
            }

            if (choice.equals("3")) {
                GroceryList.removeItemFromGroceryList();
            }

            if (choice.equals("4") || choice.toLowerCase().contains("walk") || choice.toLowerCase().contains("aisle")
                    || choice.toLowerCase().contains("browse") || choice.toLowerCase().contains("inventory")) {
                shopper.browseGroceryStore(groceryInventory);
            }

            if (choice.equals("5") || choice.toLowerCase().contains("deli")) {
                shopper.visitDeliCounter(deliItems, cart);
            }

            if (choice.equals("6")) {
                shopper.addItemsToCart(groceryInventory, cart);
            }

            if (choice.equals("7")) {
                UserOutput.viewCart(cart);
            }

            if (choice.equals("8") || choice.toLowerCase().contains("remove")) {
                shopper.removeItemsFromCart(groceryInventory, cart);
            }

            if (choice.equals("9") || choice.toLowerCase().contains("out")) {
                if (cart.getCartItems().isEmpty()) {
                    UserOutput.cartIsEmpty();
                } else {
                    register.checkout(cart);
                    System.exit(0);
                }
            }

            if (choice.equals("10") || choice.toLowerCase().contains("abandon")) {
                UserOutput.abandonCart();
                System.exit(1);
            }

            UserInput.pause();
        }
    }


}
