package in.backend.global.layer;


import static io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;

import in.backend.core.auth.presentation.payload.response.AccessTokenResponse;
import in.backend.global.fixture.AuthApiFixture;
import in.backend.global.utils.DatabaseCleaner;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.RequestSpecificationImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class AcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    DatabaseCleaner databaseCleaner;

    @BeforeEach
    void setup() {
        databaseCleaner.execute();
        RestAssured.port = port;
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

    @AfterEach
    void cleanup() {
        ((RequestSpecificationImpl) RestAssured.requestSpecification).removeCookies();
    }


}
