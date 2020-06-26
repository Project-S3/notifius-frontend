package ca.usherbrooke.notifius.frontend.models;

public class Service implements Comparable<Service>
{
    private String id;
    private String displayName;
    private String description;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public int compareTo(Service o)
    {
        if (o == null) return 0;
        return this.displayName.compareTo(o.displayName);
    }
}
