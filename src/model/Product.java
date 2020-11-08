/**
 * Inventory Management System
 * C482 Software I (Fall 2020)
 * Western Governors University
 *
 * @file Product.java
 * @author Russell Taylor
 * @date 10/14/2020
 */

package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Creates Product objects that represent products produced by the company
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int productID;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Class constructor
     * @param productID the product ID
     * @param name the product name
     * @param price the price of the product
     * @param stock the quantity in stock
     * @param min the minimum
     * @param max the maximum
     */
    public Product(int productID, String name, double price, int stock, int min, int max) {
        setProductID(productID);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMin(min);
        setMax(max);
    }

    /**
     * Gets the product ID
     * @return the product ID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Sets the product ID
     * @param ProductID the product ID
     */
    public void setProductID(int ProductID) {
        this.productID = ProductID;
    }

    /**
     * Gets the product name
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name
     * @param name the product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the product
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product
     * @param price the price of the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the quantity in stock
     * @return the quanity in stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the quantity in stock
     * @param stock the quantity in stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the minimum
     * @return the minimum
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets the minimum
     * @param min the minimum
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Gets the maximum
     * @return the maximum
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets the maximum
     * @param max the maximum
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds an associated part
     * @param selectedPart the part to add
     */
    public void addAssociatedPart(Part selectedPart) {
        associatedParts.add(selectedPart);
    }

    /**
     * Deletes an associated part
     * @param selectedPart the part to delete
     * @return a boolean value indicating whether the part was deleted
     */
    public boolean deleteAssociatedPart(Part selectedPart) {
        for (int i = 0; i < associatedParts.size(); i++) {
            if (associatedParts.get(i).getPartID() == selectedPart.getPartID()) {
                associatedParts.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a list of all associated parts
     * @return a list of all associated parts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
