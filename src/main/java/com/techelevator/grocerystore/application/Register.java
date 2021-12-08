package com.techelevator.grocerystore.application;

import com.techelevator.grocerystore.application.ShoppingCart;

public class Register {

    public void checkout(ShoppingCart cart) {

        System.out.println("\nThanks for shopping with us today!\n\nHere is your receipt!\n\n" +
                "--------------------------" + cart + "\n--------------------------" +
                "\n\nPlease come back again soon!");
    }
}
