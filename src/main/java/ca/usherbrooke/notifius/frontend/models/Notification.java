package ca.usherbrooke.notifius.frontend.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Notification
{
    private String title;
    private String content;
    private String date;
    private String service;

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    public static Comparator<Notification> getDateComparator()
    {
        Comparator comp = new Comparator<Notification>(){
            @Override
            public int compare(Notification notifA, Notification notifB)
            {
                int result=0;
                try {
                    result = dateFormat.parse(notifA.getDate()).compareTo(dateFormat.parse(notifB.getDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return result;
            }
        };
        return comp;
    }

}
