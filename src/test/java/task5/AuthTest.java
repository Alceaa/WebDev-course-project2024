package task5;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import utils.Driver;
import utils.ScreenshotsOnFailure;

public class AuthTest extends BaseTest {
    @Rule
    public ScreenshotsOnFailure failure = new ScreenshotsOnFailure(Driver.getDriver());
    @BeforeClass
    public static void setUp(){
        BaseTest.setUp();
        requestSpecification.basePath("");
    }
    @Test
    public void checkRegister(){
        AuthUser user = new AuthUser("eve.holt@reqres.in", "pistol");
        UserData newUser = checkStatusCodeGet("/register", user, 200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("AuthSchema.json"))
                .extract().as(UserData.class);
        Assert.assertNotNull(newUser.getToken());
        Assert.assertEquals(newUser.getId(), 4);
    }

    @Test
    public void checkRegisterUnsuccessful(){
        AuthUserUnsuccess user = new AuthUserUnsuccess("sydney@fife");
        Error error = checkStatusCodeGet("/register", user, 400)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ErrorSchema.json"))
                .extract().as(Error.class);
        Assert.assertEquals(error.getError(), "Missing password");
    }

    @Test
    public void checkLogin(){
        AuthUser user = new AuthUser("eve.holt@reqres.in", "cityslicka");
        UserData logUser = checkStatusCodeGet("/login", user, 200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("AuthSchema.json"))
                .extract().as(UserData.class);
        Assert.assertNotNull(logUser.getToken());
    }

    @Test
    public void checkLoginUnsuccessful(){
        AuthUserUnsuccess user = new AuthUserUnsuccess("peter@klaven");
        Error error = checkStatusCodeGet("/login", user, 400)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ErrorSchema.json"))
                .extract().as(Error.class);
        Assert.assertEquals(error.getError(), "Missing password");
    }
}
