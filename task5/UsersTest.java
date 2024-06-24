package task5;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import utils.Driver;
import utils.ScreenshotsOnFailure;

public class UsersTest extends BaseTest{
    @Rule
    public ScreenshotsOnFailure failure = new ScreenshotsOnFailure(Driver.getDriver());
    @BeforeClass
    public static void setUp(){
        BaseTest.setUp();
        requestSpecification.basePath("/users");
    }

    @Test
    public void checkListUsers() {
        UsersList usersList = checkStatusCodeGet("?page=2", 200).assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("resources/UsersSchema.json"))
                .extract().as(UsersList.class);
        Assert.assertEquals(usersList.getPage(), 2);
        Assert.assertEquals(usersList.getPerPage(), 6);
        Assert.assertEquals(usersList.getTotal(), 12);
        Assert.assertEquals(usersList.getTotalPages(), 2);
        Assert.assertFalse(usersList.getData().isEmpty());
        Assert.assertEquals(usersList.getData().get(2).getFirstName(), "Tobias");
        Assert.assertEquals(usersList.getData().get(2).getLastName(), "Funke");
    }

    @Test
    public void checkUser(){
        SingleUser user = checkStatusCodeGet("/2", 200).assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("resources/SingleUserSchema.json"))
                .extract().as(SingleUser.class);
        Assert.assertEquals(user.getData().getId(), 2);
        Assert.assertEquals(user.getData().getEmail(), "janet.weaver@reqres.in");
        Assert.assertEquals(user.getData().getFirstName(), "Janet");
        Assert.assertEquals(user.getData().getLastName(), "Weaver");
        Assert.assertEquals(user.getData().getAvatar(), "https://reqres.in/img/faces/2-image.jpg");
    }

    @Test
    public void checkEmptyUser(){
        SingleUser user = checkStatusCodeGet("/22", 404).assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("resources/EmptySchema.json"))
                .extract().as(SingleUser.class);
        Assert.assertNull(user.getData());
    }

    @Test
    public void checkNewUser(){
        PostUser user = new PostUser("morpheus", "leader");
        PostUser newUser = checkStatusCodeGet(user, 201)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("resources/ChangeUserSchema.json"))
                .extract().as(PostUser.class);
        Assert.assertEquals(newUser.getName(), "morpheus");
        Assert.assertEquals(newUser.getJob(), "leader");
    }

    @Test
    public void checkPutUpdate(){
        PostUser user = new PostUser("morpheus", "zion resident");
        PostUser newUser = checkStatusCodePut("/2", user, 200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("resources/ChangeUserSchema.json"))
                .extract().as(PostUser.class);
        Assert.assertEquals(newUser.getName(), "morpheus");
        Assert.assertEquals(newUser.getJob(), "zion resident");
    }

    @Test
    public void checkPatchUpdate(){
        PostUser user = new PostUser("morpheus", "zion resident");
        PostUser newUser = checkStatusCodePatch("/2", user, 200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("resources/ChangeUserSchema.json"))
                .extract().as(PostUser.class);
        Assert.assertEquals(newUser.getName(), "morpheus");
        Assert.assertEquals(newUser.getJob(), "zion resident");
    }

    @Test
    public void checkDelete(){
        checkStatusCodeDelete("/2", 204);
    }

    @Test
    public void checkDelayed(){
        UsersList usersList = checkStatusCodeGet("?delay=3", 200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("resources/UsersSchema.json"))
                .extract().as(UsersList.class);
        Assert.assertEquals(usersList.getPage(), 1);
        Assert.assertEquals(usersList.getPerPage(), 6);
        Assert.assertEquals(usersList.getTotal(), 12);
        Assert.assertEquals(usersList.getTotalPages(), 2);
        Assert.assertFalse(usersList.getData().isEmpty());
    }
}
