package ca.usherbrooke.notifius.frontend.services;

import ca.usherbrooke.notifius.frontend.models.NotificationSender;
import ca.usherbrooke.notifius.frontend.models.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

@Service
public class SettingsService
{

    private static final String SETTINGS_URL_FORMAT = "%s/users/%s/settings";
    private static final String NOTIFICATION_SENDERS_URL_FORMAT = "%s/notification-senders";
    private static final String SERVICES_URL_FORMAT = "%s/services";

    @Value("${notifius.backend.base-url}")
    private String notifiusBaseEndpoint;

    public Settings getSettings(@NotNull String userID)
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(String.format(SETTINGS_URL_FORMAT,
                                                       notifiusBaseEndpoint,
                                                       userID), Settings.class);
    }

    public NotificationSender getNotificationSenders()
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(String.format(NOTIFICATION_SENDERS_URL_FORMAT,
                                                       notifiusBaseEndpoint), NotificationSender.class);
    }

    public String[] getServices()
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(String.format(SERVICES_URL_FORMAT,
                                                       notifiusBaseEndpoint), String[].class);
    }

    public Settings setSettings(String userID, Settings settings)
    {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(SETTINGS_URL_FORMAT,
                                   notifiusBaseEndpoint,
                                   userID);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<Settings> requestEntity = new HttpEntity<>(settings, headers);
        return restTemplate.postForObject(url, requestEntity, Settings.class);
    }
}
