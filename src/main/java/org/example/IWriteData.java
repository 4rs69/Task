package org.example;

public interface IWriteData
{
     void recordNewTask(int id, String caption, String description, int priority, String deadline, String status, String complete);
     void EditMarkTaskCompleted(String complete);

}
