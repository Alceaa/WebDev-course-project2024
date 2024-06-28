package task2;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.checkerframework.checker.units.qual.A;
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
    public void checkMospolytech(){
        check1Access();
        check2GoToSchedule();
        check3GoToSite();
        check4EnterGroup();
        check5ShowSchedule();
    }
    @Step("Проверка доступа")
    void check1Access(){
        Assert.assertEquals(RestAssured.get(ConfProperties.getProperty("mospoly")).getStatusCode(), 200);
    }
    @Step("Нажать на кнопку \"расписания\"")
    void check2GoToSchedule(){
        mospolytechPage.getScheduleBtn().click();
    }

    @Step("Перейти на страницу расписаний")
    void check3GoToSite(){
        mospolytechPage.getGoToSiteBtn().click();
        String page = Driver.switchTo(1);
        Assert.assertEquals(page, "Расписание");
    }

    @Step("Ввести номер группы")
    void check4EnterGroup(){
        try {
            mospolytechPage.getGroupInput().sendKeys("221-361");
            int groupsCount = mospolytechPage.getGroupList().findElements(By.xpath("div")).size();
            Assert.assertEquals(groupsCount, 1);
        }catch (Exception e){
            System.out.println("Группа не найдена");
        }
    }

    @Step("Перейти к группе, проверить сегодняшний день")
    void check5ShowSchedule(){
        try {
            mospolytechPage.getGroupList().findElement(By.xpath("div")).click();
            Assert.assertTrue(mospolytechPage.getToday().findElement(By.className("schedule-day__title")).getText().contains(Utils.dayOfWeek()));
            Assert.assertTrue(mospolytechPage.getToday().findElement(By.className("schedule-day__title")).getAttribute("class").contains("bold"));
        }catch (Exception e){
            System.out.println("Группа не найдена");
        }
    }
    @AfterClass
    public static void tearDown() {
        Driver.getDriver().quit();
    }
}
