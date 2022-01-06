/**
 * @author Nik
 */
// GUICalculator.java

// import the required classes and packages

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

// class that performs the basic arithmetic operations by using
// a simple GUI interface
public class GUICalculator extends Application {
    // define the required components of the GUI
    // to set the bottom label which displays information
    // about the calculation
    Label information = new Label("Calculator");

    // RadioButtons
    ToggleButton addition = new RadioButton("+ ");
    ToggleButton subtraction = new RadioButton("- ");
    ToggleButton multiplication = new RadioButton("* ");
    ToggleButton division = new RadioButton("/ ");

    // ToggleGroup defined for the radio buttons
    ToggleGroup radioToggle = new ToggleGroup();

    // two buttons, one for calculation and other for
    // reset the text boxes and label content
    Button calculate = new Button("Calculate");

    // three labels to intimate the user what is being
    // done with the textFields
    Label firstNumPrompt = new Label("Number 1");
    Label secondNumPrompt = new Label("Number 2");

    // three TextFields, two for read input and one for
    // output
    TextField firstInput = new TextField();
    TextField secondInput = new TextField();
    TextField output = new TextField();

    // start() method which starts the designing and execution
    // of the code
    @Override
    public void start(Stage primaryStage) throws Exception {
        // define three borderPane objects
        BorderPane operationalPane = getOperationalPane();
        BorderPane inputOutputPane = getInputOutputPane();
        BorderPane informationPane = getInformationPane();

        // add the borderPanes to VBox
        VBox vbox = new VBox(inputOutputPane, operationalPane, informationPane);

        // Define the scene with vbox with dimensions, 450 and 350
        Scene scene = new Scene(vbox, 450, 350);

        // add the scene to the primaryStage
        primaryStage.setScene(scene);

        // set the title of the stage
        primaryStage.setTitle("Calculator");

        // set the visibility of the stage
        primaryStage.show();

        // call the method computeCalculation() to perform
        // the operations
        computeCalculation();
    }

    // getOperationalPane() returns a BorderPane which contains the
    // radio buttons, calculate and reset buttons
    public BorderPane getOperationalPane() {
        // TODO Auto-generated method stub

        // set the font of the radio buttons
        addition.setFont(Font.font("Times of New Roman", FontPosture.REGULAR, 20));
        subtraction.setFont(Font.font("Times of New Roman", FontPosture.REGULAR, 20));
        multiplication.setFont(Font.font("Times of New Roman", FontPosture.REGULAR, 20));
        division.setFont(Font.font("Times of New Roman", FontPosture.REGULAR, 20));

        // add the radio buttons to the toggle group
        addition.setToggleGroup(radioToggle);
        subtraction.setToggleGroup(radioToggle);
        multiplication.setToggleGroup(radioToggle);
        division.setToggleGroup(radioToggle);

        // add the radio buttons, and buttons to HBox
        // with a gap of 20
        HBox radioContainer = new HBox(20, addition, subtraction, multiplication, division, calculate);


        // Define the BorderPane object
        BorderPane operationalPane = new BorderPane();

        // set the margin
        BorderPane.setMargin(radioContainer, new Insets(20));

        // add the radioContainer to the top
        operationalPane.setBottom(radioContainer);


        // return the border pane
        return operationalPane;
    }

    // computeCalculation() method performs the button operations
    public void computeCalculation() {
        // add the event for the calculate button
        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                // TODO Auto-generated method stub
                // try to catch exceptions if there are any in the following
                // code
                try {
                    // define the variables
                    double value1 = 0, value2 = 0;

                    // if any one input text field is empty
                    // raise and exception
                    if (firstInput.getText().isEmpty() || secondInput.getText().isEmpty()) {
                        throw new Exception();
                    }

                    // otherwise get the values from the text fields
                    else {
                        // if any of input text is cannot be converted
                        // to double there are chances of exception
                        // So place the code in try..catch block
                        try {
                            // get the value from the firstInput and
                            // convert to double and store the value in value1
                            value1 = Double.parseDouble(firstInput.getText());

                            // get the value from the secondInput and
                            // convert to double and store the value in value2
                            value2 = Double.parseDouble(secondInput.getText());

                            // call the method to computeResult() by passing
                            // the value1, value2
                            computeResult(value1, value2);
                        }

                        // catch the NumberFormatException if there are
                        // any
                        catch (NumberFormatException nfe) {
                            // set the label at the bottom pane
                            // with an error message
                            information.setText("x and y have to be numbers. Try again!");
                        }
                    }
                }

                // catch the exception if the x and y's text fields are empty
                catch (Exception e) {
                    information.setText("Please enter x and y numbers. Try again!");
                }
            }
        });


    }

    // computeResult() method accepts two double variables
    // This method performs the arithmetic operation depending
    // on the radio button selected.
    // If any one operator is selected then it performs respective
    // operation depending on the radio button selected.
    // Otherwise displays an error message in the label
    public void computeResult(double value1, double value2) {
        // define a double data type variable
        double result = 0;

        // if addition radio button is selected, it performs the
        // addition operation and displays the result in output text field
        if (addition.isSelected()) {
            result = value1 + value2;
            output.setText(String.format("%.2f + %.2f = %.2f", value1, value2, result));
            information.setText("Calculator");
        }

        // if subtraction radio button is selected, it performs the
        // subtraction operation and displays the result in output text field
        else if (subtraction.isSelected()) {
            result = value1 - value2;
            output.setText(String.format("%.2f - %.2f = %.2f", value1, value2, result));
            information.setText("Calculator");
        }

        // if multiplication radio button is selected, it performs the
        // multiplication operation and displays the result in output text field
        else if (multiplication.isSelected()) {
            result = value1 * value2;
            output.setText(String.format("%.2f * %.2f = %.2f", value1, value2, result));
            information.setText("Calculator");
        }

        // if division radio button is selected, it performs the
        // division operation and displays the result in output text field
        else if (division.isSelected()) {
            // if the second value is zero, then it has display the error
            // message in information label
            if (value2 == 0) {
                information.setText("y cannot be zero. Try again!");
            }

            // otherwise, compute the division and display the value
            else {
                result = value1 / value2;
                output.setText(String.format("%.2f / %.2f = %.2f", value1, value2, result));
                information.setText("Calculator");
            }
        }

        // otherwise display the error message to the user
        else {
            information.setText("Please select an operator and then click Calculate");
        }
    }

    // getInputOutputPane() method that sets the middle Pane and returns the border
    // Pane
    public BorderPane getInputOutputPane() {
        // TODO Auto-generated method stub

        // set the font type and alignment of the text to right
        firstInput.setFont(Font.font("Times of New Roman", FontPosture.REGULAR, 20));
        firstInput.setAlignment(Pos.CENTER);
        secondInput.setFont(Font.font("Times of New Roman", FontPosture.REGULAR, 20));
        secondInput.setAlignment(Pos.CENTER);
        output.setFont(Font.font("Times of New Roman", FontPosture.REGULAR, 20));
        output.setAlignment(Pos.CENTER);

        // set the output text field Editable to false
        output.setEditable(false);

        // define the grid pane to set the label and text field aligned
        // in two columns
        GridPane gridPane = new GridPane();

        // set the horizontal and vertical gaps
        gridPane.setHgap(10);
        gridPane.setVgap(20);

        // add the labels, and text fields to the gridPane
        gridPane.add(firstNumPrompt, 0, 0);
        gridPane.add(firstInput, 1, 0);
        gridPane.add(secondNumPrompt, 0, 1);
        gridPane.add(secondInput, 1, 1);
        gridPane.add(output, 1, 2);

        // Define the borderPane
        BorderPane inputAndOutput = new BorderPane();

        // set the gridPane to the center of the borderPane
        inputAndOutput.setCenter(gridPane);

        // set the margin
        BorderPane.setMargin(gridPane, new Insets(20));


        // return the BorderPane object
        return inputAndOutput;
    }

    // getInformationPane() method to set the bottom pane and returns
    // BorderPane object
    public BorderPane getInformationPane() {
        // set the text color of information
        information.setTextFill(Color.BLACK);

        // add the label to the VBox
        VBox vbox = new VBox(information);

        // set the alignment of the component in the VBox to center
        vbox.setAlignment(Pos.CENTER);

        // Define the BorderPane object
        BorderPane infoPane = new BorderPane();

        // set the vbox to center
        infoPane.setCenter(vbox);

        // set the margin
        BorderPane.setMargin(vbox, new Insets(30));


        return infoPane;
    }

    // define the main method
    public static void main(String args[]) {
        // start the application
        launch(args);
    }
}