package ca.usherbrooke.notifius.frontend.controllers;

import ca.usherbrooke.notifius.frontend.models.Notification;
import ca.usherbrooke.notifius.frontend.models.NotificationSender;
import ca.usherbrooke.notifius.frontend.models.Settings;
import ca.usherbrooke.notifius.frontend.models.Service;
import ca.usherbrooke.notifius.frontend.services.NotificationService;
import ca.usherbrooke.notifius.frontend.services.SettingsService;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class NotifiusController {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final DateFormat displayDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private static final String SETTINGS_URL_FORMAT = "%s/users/%s/settings";

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private SettingsService settingsService;

    @Value("${notifius.backend.base-url}")
    private String notifiusBaseEndpoint;

    @GetMapping("/")
    public String notification(Principal principal, Model model) {
        Map<String, Object> details = ((AttributePrincipalImpl) principal).getAttributes();
        String userId = (String) details.get("cip");

        List<Notification> notifications = notificationService.getAllNotification(userId,
                null,
                null);
        model.addAttribute("isNotification", true);
        model.addAttribute("isSettings", false);
        model.addAttribute("isDocumentation", false);


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
    public String settings(Principal principal, Model model) {
        Map<String, Object> details = ((AttributePrincipalImpl) principal).getAttributes();
        String userId = (String) details.get("cip");

        model.addAttribute("isSettings", true);
        model.addAttribute("isNotification", false);
        model.addAttribute("isDocumentation", false);

        Settings settings = settingsService.getSettings(userId);
        model.addAttribute("settings", settings);

        Service services = settingsService.getServices();
        model.addAttribute("services", services);

        NotificationSender notificationSenders = settingsService.getNotificationSenders();

        model.addAttribute("notificationSenders", notificationSenders);

        model.addAttribute("body", "");

        return "settings";
    }

    @GetMapping("documentation")
    public String documentation(Principal principal, Model model)
    {
        Map<String, Object> details = ((AttributePrincipalImpl) principal).getAttributes();
        String userId = (String) details.get("cip");

        model.addAttribute("isSettings", false);
        model.addAttribute("isNotification", false);
        model.addAttribute("isDocumentation", true);

        return "documentation";
    }
}
