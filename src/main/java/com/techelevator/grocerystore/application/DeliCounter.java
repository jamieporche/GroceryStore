package com.techelevator.grocerystore.application;

import com.techelevator.grocerystore.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliCounter {

    private List<DeliItem> deliInventory = new ArrayList<>();

    public DeliCounter() {
        this.deliInventory = stockDeli();
    }

    public List<DeliItem> getDeliInventory() {
        return deliInventory;
    }

    private List<DeliItem> stockDeli() {

        File inventory = new File("deli_inventory.txt");

        try (Scanner scanner = new Scanner(inventory)) {

            while (scanner.hasNextLine()) {
                String[] inventoryData = scanner.nextLine().split("\\|");
                DeliItem item = new DeliItem(inventoryData[0], Double.parseDouble(inventoryData[1]),
                        inventoryData[2]);
                deliInventory.add(item);
            }
        } catch (FileNotFoundException e) {
            UserOutput.displayMessage("Unable to load deli inventory");
        }

        return deliInventory;
    }




}
