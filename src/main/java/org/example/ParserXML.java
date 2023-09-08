package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParserXML implements IFileParser
{
    public static final String TAG_NAME_TASK = "Task";
    public static final String TAG_NAME_ID = "id";
    public static final String TAG_NAME_CAPTION = "caption";
    public static final String TAG_NAME_DESCRIPTION = "Description";
    public static final String TAG_NAME_PRIORITY = "Priority";
    public static final String TAG_NAME_DEADLINE = "Deadline";
    public static final String TAG_NAME_STATUS = "Status";
    public static final String TAG_NAME_COMPLETE = "Complete";
    private Document _document;
    private ToDoList _toDoList;

    public ParserXML(Document document,ToDoList toDoList)
    {
        this._document = document;
        this._toDoList = toDoList;
    }

    @Override
    public void parseFile()
    {
        if (_document == null)
        {
            System.out.println(0);
        }

        Node root = _document.getFirstChild();
        NodeList rootChild = root.getChildNodes();

        for (int i = 0; i < rootChild.getLength(); i++)
        {
            if (rootChild.item(i).getNodeType() != Node.ELEMENT_NODE)
            {
                continue;
            }
            Task task = parseElement(rootChild.item(i));
            _toDoList.setTask(task);
        }
    }
    private Task parseElement(Node elementNode)
    {
        NodeList elementChild = elementNode.getChildNodes();
        String description = null;
        int priority = 0;
        String deadline = null;
        String status = null;
        String complete = null;

        Node currentTaskNode = elementNode;
        NamedNodeMap attributes = currentTaskNode.getAttributes();
        String caption = attributes.getNamedItem(TAG_NAME_CAPTION).getNodeValue();
        int id = Integer.parseInt(attributes.getNamedItem(TAG_NAME_ID).getNodeValue());

        for (int j = 0; j < elementChild.getLength(); j++)
        {

            switch (elementChild.item(j).getNodeName())
            {
                case TAG_NAME_DESCRIPTION:
                {
                    description = elementChild.item(j).getTextContent();
                    break;
                }
                case TAG_NAME_PRIORITY:
                {
                    priority = Integer.valueOf(elementChild.item(j).getTextContent());
                    break;
                }
                case TAG_NAME_DEADLINE:
                {
                    deadline = elementChild.item(j).getTextContent();
                    break;
                }
                case TAG_NAME_STATUS:
                {
                    status = elementChild.item(j).getTextContent();
                    break;
                }
                case TAG_NAME_COMPLETE:
                {
                    complete = elementChild.item(j).getTextContent();
                    break;
                }
            }
        }
        var task = new Task(id, caption, description, priority, deadline, status, complete);
        return task;
    }
}
