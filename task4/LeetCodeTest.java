package task4;

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
    public void case1Check1Access(){
        Assert.assertEquals(200, RestAssured.get(ConfProperties.getProperty("leetcode")).getStatusCode());
    }
    @Test
    public void case1Check2GoToProblems(){
        leetcodePage.getProblemSetPage().click();
        Assert.assertTrue(leetcodePage.getNavbarProblems().getAttribute("class").contains("nav-li-after"));
    }
    @Test
    public void case1Check3EnterProblem(){
        leetcodePage.getInputProblems().sendKeys("1. Two Sum");
        Assert.assertTrue(leetcodePage.getProblem().getText().contains("Two Sum"));
        leetcodePage.getProblem().click();
        Assert.assertTrue(leetcodePage.getProblemTitle().getText().contains("Two Sum"));
    }

    @Test
    public void case1Check4ReturnToLobby(){
        leetcodePage.getLogo(3).click();
        Assert.assertTrue(leetcodePage.getLogoTitle().getText().contains("LeetCode"));
    }

    @Test
    public void case2check1GoToExplore(){
        leetcodePage.getExplorePage().click();
        Assert.assertEquals("LeetCode Explore", leetcodePage.getTitleExplore().getText());
    }

    @Test
    public void case2check2GoToDiscuss(){
        leetcodePage.getNavbarDiscuss().click();
        Assert.assertTrue(leetcodePage.getNavbarDiscuss().getAttribute("class").contains("nav-li-after"));
    }

    @Test
    public void case2check3EnterDiscuss(){
        leetcodePage.getInputDiscuss().sendKeys("Selenium");
    }
    @Test
    public void case2check4CountOfDiscussions(){
        Assert.assertTrue(leetcodePage.getListDiscuss().findElements(By.xpath("//div[@class=\"topic-item-wrap__2FSZ\"]")).size() < 10);
    }

    @Test
    public void case2check5Log5OfDiscussions(){
        for(WebElement topic : leetcodePage.getListDiscuss().findElements(By.xpath("//div[@class=\"topic-item-wrap__2FSZ\"]"))){
            String title = topic.findElement(By.xpath(".//div[@class=\"topic-title__3LYM\"]")).getText();
            String views = topic.findElements(By.xpath(".//div[@class=\"no__1erK\"]")).get(1).getText();
            System.out.println(title + " " + views + " views");
        }
    }
    @Test
    public void case2check6ReturnToLobby(){
        leetcodePage.getLogo(2).click();
        Assert.assertTrue(leetcodePage.getLogoTitle().getText().contains("LeetCode"));
    }

    @Test
    public void case3Check1GoToProblems(){
        leetcodePage.getProblemSetPage().click();
        Assert.assertTrue(leetcodePage.getNavbarProblems().getAttribute("class").contains("nav-li-after"));
    }

    @Test
    public void case3Check2SetCountFilter(){
        leetcodePage.getFilterCount().findElement(By.xpath(".//button")).click();
        leetcodePage.getFilterCount().findElement(By.xpath(".//ul")).findElement(By.xpath(".//li")).click();
        Assert.assertEquals(20, leetcodePage.getProblemsList().findElements(By.xpath("div")).size());
    }

    @Test
    public void case3Check3SetDifficultyFilter(){
        leetcodePage.getFilterDifficulty().findElement(By.xpath(".//button")).click();
        leetcodePage.getFilterDifficulty().findElements(By.xpath(".//div")).get(1).findElement(By.xpath("div")).click();
        Assert.assertEquals(20, leetcodePage.getProblemsList().findElements(By.xpath("//*[text() = 'Easy']")).size());
    }
    @Test
    public void case3Check4ReturnToLobby(){
        leetcodePage.getLogo(1).click();
        Assert.assertTrue(leetcodePage.getLogoTitle().getText().contains("LeetCode"));
    }

}
