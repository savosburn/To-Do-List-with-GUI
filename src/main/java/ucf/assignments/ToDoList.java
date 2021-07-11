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

    // Getters and setters
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
}
