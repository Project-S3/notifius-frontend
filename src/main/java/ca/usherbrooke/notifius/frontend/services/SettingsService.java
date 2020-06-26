/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.frontend.services;

import ca.usherbrooke.notifius.frontend.models.NotificationSender;
import ca.usherbrooke.notifius.frontend.models.Service;
import ca.usherbrooke.notifius.frontend.models.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class SettingsService
{

    private static final String SETTINGS_URL_FORMAT = "%s/users/%s/settings";
    private static final String NOTIFICATION_SENDERS_URL_FORMAT = "%s/notification-senders";
    private static final String SERVICES_URL_FORMAT = "%s/services";

    @Value("${notifius.backend.base-url}")
    private String notifiusBaseEndpoint;

    public Settings getSettingsForUser(@NotNull String userID)
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(String.format(SETTINGS_URL_FORMAT,
                                                       notifiusBaseEndpoint,
                                                       userID), Settings.class);
    }

    public Settings setSettingsForUser(String userID, Settings settings)
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

    public List<NotificationSender> getAllNotificationSenders()
    {
        NotificationSender[] senders = new RestTemplate().getForObject(String.format(NOTIFICATION_SENDERS_URL_FORMAT,
                                                                                     notifiusBaseEndpoint),
                                                                       NotificationSender[].class);
        return senders == null ? new ArrayList<>() : Arrays.stream(senders)
                                                           .sorted(Comparator.comparing(
                                                                   NotificationSender::getDisplayName))
                                                           .collect(Collectors.toList());
    }

    public List<Service> getAllServices()
    {
        Service[] services = new RestTemplate().getForObject(String.format(SERVICES_URL_FORMAT,
                                                                           notifiusBaseEndpoint), Service[].class);
        return services == null ? new ArrayList<>() : Arrays.stream(services)
                                                            .sorted(Comparator.comparing(Service::getDisplayName))
                                                            .collect(Collectors.toList());
    }
}
