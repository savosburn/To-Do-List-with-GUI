/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Savannah Osburn
 */

package ucf.assignments;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class TaskController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField taskDescriptionField;

    @FXML
    private Button returnToToDoListButton;

    @FXML
    private Button addItemButton;

    @FXML
    private DatePicker dueDateCalendarField;

    @FXML
    private TextField titleTextField;

    @FXML
    void addItemButtonPressed(ActionEvent event) {
        // Item title, description, and date added to table
        // Scene switches from TaskController to ToDoListController

        // add task object to list of tasks in a to do list object
    }

    @FXML
    void dueDateCalendarFieldFilled(ActionEvent event) {
        // User clicks on calendar
        // Date chosen
        // Added to table in format: YYYY/MM/DD

        /* maybe use (if it works):
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd",Locale.US);
            String formattedValue = (myDatePicker.getValue()).format(formatter);
         */
    }

    @FXML
    void returnToToDoListButtonPressed(ActionEvent event) {
        // Make sure that any field that was filled is cleared
        // Change scene from TaskController to ToDoListController

        // return something to make this testable
    }

    @FXML
    void taskDescriptionFieldFilled(ActionEvent event) {
        // User clicks on field
        // User inputs description
        // Description is saved to add to the table
    }

    @FXML
    void titleTextFieldFilled(ActionEvent event) {
        // User clicks on field
        // User inputs title
        // Title is saved to add to the table
    }

    @FXML
    void initialize() {
        assert taskDescriptionField != null : "fx:id=\"taskDescriptionField\" was not injected: check your FXML file 'TaskController.fxml'.";
        assert returnToToDoListButton != null : "fx:id=\"returnToToDoListButton\" was not injected: check your FXML file 'TaskController.fxml'.";
        assert addItemButton != null : "fx:id=\"addItemButton\" was not injected: check your FXML file 'TaskController.fxml'.";
        assert dueDateCalendarField != null : "fx:id=\"dueDateCalendarField\" was not injected: check your FXML file 'TaskController.fxml'.";
        assert titleTextField != null : "fx:id=\"titleTextField\" was not injected: check your FXML file 'TaskController.fxml'.";

    }
}