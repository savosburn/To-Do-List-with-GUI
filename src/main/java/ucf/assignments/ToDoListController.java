/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Savannah Osburn
 */

package ucf.assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;

public class ToDoListController {

    @FXML private Button helpButton;
    @FXML private MenuButton fileMenuButton;
    @FXML private MenuItem saveItemsButton;
    @FXML private MenuItem loadItemsButton;
    @FXML private MenuButton sortMenuButton;
    @FXML private MenuItem viewAllTasksButton;
    @FXML private MenuItem viewCompleteTasksButton;
    @FXML private MenuItem viewIncompleteTasksButton;
    @FXML private TableView<ToDoList> taskTable;
    @FXML private TableColumn<ToDoList, Boolean> markCompletedColumn;
    @FXML private TableColumn<ToDoList, String> taskTitleColumn;
    @FXML private TableColumn<ToDoList, String> descriptionColumn;
    @FXML private TableColumn<ToDoList, String> dueDateColumn;
    @FXML private Button addTaskButton;
    @FXML private Button deleteTaskButton;
    @FXML private TextField taskTitleTextField;
    @FXML private TextField taskDescriptionTextField;
    @FXML private DatePicker dueDatePicker;
    @FXML private Button quickSortButton;

    FileChooser fileChooser = new FileChooser();

    ObservableList<ToDoList> toDoItems = FXCollections.observableArrayList();
    ObservableList<ToDoList> filteredList = FXCollections.observableArrayList();

    // Bonus credit: Sort by due date method
    @FXML
    public void quickSortButtonPressed() {

        ObservableList<ToDoList> sorted = toDoItems;

        taskTable.refresh();
        taskTable.setItems(sort(sorted));
    }

    // Post-conditions: Sorts an observable list
    public ObservableList<ToDoList> sort(ObservableList<ToDoList> toDo) {
        Comparator<ToDoList> dateComparator = Comparator.comparing(ToDoList :: getDueDate);
        toDo.sort(dateComparator);
        return toDo;
    }

    // Post-conditions: Adds new ToDoList object to the Observable List
    @FXML
    public void addTaskButtonPressed() {
        // Create ToDoList object
        System.out.print("Add task button pressed.\n");

        // Get what's in the text fields
        String title = taskTitleTextField.getText();
        String description = taskDescriptionTextField.getText();
        String dueDate = catchNullPointerDueDate();

        // If the description is the correct length
        if (checkDescriptionLength(description.length())) {

            // Add the input to the ObservableList
            addItems(dueDate, title, description, false);
        } else {

            // Else, output an error screen
            String sceneChange = toInvalidDescriptionController();
            System.out.print(sceneChange);
        }

        // Clear the text fields
        clearTextFields();
    }

    // Post-conditions: Adds items to the ObservableList of items
    public ObservableList<ToDoList> addItems(String date, String title, String description, Boolean isComplete) {
        // Create ToDoList object
        ToDoList tdl = new ToDoList();

        // Set the constructors
        tdl.setDueDate(date);
        tdl.setTaskTitle(title);
        tdl.setTaskDescription(description);
        tdl.isCompleted.set(isComplete);

        // Add object to ObservableList
        toDoItems.add(tdl);

        System.out.printf("%s added to To Do List.\n", tdl.taskTitle);
        return toDoItems;
    }

    // Post-conditions: Switches scene to InvalidDateController.fxml and returns a string
    public String toInvalidDescriptionController() {
        try {
            // Open new stage
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InvalidDescriptionLengthController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Invalid Description");
            stage.show();

            return "Scene switched to InvalidDescriptionController.fxml\n";
        } catch(Exception e) {

            // Catch if the stage could not be opened
            return "Scene switch unsuccessful.\n";
        }
    }

    // Post-conditions: Returns true if the description is between 1 and 256 characters
    public Boolean checkDescriptionLength(int numChars) {
        return (numChars > 1 && numChars < 256);
    }

    // Post-conditions: Clears all the fields in the gui
    private void clearTextFields() {
        taskTitleTextField.clear();
        taskDescriptionTextField.clear();
        dueDatePicker.getEditor().clear();
        dueDatePicker.setValue(null);
    }

    // Post-conditions: Returns the value of the date picker. Returns " " if the date picker was null
    private String catchNullPointerDueDate() {
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
    public void toInvalidDateController() {
        try {
            // Switch scene to Invalid date controller
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InvalidDateController.fxml")));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Invalid Date");
            stage.show();

            System.out.print("Scene switched to InvalidDateController.fxml\n");
        } catch(Exception e) {

            // Catch if the scene could not switch
            System.out.print("Scene switch unsuccessful.\n");
        }
    }

    // Post-conditions: returns true if the item was successfully removed
    @FXML
    public Boolean deleteTaskButtonPressed() {
        System.out.print("Delete task button pressed.\n");

        try {
            // Delete selected item from ObservableList
            toDoItems = deleteTask(taskTable.getSelectionModel().getSelectedItem(), toDoItems);

            // Remove selected item from gui table
            taskTable.refresh();
            taskTable.setItems(toDoItems);

            return true;
        } catch (Exception e) {

            // Return false if the item was not deleted
            return false;
        }
    }

    // Post-conditions: Resets all items of to do list to null and returns the empty object
    public ToDoList deleteAllItems(ToDoList td) {
        td.setTaskTitle(null);
        td.setTaskDescription(null);
        td.setDueDate(null);
        td.setIsCompleted(false);

        return td;
    }

    // Post-conditions: Removes task from ObservableList
    public ObservableList<ToDoList> deleteTask(ToDoList td, ObservableList<ToDoList> tdl) {
        System.out.printf("%s deleted from To Do List.\n", td.taskTitle);

        // Delete all the items in the task, then remove task from list
        td = deleteAllItems(td);
        tdl.remove(td);

        return tdl;
    }

    // Post-condition: Observable list is cleared
    @FXML
    public void clearListButtonPressed() {
        System.out.print("ClearListButton pressed.\n");

        // Clear all of the to do items
        ObservableList<ToDoList> clearedList = clearEntireList(toDoItems);

        // Refresh and reset the table
        taskTable.refresh();
        taskTable.setItems(clearedList);
    }

    // Post-conditions: Returns and clears an ObservableList
    public ObservableList<ToDoList> clearEntireList(ObservableList<ToDoList> list) {
        list.clear();
        return list;
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
            ArrayList<String> fileStrings = loadFileStrings(file);
            ObservableList<ToDoList> loadedFile = addToObservableList(fileStrings);

            // Refresh and reset the table
            taskTable.refresh();
            taskTable.setItems(loadedFile);
        } catch (Exception ex) {

            // Check if file could be loaded
            System.out.print("Could not load file.\n");
        }
    }

    // Post-conditions: Returns an ArrayList of strings found in the file
    public ArrayList<String> loadFileStrings(File file) {
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

            return items;
        } catch (FileNotFoundException e) {

            // Check if the file was not found
            System.out.print("File not found.\n");

            return null;
        }
    }

    // Post-condition: Parses the strings in the ArrayList and returns them as an ObservableList
    public ObservableList<ToDoList> addToObservableList(ArrayList<String> items) {
        ObservableList<ToDoList> test = FXCollections.observableArrayList();

        // Iterate through every object string
        for (String item : items) {

            // Parse each string
            String[] parsedString = parseStrings(item);

            // Add the string to the ObservableList
            test = addItems(parsedString[0], parsedString[1], parsedString[2], isBooleanPropertyTrue(parsedString[3]));
        }

        return test;
    }

    // Post-conditions: Checks if the Boolean property is true or false
    public Boolean isBooleanPropertyTrue(String booleanProperty) {
        return booleanProperty.equals("BooleanProperty [value: true]");
    }

    // Post-conditions: Parses all the strings at ^
    public String[] parseStrings(String item) {
        return item.split("\\^");
    }

    // Pre-condition: ObservableList is saved to a .txt file
    @FXML
    public void saveItemsButtonPressed() {
        // Open the file chooser
        Window stage = fileMenuButton.getScene().getWindow();

        fileChooser.setTitle("Save To DO List");
        fileChooser.setInitialFileName("mySaveFile");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt"));

        try {

            // Let user name the file to save
            File file = fileChooser.showSaveDialog(stage);

            // Save the chosen directory for the next time it opens
            fileChooser.setInitialDirectory(file.getParentFile());

            // Save the file
            //saveList(file);
            String saveFileOutput = saveList(file, toDoItems);
            System.out.print(saveFileOutput);
        } catch (Exception ex) {

            // Ensure that the file can be saved
            System.out.print("File could not be saved.\n");
        }
    }

    // Post-conditions: Writes the object ot the file
    public String saveList(File file, ObservableList<ToDoList> lists) {
        StringBuilder outputString = new StringBuilder(" ");

        try(FileWriter writer = new FileWriter(file)) {

            // Add all the objects to the list with ^ to separate the items in each object
            for (ToDoList toDoItem : lists) {

                writer.write(toDoItem.dueDate + "^");
                writer.write(toDoItem.taskTitle + "^");
                writer.write(toDoItem.taskDescription + "^");
                writer.write(toDoItem.isCompleted.toString() + "^");

                // Newline signifies a new object
                writer.write("\n");

                // Create strings for testing
                if (outputString.toString().equals(" ")) {
                    outputString = new StringBuilder(String.format("%s^", toDoItem.dueDate));

                } else {

                    outputString.append(String.format("%s^", toDoItem.dueDate));
                }
                outputString.append(String.format("%s^", toDoItem.taskTitle));
                outputString.append(String.format("%s^", toDoItem.taskDescription));
                outputString.append(String.format("%s^", toDoItem.isCompleted.toString()));
                outputString.append("\n");
            }

            return outputString.toString();

        } catch (IOException e) {

            // Check that the file exists
            return "File does not exist.\n";
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
    public void viewAllTasksButtonPressed() {

        // Ensure that there is not already content in filtered list or the table
        filteredList.clear();
        taskTable.refresh();

        // Get all of the tasks
        ObservableList<ToDoList> allTasks = getAllTasks(toDoItems);

        // Reset the table with the main ToDoList
        taskTable.setItems(allTasks);
    }

    // Post-conditions: Returns a list with all of the items in it
    public ObservableList<ToDoList> getAllTasks(ObservableList<ToDoList> toDo) {
        return toDo;
    }

    // Post-conditions: Returns an ObservableList with items that were marked complete
    public ObservableList<ToDoList> filterList(ObservableList<ToDoList> toDoItemsList) {

        // For every item in the To Do List
        for (ToDoList toDoList : toDoItemsList) {

            // If it is checked complete
            if (isChecked(toDoList)) {

                // Add it to the list of completed lists
                filteredList.add(toDoList);
            }
        }

        // Return the completed lists
        return filteredList;
    }

    // Post-conditions: Returns state of check box
    public Boolean isChecked(ToDoList td) {
        return td.isCompleted();
    }

    // Post-conditions: GUI displays only completed items
    @FXML
    void viewCompleteTasksButtonPressed() {

        // Clears the filtered list in case anything is already in it
        filteredList.clear();

        // Fill filtered list with only items marked complete
        ObservableList<ToDoList> filtered = filterList(toDoItems);

        // Refresh and reset the table
        taskTable.refresh();
        taskTable.setItems(filtered);
    }

    // Post-conditions: GUI displays only incomplete items
    @FXML
    void viewIncompleteTasksButtonPressed() {

        // Clear the filtered list in case anything is already in it
        filteredList.clear();

        // Add only incomplete items to the filtered lsit
        ObservableList<ToDoList> filtered = filterIncompleteList(toDoItems);

        // Refresh and reset the table
        taskTable.refresh();
        taskTable.setItems(filtered);
    }

    // Post-conditions: Adds only incomplete items the filtered list
    public ObservableList<ToDoList> filterIncompleteList(ObservableList<ToDoList> toDoItemsList) {

        // For every item in the To Do List
        for (ToDoList toDoList : toDoItemsList) {

            // If it is checked complete
            if (!isChecked(toDoList)) {

                // Add it to the list of completed lists
                filteredList.add(toDoList);
            }
        }

        // Return the completed lists
        return filteredList;
    }

    @FXML
    public void initialize() {

        assert helpButton != null : "fx:id=\"helpButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert fileMenuButton != null : "fx:id=\"fileMenuButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert saveItemsButton != null : "fx:id=\"saveItemsButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert loadItemsButton != null : "fx:id=\"loadItemsButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert sortMenuButton != null : "fx:id=\"sortMenuButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert viewAllTasksButton != null : "fx:id=\"viewAllTasksButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert viewCompleteTasksButton != null : "fx:id=\"viewCompleteTasksButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert viewIncompleteTasksButton != null : "fx:id=\"viewIncompleteTasksButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskTable != null : "fx:id=\"taskTable\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert markCompletedColumn != null : "fx:id=\"markCompletedColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskTitleColumn != null : "fx:id=\"taskTitleColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert descriptionColumn != null : "fx:id=\"descriptionColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert dueDateColumn != null : "fx:id=\"dueDateColumn\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert addTaskButton != null : "fx:id=\"addTaskButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskTitleTextField != null : "fx:id=\"taskTitleTextField\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert taskDescriptionTextField != null : "fx:id=\"taskDescriptionTextField\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert dueDatePicker != null : "fx:id=\"dueDatePicker\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert deleteTaskButton != null : "fx:id=\"deleteTaskButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";
        assert quickSortButton != null : "fx:id=\"quickSortButton\" was not injected: check your FXML file 'ToDoListController.fxml'.";

        // Set the initial directory of the file chooser to be the user's directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        // Set date picker to display yyyy-MM-dd format
        dueDatePicker.setConverter(new StringConverter<>() {

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
        taskTitleColumn.setCellValueFactory(new PropertyValueFactory<>("taskTitle"));
        taskTitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        taskTitleColumn.setOnEditCommit(event -> {
            ToDoList list = event.getRowValue();
            list.setTaskTitle(event.getNewValue());
        });

        // Refresh and reset the table
        taskTable.refresh();
        taskTable.setItems(toDoItems);

        // Set descriptionColumn to editable text fields
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("taskDescription"));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setOnEditCommit(event -> {
            ToDoList tdl = event.getRowValue();
            tdl.setTaskDescription(event.getNewValue());
        });

        // Refresh and reset the table
        taskTable.refresh();
        taskTable.setItems(toDoItems);

        // dueDateColumn set with editable text fields that have a specific format
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        dueDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dueDateColumn.setOnEditCommit(event -> {
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
        });
    }
}

