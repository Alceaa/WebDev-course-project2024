package task5;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import utils.Driver;
import utils.ScreenshotsOnFailure;


public class ResourceTest extends BaseTest {
    @Rule
    public ScreenshotsOnFailure failure = new ScreenshotsOnFailure(Driver.getDriver());
    @BeforeClass
    public static void setUp() {
        BaseTest.setUp();
        requestSpecification.basePath("/unknown");
    }

    @Test
    public void checkListResource() {
        ResourceList resourceList = checkStatusCodeGet(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResourcesSchema.json"))
                .extract().as(ResourceList.class);
        Assert.assertEquals(resourceList.getPage(), 1);
        Assert.assertEquals(resourceList.getPerPage(), 6);
        Assert.assertEquals(resourceList.getTotal(), 12);
        Assert.assertEquals(resourceList.getTotalPages(), 2);
        Assert.assertFalse(resourceList.getData().isEmpty());
    }

    @Test
    public void checkResource(){
        ResourceSingle resource = checkStatusCodeGet("/2", 200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleResourceSchema.json"))
                .extract().as(ResourceSingle.class);
        Assert.assertEquals(resource.getData().getId(), 2);
        Assert.assertEquals(resource.getData().getName(), "fuchsia rose");
        Assert.assertEquals(resource.getData().getYear(), 2001);
        Assert.assertEquals(resource.getData().getColor(), "#C74375");
        Assert.assertEquals(resource.getData().getPantone_value(), "17-2031");
    }

    @Test
    public void checkEmptyResource(){
        ResourceSingle resource = checkStatusCodeGet("/23", 404)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("EmptySchema.json"))
                .extract().as(ResourceSingle.class);
        Assert.assertNull(resource.getData());
    }
}