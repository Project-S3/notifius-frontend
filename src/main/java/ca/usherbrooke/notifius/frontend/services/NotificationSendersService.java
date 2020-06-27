/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.frontend.services;

import ca.usherbrooke.notifius.frontend.models.NotificationSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationSendersService
{
    private static final String NOTIFICATION_SENDERS_URL_FORMAT = "%s/notification-senders";

    @Value("${notifius.backend.base-url}")
    private String notifiusBaseEndpoint;

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
}
