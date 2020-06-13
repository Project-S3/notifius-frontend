package ca.usherbrooke.notifius.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotifiusController
{
    @GetMapping("/")
    public String notification(Model model)
    {
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
