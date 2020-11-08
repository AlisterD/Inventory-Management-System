/**
 * Inventory Management System
 * C482 Software I (Fall 2020)
 * Western Governors University
 *
 * @file AddPartController.java
 * @author Russell Taylor
 * @date 10/14/2020
 */

package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ResourceBundle;

import model.*;

/**
 * Controls the Modify Part screen
 */
public class ModifyPartController implements Initializable {

    @FXML
    private TextField partID;

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
    private TextField machineID;

    @FXML
    private Label machineLabel;

    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private RadioButton outsourcedRadio;

    @FXML
    private Label errorLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private Inventory inv;
    private Part selectedPart;

    /**
     * Class constructor
     * @param inv the inventory of parts and products
     */
    public ModifyPartController(Inventory inv, Part selectedPart) {
        this.inv = inv;
        this.selectedPart = selectedPart;
    }

    /**
     * Initializes the controller class
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known
     * @param rb The resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillData();
    }

    /**
     * Prefills the form fields with the corresponding data from the selected part. Distinguishes between InHouse and Outsourced parts in order to set the initial view and correctly display all of the data fields
     */
    private void fillData() {
        if (selectedPart instanceof InHouse) {
            InHouse inHousePart = (InHouse) selectedPart;
            inHouseRadio.setSelected(true);
            machineLabel.setText("Machine ID");
            this.partID.setText(Integer.toString(inHousePart.getPartID()));
            this.name.setText(inHousePart.getName());
            this.stock.setText(Integer.toString(inHousePart.getStock()));
            this.price.setText(Double.toString(inHousePart.getPrice()));
            this.min.setText(Integer.toString(inHousePart.getMin()));
            this.max.setText(Integer.toString(inHousePart.getMax()));
            this.machineID.setText(Integer.toString(inHousePart.getMachineID()));
        }
        if (selectedPart instanceof Outsourced) {
            Outsourced outsourcedPart = (Outsourced) selectedPart;
            outsourcedRadio.setSelected(true);
            machineLabel.setText("Company Name");
            this.partID.setText(Integer.toString(outsourcedPart.getPartID()));
            this.name.setText(outsourcedPart.getName());
            this.stock.setText(Integer.toString(outsourcedPart.getStock()));
            this.price.setText(Double.toString(outsourcedPart.getPrice()));
            this.min.setText(Integer.toString(outsourcedPart.getMin()));
            this.max.setText(Integer.toString(outsourcedPart.getMax()));
            this.machineID.setText(outsourcedPart.getCompanyName());
        }
    }

    /**
     * Toggles the label from Company Name to Machine ID
     * @param event mouse input when the user selects the In House radio button
     */
    @FXML
    private void selectInHouse(MouseEvent event) {
        machineLabel.setText("Machine ID");
    }

    /**
     * Toggles the label from Machine ID to Company Name
     * @param event mouse input when the user selects the Outsourced radio button
     */
    @FXML
    private void selectOutsourced(MouseEvent event) {
        machineLabel.setText("Company Name");
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
        try {
            if (inHouseRadio.isSelected() && (machineID.getText().trim().isEmpty() || Integer.parseInt(machineID.getText().trim()) <= 0))
                throw new Exception();
        }
        catch(Exception e) {
            errorLabel.setText("Please enter a valid Machine ID number");
            return;
        }
        if (outsourcedRadio.isSelected() && machineID.getText().trim().isEmpty()) {
            errorLabel.setText("Please enter a Company Name");
            return;
        }
        savePart(event);
    }

    /**
     * Saves the product and all of its variables to the main inventory
     * @param event mouse input when the user clicks the Save button
     */
    private void savePart(MouseEvent event) {
        if (inHouseRadio.isSelected()) {
            InHouse part = new InHouse(Integer.parseInt(partID.getText().trim()), name.getText().trim(), Double.parseDouble(price.getText().trim()), Integer.parseInt(stock.getText().trim()), Integer.parseInt(min.getText().trim()), Integer.parseInt(max.getText().trim()), Integer.parseInt(machineID.getText().trim()));
            inv.updatePart(part);
        }
        if (outsourcedRadio.isSelected()) {
            Outsourced part = new Outsourced(Integer.parseInt(partID.getText().trim()), name.getText().trim(), Double.parseDouble(price.getText().trim()), Integer.parseInt(stock.getText().trim()), Integer.parseInt(min.getText().trim()), Integer.parseInt(max.getText().trim()), machineID.getText().trim());
            inv.updatePart(part);
        }
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