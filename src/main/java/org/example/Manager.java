package org.example;

import java.util.Scanner;

public class Manager
{
    Scanner scanner = new Scanner(System.in);

    public void inizialize()
    {
        var fileOpener = new FileOpener();
        var document = fileOpener.OpenFile("D:/Task/src/main/java/org/example/ToDoList.xml");
        var toDoList = new ToDoList();
        var printToDoList = new PrintToDoList();
        var xmlParser = new ParserXML(document, toDoList);
        xmlParser.parseFile();
        var writerXML = new WriterXML(document);
        var xmlWriteData = new WriteDataXML(document, writerXML);
        var editTask = new EditTask(toDoList,writerXML);

        while (true)
        {
            System.out.println("1 - выводит все задачи");
            System.out.println("2 - выводит новые задачи");
            System.out.println("3 - выводит задачи в работе");
            System.out.println("4 - выводит выполненные задачи");
            System.out.println("5 - создать новую задачу");
            System.out.println("6 - Пометить задачу как выполненную");
            System.out.println("7 - выход из программы");

            String command = scanner.nextLine();
            switch (command)
            {
                case "1":
                {
                    printToDoList.printAllTasks(toDoList);
                }
                break;

                case "2":
                    printToDoList.printTaskWithStatusNew(toDoList);
                    break;

                case "3":
                    printToDoList.printTaskWithStatusInProgress(toDoList);
                    break;

                case "4":
                    printToDoList.printTaskWithStatusDone(toDoList);
                    break;

                case "5":
                    xmlWriteData.addTask(toDoList);
                    break;


                case "6":
                    editTask.markTaskCompleted();
                    break;

                case "7":
                    System.out.println("Программа завершена");
                    return;

                default:
                    System.out.println("Неверная команда");
                    break;
            }
        }
    }
}
