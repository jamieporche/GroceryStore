package com.techelevator.grocerystore;

import com.techelevator.grocerystore.application.*;
import com.techelevator.grocerystore.ui.UserOutput;

public class GroceryStoreCLI {

    public static void main(String[] args) {

        GroceryStore groceryStore = new GroceryStore();
        ShoppingCart cart = new ShoppingCart();
        Register register = new Register();
        Shopper shopper = new Shopper();
        DeliCounter deliCounter = new DeliCounter();

        UserOutput.displayHomeScreen();

        UseMenu.run(cart, register, shopper, groceryStore.getGroceryInventory(), deliCounter.getDeliInventory());
    }


}
