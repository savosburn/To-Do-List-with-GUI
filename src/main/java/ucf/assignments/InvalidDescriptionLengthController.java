package ucf.assignments;

// import java.net.URL;
// import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InvalidDescriptionLengthController {

    /*
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location; */

    @FXML
    private Button okButton;

    @FXML
    public String okButtonPressed() {
        try {
            Stage curStage = (Stage)okButton.getScene().getWindow();
            curStage.close();

            System.out.print("Scene switched.\n");

            return "Scene switched to ToDoListController.fxml\n";
        } catch(Exception e) {

            return "Scene switch unsuccessful.\n";
        }
    }

    @FXML
    void initialize() {

        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'InvalidDescriptionLengthController.fxml'.";
    }
}