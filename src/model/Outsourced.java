/**
 * Inventory Management System
 * C482 Software I (Fall 2020)
 * Western Governors University
 *
 * @file Outsourced.java
 * @author Russell Taylor
 * @date 10/14/2020
 */

package model;

/**
 * Creates Outsourced objects that represent parts sourced outside the company
 */
public class Outsourced extends Part {
    private String companyName;

    /**
     * Class constructor
     * @param partID the part ID
     * @param name the part name
     * @param price the price of the part
     * @param stock the quantity in stock
     * @param min the minimum
     * @param max the maximum
     * @param companyName the name of the company
     */
    public Outsourced(int partID, String name, double price, int stock, int min, int max, String companyName) {
        setPartID(partID);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMin(min);
        setMax(max);
        this.companyName = companyName;
    }

    /**
     * Gets the name of the company
     * @return the name of the company
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the name of the company
     * @param companyName the name of the company
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
