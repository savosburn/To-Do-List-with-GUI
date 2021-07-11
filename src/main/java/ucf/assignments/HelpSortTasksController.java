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

public class HelpSortTasksController {

    @FXML
    private Button returnButton;

    @FXML
    private Button backButton;

    // Post-conditions: Switches scene to previous help scene
    @FXML
    public String backButtonPressed() {

        try {
            // Close current scene
            Stage curStage = (Stage)backButton.getScene().getWindow();
            curStage.close();

            // Switch to previous help scene
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpSaveLoadTasksController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Help");
            stage.show();

            System.out.print("Scene switched to HelpSaveLoadTasksController.fxml\n");
            return "Scene switched to HelpSaveLoadTasksController.fxml\n";
        } catch(Exception e) {

            // Check if scene switch was unsuccessful
            System.out.print("Scene switch unsuccessful.\n");
            return "Scene switch unsuccessful.\n";
        }
    }

    // Post-conditions: Switches scene to toDoListController
    @FXML
    public String returnButtonPressed() {

        try {
            // Close current scene to return to ToDoListController that is still open in the background
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
                "'HelpSortTasksController.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file " +
                "'HelpSortTasksController.fxml'.";
    }
}
