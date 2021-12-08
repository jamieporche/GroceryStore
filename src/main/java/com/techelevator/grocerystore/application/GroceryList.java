package com.techelevator.grocerystore.application;

import com.techelevator.grocerystore.ui.UserInput;
import com.techelevator.grocerystore.ui.UserOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class GroceryList {

    public static String viewGroceryList() {

        File groceryList = new File("GroceryList.txt");
        StringBuilder groceryListOutput = new StringBuilder();

        try (Scanner input = new Scanner(groceryList)) {

            while (input.hasNextLine()) {
                groceryListOutput.append(input.nextLine()).append("\n");
            }

        } catch (FileNotFoundException e) {
            UserOutput.displayMessage("Could not find the grocery list");
        }

        return groceryListOutput.toString();
    }

    public static void addItemToGroceryList() {

        boolean addAnotherItem;

        do {
            String itemToAdd = UserInput.itemToAddToGroceryList();

            try (PrintWriter pw = new PrintWriter(new FileOutputStream("GroceryList.txt", true))) {
                pw.println(itemToAdd);
                UserOutput.itemAddedToGroceryList(itemToAdd);
            } catch (FileNotFoundException e) {
                UserOutput.displayMessage("Grocery list not found!");
            }

            addAnotherItem = UserInput.addAnotherItemToGroceryList();

        } while (addAnotherItem);
    }

    public static void removeItemFromGroceryList() {

        File groceryList = new File("GroceryList.txt");
        File groceryListCopy = new File("GroceryListCopy.txt");
        boolean removeAnotherItem;

        do {
            String itemToRemove = UserInput.itemToRemoveFromGroceryList();

            try (Scanner input = new Scanner(groceryList);
                 PrintWriter pw = new PrintWriter(groceryListCopy)) {
                int matchCount = 0;

                while (input.hasNextLine()) {

                    String inputLine = input.nextLine();

                    if (inputLine.toLowerCase().contains(itemToRemove.toLowerCase())) {
                        pw.print("");
                        matchCount++;
                    } else {
                        pw.println(inputLine);
                    }
                }
                UserOutput.itemRemovedFromGroceryList(itemToRemove, matchCount);

            } catch (FileNotFoundException e) {
                UserOutput.displayMessage("Grocery list not found!");
            }

            Path to = groceryList.toPath();
            Path from = groceryListCopy.toPath();

            try {
                Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                UserOutput.displayMessage("Unable to update the grocery list.");
            }

            removeAnotherItem = UserInput.removeAnotherItemFromGroceryList();

        } while (removeAnotherItem);
    }
}
