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

public class HelpController {

    @FXML
    private Button returnButton;

    @FXML
    private Button nextButton;

    // Post-condition: Scene is switched to next help screen
    @FXML
    public String nextButtonPressed() {
        try {

            // Close current stage
            Stage curStage = (Stage)nextButton.getScene().getWindow();
            curStage.close();

            // Open next stage
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpDeleteTasksController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Help");
            stage.show();

            System.out.print("Scene switched to HelpDeleteTasksController.fxml\n");
            return "Scene switched to HelpDeleteTasksController.fxml\n";
        } catch(Exception e) {

            // Check if the scene change was unsuccessful
            System.out.print("Scene switch unsuccessful.\n");
            return "Scene switch unsuccessful.\n";
        }
    }

    // Post-conditions: Scene is switched back to ToDoList controller
    @FXML
    public String returnButtonPressed() {

        try {
            // Close stage to go back to ToDoListController that is still open in the background
            Stage curStage = (Stage)returnButton.getScene().getWindow();
            curStage.close();

            System.out.print("Scene switched to ToDoListController.fxml\n");
            return "Scene switched to ToDoListController.fxml\n";
        } catch(Exception e) {

            // Check if the scene switch was unsuccessful
            System.out.print("Scene switch unsuccessful.\n");
            return "Scene switch unsuccessful.\n";
        }
    }

    @FXML
    public void initialize() {
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file 'HelpController.fxml'.";
        assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'HelpController.fxml'.";
    }
}