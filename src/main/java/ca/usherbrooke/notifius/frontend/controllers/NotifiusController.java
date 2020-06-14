package ca.usherbrooke.notifius.frontend.controllers;

import ca.usherbrooke.notifius.frontend.models.Notification;
import ca.usherbrooke.notifius.frontend.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class NotifiusController
{
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final DateFormat displayDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/")
    public String notification(Model model)
    {
        List<Notification> notifications = notificationService.getAllNotification("gram3504",
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
    public String settings(Model model)
    {
        model.addAttribute("isSettings", true);
        model.addAttribute("isNotification", false);

        return "settings";
    }

}
