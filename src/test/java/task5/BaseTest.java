package task5;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import utils.ConfProperties;


import static io.restassured.RestAssured.given;

public class BaseTest {
    protected static WebPage webPage;
    static RequestSpecification requestSpecification;
    static final String BASE_URL = ConfProperties.getProperty("basic_url");

    @BeforeClass
    public static void setUp() {
        requestSpecification = given()
                .baseUri(BASE_URL)
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    static ValidatableResponse checkStatusCodeGet(String url, int code) {
        return given(requestSpecification)
                .get(url)
                .then()
                .log().all()
                .statusCode(code);
    }

    static ValidatableResponse checkStatusCodeGet(int code) {
        return given(requestSpecification)
                .get()
                .then()
                .log().all()
                .statusCode(code);
    }

    static ValidatableResponse checkStatusCodeGet(String url, Object body, int code) {
        return given(requestSpecification)
                .body(body)
                .post(url)
                .then()
                .log().all()
                .statusCode(code);
    }

    static ValidatableResponse checkStatusCodeGet(Object body, int code) {
        return given(requestSpecification)
                .body(body)
                .post()
                .then()
                .log().all()
                .statusCode(code);
    }
    static ValidatableResponse checkStatusCodePut(String url, Object body, int code){
        return given(requestSpecification)
                .body(body)
                .put(url)
                .then()
                .log().all()
                .statusCode(code);
    }
    static ValidatableResponse checkStatusCodePatch(String url, Object body, int code){
        return given(requestSpecification)
                .body(body)
                .patch(url)
                .then()
                .log().all()
                .statusCode(code);
    }
    static ValidatableResponse checkStatusCodeDelete(String url, int code){
        return given(requestSpecification)
                .delete(url)
                .then()
                .log().all()
                .statusCode(code);
    }
}
