package ca.usherbrooke.notifius.frontend.services;

import ca.usherbrooke.notifius.frontend.models.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NotificationService
{
    private static final String NOTIFICATION_URL_FORMAT = "%s/users/%s/notifications" +
                                                          "?service={service}" +
                                                          "&date={date}";

    private static final String NOTIFICATION_POST_URL_FORMAT = "%s/users/%s/notifications";

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

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
        ArrayList<Notification> notifList = new ArrayList<>(Arrays.asList(result)) ;
        notifList.sort(Notification.getDateComparator().reversed());
        return notifList;
    }

    public Notification sendTestNotification(@NotNull String userID)
    {
        String jokeUrl = "https://sv443.net/jokeapi/v2/joke/Programming?blacklistFlags=nsfw,religious,political,racist,sexist&format=txt&type=single";
        RestTemplate restTemplate2 = new RestTemplate();
        String joke = restTemplate2.getForObject(jokeUrl, String.class);

        Notification notification = new Notification();
        notification.setTitle("Test de vos notifications");
        notification.setContent(joke);
        notification.setDate(dateFormat.format(new Date(System.currentTimeMillis())));
        notification.setService("TEST");

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(NOTIFICATION_POST_URL_FORMAT,
                notifiusBaseEndpoint,
                userID);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<Notification> requestEntity = new HttpEntity<>(notification, headers);
        return restTemplate.postForObject(url, requestEntity, Notification.class);
    }
}
