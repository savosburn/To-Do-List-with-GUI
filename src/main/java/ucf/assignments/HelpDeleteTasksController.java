package ucf.assignments;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class HelpDeleteTasksController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button returnButton;

    @FXML
    private MenuItem AddTasksButton;

    @FXML
    private MenuItem DeleteTasksButton;

    @FXML
    private MenuItem ModifyTasksButton;

    @FXML
    private MenuItem SortTasks;

    @FXML
    private MenuItem SaveLoadTasksButton;

    @FXML
    void AddTasksButtonPressed(ActionEvent event) {

    }

    @FXML
    void DeleteTasksButtonPressed(ActionEvent event) {

    }

    @FXML
    void ModifyTasksButtonPressed(ActionEvent event) {

    }

    @FXML
    void SaveLoadTasksButtonPressed(ActionEvent event) {

    }

    @FXML
    void SortTasksButtonPressed(ActionEvent event) {

    }

    @FXML
    void returnButtonPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file 'HelpDeleteTasksController.fxml'.";
        assert AddTasksButton != null : "fx:id=\"AddTasksButton\" was not injected: check your FXML file 'HelpDeleteTasksController.fxml'.";
        assert DeleteTasksButton != null : "fx:id=\"DeleteTasksButton\" was not injected: check your FXML file 'HelpDeleteTasksController.fxml'.";
        assert ModifyTasksButton != null : "fx:id=\"ModifyTasksButton\" was not injected: check your FXML file 'HelpDeleteTasksController.fxml'.";
        assert SortTasks != null : "fx:id=\"SortTasks\" was not injected: check your FXML file 'HelpDeleteTasksController.fxml'.";
        assert SaveLoadTasksButton != null : "fx:id=\"SaveLoadTasksButton\" was not injected: check your FXML file 'HelpDeleteTasksController.fxml'.";

    }
}