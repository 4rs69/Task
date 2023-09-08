package org.example;

import java.util.ArrayList;
import java.util.List;

public class ToDoList
{
    private final List<Task> _task;
    public ToDoList()
    {
        _task = new ArrayList<>();
    }
    public List<Task> getTask()
    {
        return _task;
    }
    public void setTask(Task task)
    {
        _task.add(task);
    }
    @Override
    public String toString()
    {
        return "ToDoList" + "\n" +
                _task;
    }
}
