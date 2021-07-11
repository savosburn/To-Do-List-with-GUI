/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Savannah Osburn
 */

package ucf.assignments;

import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpSaveLoadTasksController {

    @FXML
    private Button returnButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button backButton;

    // Post-conditions: GUI scene is switched to previous help screen
    @FXML
    public String backButtonPressed() {
        try {
            // Close current scene
            Stage curStage = (Stage)backButton.getScene().getWindow();
            curStage.close();

            // Open next help scene
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpModifyTasksController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Help");
            stage.show();

            System.out.print("Scene switched to InvalidDateController.fxml\n");
            return "Scene switched to InvalidDateController.fxml\n";
        } catch(Exception e) {

            // Check if scene switch was unsuccessful
            System.out.print("Scene switch unsuccessful.\n");
            return "Scene switch unsuccessful.\n";
        }
    }

    // Post-conditions: GUI scene is switched to next help screen
    @FXML
    public String nextButtonPressed() {

        try {
            // Current scene closed
            Stage curStage = (Stage)nextButton.getScene().getWindow();
            curStage.close();

            // Scene switched to next help scene
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpSortTasksController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Invalid Description");
            stage.show();

            System.out.print("Scene switched to InvalidDateController.fxml\n");
            return "Scene switched to InvalidDateController.fxml\n";
        } catch(Exception e) {

            // Check if the scene switch was successful
            System.out.print("Scene switched to InvalidDateController.fxml\n");
            return "Scene switch unsuccessful.\n";
        }
    }

    // Post-conditions: Scene switched back to ToDoList controller
    @FXML
    public String returnButtonPressed() {

        try {
            // Current scene closed to return to ToDoList controller that's open in the background
            Stage curStage = (Stage)returnButton.getScene().getWindow();
            curStage.close();

            System.out.print("Scene switched to ToDoListController.fxml\n");
            return "Scene switched to ToDoListController.fxml\n";
        } catch(Exception e) {

            // Check if scene switch was successful
            System.out.print("Scene switch unsuccessful.\n");
            return "Scene switch unsuccessful.\n";
        }
    }

    @FXML
    public void initialize() {
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file " +
                "'HelpSaveLoadTasksController.fxml'.";
        assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file " +
                "'HelpSaveLoadTasksController.fxml'.";
    }
}