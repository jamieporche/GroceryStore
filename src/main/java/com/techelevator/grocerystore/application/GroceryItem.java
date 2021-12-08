package com.techelevator.grocerystore.application;

import java.math.BigDecimal;
import java.util.Map;

public class GroceryItem {

    private String department;
    private String singularName;
    private String pluralName;
    private BigDecimal price;

    public GroceryItem(String singularName, String pluralName, String department, String price) {
        this.singularName = singularName;
        this.pluralName = pluralName;
        this.department = department;
        this.price = new BigDecimal(price);
    }

    public GroceryItem(String singularName, String pluralName, String department, BigDecimal price) {
        this.singularName = singularName;
        this.pluralName = pluralName;
        this.department = department;
        this.price = price;
    }

    public String getDepartment() {
        return department;
    }

    public String getSingularName() {
        return singularName;
    }

    public String getPluralName() {
        return pluralName;
    }

    public String getSingularOrPluralName(GroceryItem item, Map<GroceryItem, Integer> groceryMap) {

        if (groceryMap.get(item) == 1) {
            return singularName;
        }
        return pluralName;
    }

    public String getSingularOrPluralName(GroceryItem item, int quantity) {

        if (quantity > 1) {
            return pluralName;
        }
        return singularName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean canBeReturnedToShelf() {
        return true;
    }

    public String toString() {
        return this.singularName + " $" + this.price;
    }
}
