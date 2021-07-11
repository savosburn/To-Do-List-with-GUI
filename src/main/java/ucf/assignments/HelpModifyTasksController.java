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

    @FXML
    public String backButtonPressed(ActionEvent event) {


        try {

            Stage curStage = (Stage)backButton.getScene().getWindow();
            curStage.close();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpDeleteTasksController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Invalid Description");
            stage.show();

            return "Scene switched to InvalidDateController.fxml\n";
        } catch(Exception e) {

            return "Scene switch unsuccessful.\n";
        }

    }

    @FXML
    public String nextButtonPressed(ActionEvent event) {

        try {

            Stage curStage = (Stage)nextButton.getScene().getWindow();
            curStage.close();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpSaveLoadTasksController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Invalid Description");
            stage.show();

            return "Scene switched to InvalidDateController.fxml\n";
        } catch(Exception e) {

            return "Scene switch unsuccessful.\n";
        }

    }

    @FXML
    public String returnButtonPressed(ActionEvent event) {

        try {
            Stage curStage = (Stage)returnButton.getScene().getWindow();
            curStage.close();

            System.out.print("Scene switched.\n");

            return "Scene switched to ToDoListController.fxml\n";
        } catch(Exception e) {

            return "Scene switch unsuccessful.\n";
        }

    }

    @FXML
    void initialize() {
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file 'HelpModifyTasksController.fxml'.";
        assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'HelpModifyTasksController.fxml'.";

    }
}