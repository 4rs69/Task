package org.example;

public class PrintToDoList
{
    public void printAllTasks(ToDoList todDoList)
    {
        if (todDoList.getTask().isEmpty())
        {
            System.out.println("Нету никаких задач");
        }
        else
            System.out.println(todDoList.toString());
    }
    public void printTaskWithStatusNew(ToDoList todDoList)
    {
        todDoList.getTask().stream().filter(task ->
                {
                    return task.getStatus().equals("new");
                }
        ).forEach(task ->
        {
            System.out.println(task);
        });
    }
    public void printTaskWithStatusDone(ToDoList todDoList)
    {
        todDoList.getTask().stream().filter(task ->
                {
                    return task.getStatus().equals("done");
                }
        ).forEach(task ->
        {
            System.out.println(task);
        });
    }
    public void printTaskWithStatusInProgress(ToDoList todDoList)
    {
        todDoList.getTask().stream().filter(task ->
                {
                    return task.getStatus().equals("in_progress");
                }
        ).forEach(task ->
        {
            System.out.println(task);
        });
    }
}
