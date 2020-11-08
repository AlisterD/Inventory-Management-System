/**
 * Inventory Management System
 * C482 Software I (Fall 2020)
 * Western Governors University
 *
 * @file MainController.java
 * @author Russell Taylor
 * @date 10/14/2020
 */

package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import model.*;

/**
 * Controls the Main screen
 */
public class MainController implements Initializable {

    @FXML
    private TextField partSearch;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partIDColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private Button addPartButton;

    @FXML
    private Button modifyPartButton;

    @FXML
    private Button deletePartButton;

    @FXML
    private TextField productSearch;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, Integer> productIDColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    @FXML
    private Button addProductButton;

    @FXML
    private Button modifyProductButton;

    @FXML
    private Button deleteProductButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Button exitButton;

    private Inventory inv;
    private ObservableList<Part> partsInventory = FXCollections.observableArrayList();
    private ObservableList<Part> partsInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Product> productsInventory = FXCollections.observableArrayList();
    private ObservableList<Product> productsInventorySearch = FXCollections.observableArrayList();

    /**
     * Class constructor
     * @param inv the inventory of parts and products
     */
    public MainController(Inventory inv) {
        this.inv = inv;
    }

    /**
     * Initializes the controller class
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known
     * @param rb The resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generatePartsTable();
        generateProductsTable();
    }

    /**
     * Generates the parts table view, populates it with data from the partsInventory ObservableList, then refreshes the table view
     */
    private void generatePartsTable() {
        partsInventory.setAll(inv.getAllParts());
        partsTable.setItems(partsInventory);
        partsTable.refresh();
    }

    /**
     * Generates the parts table view, populates it with data from the productsInventory ObservableList, then refreshes the table view
     */
    private void generateProductsTable() {
        productsInventory.setAll(inv.getAllProducts());
        productsTable.setItems(productsInventory);
        productsTable.refresh();
    }

    /**
     * Searches for a part by finding which part IDs and part names contain the text entered into the search box. Displays only the matching results in the table view. When the search box is cleared, the full list of parts is again displayed in the table view
     * @param event keyboard input when the user types into the Search Box in the parts pane
     */
    @FXML
    private void searchForPart(KeyEvent event) {
        if (!partSearch.getText().trim().isEmpty()) {
            partsInventorySearch.clear();
            errorLabel.setText("");
            for (Part p : partsInventory) {
                if (p.getName().contains(partSearch.getText().trim()))
                    partsInventorySearch.add(p);
                else if (Integer.toString(p.getPartID()).contains(partSearch.getText().trim()))
                    partsInventorySearch.add(p);
            }
            if (partsInventorySearch.isEmpty())
                errorLabel.setText("No parts found");
            partsTable.setItems(partsInventorySearch);
            partsTable.refresh();
        }
        else {
            errorLabel.setText("");
            partsTable.setItems(partsInventory);
            partsTable.refresh();
        }
    }

    /**
     * Searches for a product by finding which product IDs and part names contain the text entered into the search box. Displays only the matching results in the table view. When the search box is cleared, the full list of products is again displayed in the table view
     * @param event keyboard input when the user types into the Search Box in the products pane
     */
    @FXML
    private void searchForProduct(KeyEvent event) {
        if (!productSearch.getText().trim().isEmpty()) {
            productsInventorySearch.clear();
            errorLabel.setText("");
            for (Product p : productsInventory) {
                if (p.getName().contains(productSearch.getText().trim()))
                    productsInventorySearch.add(p);
                else if (Integer.toString(p.getProductID()).contains(productSearch.getText().trim()))
                    productsInventorySearch.add(p);
            }
            if (productsInventorySearch.isEmpty())
                errorLabel.setText("No products found");
            productsTable.setItems(productsInventorySearch);
            productsTable.refresh();
        }
        else {
            errorLabel.setText("");
            productsTable.setItems(productsInventory);
            productsTable.refresh();
        }
    }

    /**
     * Launches the Add Part screen
     * @param event mouse input when the user clicks the Add button in the parts pane
     */
    @FXML
    private void addPart(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addPart.fxml"));
            AddPartController controller = new AddPartController(inv);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Launches the Add Product screen
     * @param event mouse input when the user clicks the Add button in the products pane
     */
    @FXML
    private void addProduct(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addProduct.fxml"));
            AddProductController controller = new AddProductController(inv);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Launches the Modify Part screen. Checks that the inventory is not empty and that a part is currently selected
     * @param event mouse input when the user clicks the Modify button in the parts pane
     */
    @FXML
    private void modifyPart(MouseEvent event) {
        try {
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
            if (partsInventory.isEmpty()) {
                errorLabel.setText("There are no items in the inventory");
                return;
            }
            else if (selectedPart == null) {
                errorLabel.setText("You must select an item");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyPart.fxml"));
            ModifyPartController controller = new ModifyPartController(inv, selectedPart);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Launches the Modify Product screen. Checks that the inventory is not empty and that a product is currently selected
     * @param event mouse input when the user clicks the Modify button in the products pane
     */
    @FXML
    private void modifyProduct(MouseEvent event) {
        try {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
            if (productsInventory.isEmpty()) {
                errorLabel.setText("There are no items in the inventory");
                return;
            }
            else if (selectedProduct == null) {
                errorLabel.setText("You must select an item");
                return;
            }
            else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyProduct.fxml"));
                ModifyProductController controller = new ModifyProductController(inv, selectedProduct);
                loader.setController(controller);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Deletes a part from the main inventory. Checks that the inventory is not empty and that a part is currently selected. Asks the user to confirm the deletion
     * @param event mouse input when the user clicks the Delete button in the parts pane
     */
    @FXML
    private void deletePart(MouseEvent event) {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (partsInventory.isEmpty()) {
            errorLabel.setText("There are no items in the inventory");
            return;
        }
        else if (selectedPart == null) {
            errorLabel.setText("You must select an item");
            return;
        }
        boolean confirm = confirmationWindow(selectedPart.getName());
        if (!confirm)
            return;

        inv.deletePart(selectedPart);
        partsInventory.remove(selectedPart);
        partsTable.setItems(partsInventory);
        partsTable.refresh();
    }

    /**
     * Deletes a product from the main inventory. Checks that that inventory is not empty, that a product is currently selected, and that the product has no associated parts. Asks the user to confirm the deletion
     * @param event mouse input when the user clicks the Delete button in the products pane
     */
    @FXML
    private void deleteProduct(MouseEvent event) {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (productsInventory.isEmpty()) {
            errorLabel.setText("There are no items in the inventory");
            return;
        }
        else if (selectedProduct == null) {
            errorLabel.setText("You must select an item");
            return;
        }
        else if (!selectedProduct.getAllAssociatedParts().isEmpty()) {
            errorLabel.setText("This product has associated parts and cannot be deleted");
            return;
        }
        boolean confirm = confirmationWindow(selectedProduct.getName());
        if (!confirm)
            return;

        inv.deleteProduct(selectedProduct);
        productsInventory.remove(selectedProduct);
        productsTable.setItems(productsInventory);
        productsTable.refresh();
    }

    /**
     * Clears error messages
     */
    @FXML
    private void clearError() {
        errorLabel.setText("");
    }

    /**
     * Displays a confirmation prompt before deleting an item from inventory
     * @param name the part or product to be deleted
     * @return a boolean value indicating whether the user wants to proceed
     */
    private boolean confirmationWindow(String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Item");
        alert.setContentText("Are you sure you want to delete " + name +"?");
        alert.setHeaderText(null);
        alert.setGraphic(null);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Exits the program
     * @param event mouse input when the user clicks the Exit button
     */
    @FXML
    void exitProgram(MouseEvent event) {
        Platform.exit();
    }
}