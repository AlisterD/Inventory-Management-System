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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import model.*;

/**
 * Controls the Add Part screen
 */
public class AddPartController implements Initializable {

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

    /**
     * Class constructor
     * @param inv the inventory of parts and products
     */
    public AddPartController(Inventory inv) {
        this.inv = inv;
    }

    /**
     * Initializes the controller class
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known
     * @param rb The resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inHouseRadio.setSelected(true);
        partID.setText(generatePartID());
        machineLabel.setText("Machine ID");
    }

    /**
     * Generates a unique Part ID by calculating the largest existing Part ID and adding 1
     * @return a unique Part ID
     */
    private String generatePartID() {
        if (!inv.getAllParts().isEmpty()) {
            int i = 1;
            for (Part p : inv.getAllParts())
                if (p.getPartID() > i)
                    i = p.getPartID();
            return Integer.toString(i + 1);
        }
        else {
            return "1";
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
            inv.addPart(part);
        }
        if (outsourcedRadio.isSelected()) {
            Outsourced part = new Outsourced(Integer.parseInt(partID.getText().trim()), name.getText().trim(), Double.parseDouble(price.getText().trim()), Integer.parseInt(stock.getText().trim()), Integer.parseInt(min.getText().trim()), Integer.parseInt(max.getText().trim()), machineID.getText().trim());
            inv.addPart(part);
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
