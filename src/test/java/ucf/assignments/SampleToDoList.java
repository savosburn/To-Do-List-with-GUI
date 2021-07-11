package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SampleToDoList {

    public ObservableList<ToDoList> tdl () {

        ObservableList<ToDoList> list = FXCollections.observableArrayList();

        list.add(toDoList());

        return list;
    }

    public ToDoList toDoList() {
        ToDoList tdl = new ToDoList();

        tdl.setDueDate("2021-12-17");
        tdl.setTaskTitle("Take out trash");
        tdl.setTaskDescription("Put the trash by the street.");
        tdl.setIsCompleted(true);

        return tdl;
    }
}
