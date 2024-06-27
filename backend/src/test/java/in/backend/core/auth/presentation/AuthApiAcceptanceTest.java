package in.backend.core.auth.presentation;

import static io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;

import in.backend.core.auth.presentation.payload.response.AccessTokenResponse;
import in.backend.global.fixture.AuthApiFixture;
import in.backend.global.layer.AcceptanceTest;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class AuthApiAcceptanceTest extends AcceptanceTest {
    @Test
    void 회원가입_후_바로_로그인됩니다() {
        RestAssured.given().log().all()
                .body(AuthApiFixture.signupRequest())
                .contentType(APPLICATION_JSON)
                .when()
                .post("/api/auth/signup")
                .then()
                .statusCode(201)
                .cookie("refreshToken", Matchers.notNullValue())
                .body("accessToken", Matchers.notNullValue());
    }

    @Test
    void 회원가입_후_토큰을_재발급할_수_있습니다() {
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

        RestAssured.given().log().all()
                .cookies(response.cookies())
                .header("Authorization", STR."Bearer \{accessToken}")
                .when().log().all()
                .post("/api/auth/token/reissue")
                .then()
                .statusCode(201)
                .body("accessToken", Matchers.notNullValue());

    }

    @Test
    void 회원가입_후_로그아웃_할_수_있습니다() {
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

        RestAssured.given().log().all()
                .cookies(response.cookies())
                .header("Authorization", STR."Bearer \{accessToken}")
                .when().log().all()
                .delete("/api/auth/logout")
                .then()
                .statusCode(204);
    }

    @Test
    void 로그인_할_수_있습니다() {
        RestAssured.given().log().all()
                .body(AuthApiFixture.signupRequest())
                .contentType(APPLICATION_JSON)
                .when()
                .post("/api/auth/signup")
                .then()
                .extract();

        RestAssured.given().log().all()
                .body(AuthApiFixture.signupRequest())
                .contentType(APPLICATION_JSON)
                .when()
                .post("/api/auth/signin")
                .then()
                .statusCode(201)
                .cookie("refreshToken", Matchers.notNullValue())
                .body("accessToken", Matchers.notNullValue());
    }
}