package in.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "in.backend")
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}
