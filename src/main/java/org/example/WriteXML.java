package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WriteXML implements IWriteData
{
    private Document _document;

    public WriteXML(Document document)
    {
        this._document = document;
    }
    @Override
    public void recordNewTask(int id, String caption, String description, int priority, String deadline, String status, String complete)
    {
        try
        {
            Element taskElement = _document.createElement(ParserXML.TAG_NAME_TASK);

            taskElement.setAttribute(ParserXML.TAG_NAME_ID, String.valueOf(id));
            taskElement.setAttribute(ParserXML.TAG_NAME_CAPTION, caption);

            Element descriptionElement = _document.createElement(ParserXML.TAG_NAME_DESCRIPTION);
            descriptionElement.setTextContent(description);
            taskElement.appendChild(descriptionElement);

            Element priorityElement = _document.createElement(ParserXML.TAG_NAME_PRIORITY);
            priorityElement.setTextContent(String.valueOf(priority));
            taskElement.appendChild(priorityElement);

            Element deadlineElement = _document.createElement(ParserXML.TAG_NAME_DEADLINE);
            deadlineElement.setTextContent(deadline);
            taskElement.appendChild(deadlineElement);

            Element statusElement = _document.createElement(ParserXML.TAG_NAME_STATUS);
            statusElement.setTextContent("new");
            taskElement.appendChild(statusElement);

            Element completeElement = _document.createElement(ParserXML.TAG_NAME_COMPLETE);
            completeElement.setTextContent(complete);
            taskElement.appendChild(completeElement);

            _document.getDocumentElement().appendChild(taskElement);

            saveData();
        }
        catch (Exception e)
        {
            System.out.println("Ошибка при записи в XML: " + e);
        }
    }

    @Override
    public void EditMarkTaskCompleted(String complete)
    {
        NodeList toDoList = _document.getFirstChild().getChildNodes();

        for (int i = 0; i < toDoList.getLength(); i++)
        {
            if (toDoList.item(i).getNodeType() != Node.ELEMENT_NODE)
            {
                continue;
            }
            Node task = toDoList.item(i);
            var taskElement = findTaskElement(task,ParserXML.TAG_NAME_COMPLETE);
            taskElement.setTextContent(complete);
        }

        Node nodeToSave = _document.getElementsByTagName(ParserXML.TAG_NAME_COMPLETE).item(0);
        nodeToSave.setTextContent(complete);

        saveData();
    }

    private Node findTaskElement(Node taskChild, String element)
    {
        NodeList taskList = taskChild.getChildNodes();
        for (int i = 0; i < taskList.getLength(); i++)
        {
            if (taskList.item(i).getNodeType() != Node.ELEMENT_NODE)
            {
                continue;
            }
            if (taskList.item(i).getNodeName().equals(element))
            {
                return taskList.item(i);
            }
        }
        return null;
    }
    private void saveData()
    {
        try
        {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            var transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(_document);
            StreamResult result = new StreamResult(new File("D:/Task/src/main/java/org/example/ToDoList.xml"));
            transformer.transform(source, result);
        } catch (TransformerException e)
        {
            System.out.println("Ошибка при записи в XML: " + e);
        }
    }
}
