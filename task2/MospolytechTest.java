package task2;

import io.restassured.RestAssured;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import utils.ConfProperties;
import utils.Driver;
import utils.ScreenshotsOnFailure;
import utils.Utils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MospolytechTest {
    @Rule
    public ScreenshotsOnFailure failure = new ScreenshotsOnFailure(Driver.getDriver());
    private static Mospolytech mospolytechPage;
    @BeforeClass
    public static void setup(){
        Driver.setup();
        Driver.setDriverUrl(ConfProperties.getProperty("mospoly"));
        mospolytechPage = new Mospolytech(Driver.getDriver());
    }

    @Test
    public void check1Access(){
        Assert.assertEquals(RestAssured.get(ConfProperties.getProperty("mospoly")).getStatusCode(), 200);
    }
    @Test
    public void check2GoToSchedule(){
        mospolytechPage.getScheduleBtn().click();
    }

    @Test
    public void check3GoToSite(){
        mospolytechPage.getGoToSiteBtn().click();
        String page = Driver.switchTo(1);
        Assert.assertEquals(page, "Расписание");
    }

    @Test
    public void check4EnterGroup(){
        mospolytechPage.getGroupInput().sendKeys("221-361");
        int groupsCount = mospolytechPage.getGroupList().findElements(By.xpath("div")).size();
        Assert.assertEquals(groupsCount, 1);
    }

    @Test
    public void check5ShowSchedule(){
        mospolytechPage.getGroupList().findElement(By.xpath("div")).click();
        Assert.assertTrue(mospolytechPage.getToday().findElement(By.className("schedule-day__title")).getText().contains(Utils.dayOfWeek()));
        Assert.assertTrue(mospolytechPage.getToday().findElement(By.className("schedule-day__title")).getAttribute("class").contains("bold"));
    }
    @AfterClass
    public static void tearDown() {
        Driver.getDriver().quit();
    }
}
