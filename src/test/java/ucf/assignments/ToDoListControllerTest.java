/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Savannah Osburn
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListControllerTest {

    @Test
    public void addItemsTest() {
        // Create objects
        ToDoListController tdlc = new ToDoListController();
        ObservableList<ToDoList> lists = FXCollections.observableArrayList();
        ToDoList toDo = new ToDoList();

        // Get expected output
        toDo.setDueDate("2021-07-11");
        toDo.setTaskTitle("Finish project.");
        toDo.setTaskDescription("Code the rest of the project.");
        toDo.setIsCompleted(true);
        lists.add(toDo);

        // Compare with actual output
        for (int i = 0; i < lists.size(); i++ ) {
            assertEquals(lists.get(i).getDueDate(), tdlc.addItems("2021-07-11", "Finish project.", "Code the rest of the project.", true).get(i).getDueDate());
            assertEquals(lists.get(i).getTaskTitle(), tdlc.addItems("2021-07-11", "Finish project.", "Code the rest of the project.", true).get(i).getTaskTitle());
            assertEquals(lists.get(i).getTaskDescription(), tdlc.addItems("2021-07-11", "Finish project.", "Code the rest of the project.", true).get(i).getTaskDescription());
            assertEquals(lists.get(i).isCompleted(), tdlc.addItems("2021-07-11", "Finish project.", "Code the rest of the project.", true).get(i).isCompleted());
        }
    }

    @Test
    public void checkDescriptionLengthTest() {
        // Create a ToDoListController object
        ToDoListController tdlc = new ToDoListController();

        // Check different values
        assertFalse(tdlc.checkDescriptionLength(Integer.MIN_VALUE));
        assertFalse(tdlc.checkDescriptionLength(0));
        assertFalse(tdlc.checkDescriptionLength(1));

        for (int i = 2; i > 1 && i < 256; i++) {
            assertTrue(tdlc.checkDescriptionLength(i));
        }

        assertFalse(tdlc.checkDescriptionLength(256));
        assertFalse(tdlc.checkDescriptionLength(Integer.MAX_VALUE));
    }

    @Test
    public void isBooleanPropertyTrue() {
        // Create a ToDoListController object
        ToDoListController tdlc = new ToDoListController();

        // Test different values: only one value should return true
        assertTrue(tdlc.isBooleanPropertyTrue("BooleanProperty [value: true]"));
        assertFalse(tdlc.isBooleanPropertyTrue("BooleanProperty [value: false]"));
        assertFalse(tdlc.isBooleanPropertyTrue("true"));
        assertFalse(tdlc.isBooleanPropertyTrue("false"));
    }

    @Test
    public void canHoldOverOneHundredToDoLists() {
        // Create an ObservableList of tasks
        ObservableList<ToDoList> tasks = FXCollections.observableArrayList();

        // Add 101 items into an ObservableList
        for (int i = 0; i < 101; i++) {

            ToDoList toDo = new ToDoList();

            toDo.setDueDate("2021-07-11");
            toDo.setTaskTitle("Finish project.");
            toDo.setTaskDescription("Code the rest of the project.");
            toDo.setIsCompleted(true);
            tasks.add(toDo);
        }

        // Check that the ObservableList properly holds 101 items
        assertEquals(101, tasks.size());
    }

    @Test
    public void deleteAllItemsTest(){
        // Create a new ToDoListController
        ToDoListController tdlc = new ToDoListController();
        ToDoList toDo = new ToDoList();

        // Create a test To Do List
        toDo.setDueDate("2021-07-11");
        toDo.setTaskTitle("Finish project.");
        toDo.setTaskDescription("Code the rest of the project.");
        toDo.setIsCompleted(true);

        // Delete everything from the To Do List object and assert that they are null or false
        assertNull(tdlc.deleteAllItems(toDo).getTaskTitle());
        assertNull(tdlc.deleteAllItems(toDo).getTaskDescription());
        assertNull(tdlc.deleteAllItems(toDo).getDueDate());
        assertFalse(tdlc.deleteAllItems(toDo).isCompleted());
    }

    @Test
    public void deleteOneTask() {
        // Create objects
        ToDoListController toDoListController = new ToDoListController();
        ObservableList<ToDoList> expected = FXCollections.observableArrayList();
        ObservableList<ToDoList> tasks = FXCollections.observableArrayList();

        // Add information to To Do List
        ToDoList toDo = new ToDoList();
        toDo.setDueDate("2021-07-11");
        toDo.setTaskTitle("Finish project.");
        toDo.setTaskDescription("Code the rest of the project.");
        toDo.setIsCompleted(true);
        tasks.add(toDo);
        expected.add(toDo);

        ToDoList tdl = new ToDoList();
        tdl.setDueDate("2020-07-11");
        tdl.setTaskTitle("Finish code.");
        tdl.setTaskDescription("Finish coding project");
        tdl.setIsCompleted(false);
        tasks.add(tdl);

        // Remove a task with the delete task function
        ObservableList<ToDoList> actual = toDoListController.deleteTask(tdl, tasks);

        // Assert that the expected and actual lists are equal
        for (int i = 0; i < expected.size(); i++ ) {
            assertEquals(expected.get(i).getTaskTitle(), actual.get(i).getTaskTitle());
            assertEquals(expected.get(i).getTaskDescription(), actual.get(i).getTaskDescription());
            assertEquals(expected.get(i).getDueDate(), actual.get(i).getDueDate());
            assertEquals(expected.get(i).isCompleted(), actual.get(i).isCompleted());
        }
    }

    @Test
    public void loadFileStringsTest() {

        ToDoListController tdlc = new ToDoListController();
        File file = new File("ToDoListInput.txt");
        ArrayList<String> items = new ArrayList<>();
        ArrayList<String> actual = new ArrayList<>();

        items.add("2021-07-03^Write pseudocode^Make sure that the pseudocode accurately describes the coding process.^BooleanProperty [value: true]^");
        items.add("2021-07-03^Create puml diagram^Keep updating this diagram as the code is updated^BooleanProperty [value: true]^");
        items.add("2021-07-03^Use SceneBuilder^Create a scene for every scene necessary.^BooleanProperty [value: true]^");
        items.add("2021-07-10^Code^Code the To Do List^BooleanProperty [value: true]^");
        items.add("2021-07-11^Update pseudocode^Make pseudocode reflect the new code^BooleanProperty [value: true]^");
        items.add("2021-07-11^Update puml diagrams^Make sure the diagrams accurately reflect the new code^BooleanProperty [value: true]^");
        items.add("2021-07-11^Test classes^Test all of the methods in your classes and try not to cry^BooleanProperty [value: false]^");
        items.add("2021-07-11^Copyright^Ensure that all the copyrights are in the correct place^BooleanProperty [value: false]^");
        items.add("2021-07-11^Turn in project^Submit project to github and cry^BooleanProperty [value: false]^");

        actual = tdlc.loadFileStrings(file);

        for (int i = 0; i < items.size(); i++) {
            assertEquals(items.get(i), actual.get(i));
        }
    }

    @Test
    public void addToObservableListTest() {

        // Create objects and lists
        ToDoListController tdlc = new ToDoListController();
        ObservableList<ToDoList> tasks = FXCollections.observableArrayList();
        ObservableList<ToDoList> expected = FXCollections.observableArrayList();
        ObservableList<ToDoList> actual = FXCollections.observableArrayList();

        // Create test ArrayList
        ArrayList<String> items = new ArrayList<>();
        items.add("2021-07-03^Write pseudocode^Make sure that the pseudocode accurately describes the coding process.^BooleanProperty [value: true]^");
        items.add("2021-07-03^Create puml diagram^Keep updating this diagram as the code is updated^BooleanProperty [value: true]^");

        // Fill in test observable lists with test data
        ToDoList toDo = new ToDoList();
        toDo.setDueDate("2021-07-03");
        toDo.setTaskTitle("Write pseudocode");
        toDo.setTaskDescription("Make sure that the pseudocode accurately describes the coding process.");
        toDo.setIsCompleted(true);
        tasks.add(toDo);
        expected.add(toDo);

        ToDoList tdl = new ToDoList();
        tdl.setDueDate("2021-07-03");
        tdl.setTaskTitle("Create puml diagram");
        tdl.setTaskDescription("Keep updating this diagram as the code is updated");
        tdl.setIsCompleted(true);
        tasks.add(tdl);

        // Get the actual observable list when it is passed through the method
        actual = tdlc.addToObservableList(items);

        // Assert that the two ObservableLists are still equal
        for (int i = 0; i < expected.size(); i++ ) {
            assertEquals(expected.get(i).getTaskTitle(), actual.get(i).getTaskTitle());
            assertEquals(expected.get(i).getTaskDescription(), actual.get(i).getTaskDescription());
            assertEquals(expected.get(i).getDueDate(), actual.get(i).getDueDate());
            assertEquals(expected.get(i).isCompleted(), actual.get(i).isCompleted());
        }
    }

    @Test
    public void parseStringsTest() {
        // Create a ToDoListController object
        ToDoListController tdlc = new ToDoListController();

        // Get the expected and actual string arrays
        String[] expected = {"2021-07-10", "Code", "Code the To Do List", "BooleanProperty [value: true]","\n"};
        String[] actual = tdlc.parseStrings("2021-07-10^Code^Code the To Do List^BooleanProperty [value: true]^\n");

        // Ensure that the contents of each string array are the same
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void getAllTasksTest() {
        // Create objects and lists
        ToDoListController toDoListController = new ToDoListController();
        ObservableList<ToDoList> tasks = FXCollections.observableArrayList();
        ObservableList<ToDoList> expected = FXCollections.observableArrayList();

        // Fill the lists with test data
        ToDoList toDo = new ToDoList();
        toDo.setDueDate("2021-07-11");
        toDo.setTaskTitle("Finish project.");
        toDo.setTaskDescription("Code the rest of the project.");
        toDo.setIsCompleted(true);
        tasks.add(toDo);
        expected.add(toDo);

        ToDoList tdl = new ToDoList();
        tdl.setDueDate("2020-07-11");
        tdl.setTaskTitle("Finish code.");
        tdl.setTaskDescription("Finish coding project");
        tdl.setIsCompleted(false);
        tasks.add(tdl);
        expected.add(tdl);

        // Get all of the tasks with the getAllTasks method
        ObservableList<ToDoList> actual = toDoListController.getAllTasks(tasks);

        // Assert that the contents of the expected and actual lists are still equal
        for (int i = 0; i < expected.size(); i++ ) {
            assertEquals(expected.get(i).getTaskTitle(), actual.get(i).getTaskTitle());
            assertEquals(expected.get(i).getTaskDescription(), actual.get(i).getTaskDescription());
            assertEquals(expected.get(i).getDueDate(), actual.get(i).getDueDate());
            assertEquals(expected.get(i).isCompleted(), actual.get(i).isCompleted());
        }
    }

    @Test
    public void saveList() {

        // Create object, file, and list
        ToDoListController tdlc = new ToDoListController();
        File file = new File("JUnitTest.txt");
        ObservableList<ToDoList> list = FXCollections.observableArrayList();

        // Populate ObservableList with testData
        ToDoList toDo = new ToDoList();
        toDo.setDueDate("2021-07-21");
        toDo.setTaskTitle("finish program");
        toDo.setTaskDescription("cry");
        toDo.setIsCompleted(false);
        list.add(toDo);

        ToDoList tdl = new ToDoList();
        tdl.setDueDate("2021-07-28");
        tdl.setTaskTitle("eat cake");
        tdl.setTaskDescription("celebrate");
        tdl.setIsCompleted(true);
        list.add(tdl);

        ToDoList newToDo = new ToDoList();
        newToDo.setDueDate("2021-07-27");
        newToDo.setTaskTitle("go to orlando");
        newToDo.setTaskDescription("have fun");
        newToDo.setIsCompleted(false);
        list.add(newToDo);

        // Get the expected string
        String expected = """
                2021-07-21^finish program^cry^BooleanProperty [value: false]^
                2021-07-28^eat cake^celebrate^BooleanProperty [value: true]^
                2021-07-27^go to orlando^have fun^BooleanProperty [value: false]^
                """;

        // Get the actual string
        String actual = tdlc.saveList(file, list);

        // Assert that the two are equal
        assertEquals(expected, actual);
    }

    @Test
    public void filterListTest() {
        // Create objects and lists
        ToDoListController toDoListController = new ToDoListController();
        ObservableList<ToDoList> tasks = FXCollections.observableArrayList();
        ObservableList<ToDoList> expected = FXCollections.observableArrayList();

        // Populate lists with test values
        ToDoList toDo = new ToDoList();
        toDo.setDueDate("2021-07-11");
        toDo.setTaskTitle("Finish project.");
        toDo.setTaskDescription("Code the rest of the project.");
        toDo.setIsCompleted(true);
        tasks.add(toDo);
        expected.add(toDo);

        ToDoList tdl = new ToDoList();
        tdl.setDueDate("2020-07-11");
        tdl.setTaskTitle("Finish code.");
        tdl.setTaskDescription("Finish coding project");
        tdl.setIsCompleted(false);
        tasks.add(tdl);

        // Get the actual list that keeps only complete items
        ObservableList<ToDoList> actual = toDoListController.filterList(tasks);

        // Assert that the contents of the expected and actual lists are still equal
        for (int i = 0; i < expected.size(); i++ ) {
            assertEquals(expected.get(i).getTaskTitle(), actual.get(i).getTaskTitle());
            assertEquals(expected.get(i).getTaskDescription(), actual.get(i).getTaskDescription());
            assertEquals(expected.get(i).getDueDate(), actual.get(i).getDueDate());
            assertEquals(expected.get(i).isCompleted(), actual.get(i).isCompleted());
        }
    }

    @Test
    public void filterIncompleteListTest() {

        // Create lists and objects
        ToDoListController toDoListController = new ToDoListController();
        ObservableList<ToDoList> tasks = FXCollections.observableArrayList();
        ObservableList<ToDoList> expected = FXCollections.observableArrayList();

        // Populate lists with test data
        ToDoList toDo = new ToDoList();
        toDo.setDueDate("2021-07-11");
        toDo.setTaskTitle("Finish project.");
        toDo.setTaskDescription("Code the rest of the project.");
        toDo.setIsCompleted(true);
        tasks.add(toDo);

        ToDoList tdl = new ToDoList();
        tdl.setDueDate("2020-07-11");
        tdl.setTaskTitle("Finish code.");
        tdl.setTaskDescription("Finish coding project");
        tdl.setIsCompleted(false);
        tasks.add(tdl);
        expected.add(tdl);

        // Get the actual list that keeps only incomplete items
        ObservableList<ToDoList> actual = toDoListController.filterIncompleteList(tasks);

        // Assert that the contents of the expected and actual lists are still equal
        for (int i = 0; i < expected.size(); i++ ) {
            assertEquals(expected.get(i).getTaskTitle(), actual.get(i).getTaskTitle());
            assertEquals(expected.get(i).getTaskDescription(), actual.get(i).getTaskDescription());
            assertEquals(expected.get(i).getDueDate(), actual.get(i).getDueDate());
            assertEquals(expected.get(i).isCompleted(), actual.get(i).isCompleted());
        }
    }

    @Test
    public void clearListTest() {

        // Create lists and objects
        ToDoListController toDoListController = new ToDoListController();
        ObservableList<ToDoList> tasks = FXCollections.observableArrayList();
        ObservableList<ToDoList> expected = FXCollections.observableArrayList();

        // Populate lists with test data
        ToDoList toDo = new ToDoList();
        toDo.setDueDate("2021-07-11");
        toDo.setTaskTitle("Finish project.");
        toDo.setTaskDescription("Code the rest of the project.");
        toDo.setIsCompleted(true);
        tasks.add(toDo);

        ToDoList tdl = new ToDoList();
        tdl.setDueDate("2020-07-11");
        tdl.setTaskTitle("Finish code.");
        tdl.setTaskDescription("Finish coding project");
        tdl.setIsCompleted(false);
        tasks.add(tdl);

        // Clear all the items from the list
        ObservableList<ToDoList> actual = toDoListController.clearEntireList(tasks);

        // Assert that the contents of the expected and actual lists are still equal
        for (int i = 0; i < expected.size(); i++ ) {
            assertEquals(expected.get(i).getTaskTitle(), actual.get(i).getTaskTitle());
            assertEquals(expected.get(i).getTaskDescription(), actual.get(i).getTaskDescription());
            assertEquals(expected.get(i).getDueDate(), actual.get(i).getDueDate());
            assertEquals(expected.get(i).isCompleted(), actual.get(i).isCompleted());
        }
    }
}