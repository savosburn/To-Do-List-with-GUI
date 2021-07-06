/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Savannah Osburn
 */

package ucf.assignments;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private Button returnToAllListsButton;

    @FXML
    private MenuButton sortMenuButton;

    @FXML
    private MenuItem viewAllTasksButton;

    @FXML
    private MenuItem viewCompleteTasksButton;

    @FXML
    private MenuItem viewIncompleteTasksButton;

    @FXML
    private TextField titleTextField;

    @FXML
    private TableView<?> taskTable;

    @FXML
    private TableColumn<?, ?> markCompletedColumn;

    @FXML
    private TableColumn<?, ?> taskTitleColumn;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TableColumn<?, ?> dueDateColumn;

    @FXML
    private Button addTaskButton;

    @FXML
    private Button deleteTaskButton;

    @FXML
    private Button addToDoListButton;

    @FXML
    void addTaskButtonPressed(ActionEvent event) {
        // Scene switches from ToDoListController to TaskController
        System.out.print(toTaskController());

        // User adds task
        // Scene switches back if it's added
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
            stage.setTitle("To Do");
            stage.show();

            return "Scene switched to task controller\n";
        } catch(Exception e) {
            System.out.print("\nInvalid\n");
        }

        return null;
    }

    @FXML
    void addToDoListButtonPressed(ActionEvent event) {
        // List is added to lists
        // ? maybe have scene switch from ToDoLisController to ListsController
    }

    @FXML
    void deleteTaskButtonPressed(ActionEvent event) {
        // User clicks on a task
        // User clicks on delete
        // Task is deleted and other tasks move up to fill the space
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
    void titleTextFieldClicked(ActionEvent event) {
        // User Clicks on text field
        // User types in title
        // To-Do List title is renamed
    }

    @FXML
    void sortByDateButtonClicked(ActionEvent event) {
        // Sorts by date
        // This method is likely unnecessary
        // Table automatically allows user to sort by date if they click on the column heading
    }

    @FXML
    void initialize() {
        assert fileMenuButton != null : "fx:id=\"fileMenuButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert saveItemsButton != null : "fx:id=\"saveItemsButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert loadItemsButton != null : "fx:id=\"loadItemsButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert returnToAllListsButton != null : "fx:id=\"returnToAllListsButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert sortMenuButton != null : "fx:id=\"sortMenuButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert viewAllTasksButton != null : "fx:id=\"viewAllTasksButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert viewCompleteTasksButton != null : "fx:id=\"viewCompleteTasksButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert viewIncompleteTasksButton != null : "fx:id=\"viewIncompleteTasksButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert titleTextField != null : "fx:id=\"titleTextField\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskTable != null : "fx:id=\"taskTable\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert markCompletedColumn != null : "fx:id=\"markCompletedColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskTitleColumn != null : "fx:id=\"taskTitleColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert descriptionColumn != null : "fx:id=\"descriptionColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert dueDateColumn != null : "fx:id=\"dueDateColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert addTaskButton != null : "fx:id=\"addTaskButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert deleteTaskButton != null : "fx:id=\"deleteTaskButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert addToDoListButton != null : "fx:id=\"addToDoListButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
    }
}