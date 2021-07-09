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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ToDoListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button helpButton;

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

    @FXML
    private TableView<ToDoList> taskTable;

    @FXML
    private TableColumn<ToDoList, Boolean> markCompletedColumn;

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
    private Button clearListButton;

    ObservableList<ToDoList> toDoItems = FXCollections.observableArrayList();
    ObservableList<ToDoList> filteredList = FXCollections.observableArrayList();

    @FXML
    public void addTaskButtonPressed() {

        ToDoList td = new ToDoList();
        System.out.print("Task added.\n");

        // Add user input to a toDoList object
        setToDoItems(td);

        // Only do this if setToDoItems is true
        // Get user input and add it to the table and the ObservableList
        toDoItems.add(td);

        // Clear the text fields
        clearTextFields();

        // TESTING
        printList();
    }

    // TESTING PURPOSES ONLY
    public void printList() {
        for (ToDoList toDoItem : toDoItems) {

            System.out.print(toDoItem.dueDate + "\n");
            System.out.print(toDoItem.taskDescription + "\n");
            System.out.print(toDoItem.taskTitle + "\n");
            System.out.print(toDoItem.isCompleted + "\n");
        }
    }


    private void setToDoItems(ToDoList td) {

        // Only do this if the description length is correct
        // Make method return a boolean
        td.setTaskTitle(taskTitleTextField.getText());
        td.setTaskDescription(taskDescriptionTextField.getText());
        td.setDueDate(catchNullPointerDueDate());
        td.setIsCompleted(false);
    }

    private Boolean checkDescriptionLength() {
        return (taskDescriptionTextField.getText().length() > 1 && taskDescriptionTextField.getText().length() < 256);
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

        if (!output.equals(" ")) {
            System.out.print(output + " picked.\n");
        }

        return output;
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

        // Delete selected item from ObservableList
        toDoItems.remove(taskTable.getSelectionModel().getSelectedItem());

        // Remove selected item from gui table
        taskTable.refresh();
        taskTable.setItems(toDoItems);

        return true;
    }

    @FXML
    public void clearListButtonPressed() {

        System.out.print("ClearListButton pressed.\n");

        toDoItems.clear();

        taskTable.refresh();
        taskTable.setItems(toDoItems);
    }

    @FXML
    void helpButtonPressed(ActionEvent event) {

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
    public void sortMenuButtonClicked() {
        // User clicks on sort menu button
        // Drop down appears
        /* This method may be unnecessary */
        System.out.print("Sorting menu opened.\n");
    }

    @FXML
    public void viewAllTasksButtonPressed(ActionEvent event) {

        filteredList.clear();

        taskTable.refresh();

        taskTable.setItems(toDoItems);
    }

    public void filterList() {
        for (ToDoList toDoItem : toDoItems) {
            if (isChecked(toDoItem)) {
                filteredList.add(toDoItem);
            }
        }

        printList();
    }

    // Check if checkbox is checked
    public Boolean isChecked(ToDoList td) {
        return td.isCompleted();
    }

    @FXML
    void viewCompleteTasksButtonPressed(ActionEvent event) {
        // User clicks on button
        // Only events with completed button filled are displayed
        filteredList.clear();

        filterList();
        taskTable.refresh();
        taskTable.setItems(filteredList);
    }

    @FXML
    void viewIncompleteTasksButtonPressed(ActionEvent event) {
        // User clicks on button
        // Only events with completed button NOT filled are displayed
        filteredList.clear();
        filterIncompleteList();
        taskTable.refresh();
        taskTable.setItems(filteredList);
    }

    public void filterIncompleteList() {
        for (ToDoList toDoItem : toDoItems) {
            if (!isChecked(toDoItem)) {
                filteredList.add(toDoItem);
            }
        }

        printList();
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

        // Set the items
        taskTable.setItems(toDoItems);


        markCompletedColumn.setCellValueFactory(param -> param.getValue().completedProperty());
        markCompletedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(markCompletedColumn));

        taskTitleColumn.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("taskTitle"));
        taskTitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        taskTitleColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ToDoList, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ToDoList, String> event) {
                ToDoList list = event.getRowValue();
                list.setTaskTitle(event.getNewValue());
            }
        });

        taskTable.refresh();
        taskTable.setItems(toDoItems);

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("taskDescription"));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ToDoList, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ToDoList, String> event) {
                ToDoList tdl = event.getRowValue();
                tdl.setTaskDescription(event.getNewValue());
            }
        });

        taskTable.refresh();
        taskTable.setItems(toDoItems);

        dueDateColumn.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("dueDate"));

        // TESTING
        printList();
    }
}