/**
 * Inventory Management System
 * C482 Software I (Fall 2020)
 * Western Governors University
 *
 * @file ModifyProductController.java
 * @author Russell Taylor
 * @date 10/14/2020
 */

package controller;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import model.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controls the Modify Product screen
 */
public class ModifyProductController implements Initializable {

    @FXML
    private TextField productID;

    @FXML
    private TextField name;

    @FXML
    private TextField stock;

    @FXML
    private TextField price;

    @FXML
    private TextField max;

    @FXML
    private TextField min;

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
    private Button addAssociatedPartButton;

    @FXML
    private Button removeAssociatedPartButton;

    @FXML
    private TableView<Part> associatedPartsTable;

    @FXML
    private TableColumn<Part, Integer> associatedPartIDColumn;

    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> associatedPartInventoryColumn;

    @FXML
    private TableColumn<Part, Double> associatedPartPriceColumn;

    @FXML
    private Label errorLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private Inventory inv;
    private Product selectedProduct;
    private ObservableList<Part> partsInventory = FXCollections.observableArrayList();
    private ObservableList<Part> partsInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Part> associatedPartsInventory = FXCollections.observableArrayList();

    /**
     * Class constructor
     * @param inv the inventory of parts and products
     */
    public ModifyProductController(Inventory inv, Product selectedProduct) {
        this.inv = inv;
        this.selectedProduct = selectedProduct;
    }

    /**
     * Initializes the controller class
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known
     * @param rb The resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillData();
        generateAssociatedPartsTable();
        generatePartsTable();
    }

    /**
     * Prefills the form fields with the corresponding data from the selected product
     */
    private void fillData() {
        this.productID.setText(Integer.toString(selectedProduct.getProductID()));
        this.name.setText(selectedProduct.getName());
        this.stock.setText(Integer.toString(selectedProduct.getStock()));
        this.price.setText(Double.toString(selectedProduct.getPrice()));
        this.min.setText(Integer.toString(selectedProduct.getMin()));
        this.max.setText(Integer.toString(selectedProduct.getMax()));
    }

    /**
     * Generates the associated parts table view, populates it with data from the associatedPartsInventory ObservableList, then refreshes the table view
     */
    private void generateAssociatedPartsTable() {
        associatedPartsInventory.setAll(selectedProduct.getAllAssociatedParts());
        associatedPartsTable.setItems(associatedPartsInventory);
        associatedPartsTable.refresh();
    }

    /**
     * Generates the parts table view, populates it with data from the partsInventory ObservableList, removes parts associated with the selected product, then refreshes the table view
     */
    private void generatePartsTable() {
        partsInventory.setAll(inv.getAllParts());
        partsInventory.removeAll(selectedProduct.getAllAssociatedParts());
        partsTable.setItems(partsInventory);
        partsTable.refresh();
    }

    /**
     * Searches for a part by finding which part IDs and part names contain the text entered into the search box. Displays only the matching results in the table view. When the search box is cleared, the full list of parts is again displayed in the table view
     * @param event keyboard input when the user types into the Search Box
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
     * Adds an associated part to the selected product and displays it in the lower table view. Removes the part from the upper table view. Checks that the inventory is not empty and that a part is currently selected
     * @param event Mouse input when the user clicks the Add Associated Part button
     */
    @FXML
    void addAssociatedPart(MouseEvent event) {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (partsInventory.isEmpty()) {
            errorLabel.setText("There are no items to add");
            return;
        }
        else if (selectedPart == null) {
            errorLabel.setText("You must select an item");
            return;
        }

        associatedPartsInventory.add(selectedPart);
        associatedPartsTable.setItems(associatedPartsInventory);
        associatedPartsTable.refresh();

        partsInventory.remove(selectedPart);
        partsTable.setItems(partsInventory);
        partsTable.refresh();

        partSearch.setText("");
    }

    /**
     * Removes an associated part from the selected product and displays it in the upper table view. Removes the part from the lower table view. Checks that the associated parts list is not empty and that a part is currently selected. Asks the user to confirm the removal
     * @param event Mouse input when the user clicks the Add Associated Part button
     */
    @FXML
    void removeAssociatedPart(MouseEvent event) {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        if (associatedPartsInventory.isEmpty()) {
            errorLabel.setText("There are no items to remove");
            return;
        }
        else if (selectedPart == null) {
            errorLabel.setText("You must select an item");
            return;
        }
        boolean confirm = confirmationWindow(selectedPart.getName());
        if (!confirm)
            return;

        partsInventory.add(selectedPart);
        partsTable.setItems(partsInventory);
        partsTable.refresh();

        associatedPartsInventory.remove(selectedPart);
        associatedPartsTable.setItems(associatedPartsInventory);
        associatedPartsTable.refresh();

        partSearch.setText("");
    }

    /**
     * Clears error messages
     */
    @FXML
    private void clearError() {
        errorLabel.setText("");
    }

    /**
     * Displays a confirmation prompt before removing a part
     * @param name the part to be removed
     * @return a boolean value indicating whether the user wants to proceed
     */
    private boolean confirmationWindow(String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Item");
        alert.setContentText("Are you sure you want to remove " + name +"?");
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
     * Checks that the data input by the user does not contain any empty fields, invalid data types, or invalid values
     * @param event mouse input when the user clicks the Save button
     */
    @FXML
    private void checkInput(MouseEvent event) {
        if (name.getText().trim().isEmpty()) {
            errorLabel.setText("Please enter a Name");
            return;
        }
        try {
            if (stock.getText().trim().isEmpty() || Integer.parseInt(stock.getText().trim()) <= 0)
                throw new Exception();
        } catch (Exception e) {
            errorLabel.setText("Please enter a valid Inventory number");
            return;
        }
        try {
            if (price.getText().trim().isEmpty() || Double.parseDouble(price.getText().trim()) <= 0)
                throw new Exception();
        } catch (Exception e) {
            errorLabel.setText("Please enter a valid Price");
            return;
        }
        try {
            if (max.getText().trim().isEmpty() || Integer.parseInt(max.getText().trim()) <= 0)
                throw new Exception();
        } catch (Exception e) {
            errorLabel.setText("Please enter a valid Max number");
            return;
        }
        try {
            if (min.getText().trim().isEmpty() || Integer.parseInt(min.getText().trim()) <= 0)
                throw new Exception();
        } catch (Exception e) {
            errorLabel.setText("Please enter a valid Min number");
            return;
        }
        if (Integer.parseInt(min.getText().trim()) > Integer.parseInt(max.getText().trim())) {
            errorLabel.setText("Min is greater than Max");
            return;
        }
        if (Integer.parseInt(stock.getText().trim()) > Integer.parseInt(max.getText().trim())) {
            errorLabel.setText("Inventory is greater than Max");
            return;
        }
        if (Integer.parseInt(stock.getText().trim()) < Integer.parseInt(min.getText().trim())) {
            errorLabel.setText("Inventory is less than Min");
            return;
        }
        saveProduct(event);
    }

    /**
     * Saves the product and all of its variables to the main inventory
     * @param event mouse input when the user clicks the Save button
     */
    private void saveProduct(MouseEvent event) {
        Product product = new Product(Integer.parseInt(productID.getText().trim()), name.getText().trim(), Double.parseDouble(price.getText().trim()), Integer.parseInt(stock.getText().trim()), Integer.parseInt(min.getText().trim()), Integer.parseInt(max.getText().trim()));
        for (Part p : associatedPartsInventory)
            product.addAssociatedPart(p);
        inv.updateProduct(product);

        returnToMain(event);
    }

    /**
     * Launches the Main screen
     * @param event mouse input when the user clicks the Cancel button
     */
    @FXML
    private void returnToMain(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
            MainController controller = new MainController(inv);
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
}
