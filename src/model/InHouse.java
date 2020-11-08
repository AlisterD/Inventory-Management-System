/**
 * Inventory Management System
 * C482 Software I (Fall 2020)
 * Western Governors University
 *
 * @file InHouse.java
 * @author Russell Taylor
 * @date 10/14/2020
 */

package model;

/**
 * Creates InHouse objects that represent parts produced internally within the company
 */
public class InHouse extends Part {
    private int machineID;

    /**
     * Class constructor
     * @param partID the part ID
     * @param name the part name
     * @param price the price of the part
     * @param stock the quantity in stock
     * @param min the minimum
     * @param max the maximum
     * @param machineID the machine ID
     */
    public InHouse(int partID, String name, double price, int stock, int min, int max, int machineID) {
        setPartID(partID);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMin(min);
        setMax(max);
        this.machineID = machineID;
    }

    /**
     * Gets the machine ID
     * @return the machine ID
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     * Sets the machine ID
     * @param machineID the machine ID
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
