package com.lermiller.simplecalulator;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class CalculatorController {
    // Link to the View
    @FXML
    private Label displayLabel;

    // Create an instance of the Model
    private CalculatorModel model = new CalculatorModel();

    @FXML
    public void initialize() {
        displayLabel.setText(model.getCurrentValueString());
    }

    @FXML
    public void handleDigit(ActionEvent event) {
        String input = ((Button)event.getSource()).getText();

        // Update the View (Display) using the string returned by the Model
        displayLabel.setText(model.processInput(input));
    }

    @FXML
    public void handleUnaryMinus() {
        displayLabel.setText(model.toggleSign());
    }
    @FXML
    public void handleOperator(ActionEvent event) {
        String operator = ((Button) event.getSource()).getText();   // Get the source button and its character
        model.setOperation(operator);                               // Call the setOperation method in the Model
        displayLabel.setText(model.getCurrentValueString());        // Update the View
    }

    @FXML
    public void handleClear() {     // just calls the clear method in the model and updates the view
        model.clear();
        displayLabel.setText(model.getCurrentValueString());
    }

    @FXML
    public void handleEquals() {    // just calls the calculateResult method in the model and updates the view
        model.calculateResult();
        displayLabel.setText(model.getCurrentValueString());
    }


}
