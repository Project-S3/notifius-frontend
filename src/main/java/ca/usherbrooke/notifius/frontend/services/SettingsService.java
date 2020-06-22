package ca.usherbrooke.notifius.frontend.services;

import ca.usherbrooke.notifius.frontend.models.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class SettingsService {

    private static final String SETTINGS_URL_FORMAT = "%s/users/%s/settings";
    private static final String NOTIFICATION_SENDERS_URL_FORMAT = "%s/notification-senders";
    private static final String SERVICES_URL_FORMAT = "%s/services";

    @Value("${notifius.backend.base-url}")
    private String notifiusBaseEndpoint;

    public Settings getSettings(@NotNull String userID)
    {
        RestTemplate restTemplate = new RestTemplate();
        Settings result = restTemplate.getForObject(String.format(SETTINGS_URL_FORMAT,
                notifiusBaseEndpoint,
                userID), Settings.class);
        return result;
    }

    public String getNotificationSenders()
    {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(String.format(NOTIFICATION_SENDERS_URL_FORMAT,
                notifiusBaseEndpoint), String.class);
        return result;
    }

    public String getServices()
    {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(String.format(SERVICES_URL_FORMAT,
                notifiusBaseEndpoint), String.class);
        return result;
    }
}
