/**
 * Inventory Management System
 * C482 Software I (Fall 2020)
 * Western Governors University
 * Course Instructor:
 *
 * @file Inventory.java
 * @author Russell Taylor
 * @date 10/14/2020
 */

package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Creates Inventory objects that represent an inventory of products and parts produced by the company
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Adds a new part to the inventory
     * @param newPart the part to be added to the inventory
     */
    public static void addPart(Part newPart) {
        if (newPart != null) {
            allParts.add(newPart);
        }
    }

    /**
     * Adds a new product to the inventory
     * @param newProduct the product to be added to the inventory
     */
    public static void addProduct(Product newProduct) {
        if (newProduct != null) {
            allProducts.add(newProduct);
        }
    }

    /**
     * Looks up a part in the inventory by part ID
     * @param partID the ID of the part to look up
     * @return the Part object if found, otherwise returns null
     */
    public static Part lookupPart(int partID) {
        if (!allParts.isEmpty()) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getPartID() == partID) {
                    return allParts.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Looks up a part in the inventory by part name
     * @param partName the name of the part to look up
     * @return the Part object if found, otherwise returns null
     */
    public static Part lookupPart(String partName) {
        if (!allParts.isEmpty()) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getName() == partName) {
                    return allParts.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Looks up a product in the inventory by product ID
     * @param productID the ID of the product to look up
     * @return the Product object if found, otherwise returns null
     */
    public static Product lookupProduct(int productID) {
        if (!allProducts.isEmpty()) {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getProductID() == productID) {
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Looks up a product in the inventory by product name
     * @param productName the name of the product to look up
     * @return the Product object if found, otherwise returns null
     */
    public static Product lookupProduct(String productName) {
        if (!allProducts.isEmpty()) {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getName() == productName) {
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Updates the selected part
     * @param selectedPart the part to update
     */
    public static void updatePart(Part selectedPart) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getPartID() == selectedPart.getPartID()) {
                allParts.set(i, selectedPart);
                break;
            }
        }
    }

    /**
     * Updates the selected product
     * @param selectedProduct the product to update
     */
    public static void updateProduct(Product selectedProduct) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getProductID() == selectedProduct.getProductID()) {
                allProducts.set(i, selectedProduct);
                break;
            }
        }
    }

    /**
     * Deletes the selected part
     * @param selectedPart the part to delete
     * @return a boolean value indicating whether the part was deleted
     */
    public static boolean deletePart(Part selectedPart) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getPartID() == selectedPart.getPartID()) {
                allParts.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes the selected product
     * @param selectedProduct the product to delete
     * @return a boolean value indicating whether the product was deleted
     */
    public static boolean deleteProduct(Product selectedProduct) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getProductID() == selectedProduct.getProductID()) {
                allProducts.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a list of all parts in the inventory
     * @return a list of all parts in the inventory
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Gets a list of all products in the inventory
     * @return a list of all products in the inventory
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}