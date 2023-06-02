package tn.conge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.conge.core.properties.CongeProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({CongeProperties.class})
public class CongeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CongeApplication.class, args);
    }

}
