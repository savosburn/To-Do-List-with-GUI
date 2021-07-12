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

public class HelpDeleteTasksController {

    @FXML private Button returnButton;
    @FXML private Button nextButton;
    @FXML private Button backButton;

    // Post-conditions: Scene is switched to previous help screen
    @FXML
    public String backButtonPressed() {
        try {

            // Close current stage
            Stage curStage = (Stage)backButton.getScene().getWindow();
            curStage.close();

            // Open previous stage
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Help");
            stage.show();

            System.out.print("Scene switched to HelpController.fxml\n");
            return "Scene switched to HelpController.fxml\n";
        } catch(Exception e) {

            // Check if scene could not be switched
            System.out.print("Scene switch unsuccessful.\n");
            return "Scene switch unsuccessful.\n";
        }
    }

    // Post-conditions: Switch scene to next help scene
    @FXML
    public String nextButtonPressed() {
        try {

            // Close current stage
            Stage curStage = (Stage)nextButton.getScene().getWindow();
            curStage.close();

            // Open previous scene
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpModifyTasksController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Help");
            stage.show();

            System.out.print("Scene switched to HelpModifyTasksController.fxml\n");
            return "Scene switched to HelpModifyTasksController.fxml\n";
        } catch(Exception e) {

            // Check if scene doesn't exist
            System.out.print("Scene switch unsuccessful.\n");
            return "Scene switch unsuccessful.\n";
        }

    }

    // Post-conditions: Scene switched back to to do list controller
    @FXML
    public String returnButtonPressed() {
        try {
            // Close current stage to return to ToDoListController which is still open in the background
            Stage curStage = (Stage)returnButton.getScene().getWindow();
            curStage.close();

            System.out.print("Scene switched to ToDoListController.fxml.\n");
            return "Scene switched to ToDoListController.fxml\n";
        } catch(Exception e) {

            // Check if scene was successfully switched
            System.out.print("Scene switch unsuccessful.\n");
            return "Scene switch unsuccessful.\n";
        }
    }

    @FXML
    public void initialize() {
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file 'HelpDeleteTasksController.fxml'.";
        assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'HelpDeleteTasksController.fxml'.";
    }
}
