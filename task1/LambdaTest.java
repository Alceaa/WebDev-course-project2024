package task1;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ConfProperties;
import utils.Driver;
import utils.ScreenshotsOnFailure;

import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LambdaTest {
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
    public void check1Title(){
        Assert.assertEquals(lambdaPage.getTitle().getText(), "LambdaTest Sample App");
    }

    @Test
    public void check2Span(){
        Assert.assertEquals(lambdaPage.getSpan().getText(), "5 of 5 remaining");
    }

    @Test
    public void check3FirstClear(){
        Assert.assertEquals(lambdaPage.getFirst().findElement(By.xpath("span")).getAttribute("class"), "done-false");
    }

    @Test
    public void check4FirstClicked(){
        lambdaPage.getFirst().findElement(By.xpath("input")).click();
        Assert.assertEquals(lambdaPage.getFirst().findElement(By.xpath("span")).getAttribute("class"), "done-true");
        Assert.assertEquals(lambdaPage.getSpan().getText(), "4 of 5 remaining");
        lambdaPage.getFirst().findElement(By.xpath("input")).click();
    }

    @Test
    public void check5OtherClear(){
        for(Map.Entry<String, WebElement> entry : lambdaPage.getElements().entrySet()){
            if(!entry.getKey().equals("First Item")){
                Assert.assertEquals(entry.getValue().findElement(By.xpath("span")).getAttribute("class"), "done-false");
            }
        }
    }

    @Test
    public void check6OtherClicked(){
        for(Map.Entry<String, WebElement> entry : lambdaPage.getElements().entrySet()){
            if(!entry.getKey().equals("First Item")){
                entry.getValue().findElement(By.xpath("input")).click();
                Assert.assertEquals(entry.getValue().findElement(By.xpath("span")).getAttribute("class"), "done-true");
                Assert.assertEquals(lambdaPage.getSpan().getText(), "4 of 5 remaining");
                entry.getValue().findElement(By.xpath("input")).click();
            }
        }
    }

    @Test
    public void check7Add(){
        lambdaPage.getInput().sendKeys("Sixth Item");
        lambdaPage.getInput().submit();
        Assert.assertEquals(lambdaPage.getElements().get("Sixth Item").findElement(By.xpath("span")).getAttribute("class"), "done-false");
        Assert.assertEquals(lambdaPage.getSpan().getText(), "6 of 6 remaining");
    }

    @Test
    public void check8ClickOnAdded(){
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
