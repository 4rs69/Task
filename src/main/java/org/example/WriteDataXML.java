package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class WriteDataXML
{
    private Document _document;
    private WriteXML _writerXML;

    private Scanner scanner = new Scanner(System.in);
    public WriteDataXML(Document document, WriteXML xmlWriter)
    {
        this._document = document;
        this._writerXML = xmlWriter;
    }

    private int lastId(ToDoList _toDoList)
    {
        int s =0;
        var count = _toDoList.getTask().size();

        for (int i = 0; i < count; i++)
        {
            s = _toDoList.getTask().get(i).getId();
        }
        s++;

        return s++;
    }
    private int priority()
    {
        int priority;
        while (true)
        {
            if (scanner.hasNextInt())
            {
                priority = scanner.nextInt();
                scanner.nextLine();
                if (priority > 0 && priority <= 10)
                {
                    break;
                }
            }
            System.out.println("Вы ввели некорректные данные, введите еще раз:");
            scanner.nextLine();
        }
        return priority;
    }
    private String formatDateToString(String name)
    {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = null;
        do
        {
            System.out.println("Введите дату "+ name+ " yyyy-MM-dd:");
            String deadlineString = scanner.nextLine();

            try
            {
                date = LocalDate.parse(deadlineString, formatter);
            } catch (Exception e)
            {
                System.out.println("Некорректный формат даты, попробуйте еще раз.");
            }
        } while (date == null);

        String formattedDate = date.format(formatter);
        return formattedDate;
    }
    public void addTask(ToDoList toDoList)
    {

        Element toDoListElement = _document.getDocumentElement();
        if (toDoListElement == null)
        {
            toDoListElement = _document.createElement("ToDoList");
            _document.appendChild(toDoListElement);
        }

        var id = lastId(toDoList);

        System.out.println("Введите заголовок задачи:");
        String caption = scanner.nextLine();


        System.out.println("Введите описание задачи:");
        String description = scanner.nextLine();

        System.out.println("Введите приоритет задачи от 1 до 10:");
        var priority = priority();

        String formattedDeadline = formatDateToString(ParserXML.TAG_NAME_DEADLINE);
        String deadline = formattedDeadline.format(formattedDeadline);

        Element statusElement = _document.createElement(ParserXML.TAG_NAME_STATUS);
        statusElement.setTextContent("new");

        String formattedComplete = formatDateToString(ParserXML.TAG_NAME_COMPLETE);
        String complete = String.format(formattedComplete);

        try
        {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            var transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(_document);
            StreamResult result = new StreamResult(new File("D:/test-maven-project/src/main/java/org/example/ToDoList.xml"));
            transformer.transform(source, result);
        }
        catch (TransformerException e)
        {
            System.out.println("Ошибка при записи в XML: " + e);
        }

        var task = new Task(id, caption, description, priority, deadline, "new", complete);
        toDoList.setTask(task);

        _writerXML.recordNewTask(id,caption,description,priority,deadline.toString(),"new",complete);

    }

}
