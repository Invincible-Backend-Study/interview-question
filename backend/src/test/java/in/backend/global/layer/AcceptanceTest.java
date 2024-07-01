package in.backend.global.layer;


import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;

import in.backend.core.auth.presentation.payload.response.AccessTokenResponse;
import in.backend.global.fixture.AuthApiFixture;
import in.backend.global.layer.AcceptanceTest.ConditionalLoginRunner;
import in.backend.global.utils.DatabaseCleaner;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.RequestSpecificationImpl;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@Execution(ExecutionMode.SAME_THREAD)
@ExtendWith(ConditionalLoginRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    DatabaseCleaner databaseCleaner;

    @BeforeEach
    void setup() {
        databaseCleaner.execute();
        RestAssured.port = port;
        System.out.println("check");

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader(HttpHeaderNames.CONTENT_TYPE.toString(), APPLICATION_JSON)
                .addHeader(HttpHeaderNames.ACCEPT.toString(), APPLICATION_JSON)
                .build();
    }

    @AfterEach
    void cleanup() {
        ((RequestSpecificationImpl) RestAssured.requestSpecification).removeCookies();
    }


    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LogIn {

    }

    public static class ConditionalLoginRunner implements BeforeTestExecutionCallback {
        @Override
        public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {

            Stream.concat(
                            Arrays.stream(extensionContext.getTestClass()
                                    .orElseThrow()
                                    .getAnnotations()),
                            Arrays.stream(extensionContext.getTestMethod()
                                    .orElseThrow()
                                    .getAnnotations())
                    )
                    .filter(annotation -> annotation.annotationType().equals(LogIn.class))
                    .findFirst()
                    .ifPresent((_) -> signupAndLogin());

        }

        private void signupAndLogin() {
            var response = RestAssured.given().log().all()
                    .body(AuthApiFixture.signupRequest())
                    .contentType(APPLICATION_JSON)
                    .when()
                    .post("/api/auth/signup")
                    .then()
                    .extract();

            var accessToken = response.jsonPath()
                    .getObject(".", AccessTokenResponse.class)
                    .accessToken();

            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .addHeader(HttpHeaderNames.CONTENT_TYPE.toString(), APPLICATION_JSON)
                    .addHeader(HttpHeaderNames.AUTHORIZATION.toString(), STR."Bearer \{accessToken}")
                    .addCookies(response.cookies())
                    .addHeader(HttpHeaderNames.ACCEPT.toString(), APPLICATION_JSON)
                    .build();
        }

    }
}
