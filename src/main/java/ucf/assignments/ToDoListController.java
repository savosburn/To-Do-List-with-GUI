/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Savannah Osburn
 */

package ucf.assignments;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ToDoListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuButton fileMenuButton;

    @FXML
    private MenuItem saveItemsButton;

    @FXML
    private MenuItem loadItemsButton;

    @FXML
    private MenuButton sortMenuButton;

    @FXML
    private MenuItem viewAllTasksButton;

    @FXML
    private MenuItem viewCompleteTasksButton;

    @FXML
    private MenuItem viewIncompleteTasksButton;

    // THIS WAS MODIFIED FROM: private TableView<?> taskTable;
    @FXML
    private TableView<ToDoList> taskTable;

    @FXML
    private TableColumn<ToDoList, CheckBox> markCompletedColumn;

    @FXML
    private TableColumn<ToDoList, String> taskTitleColumn;

    @FXML
    private TableColumn<ToDoList, String> descriptionColumn;

    @FXML
    private TableColumn<ToDoList, String> dueDateColumn;

    @FXML
    private Button addTaskButton;

    @FXML
    private Button deleteTaskButton;

    @FXML
    private TextField taskTitleTextField;

    @FXML
    private TextField taskDescriptionTextField;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    public void addTaskButtonPressed() {

        ToDoList td = new ToDoList();
        System.out.print("Task added.\n");

        // Get user input and add it to the table
        setItems(td);
        taskTable.getItems().add(td);

        // Clear the text fields
        clearTextFields();
    }

    private void setItems(ToDoList td) {
        td.setTaskTitle(taskTitleTextField.getText());
        td.setTaskDescription(taskDescriptionTextField.getText());
        td.setDueDate(catchNullPointerDueDate());
    }

    private void clearTextFields() {
        taskTitleTextField.clear();
        taskDescriptionTextField.clear();
        dueDatePicker.getEditor().clear();
        dueDatePicker.setValue(null);
    }

    public String catchNullPointerDueDate() {
        try {
            dueDatePicker.getValue();

            return dueDatePicker.getValue().toString();
        } catch (NullPointerException e) {
            return " ";
        }
    }

    @FXML
    public String dueDatePicked() {

        // Signify that a date was picked
        String output = catchNullPointerDueDate();
        System.out.print(output + " picked.\n");
        return null;

    }

    // Pre-conditions:
    // Post-conditions: Switches scene to TaskController.fxml and returns a string
    public String toTaskController() {
        try {
            Stage curStage = (Stage)addTaskButton.getScene().getWindow();
            curStage.close();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TaskController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("New Task");
            stage.show();

            return "Scene switched to TaskController.fxml\n";
        } catch(Exception e) {

            return "Scene switch unsuccessful.\n";
        }
    }

    @FXML
    public Boolean deleteTaskButtonPressed() {

        // User selects row and presses delete to remove item
        return taskTable.getItems().removeAll(
                taskTable.getSelectionModel().getSelectedItems()
        );
    }

    @FXML
    void fileMenuButtonClicked(ActionEvent event) {
        // User clicks on file menu button
        // Drop down appears

        /* This method may be unnecessary */
    }

    @FXML
    void loadItemsButtonPressed(ActionEvent event) {
        // User clicks on load items
        // File menu appears for user to use
        // Loading an item automatically adds it to the end of the to-do list
    }

    @FXML
    void saveItemsButtonPressed(ActionEvent event) {
        // To Do List is added to the ListsController scene
        // Scene is not switched unless user presses return button
    }

    @FXML
    void sortMenuButtonClicked(ActionEvent event) {
        // User clicks on sort menu button
        // Drop down appears
        /* This method may be unnecessary */
    }

    @FXML
    void viewAllTasksButtonPressed(ActionEvent event) {
        // Default button / If user clicks on it
        // All tasks are showed
    }

    @FXML
    void viewCompleteTasksButtonPressed(ActionEvent event) {
        // User clicks on button
        // Only events with completed button filled are displayed
    }

    @FXML
    void viewIncompleteTasksButtonPressed(ActionEvent event) {
        // User clicks on button
        // Only events with completed button NOT filled are displayed
    }

    @FXML
    public void initialize() {

        assert fileMenuButton != null : "fx:id=\"fileMenuButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert saveItemsButton != null : "fx:id=\"saveItemsButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert loadItemsButton != null : "fx:id=\"loadItemsButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert sortMenuButton != null : "fx:id=\"sortMenuButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert viewAllTasksButton != null : "fx:id=\"viewAllTasksButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert viewCompleteTasksButton != null : "fx:id=\"viewCompleteTasksButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert viewIncompleteTasksButton != null : "fx:id=\"viewIncompleteTasksButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert deleteTaskButton != null : "fx:id=\"deleteTaskButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskTable != null : "fx:id=\"taskTable\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert markCompletedColumn != null : "fx:id=\"markCompletedColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskTitleColumn != null : "fx:id=\"taskTitleColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert descriptionColumn != null : "fx:id=\"descriptionColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert dueDateColumn != null : "fx:id=\"dueDateColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert addTaskButton != null : "fx:id=\"addTaskButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskTitleTextField != null : "fx:id=\"taskTitleTextField\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskDescriptionTextField != null : "fx:id=\"taskDescriptionTextField\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert dueDatePicker != null : "fx:id=\"dueDatePicker\" was not injected: check your FXML file 'ToDoListController.fxml'.";

        // Set up the columns in the table
        // BREAK INTO SMALLER METHODS
        dueDatePicker.setConverter(new StringConverter<LocalDate>() {

            private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });

        markCompletedColumn.setCellFactory(column -> new CheckBoxTableCell<>());
        taskTitleColumn.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("taskTitle"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("taskDescription"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("dueDate"));
    }
}