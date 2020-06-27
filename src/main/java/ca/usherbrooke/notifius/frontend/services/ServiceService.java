/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.frontend.services;

import ca.usherbrooke.notifius.frontend.models.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceService
{
    private static final String SERVICES_URL_FORMAT = "%s/services";

    @Value("${notifius.backend.base-url}")
    private String notifiusBaseEndpoint;

    public List<ca.usherbrooke.notifius.frontend.models.Service> getAllServices()
    {
        ca.usherbrooke.notifius.frontend.models.Service[] services = new RestTemplate().getForObject(String.format(SERVICES_URL_FORMAT,
                                                                                                                   notifiusBaseEndpoint), Service[].class);
        return services == null ? new ArrayList<>() : Arrays.stream(services)
                                                            .sorted(Comparator.comparing(Service::getDisplayName))
                                                            .collect(Collectors.toList());
    }
}
