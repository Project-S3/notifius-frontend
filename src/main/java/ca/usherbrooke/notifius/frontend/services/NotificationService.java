package ca.usherbrooke.notifius.frontend.services;

import ca.usherbrooke.notifius.frontend.models.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class NotificationService
{
    private static final String NOTIFICATION_URL_FORMAT = "%s/users/%s/notifications" +
                                                          "?service={service}" +
                                                          "&date={date}";

    @Value("${notifius.backend.base-url}")
    private String notifiusBaseEndpoint;

    public List<Notification> getAllNotification(@NotNull String userID, String service, String date)
    {
        Map<String, String> param = new HashMap<>();
        param.put("service", service);
        param.put("date", date);

        RestTemplate restTemplate = new RestTemplate();
        Notification[] result = restTemplate.getForObject(String.format(NOTIFICATION_URL_FORMAT,
                                                                        notifiusBaseEndpoint,
                                                                        userID),
                                                          Notification[].class,
                                                          param);
        return result == null ? new ArrayList<>() : Arrays.asList(result);
    }
}
