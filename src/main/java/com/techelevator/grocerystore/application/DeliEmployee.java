package com.techelevator.grocerystore.application;

import com.techelevator.grocerystore.ui.UserInput;
import com.techelevator.grocerystore.ui.UserOutput;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DeliEmployee {

    public static void addDeliItemsToCart(List<DeliItem> deliItems, ShoppingCart cart) {

        boolean itemAddedToCart;
        boolean addAnotherItem = false;
        boolean itemFound;

        do {
            itemFound = false;
            String itemToAdd = UserInput.deliItemToAddToCart();

            // check to see if the item the user wants to add is in the deli's inventory by iterating through
            // the list containing the inventory and checking each item's name against the user's input. If the
            // name of the Deli Item contains the user's input, itemFound will be set to true, and will continue
            // with the code using that Deli Item
            for (DeliItem deliItem : deliItems) {
                if (deliItem.getName().toLowerCase().contains(itemToAdd.toLowerCase()) && deliItem.getWeight() > 0) {
                    itemFound = true;

                    do {
                       itemAddedToCart = addDeliItemToCart(deliItem, cart);
                    } while (!itemAddedToCart);

                    addAnotherItem = UserInput.addAnotherItemToCart();
                    break;
                }
            }
            if (!itemFound) {
                addAnotherItem = UserInput.addADifferentItemToCart(itemToAdd);
            }
        } while (addAnotherItem);
    }

    public static boolean addDeliItemToCart(DeliItem deliItem, ShoppingCart cart) {
        boolean addAllToCart;
        String weightDesiredInput = UserInput.getWeightToAddToCart(deliItem);

        try {
            double weightDesired = Double.parseDouble(weightDesiredInput);

            // if the deli has the amount in stock that the user wants to add to their cart,
            // and the user wants to add a positive amount, create a new deli item of that weight
            // add it to the user's cart, and remove an equal weight from the Deli Item from the deli
            if (deliItem.getWeight() >= weightDesired && weightDesired > 0) {
                scoopDeliItemIntoContainer(deliItem, weightDesired, cart);
                return true;

                // if the deli doesn't have the amount the users wants, and the user asked for a positive
                // amount, ask them if they would like to add all of the deli item available to their cart
            } else if (weightDesired > 0) {
                addAllToCart = UserInput.addAllDeliItemToCart(deliItem);

                if (addAllToCart) {
                    scoopDeliItemIntoContainer(deliItem, deliItem.getWeight(), cart);
                    return true;
                }

                // if the user did not ask for a positive amount, tell them to enter a positive number
            } else {
                UserOutput.enterPositiveWeightToAdd(deliItem);
                return false;
            }
        } catch (NumberFormatException e) {

            if (weightDesiredInput.toLowerCase().contains("cancel")) {
                return true;

            } else {
                UserOutput.deliItemNotAddedToCart();
                return false;
            }
        }
        return false;
    }

    private static void scoopDeliItemIntoContainer(DeliItem deliItem, double amountToScoop, ShoppingCart cart) {
        cart.addToCart(new DeliItem(deliItem.getName(), amountToScoop,
                new BigDecimal(String.valueOf(deliItem.getPrice()
                        .multiply(new BigDecimal(String.valueOf(amountToScoop)))))
                        .setScale(2, RoundingMode.HALF_UP)), 1);
        deliItem.scoopFromContainer(amountToScoop);
    }
}
