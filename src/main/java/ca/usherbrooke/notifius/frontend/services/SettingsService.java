/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.frontend.services;

import ca.usherbrooke.notifius.frontend.models.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@org.springframework.stereotype.Service
public class SettingsService
{

    private static final String SETTINGS_URL_FORMAT = "%s/users/%s/settings";

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
}
