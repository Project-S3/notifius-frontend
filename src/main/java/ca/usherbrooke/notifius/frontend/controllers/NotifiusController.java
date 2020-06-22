package ca.usherbrooke.notifius.frontend.controllers;

import ca.usherbrooke.notifius.frontend.models.Notification;
import ca.usherbrooke.notifius.frontend.models.Settings;
import ca.usherbrooke.notifius.frontend.services.NotificationService;
import ca.usherbrooke.notifius.frontend.services.SettingsService;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class NotifiusController
{
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final DateFormat displayDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private SettingsService settingsService;

    @GetMapping("/")
    public String notification(Principal principal ,Model model)
    {
        Map<String, Object> details = ((AttributePrincipalImpl) principal).getAttributes();
        String userId = (String) details.get("cip");

        List<Notification> notifications = notificationService.getAllNotification(userId,
                                                                                  null,
                                                                                  null);
        model.addAttribute("isNotification", true);
        model.addAttribute("isSettings", false);


        notifications.forEach(n -> {
            try {

                n.setDate(displayDateFormat.format(dateFormat.parse(n.getDate())));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            n.setService(n.getService().toLowerCase().replace("_", " "));
        });
        model.addAttribute("notifications", notifications);

        return "notifications";
    }

    @GetMapping("settings")
    public String settings(Principal principal, Model model)
    {
        Map<String, Object> details = ((AttributePrincipalImpl) principal).getAttributes();
        String userId = (String) details.get("cip");

        Settings settings = settingsService.getSettings(userId);

        model.addAttribute("isSettings", true);
        model.addAttribute("settings", settings);

        return "settings";
    }

}
