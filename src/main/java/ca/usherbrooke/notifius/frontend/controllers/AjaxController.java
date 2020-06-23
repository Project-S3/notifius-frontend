package ca.usherbrooke.notifius.frontend.controllers;

import ca.usherbrooke.notifius.frontend.models.Settings;
import ca.usherbrooke.notifius.frontend.services.SettingsService;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class AjaxController
{
    @Autowired
    private SettingsService settingsService;

    @PostMapping("settings")
    public Settings settings(Principal principal, @RequestBody Settings settings)
    {
        Map<String, Object> details = ((AttributePrincipalImpl) principal).getAttributes();
        String userId = (String) details.get("cip");

        return settingsService.setSettings(userId, settings);
    }
}
