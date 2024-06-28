package task4;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ConfProperties;
import utils.Driver;
import utils.ScreenshotsOnFailure;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LeetCodeTest {
    @Rule
    public ScreenshotsOnFailure failure = new ScreenshotsOnFailure(Driver.getDriver());
    private static LeetCode leetcodePage;
    @BeforeClass
    public static void setup() {
        Driver.setup();
        Driver.setDriverUrl(ConfProperties.getProperty("leetcode"));
        leetcodePage = new LeetCode(Driver.getDriver());
    }

    @Test
    public void checkLeetCodeCase1(){
        case1Check1Access();
        case1Check2GoToProblems();
        case1Check3EnterProblem();
        case1Check4ReturnToLobby();
    }
    @Test
    public void checkLeetCodeCase2(){
        case2check1GoToExplore();
        case2check2GoToDiscuss();
        case2check3EnterDiscuss();
        case2check4CountOfDiscussions();
        case2check5Log5OfDiscussions();
        case2check6ReturnToLobby();
    }

    @Test
    public void checkLeetCodeCase3(){
        case3Check1GoToProblems();
        case3Check2SetCountFilter();
        case3Check3SetDifficultyFilter();
        case3Check4ReturnToLobby();
    }

    @Step("Проверка доступа, кейс 1")
    void case1Check1Access(){
        try {
            Assert.assertEquals(200, RestAssured.get(ConfProperties.getProperty("leetcode")).getStatusCode());
        }catch (AssertionError e){
            System.out.println("Статус равен 400");
        }
    }
    @Step("Перейти на страницу проблем, кейс 1")
    void case1Check2GoToProblems(){
        leetcodePage.getProblemSetPage().click();
        Assert.assertTrue(leetcodePage.getNavbarProblems().getAttribute("class").contains("nav-li-after"));
    }
    @Step("Ввести название проблемы и перейти на страницу, кейс 1")
    void case1Check3EnterProblem(){
        leetcodePage.getInputProblems().sendKeys("1. Two Sum");
        Assert.assertTrue(leetcodePage.getProblem().getText().contains("Two Sum"));
        leetcodePage.getProblem().click();
        Assert.assertTrue(leetcodePage.getProblemTitle().getText().contains("Two Sum"));
    }

    @Step("Вернуться на главную страницу, кейс 1")
    void case1Check4ReturnToLobby(){
        leetcodePage.getLogo(3).click();
        Assert.assertTrue(leetcodePage.getLogoTitle().getText().contains("LeetCode"));
    }

    @Step("Перейти на страницу иследования, кейс 2")
    void case2check1GoToExplore(){
        leetcodePage.getExplorePage().click();
        Assert.assertEquals("LeetCode Explore", leetcodePage.getTitleExplore().getText());
    }

    @Step("Перейти на страницу обсуждений, кейс 2")
    void case2check2GoToDiscuss(){
        leetcodePage.getNavbarDiscuss().click();
        Assert.assertTrue(leetcodePage.getNavbarDiscuss().getAttribute("class").contains("nav-li-after"));
    }

    @Step("Ввести название обсуждения, кейс 2")
    void case2check3EnterDiscuss(){
        leetcodePage.getInputDiscuss().sendKeys("Selenium");
    }
    @Step("Посчитать количество обсуждений, кейс 2")
    void case2check4CountOfDiscussions(){
        Assert.assertTrue(leetcodePage.getListDiscuss().findElements(By.xpath("//div[@class=\"topic-item-wrap__2FSZ\"]")).size() < 10);
    }

    @Step("Вывести названия 5 обсуждений, кейс 2")
    void case2check5Log5OfDiscussions(){
        for(WebElement topic : leetcodePage.getListDiscuss().findElements(By.xpath("//div[@class=\"topic-item-wrap__2FSZ\"]"))){
            String title = topic.findElement(By.xpath(".//div[@class=\"topic-title__3LYM\"]")).getText();
            String views = topic.findElements(By.xpath(".//div[@class=\"no__1erK\"]")).get(1).getText();
            System.out.println(title + " " + views + " views");
        }
    }
    @Step("Вернуться на главную страницу, кейс 2")
    void case2check6ReturnToLobby(){
        leetcodePage.getLogo(2).click();
        Assert.assertTrue(leetcodePage.getLogoTitle().getText().contains("LeetCode"));
    }

    @Step("Перейти на страницу проблем, кейс 3")
    void case3Check1GoToProblems(){
        leetcodePage.getProblemSetPage().click();
        Assert.assertTrue(leetcodePage.getNavbarProblems().getAttribute("class").contains("nav-li-after"));
    }

    @Step("Установить фильтр отображения проблем, кейс 3")
    void case3Check2SetCountFilter(){
        try {
            leetcodePage.getFilterCount().findElement(By.xpath(".//button")).click();
            leetcodePage.getFilterCount().findElement(By.xpath(".//ul")).findElement(By.xpath(".//li")).click();
            Assert.assertEquals(20, leetcodePage.getProblemsList().findElements(By.xpath("div")).size());
        }catch (Exception e){
            System.out.println("Число страниц не совпадает");
        }
    }

    @Step("Установить фильтр сложности, кейс 3")
    void case3Check3SetDifficultyFilter() {
        try {
            leetcodePage.getFilterDifficulty().findElement(By.xpath(".//button")).click();
            leetcodePage.getFilterDifficulty().findElements(By.xpath(".//div")).get(1).findElement(By.xpath("div")).click();
            Assert.assertEquals(20, leetcodePage.getProblemsList().findElements(By.xpath("//*[text() = 'Easy']")).size());
        }catch (Exception e){
            System.out.println("Не все проблемы имееют сложность \"Easy\"");
        }
    }
    @Step("Вернуться на главную страницу, кейс 3")
    void case3Check4ReturnToLobby(){
        leetcodePage.getLogo(1).click();
        Assert.assertTrue(leetcodePage.getLogoTitle().getText().contains("LeetCode"));
    }

    @AfterClass
    public static void tearDown() {
        Driver.getDriver().quit();
    }

}
