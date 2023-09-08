package org.example;

public class Task
{
    private int _id;
    private String _caption;
    private String _description;
    private int _priority;
    private String _deadline;
    private String _status;
    private String _complete;

    public Task(int id, String caption, String description, int priority, String deadline, String status, String complete)
    {
        this._id = id;
        this._caption = caption;
        this._description = description;
        this._priority = priority;
        this._deadline = deadline;
        this._status = status;
        this._complete = complete;
    }
    public void setId(int _id)
    {
        this._id = _id;
    }

    public void setCaption(String _caption)
    {
        this._caption = _caption;
    }

    public void setDescription(String _description)
    {
        this._description = _description;
    }

    public void setPriority(int _priority)
    {
        this._priority = _priority;
    }

    public void setDeadline(String _deadline)
    {
        this._deadline = _deadline;
    }

    public void setStatus(String _status)
    {
        this._status = _status;
    }

    public void setComplete(String _complete)
    {
        this._complete = _complete;
    }

    public int getId()
    {
        return _id;
    }

    public String getCaption()
    {
        return _caption;
    }

    public String getDescription()
    {
        return _description;
    }

    public int getPriority()
    {
        return _priority;
    }

    public String getDeadline()
    {
        return _deadline;
    }

    public String getStatus()
    {
        return _status;
    }

    public String get_complete()
    {
        return _complete;
    }

    @Override
    public String toString()
    {
        return "Task: " +
                "id = " + _id +
                ", caption = '" + _caption  + '\'' + "\n" +
                "description = '" + _description + '\'' +"\n" +
                "priority = " + _priority +"\n" +
                "deadline = " + _deadline +"\n" +
                "status = '" + _status + '\'' +"\n" +
                "complete = " + _complete + "\n";
    }
}
