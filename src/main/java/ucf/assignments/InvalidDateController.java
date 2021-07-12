/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Savannah Osburn
 */

package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InvalidDateController {

    @FXML private Button okButton;

    // Post-conditions: Scene is switched back to ToDoList controller
    @FXML
    public String okButtonPressed() {
        try {
            // Close current stage to return to ToDoList Controller that is still open in the background
            Stage curStage = (Stage)okButton.getScene().getWindow();
            curStage.close();

            System.out.print("Scene switched to ToDoListController.fxml\n");
            return "Scene switched to ToDoListController.fxml\n";
        } catch(Exception e) {

            // Check if the scene could not be switched
            System.out.print("Scene switch unsuccessful.\n");
            return "Scene switch unsuccessful.\n";
        }
    }

    @FXML
    public void initialize() {
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file " +
                "'InvalidDateController.fxml'.";
    }
}