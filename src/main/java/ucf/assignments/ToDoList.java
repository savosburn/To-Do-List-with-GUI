/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Savannah Osburn
 */

package ucf.assignments;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ToDoList {

    public BooleanProperty isCompleted = new SimpleBooleanProperty(false);
    String taskTitle;
    String taskDescription;
    String dueDate;


    public ToDoList(String taskTitle, String taskDescription, String dueDate, Boolean isCompleted) {

        this.isCompleted = new SimpleBooleanProperty(isCompleted);
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.dueDate = dueDate;
    }

    public ToDoList() {

    }

    public BooleanProperty completedProperty() {
        return isCompleted;
    }

    public boolean isCompleted() {
        return this.isCompleted.get();
    }

    public void setIsCompleted(boolean value) {
        this.isCompleted.set(value);
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        //this.taskTitle = taskTitle;
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }



    /*
    private final SimpleStringProperty isCompleted;
    private final SimpleStringProperty taskTitle;
    private final SimpleStringProperty taskDescription;
    private final SimpleStringProperty dueDate;

    public ToDoList(String isCompleted, String taskTitle, String taskDescription, String dueDate) {
        this.isCompleted = new SimpleStringProperty(isCompleted);
        this.taskTitle = new SimpleStringProperty(taskTitle);
        this.taskDescription = new SimpleStringProperty(taskDescription);
        this.dueDate = new SimpleStringProperty(dueDate);
    }

    public String getIsCompleted() {
        return isCompleted.get();
    }

    public void setIsCompleted(String completed) {
        isCompleted.set(completed);
    }

    public String getTaskTitle() {
        return taskTitle.get();
    }

    public void setTaskTitle(String title) {
        taskTitle.set(title);
    }

    public String getTaskDescription() {
        return taskDescription.get();
    }

    public void setTaskDescription(String description) {
        taskDescription.set(description);
    }

    public String getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(String date) {
        dueDate.set(date);
    }




    /*
    public boolean isIsCompleted() {
        return isCompleted.get();
    }

    /*
    public SimpleBooleanProperty isCompletedProperty() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted.set(isCompleted);
    }

    public String getTaskTitle() {
        return taskTitle.get();
    }


    public SimpleStringProperty taskTitleProperty() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle.set(taskTitle);
    }

    public String getTaskDescription() {
        return taskDescription.get();
    }


    public SimpleStringProperty taskDescriptionProperty() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription.set(taskDescription);
    }


    public SimpleDateFormat getDueDate() {
        return dueDate;
    }*/




    // This definitely shouldn't be strings
    //      determine proper type later

    /*
    public ArrayList<String> tasks;

    // Figure out proper return types for all of these
    public String addTask(String task) {

        // Get the task
        // Add it to the arraylist of tasks
        // Return a string of the task that was added for testing
        //      or return the arrayList?

        return null; // change return type later
    }

    public String deleteTask(String task) {

        // Get the task
        // Remove it from the arrayList of tasks
        // Return a string of the task or the new array list
        //      for testing

        return null; // change return type later
    }


    public String displayTasks() {

        // Return a string of all the tasks currently in the arraylist

        return null; // change later
    }*/

}
