package com.techelevator.grocerystore.application;

import com.techelevator.grocerystore.application.GroceryItem;
import com.techelevator.grocerystore.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GroceryStore {

    private Map<GroceryItem, Integer> groceryInventory = new HashMap<>();

    public GroceryStore() {
        groceryInventory = stockShelves();
    }

    public Map<GroceryItem, Integer> getGroceryInventory() {
        return this.groceryInventory;
    }

    private Map<GroceryItem, Integer> stockShelves() {

        File inventory = new File("grocery_inventory.txt");

        try (Scanner scanner = new Scanner(inventory)) {

            while (scanner.hasNextLine()) {
                String[] inventoryData = scanner.nextLine().split("\\|");
                GroceryItem item = new GroceryItem(inventoryData[0], inventoryData[1], inventoryData[2],
                        inventoryData[3]);
                groceryInventory.put(item, (int) (Math.random() * 10));
            }
        } catch (FileNotFoundException e) {
            UserOutput.displayMessage("Unable to load grocery inventory");
        }

        return groceryInventory;
    }
}
