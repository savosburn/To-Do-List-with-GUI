/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Savannah Osburn
 */

package ucf.assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;

public class ToDoListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button helpButton;

    @FXML
    private MenuButton fileMenuButton;

    @FXML
    private MenuItem saveItemsButton;

    @FXML
    private MenuItem loadItemsButton;

    @FXML
    private MenuButton sortMenuButton;

    @FXML
    private MenuItem viewAllTasksButton;

    @FXML
    private MenuItem viewCompleteTasksButton;

    @FXML
    private MenuItem viewIncompleteTasksButton;

    @FXML
    private TableView<ToDoList> taskTable;

    @FXML
    private TableColumn<ToDoList, Boolean> markCompletedColumn;

    @FXML
    private TableColumn<ToDoList, String> taskTitleColumn;

    @FXML
    private TableColumn<ToDoList, String> descriptionColumn;

    @FXML
    private TableColumn<ToDoList, String> dueDateColumn;

    @FXML
    private Button addTaskButton;

    @FXML
    private Button deleteTaskButton;

    @FXML
    private TextField taskTitleTextField;

    @FXML
    private TextField taskDescriptionTextField;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private Button clearListButton;

    @FXML
    private AnchorPane controller;

    FileChooser fileChooser = new FileChooser();

    ObservableList<ToDoList> toDoItems = FXCollections.observableArrayList();
    ObservableList<ToDoList> filteredList = FXCollections.observableArrayList();

    // Post-conditions: Adds new ToDoList object to the Observable List
    @FXML
    public void addTaskButtonPressed() {

        // Create ToDoList object
        ToDoList td = new ToDoList();
        System.out.print("Add task button pressed.\n");

        // Add user input to a toDoList object
        Boolean isSet = setToDoItems(td);

        // If the items were successfully added to a ToDoList
        if (isSet) {
            // Add the ToDoList to the ObservableList
            toDoItems.add(td);
            System.out.print("items added.\n");
        } else {

            // Else, tell user that the input was incorrect
            String sceneChange = toInvalidDescriptionController();
            System.out.print("items not added.\n");
        }

        // Clear the text fields
        clearTextFields();
    }

    // Post-conditions: Switches scene to InvalidDateController.fxml and returns a string
    public String toInvalidDescriptionController() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InvalidDescriptionLengthController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Invalid Description");
            stage.show();

            return "Scene switched to InvalidDateController.fxml\n";
        } catch(Exception e) {

            return "Scene switch unsuccessful.\n";
        }
    }

    // Post-conditions: Ensures that items are set properly
    private Boolean setToDoItems(ToDoList td) {

        // If the description is the correct length
        if (checkDescriptionLength()) {

            // Set all of the To Do List fields
            td.setTaskTitle(taskTitleTextField.getText());
            td.setTaskDescription(taskDescriptionTextField.getText());
            td.setDueDate(catchNullPointerDueDate());
            td.setIsCompleted(false);

            // Return true because everything was set
            return true;
        }

        // Return false if items were not properly set
        return false;
    }

    // Post-conditions: Returns true if the description is between 1 and 256 characters
    public Boolean checkDescriptionLength() {
        return (taskDescriptionTextField.getText().length() > 1 && taskDescriptionTextField.getText().length() < 256);
    }

    // Post-conditions: Clears all the fields in the gui
    private void clearTextFields() {
        taskTitleTextField.clear();
        taskDescriptionTextField.clear();
        dueDatePicker.getEditor().clear();
        dueDatePicker.setValue(null);
    }

    // Post-conditions: Returns the value of the date picker. Returns " " if the date picker was null
    public String catchNullPointerDueDate() {
        try {

            // Return value taken from date picker
            return dueDatePicker.getValue().toString();
        } catch (NullPointerException e) {

            // No date was selected
            return " ";
        }
    }

    // Post-conditions: Returns the chosen due date
    @FXML
    public String dueDatePicked() {

        // Signify that a date was picked
        String output = catchNullPointerDueDate();

        // If a date was picked
        if (!output.equals(" ")) {

            // Display the date that was picked in the console
            System.out.print(output + " picked.\n");
        }

        // Return the date that was picked
        return output;
    }

    // Post-conditions: Switches scene to InvalidDateController.fxml and returns a string
    public String toInvalidDateController() {
        try {

            // Switch scene to Invalid date controller
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InvalidDateController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Invalid Date");
            stage.show();

            return "Scene switched to InvalidDateController.fxml\n";
        } catch(Exception e) {

            // Catch if the scene could not switch
            return "Scene switch unsuccessful.\n";
        }
    }

    // Post-conditions: returns true if the item was successfully removed
    @FXML
    public Boolean deleteTaskButtonPressed() {

        System.out.print("Delete task button pressed.\n");

        try {
            // Delete selected item from ObservableList
            toDoItems.remove(taskTable.getSelectionModel().getSelectedItem());

            // Remove selected item from gui table
            taskTable.refresh();
            taskTable.setItems(toDoItems);

            return true;
        } catch (Exception e) {

            // Return false if the item was not deleted
            return false;
        }
    }

    // Post-condition: Observable list is cleared
    @FXML
    public void clearListButtonPressed() {

        System.out.print("ClearListButton pressed.\n");

        // Clear all of the to do items
        toDoItems.clear();

        // Refresh and reset the table
        taskTable.refresh();
        taskTable.setItems(toDoItems);
    }

    // Post-conditions: Switches scene to the help controller
    @FXML
    public void helpButtonPressed() {

        String sceneSwitch = toHelpController();
        System.out.print(sceneSwitch);
    }

    // Post-conditions: Switches scene to the help controller
    public String toHelpController() {
        try {

            // Switch scene to help controller
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Help");
            stage.show();

            return "Scene switched to HelpController.fxml\n";
        } catch(Exception e) {

            // Catch if the scene was unable to switch
            return "Scene switch unsuccessful.\n";
        }
    }

    // Post-conditions: Signifies that the file menu was opened
    @FXML
    public void fileMenuButtonClicked() {
        // User clicks on file menu button
        // Drop down appears
        System.out.print("File Menu Opened.\n");
    }

    // Post-conditions: Opens file chooser to load items
    @FXML
    public void loadItemsButtonPressed() {

        // File chooser opens
        Window stage = fileMenuButton.getScene().getWindow();
        fileChooser.setTitle("Load To Do List");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt"));

        try {
            // User chooses file
            File file = fileChooser.showOpenDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile()); // save the chosen directory for the next time it opens

            // Clear ObservableList to load new ObservableList
            toDoItems.clear();

            // Chosen file is loaded
            loadItems(file);
        } catch (Exception ex) {
            System.out.print("Could not load file.\n");
        }

    }

    // Post-conditions: Observable list is filled with info from .txt file
    public void loadItems(File file) {
        // Create new ArrayList
        ArrayList<String> items = new ArrayList<>();

        try {
            // Get the file and make it readable
            File myFile = new File(file.getName());
            Scanner scanner = new Scanner(myFile);

            // Each line holds one object, add the entire object string to the ArrayList
            while (scanner.hasNextLine()) {
                items.add(scanner.nextLine());
            }

            // Parse the strings in the ArrayList to turn them into an Observable list
            addToObservableList(items);

        } catch (FileNotFoundException e) {

            // Check if the file was not found
            System.out.print("File not found.\n");
        }
    }

    // Post-conditions: Populates array list with data from loaded file
    public void addToObservableList(ArrayList<String> items) {

        // Iterate through every object string
        for (String item : items) {

            // Create a new ToDoList object
            ToDoList td = new ToDoList();

            // Parse the strings
            td = parseStrings(item, td);

            // Add the object the observable list
            toDoItems.add(td);
        }

    }

    // Post-conditions: Parses the items
    public ToDoList parseStrings(String item, ToDoList td) {

        // Create a new ToDoList object
        ToDoList tdl = new ToDoList();

        // Splits the string at every ^
        String[] splitString = item.split("\\^");

        // Sets the To Do List items
        tdl.dueDate = splitString[0];
        tdl.taskTitle = splitString[1];
        tdl.taskDescription = splitString[2];
        tdl.isCompleted.set(splitString[3].equals("BooleanProperty [value: true]"));

        // Return the to do list
        return tdl;
    }

    // Pre-condition: ObservableList is saved to a .txt file
    @FXML
    public void saveItemsButtonPressed(ActionEvent event) {
        // To Do List is added to the ListsController scene
        // Scene is not switched unless user presses return button

        // Open the file chooser
        Window stage = fileMenuButton.getScene().getWindow();
        fileChooser.setTitle("Save To DO List");
        fileChooser.setInitialFileName("mySaveFile");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt"));

        try {

            // Let user namem the file to save
            File file = fileChooser.showSaveDialog(stage);

            // Save the chosen directory for the next time it opens
            fileChooser.setInitialDirectory(file.getParentFile());

            // Save the file
            saveList(file);
        } catch (Exception ex) {

            // Ensure that the file can be saved
            System.out.print("File could not be saved.\n");
        }
    }

    // Post-conditions: Writes the object ot the file
    public void saveList(File file) {

        try(FileWriter writer = new FileWriter(file)) {

            // Add all the objects to the list with ^ to separate the items in each object
            for (ToDoList toDoItem : toDoItems) {
                writer.write(toDoItem.dueDate + "^");
                writer.write(toDoItem.taskTitle + "^");
                writer.write(toDoItem.taskDescription + "^");
                writer.write(toDoItem.isCompleted.toString() + "^");

                // Newline signifies a new object
                writer.write("\n");
            }

        } catch (IOException e) {

            // Check that the file exists
            System.out.print("File does not exist.\n");
        }
    }

    // Post-conditions: Signifies that the sort menu was clicked
    @FXML
    public void sortMenuButtonClicked() {
        // User clicks on sort menu button
        // Drop down appears
        System.out.print("Sorting menu opened.\n");
    }

    // Post-conditions: GUI displays all items
    @FXML
    public void viewAllTasksButtonPressed(ActionEvent event) {

        // Ensure that there is not already content in filtered list or the table
        filteredList.clear();
        taskTable.refresh();

        // Reset the table with the main ToDoList
        taskTable.setItems(toDoItems);
    }

    // Post-condition: Creates a list with only items whose isCompleted box is checked true
    public void filterList() {

        // For every item in the list of to do lists
        for (ToDoList toDoItem : toDoItems) {

            // Check if the item is marked complete
            if (isChecked(toDoItem)) {

                // Add it to the filtered list
                filteredList.add(toDoItem);
            }
        }
    }

    // Post-conditions: Returns state of check box
    public Boolean isChecked(ToDoList td) {
        return td.isCompleted();
    }

    // Post-conditions: GUI displays only completed items
    @FXML
    void viewCompleteTasksButtonPressed(ActionEvent event) {

        // Clears the filtered list in case anything is already in it
        filteredList.clear();

        // Fill filtered list with only items marked complete
        filterList();

        // Refresh and reset the table
        taskTable.refresh();
        taskTable.setItems(filteredList);
    }

    // Post-conditions: GUI displays only incomplete items
    @FXML
    void viewIncompleteTasksButtonPressed(ActionEvent event) {

        // Clear the filtered list in case anything is already in it
        filteredList.clear();

        // Add only incomplete items to the filtered lsit
        filterIncompleteList();

        // Refresh and reset the table
        taskTable.refresh();
        taskTable.setItems(filteredList);
    }

    // Post-conditions: Adds only incomplete items the filtered list
    public void filterIncompleteList() {

        // For every item in the list of to do lists
        for (ToDoList toDoItem : toDoItems) {

            // If it is not completed
            if (!isChecked(toDoItem)) {

                // Add it to the filtered list
                filteredList.add(toDoItem);
            }
        }
    }

    @FXML
    public void initialize() {

        assert fileMenuButton != null : "fx:id=\"fileMenuButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert saveItemsButton != null : "fx:id=\"saveItemsButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert loadItemsButton != null : "fx:id=\"loadItemsButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert sortMenuButton != null : "fx:id=\"sortMenuButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert viewAllTasksButton != null : "fx:id=\"viewAllTasksButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert viewCompleteTasksButton != null : "fx:id=\"viewCompleteTasksButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert viewIncompleteTasksButton != null : "fx:id=\"viewIncompleteTasksButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert deleteTaskButton != null : "fx:id=\"deleteTaskButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskTable != null : "fx:id=\"taskTable\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert markCompletedColumn != null : "fx:id=\"markCompletedColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskTitleColumn != null : "fx:id=\"taskTitleColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert descriptionColumn != null : "fx:id=\"descriptionColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert dueDateColumn != null : "fx:id=\"dueDateColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert addTaskButton != null : "fx:id=\"addTaskButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskTitleTextField != null : "fx:id=\"taskTitleTextField\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskDescriptionTextField != null : "fx:id=\"taskDescriptionTextField\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert dueDatePicker != null : "fx:id=\"dueDatePicker\" was not injected: check your FXML file 'ToDoListController.fxml'.";


        // Set the initial directory of the file chooser to be the user's directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));


        // Set date picker to display yyyy-MM-dd format
        dueDatePicker.setConverter(new StringConverter<LocalDate>() {

            private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });

        // Set the items in the task table
        taskTable.setItems(toDoItems);

        // Set markCompletedColumn to populate with check boxes that are binded to the program
        markCompletedColumn.setCellValueFactory(param -> param.getValue().completedProperty());
        markCompletedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(markCompletedColumn));

        // Set taskTitle column to editable text fields
        taskTitleColumn.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("taskTitle"));
        taskTitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        taskTitleColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ToDoList, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ToDoList, String> event) {
                ToDoList list = event.getRowValue();
                list.setTaskTitle(event.getNewValue());
            }
        });

        // Refresh and reset the table
        taskTable.refresh();
        taskTable.setItems(toDoItems);

        // Set descriptionColumn to editable text fields
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("taskDescription"));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ToDoList, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ToDoList, String> event) {
                ToDoList tdl = event.getRowValue();
                tdl.setTaskDescription(event.getNewValue());
            }
        });

        // Refresh and reset the table
        taskTable.refresh();
        taskTable.setItems(toDoItems);

        // dueDateColumn set with editable text fields that have a specific format
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("dueDate"));
        dueDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dueDateColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ToDoList, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ToDoList, String> event) {
                ToDoList tdl = event.getRowValue();

                String current = tdl.dueDate;
                String newDate = event.getNewValue();

                // Ensure that the user's date is in the correct format
                String regex = "\\d\\d\\d\\d-\\d\\d-\\d\\d";
                Pattern pt = Pattern.compile(regex);
                Matcher mt = pt.matcher(newDate);

                boolean result = mt.matches();

                if (!result) {
                    System.out.print("Invalid format.\n");
                    tdl.setDueDate(current);

                    toInvalidDateController();
                } else {
                    System.out.print("valid format");
                    tdl.setDueDate(newDate);
                }

                taskTable.refresh();
                taskTable.setItems(toDoItems);

            }
        });
    }
}

