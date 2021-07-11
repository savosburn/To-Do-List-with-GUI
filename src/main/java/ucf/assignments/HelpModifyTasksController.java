/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Savannah Osburn
 */

package ucf.assignments;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpModifyTasksController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button returnButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button backButton;


    // Post-conditions: Scene is switched to previous help scene
    @FXML
    public String backButtonPressed() {
        try {

            // Close current scene
            Stage curStage = (Stage)backButton.getScene().getWindow();
            curStage.close();

            // Open previous help scene
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpDeleteTasksController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Help");
            stage.show();

            System.out.print("Scene switched to HelpDeleteTasksController.fxml\n");
            return "Scene switched to HelpDeleteTasksController.fxml\n";
        } catch(Exception e) {

            // Check that the scene can be properly switched
            System.out.print("Scene switched to HelpDeleteTasksController.fxml\n");
            return "Scene switch unsuccessful.\n";
        }
    }

    // Post-conditions: Scene switched to next help scene
    @FXML
    public String nextButtonPressed() {

        try {
            // Close current scene
            Stage curStage = (Stage)nextButton.getScene().getWindow();
            curStage.close();

            // Open next help scene
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpSaveLoadTasksController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Help");
            stage.show();

            System.out.print("Scene switched to InvalidDateController.fxml\n");
            return "Scene switched to InvalidDateController.fxml\n";
        } catch(Exception e) {

            // Check if scene wasn't switched properly
            System.out.print("Scene switch unsuccessful.\n");
            return "Scene switch unsuccessful.\n";
        }

    }

    // Post-conditions: Switches scene to ToDoListController
    @FXML
    public String returnButtonPressed() {

        try {
            // Close current scene to return to ToDoListController that is still open in the background
            Stage curStage = (Stage)returnButton.getScene().getWindow();
            curStage.close();

            System.out.print("Scene switched to ToDoListController.fxml\n");
            return "Scene switched to ToDoListController.fxml\n";
        } catch(Exception e) {

            // Check if scene couldn't switch
            System.out.print("Scene switched to ToDoListController.fxml");
            return "Scene switch unsuccessful.\n";
        }
    }

    @FXML
    public void initialize() {
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file " +
                "'HelpModifyTasksController.fxml'.";
        assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file" +
                " 'HelpModifyTasksController.fxml'.";
    }
}