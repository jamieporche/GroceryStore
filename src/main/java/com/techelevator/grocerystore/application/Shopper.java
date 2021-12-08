package com.techelevator.grocerystore.application;

import com.techelevator.grocerystore.ui.UserInput;
import com.techelevator.grocerystore.ui.UserOutput;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Shopper {

    public void browseGroceryStore(Map<GroceryItem, Integer> groceryInventory) {

        int chanceEncounter = (int) (Math.random() * 10);

        if (chanceEncounter == 1 || chanceEncounter == 2) {

            String choice = UserInput.handleEncounteringHighSchoolAcquaintance();

            if (choice.equals("1") || choice.toLowerCase().contains("chat")) {

                chatWithHighSchoolAcquaintance();
                UserOutput.backToBrowsing();
                UserOutput.displayInventory(groceryInventory);

            } else if (choice.equals("2") || choice.toLowerCase().contains("pretend")) {

                UserOutput.sneakPastHighSchoolAcquaintance();
                UserOutput.displayInventory(groceryInventory);

            } else if (choice.equals("3") || choice.toLowerCase().contains("abandon") ||
                    choice.toLowerCase().contains("leave")) {

                UserOutput.runAwayFromHighSchoolAcquaintance();
                System.exit(0);
            }
        }

        if (!(chanceEncounter == 1 || chanceEncounter == 2)) {

            UserOutput.walkAisles();
            UserInput.pause();
            UserOutput.browseAisles();
            UserOutput.displayInventory(groceryInventory);
        }
    }

    public void chatWithHighSchoolAcquaintance() {

        UserOutput.chatWithHighSchoolAcquaintance();

        boolean chatting = true;

        while (chatting) {
            try {
                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final Runnable runnable = new Runnable() {

                    int countdownStarter = 15;

                    @Override
                    public void run() {
                        System.out.println();
                        countdownStarter--;

                        if (countdownStarter < 0) {
                            scheduler.shutdown();
                        }
                    }
                };
                scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
                chatting = scheduler.awaitTermination(15, SECONDS);
            } catch (InterruptedException e) {
                UserOutput.displayMessage(e.getMessage());
            }
        }
    }

    public void addItemsToCart(Map<GroceryItem, Integer> groceryInventory, ShoppingCart cart) {

        boolean itemAddedToCart = false;
        boolean addAnotherItem = false;
        boolean itemFound;
        boolean addAllToCart;

        do {
            itemFound = false;
            String itemToAdd = UserInput.itemToAddToCart();

            // check to see if the item the user wants to add is in the grocery store's inventory by iterating through
            // the map containing the inventory and checking each item's name against the user's input. If the
            // name of the Grocery Item contains the user's input, itemFound will be set to true, and will continue
            // with the code using that Grocery Item
            for (Map.Entry<GroceryItem, Integer> item : groceryInventory.entrySet()) {
                if (item.getKey().getSingularName().toLowerCase().contains(itemToAdd.toLowerCase())
                        && item.getValue() > 0) {
                    itemFound = true;

                    do {
                        itemAddedToCart = addItemToCart(groceryInventory, item, cart);
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

    public boolean addItemToCart(Map<GroceryItem, Integer> groceryInventory,
                                 Map.Entry<GroceryItem, Integer> item, ShoppingCart cart) {
        boolean addAllToCart = false;
        String quantityToAddInput = UserInput.getQuantityToAdd(item);

        try {
            Integer quantityToAdd = Integer.parseInt(quantityToAddInput);

            // if the store has the quantity in stock that the user wants to add to their cart,
            // and the user wants to add a positive quantity, add the quantity of items to the
            // cart, and remove an equal quantity from the store's inventory
            if (item.getValue() >= quantityToAdd && quantityToAdd > 0) {
                cart.addToCart(item.getKey(), quantityToAdd);
                takeItemFromShelf(groceryInventory, item.getKey(), quantityToAdd);
                return true;

                // if the store doesn't have the quantity the user wants in stock, and the user asked
                // for a positive quantity, ask them if they would like to add all the items of that
                // type that the store has in stock to their cart
            } else if (quantityToAdd > 0) {
                addAllToCart = UserInput.addAllToCart(item, groceryInventory);

                if (addAllToCart) {
                    cart.addToCart(item.getKey(), item.getValue());
                    takeItemFromShelf(groceryInventory, item.getKey(), item.getValue());
                    return true;
                }

                // if the user did not ask for a positive quantity, tell them to enter a positive
                // number
            } else {
                UserOutput.enterPositiveNumberToAdd(item);
                return false;
            }
        } catch (NumberFormatException e) {

            if (quantityToAddInput.toLowerCase().contains("cancel")) {
                return true;

            } else {
                UserOutput.itemNotAddedToCart();
                return false;
            }
        }
        return false;
    }

    public void removeItemsFromCart(Map<GroceryItem, Integer> groceryInventory, ShoppingCart cart) {

        boolean itemFound;
        boolean itemRemovedFromCart = false;
        boolean removeAnotherItem = false;

        do {
            itemFound = false;
            String itemToRemove = UserInput.itemToRemoveFromCart();

            // check to see if the item the user wants to remove is in their cart by iterating through
            // the map containing the cart items and checking each item's name against the user's input. If the
            // name of the Grocery Item contains the user's input, itemFound will be set to true, and will continue
            // with the code using that Grocery Item
            for (Map.Entry<GroceryItem, Integer> item : cart.getCartItems().entrySet()) {
                if (item.getKey().getSingularOrPluralName(item.getKey(), cart.getCartItems()).toLowerCase()
                        .contains(itemToRemove.toLowerCase())) {
                    itemFound = true;
                    if (item.getKey().canBeReturnedToShelf()) {
                        do {
                            itemRemovedFromCart = removeItemFromCart(groceryInventory, item, cart);
                        } while (!itemRemovedFromCart);
                    } else {
                        if (UserInput.removeDeliItemFromCart()) {
                            removeAllOfItemFromCart(groceryInventory, item, cart);
                        }
                    }

                    removeAnotherItem = UserInput.removeAnotherItemFromCart();
                    break;
                }
            }
            if (!itemFound) {

                removeAnotherItem = UserInput.removeADifferentItemFromCart(itemToRemove);
            }
        } while (removeAnotherItem);
    }

    private boolean removeItemFromCart(Map<GroceryItem, Integer> groceryInventory,
                                    Map.Entry<GroceryItem, Integer> item, ShoppingCart cart) {
        String quantityToRemoveInput = UserInput.getQuantityToRemove(item);

        try {
            Integer quantityToRemove = Integer.parseInt(quantityToRemoveInput);

            // if the cart has more than the quantity in it that the user wants to remove,
            // and the user wants to remove a positive quantity, remove the quantity of items from the
            // cart, and add an equal quantity to the store's inventory
            if (item.getValue() > quantityToRemove && quantityToRemove > 0) {
                cart.removeFromCart(item.getKey(), quantityToRemove);
                putItemBackOnShelf(groceryInventory, item.getKey(), quantityToRemove);
                return true;

                // if the cart has an equal amount or less than the user asked to remove, and the user asked
                // to remove a positive quantity, ask them if they would like to remove all the items of that
                // type from their cart
            } else if (quantityToRemove > 0) {

                boolean removeAllFromCart = UserInput.removeAllFromCart(item, cart);

                if (removeAllFromCart) {
                    removeAllOfItemFromCart(groceryInventory, item, cart);
                    return true;
                }

                // if the user did not ask to remove a positive quantity, tell them to enter a positive
                // number
            } else {
                UserOutput.enterPositiveNumberToRemove(item);
                return false;
            }

        } catch (NumberFormatException e) {

            if (quantityToRemoveInput.toLowerCase().contains("cancel")) {
                return true;

            } else {
                UserOutput.itemNotRemovedFromCart();
                return false;
            }
        }
        return false;
    }

    private void removeAllOfItemFromCart(Map<GroceryItem, Integer> groceryInventory,
                                         Map.Entry<GroceryItem, Integer> item, ShoppingCart cart) {
        cart.getCartItems().remove(item.getKey());
        putItemBackOnShelf(groceryInventory, item.getKey(), item.getValue());
    }

    private void takeItemFromShelf(Map<GroceryItem, Integer> groceryInventory, GroceryItem itemToRemove,
                                         Integer quantityToRemove) {

        int newTotal = groceryInventory.get(itemToRemove) - quantityToRemove;
        groceryInventory.put(itemToRemove, newTotal);
    }

    private void putItemBackOnShelf(Map<GroceryItem, Integer> groceryInventory, GroceryItem itemToAdd,
                                        Integer quantityToAdd) {
        if (itemToAdd.canBeReturnedToShelf()) {
            int newTotal = groceryInventory.get(itemToAdd) + quantityToAdd;
            groceryInventory.put(itemToAdd, newTotal);
        } else {
            UserOutput.displayMessage("\nYou set the " + itemToAdd.getSingularName() + " down next to the cereal while " +
                    "someone in the aisle glares at you judgmentally.");
        }
    }
    public void visitDeliCounter(List<DeliItem> deliItems, ShoppingCart cart) {

        boolean exit = false;

        while (!exit) {
            String choice = UserInput.deliMenu();

            switch (choice) {
                case "1":
                    UserOutput.displayDeliInventory(deliItems);
                    UserInput.pause();
                    break;
                case "2":
                    DeliEmployee.addDeliItemsToCart(deliItems, cart);
                    UserInput.pause();
                    break;
                case "3":
                    exit = true;
                    break;
            }
        }
    }

}
