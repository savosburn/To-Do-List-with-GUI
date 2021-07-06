/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Savannah Osburn
 */

package ucf.assignments;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class ListsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuButton fileActionsMenu;

    @FXML
    private MenuItem saveListsButton;

    @FXML
    private MenuItem loadListsButton;

    @FXML
    private ListView<?> toDoListLists;

    @FXML
    private Button newToDoListButton;

    @FXML
    private Button deleteListsButton;

    private Stage stage;
    private Parent root;


    /*
            Create a list of to-do list objects somehow
     */



    @FXML
    void addListButtonPressed(ActionEvent event) {

        /* May help with switching between scenes:
           https://www.youtube.com/watch?v=hcM-R-YOKkQ
         */

        // Set scene to ToDoListController
        System.out.print(toToDoListController());
        // Allow user to fill in information
        // Return to ListsController Scene
        // Only to do list's title should be displayed

        // Modify later: Starter code so grader can see next fxml screen
    }

    // Pre-conditions:
    // Post-conditions: Switches scene to ToDoListController.fxml and returns a string
    public String toToDoListController() {
        try {
            Stage curStage = (Stage) newToDoListButton.getScene().getWindow();
            curStage.close();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ToDoListController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("To Do");
            stage.show();

            return "Scene switched to To Do List Controller\n";
        } catch (Exception e) {
            System.out.print("\nInvalid\n");
        }

        return null;
    }

    @FXML
    void deleteListButtonPressed(ActionEvent event) {
        // User clicks on a list
        // User clicks on delete button
        // List is removed from the list of To Do Lists
        // The other lists should shift up to fill the empty spot
        // List's title and table should also be deleted
    }

    @FXML
    void loadListsButtonPressed(ActionEvent event) {
        // User clicks on load button
        // User can then view external files
        // User can choose appropriate file
        // File content is loaded into a new list

        // Allow user to either select one or multiple
        // Determine if this should be one or two methods once coding starts
    }

    @FXML
    void saveListsButtonPressed(ActionEvent event) {
        // User clicks on save button
        // User saves externally
    }

    @FXML
    void initialize() {
        // List of to do lists should be initially empty

        assert fileActionsMenu != null : "fx:id=\"fileActionsMenu\" was not injected: check your FXML file 'ListsController.fxml'.";
        assert saveListsButton != null : "fx:id=\"saveListsButton\" was not injected: check your FXML file 'ListsController.fxml'.";
        assert loadListsButton != null : "fx:id=\"loadListsButton\" was not injected: check your FXML file 'ListsController.fxml'.";
        assert toDoListLists != null : "fx:id=\"toDoListLists\" was not injected: check your FXML file 'ListsController.fxml'.";
        assert newToDoListButton != null : "fx:id=\"newToDoListButton\" was not injected: check your FXML file 'ListsController.fxml'.";
        assert deleteListsButton != null : "fx:id=\"deleteListsButton\" was not injected: check your FXML file 'ListsController.fxml'.";
    }
}