package org.example;

import java.util.Scanner;


public class EditTask implements IEditTask
{
    private ToDoList _toDoList;
    private WriteXML _writerXML;
    public EditTask(ToDoList toDoList, WriteXML writerXML)
    {
        this._toDoList = toDoList;
        _writerXML = writerXML;
    }
    @Override
    public void markTaskCompleted()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер задачи:");
        var id = scanner.nextInt();

        if (id <= 0 || id > _toDoList.getTask().size())
        {
            System.out.println("Такой задачи нет.");
            return;
        }

        id--;
        var task = _toDoList.getTask().get(id);

        System.out.println("Введите дату:");
        var date = scanner.nextLine();

        task.setComplete(date);
        _writerXML.EditMarkTaskCompleted(date);
    }
}
