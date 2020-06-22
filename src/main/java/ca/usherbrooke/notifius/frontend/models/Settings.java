package ca.usherbrooke.notifius.frontend.models;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Settings
{
    private Set<String> enableServices = new HashSet<>();
    private Map<String, String> notificationSenders = new HashMap<>();

    public Set<String> getEnableServices()
    {
        return enableServices;
    }

    public void setEnableServices(Set<String> enableServices)
    {
        this.enableServices = enableServices;
    }

    public Settings withEnableServices(Set<String> enableServices)
    {
        setEnableServices(enableServices);
        return this;
    }

    public Map<String, String> getNotificationSenders()
    {
        return notificationSenders;
    }

    public void setNotificationSenders(Map<String, String> notificationSenders)
    {
        this.notificationSenders = notificationSenders;
    }

    public Settings withNotificationSenders(Map<String, String> notificationSenders)
    {
        setNotificationSenders(notificationSenders);
        return this;
    }
}
