/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.frontend.controllers;

import ca.usherbrooke.notifius.frontend.models.Notification;
import ca.usherbrooke.notifius.frontend.models.Settings;
import ca.usherbrooke.notifius.frontend.services.NotificationService;
import ca.usherbrooke.notifius.frontend.services.SettingsService;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AjaxController
{
    @Autowired
    private SettingsService settingsService;
    @Autowired
    private NotificationService notificationService;

    @PostMapping("settings")
    public ResponseEntity<Object> settings(Principal principal, @RequestBody Settings settings)
    {
        Map<String, Object> details = ((AttributePrincipalImpl) principal).getAttributes();
        String userId = (String) details.get("cip");
        try {
            return new ResponseEntity<>(settingsService.setSettingsForUser(userId, settings), HttpStatus.CREATED);
        } catch (RestClientException e) {
            return new ResponseEntity<>(((HttpClientErrorException.BadRequest) e).getResponseBodyAsString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("send-notification")
    public Notification sendNotification(Principal principal)
    {
        Map<String, Object> details = ((AttributePrincipalImpl) principal).getAttributes();
        String userId = (String) details.get("cip");

        return notificationService.sendTestNotification(userId);
    }
}
