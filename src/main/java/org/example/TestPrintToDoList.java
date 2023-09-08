package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
public class TestPrintToDoList
{
    @Test
    public void testPrintAllTasksNoTasks()
    {
        PrintToDoList printer = new PrintToDoList();
        ToDoList toDoList = new ToDoList();

        String expected = "Нету никаких задач";
        assertEquals(expected, printOutput(printer, toDoList));
    }
    @Test
    public void testPrintAllTasksWithTasks()
    {
        PrintToDoList printer = new PrintToDoList();

        var task = new Task(1, "Caption", "description", 10, "2023-09-08", "done", "2023-09-08");

        ToDoList toDoList = new ToDoList();
        toDoList.setTask(task);

        String expected = "ToDoList\n[Task: id = 1, caption = 'Caption'\ndescription = 'description'\npriority = 10\ndeadline = 2023-09-08\nstatus = 'done'\ncomplete = 2023-09-08\n]";
        assertEquals(expected, printOutput(printer, toDoList));
    }

    @Test
    public void testPrintTaskWithStatusNew()
    {
        PrintToDoList printer = new PrintToDoList();

        var taskDone = new Task(1, "Caption", "description", 10, "2023-09-08", "new", "2023-09-08");
        ToDoList toDoList = new ToDoList();
        toDoList.setTask(taskDone);

        String expected = "Task: id = 1, caption = 'Caption'\ndescription = 'description'\npriority = 10\ndeadline = 2023-09-08\nstatus = 'new'\ncomplete = 2023-09-08";
        assertEquals(expected, printOutputForStatus(printer, toDoList, "new"));
    }

    @Test
    public void testPrintTaskWithStatusDone()
    {
        PrintToDoList printer = new PrintToDoList();
        var taskDone = new Task(1, "Caption", "description", 10, "2023-09-08", "done", "2023-09-08");
        ToDoList toDoList = new ToDoList();
        toDoList.setTask(taskDone);

        String expected = "Task: id = 1, caption = 'Caption'\ndescription = 'description'\npriority = 10\ndeadline = 2023-09-08\nstatus = 'done'\ncomplete = 2023-09-08";
        assertEquals(expected, printOutputForStatus(printer, toDoList, "done"));
    }
    @Test
    public void testPrintTaskWithStatusInProgress()
    {
        PrintToDoList printer = new PrintToDoList();
        List<Task> tasks = new ArrayList<>();
        var taskInProgress = new Task(3, "Caption", "description", 10, "2023-09-08", "in_progress", "2023-09-08");
        ToDoList toDoList = new ToDoList();
        toDoList.setTask(taskInProgress);

        String expected = "Task: id = 3, caption = 'Caption'\ndescription = 'description'\npriority = 10\ndeadline = 2023-09-08\nstatus = 'in_progress'\ncomplete = 2023-09-08";
        assertEquals(expected, printOutputForStatus(printer, toDoList, "in_progress"));
    }

    private String printOutput(PrintToDoList printer, ToDoList toDoList)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        printer.printAllTasks(toDoList);
        return outputStream.toString().trim();
    }

    private String printOutputForStatus(PrintToDoList printer, ToDoList toDoList, String status)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        switch (status)
        {
            case "new":
                printer.printTaskWithStatusNew(toDoList);
                break;
            case "done":
                printer.printTaskWithStatusDone(toDoList);
                break;
            case "in_progress":
                printer.printTaskWithStatusInProgress(toDoList);
                break;
        }
        return outputStream.toString().trim();
    }
}