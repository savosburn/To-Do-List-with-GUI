/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Savannah Osburn
 */

package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InvalidDescriptionLengthController {

    @FXML private Button okButton;

    // Post-conditions: Scene switches back to ToDoList Controller
    @FXML
    public String okButtonPressed() {
        try {
            // Close scene to return to ToDoList controller that is still open in the background
            Stage curStage = (Stage)okButton.getScene().getWindow();
            curStage.close();

            System.out.print("Scene switched to ToDoListController.fxml\n");
            return "Scene switched to ToDoListController.fxml\n";
        } catch(Exception e) {

            // Check that scene is properly switched
            System.out.print("Scene switch unsuccessful.\n");
            return "Scene switch unsuccessful.\n";
        }
    }

    @FXML
    void initialize() {
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file " +
                "'InvalidDescriptionLengthController.fxml'.";
    }
}