package ca.usherbrooke.notifius.frontend.controllers;

import ca.usherbrooke.notifius.frontend.models.Notification;
import ca.usherbrooke.notifius.frontend.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class NotifiusController
{
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
