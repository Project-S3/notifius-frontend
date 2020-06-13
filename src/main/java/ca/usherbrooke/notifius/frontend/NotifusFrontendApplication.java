package ca.usherbrooke.notifius.frontend;

import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCasClient
@SpringBootApplication
public class NotifusFrontendApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(NotifusFrontendApplication.class, args);
    }
}
