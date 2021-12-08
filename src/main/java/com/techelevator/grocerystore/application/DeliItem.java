package com.techelevator.grocerystore.application;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DeliItem extends GroceryItem {

    private double weight;

    public DeliItem(String name, double weight, String price) {
        super(name, name, "Deli", price);
        this.weight = weight;
    }

    public DeliItem(String name, double weight, BigDecimal price) {
        super(name, name, "Deli", price);
        this.weight = weight;
    }

    public String getName() {
        return getSingularName();
    }

    public double getWeight() {
        return weight;
    }

    public void scoopFromContainer(double amountToScoop) {
        this.weight -= amountToScoop;
    }

    public boolean canBeReturnedToShelf() {
        return false;
    }

    public String toString() {
        return this.getName() + " $" + this.getPrice() + "\n\t" + this.weight + " lbs @ " + this.getPrice()
                .divide(new BigDecimal(String.valueOf(this.weight)), 2, RoundingMode.HALF_UP) + " /lb";
    }
}
