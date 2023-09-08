package org.example;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FileOpener
{
    private Document document;
    public  Document OpenFile(String filePath)
    {
        if (document == null)
        {
            try
            {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                document = db.parse(filePath);
            } catch (Exception e)
            {
                System.out.println("Ошибка при открытии файла " + e);
            }
        }

        return document;
    }
}
