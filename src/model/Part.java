/**
 * Inventory Management System
 * C482 Software I (Fall 2020)
 * Western Governors University
 *
 * @file Part.java
 * @author Russell Taylor
 * @date 10/14/2020
 *
 * This code was provided as part of the assignment.
 */

package model;

/**
 * An abstract class guiding the creation of objects that represent parts
 */
public abstract class Part {

    protected int partID;
    protected String name;
    protected double price = 0.0;
    protected int stock;
    protected int min;
    protected int max;

    /**
     * Gets the part ID
     * @return the part ID
     */
    public int getPartID() {
        return partID;
    }

    /**
     * Sets the part ID
     * @param partID the part ID
     */
    public void setPartID(int partID) {
        this.partID = partID;
    }

    /**
     * Gets the part name
     * @return the part name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the part name
     * @param name the part name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the part
     * @return the price of the part
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the part
     * @param price the price of the part
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the quantity in stock
     * @return the quantity in stock
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
}
