package in.backend.global.layer;


import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(properties = {
        "spring.jpa.show-sql=true",
        "spring.jpa.properties.hibernate.format_sql=true",
        "spring.jpa.properties.hibernate.use_sql_comments=true"
})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ServiceTest {

}
