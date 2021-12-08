package com.techelevator.grocerystore.application;

import com.techelevator.grocerystore.application.DeliItem;
import com.techelevator.grocerystore.application.GroceryItem;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<GroceryItem, Integer> cartItems = new HashMap<>();

    public Map<GroceryItem, Integer> getCartItems() {
        return cartItems;
    }

    public String toString() {
        String output = "\n";
        BigDecimal total = BigDecimal.ZERO;

        if (cartItems.isEmpty()) {
            return "\nYour cart is empty.\n";
        }

        for (Map.Entry<GroceryItem, Integer> cartItem : cartItems.entrySet()) {

            output += cartItem.getValue() + " " + cartItem.getKey() + "\n";
            total =  total.add(cartItem.getKey().getPrice().multiply(BigDecimal.valueOf(cartItem.getValue())));
        }

        output += "\nTotal: $" + total;

        return output;
    }

    public void addToCart(GroceryItem grocery, int quantity) {
        if (cartItems.containsKey(grocery)) {
            int newTotal = cartItems.get(grocery) + quantity;
            cartItems.put(grocery, newTotal);
            System.out.println("\n" + quantity + " " + grocery.getSingularOrPluralName(grocery,quantity) +
                    (quantity == 1 ? " was" : " were") + " added to your cart! \nYou now have " + quantity + " " +
                    grocery.getSingularOrPluralName(grocery, cartItems) + " in your cart!");
            return;
        }
        cartItems.put(grocery, quantity);
        System.out.println("\n" + quantity + " " + grocery.getSingularOrPluralName(grocery, quantity) +
                (quantity == 1 ? " was" : " were") + " added to your cart!");
    }

    public void removeFromCart(GroceryItem grocery, int quantity) {
        if (cartItems.containsKey(grocery) && cartItems.get(grocery) - quantity >= 0) {
            int newTotal = cartItems.get(grocery) - quantity;
            cartItems.put(grocery, newTotal);
            System.out.println("\n" + quantity + " " + grocery.getSingularOrPluralName(grocery, quantity) +
                    (quantity == 1 ? " was" : " were") + " removed from your cart! \nYou now have " + newTotal + " " +
                    grocery.getSingularOrPluralName(grocery, cartItems) + " in your cart!");
        }
    }
}

