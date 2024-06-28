package task1;

import io.qameta.allure.Step;
import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.*;

import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ExtendWith(TestListener.class)
public class LambdaTest extends LogsForAllure {
    @Rule
    public ScreenshotsOnFailure failure = new ScreenshotsOnFailure(Driver.getDriver());
    private static Lambda lambdaPage;
    @BeforeClass
    public static void setup(){
        Driver.setup();
        Driver.setDriverUrl(ConfProperties.getProperty("lambdapage"));
        lambdaPage = new Lambda(Driver.getDriver());
    }
    @Test
    public void checkLambda(){
        check1Title();
        check2Span();
        check3FirstClear();
        check4FirstClicked();
        check5OtherClear();
        check6OtherClicked();
        check7Add();
        check8ClickOnAdded();
    }

    @Step("Проверка заголовка")
    void check1Title(){
        Assert.assertEquals(lambdaPage.getTitle().getText(), "LambdaTest Sample App");
    }

    @Step("Проверка текста о количестве элементов")
    void check2Span(){
        Assert.assertEquals(lambdaPage.getSpan().getText(), "5 of 5 remaining");
    }

    @Step("Проверка, что первый элемент не зачеркнут")
    void check3FirstClear(){
        Assert.assertEquals(lambdaPage.getFirst().findElement(By.xpath("span")).getAttribute("class"), "done-false");
    }

    @Step("Поставить галочку у первого элемента")
    void check4FirstClicked(){
        lambdaPage.getFirst().findElement(By.xpath("input")).click();
        Assert.assertEquals(lambdaPage.getFirst().findElement(By.xpath("span")).getAttribute("class"), "done-true");
        Assert.assertEquals(lambdaPage.getSpan().getText(), "4 of 5 remaining");
        lambdaPage.getFirst().findElement(By.xpath("input")).click();
    }

    @Step("Повтор шагов 3-4 для остальных элементов (свойство)")
    void check5OtherClear(){
        for(Map.Entry<String, WebElement> entry : lambdaPage.getElements().entrySet()){
            if(!entry.getKey().equals("First Item")){
                Assert.assertEquals(entry.getValue().findElement(By.xpath("span")).getAttribute("class"), "done-false");
            }
        }
    }

    @Step("Повтор шагов 3-4 для остальных элементов (клик)")
    void check6OtherClicked(){
        for(Map.Entry<String, WebElement> entry : lambdaPage.getElements().entrySet()){
            if(!entry.getKey().equals("First Item")){
                entry.getValue().findElement(By.xpath("input")).click();
                Assert.assertEquals(entry.getValue().findElement(By.xpath("span")).getAttribute("class"), "done-true");
                Assert.assertEquals(lambdaPage.getSpan().getText(), "4 of 5 remaining");
                entry.getValue().findElement(By.xpath("input")).click();
            }
        }
    }

    @Step("Добавить новый элемент")
    void check7Add(){
        lambdaPage.getInput().sendKeys("Sixth Item");
        lambdaPage.getInput().submit();
        Assert.assertEquals(lambdaPage.getElements().get("Sixth Item").findElement(By.xpath("span")).getAttribute("class"), "done-false");
        Assert.assertEquals(lambdaPage.getSpan().getText(), "6 of 6 remaining");
    }

    @Step("Нажать на новый элмент")
    void check8ClickOnAdded(){
        lambdaPage.getElements().get("Sixth Item").findElement(By.xpath("input")).click();
        Assert.assertEquals(lambdaPage.getElements().get("Sixth Item").findElement(By.xpath("span")).getAttribute("class"), "done-true");
        Assert.assertEquals(lambdaPage.getSpan().getText(), "5 of 6 remaining");
        lambdaPage.getElements().get("Sixth Item").findElement(By.xpath("input")).click();
    }
    @AfterClass
    public static void tearDown() {
        Driver.getDriver().quit();
    }
}
