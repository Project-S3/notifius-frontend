package ca.usherbrooke.notifius.frontend;

import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCasClient
@SpringBootApplication
public class NotifiusFrontendApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(NotifiusFrontendApplication.class, args);
    }
}
