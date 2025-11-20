package com.lermiller.simplecalulator;
// The CalculatorModel class manages the state of the calculation and does the arithmetic. It is the M in MVC.

public class CalculatorModel {
    // These private state variables are used here in the Model's functions
    private double currentValue;    // The number to be displayed
    private double previousValue;   // The first operand
    private String operator;        // The pending operation (+, -, *, /)
    private boolean startNewNumber; // A boolean flag to say if a new number should be started


    public CalculatorModel() {      // The constructor starts the Model with default variables
        clear();
    }

    public void clear() {           // This method resets all the variables to default
        currentValue = 0.0;
        previousValue = 0.0;
        operator = "";
        startNewNumber = true;
    }

    public void processInput(String input) {    // This method handles input of numbers
        String currentText = String.valueOf(currentValue);

        if (startNewNumber) {                   // If a new number is started, like after an operator or clear
            currentText = input;                // Set the currentText to be the inputted character (. or 0-9)
            startNewNumber = false;             // Switch the flag to start appending characters
        }
        else if (input.equals(".")){            // If a "." is entered, append it if there isn't already a "."
            if (!currentText.contains(".")){
                currentText += input;
            }
        }
        else {
            if (currentText.equals("0")){       // If the currentText is 0, overwrite the 0 with the input
                currentText = input;
            }
            else {
                currentText += input;           // The standard case; append the input digit to the currentText
            }
        }
        currentValue = Double.parseDouble(currentText);     // Updates the state variable to the full input
    }

    public void setOperation(String newOperator){   // Called when an operand is entered ("+, -, *, /)
        if (!operator.isEmpty()){   // If there is a pending operator, calculate that result before moving on... Handles the case of a user hitting a new operator without hitting "=" first
            calculateResult();
        }
        previousValue = currentValue;   // Store the displayed value as the first operand
        operator = newOperator;         // Sets the state variable operator to the inputted operator
        startNewNumber = true;          // Sets the flag for processInput to start a new number
    }

    public void calculateResult() {
        if (operator.isEmpty()){    // If there isnt an operator there is nothing to calculate
            return;
        }
        double result = 0.0;        // An internal variable for this function to return
        switch (operator){
            case "+":
                result = previousValue + currentValue;
                break;
            case "-":
                result = previousValue - currentValue;
                break;
            case "*":
                result = previousValue * currentValue;
                break;
            case "/":
                if (currentValue != 0) {
                    result = previousValue / currentValue;
                    break;
                }
                else {
                    System.out.println("Error: Cannot divide by zero");
                    result = 0.0;
                }
                break;
        }
        // Update the state:
        currentValue = result;  // Updates the state variable to the result to display/ carry over to the next calculateResult call
        previousValue = 0.0;    // Resets the first operand
        operator = "";          // Clears the pending operator
        startNewNumber = true;  // Sets the flag for processInput to start a new number
    }

    public String getCurrentValueString() {     // Gives the sting value of the currentValue
        if (currentValue == (long) currentValue){               // If the currentValue is the same without the decimal part...
            return String.format("%d", (long) currentValue);    // Return the currentValue formatted as an integer
        }
        else {
            return String.valueOf(currentValue);                // If the decimal part matters, return the currentValue with the decimal part
        }
    }

}


