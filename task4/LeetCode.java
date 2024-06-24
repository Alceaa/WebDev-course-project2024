package task4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeetCode{
    WebDriver driver;

    public LeetCode(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"product\"]/div/div/div[1]/div/a")
    private WebElement problemSetPage;

    @FindBy(xpath = "//*[@id=\"leetcode-navbar\"]/div[1]/ul/li[2]")
    private WebElement navbarProblems;

    @FindBy(xpath = "//*[@id=\"__next\"]/div[1]/div[4]/div[2]/div[1]/div[4]/div[1]/div[1]/div[5]/div[1]/div/input")
    private WebElement inputProblems;

    @FindBy(xpath = "//a[@href=\"/problems/two-sum\"]")
    private WebElement problem;

    @FindBy(xpath = "//a[@href=\"/problems/two-sum/\"]")
    private WebElement problemTitle;

    @FindBy(xpath = "//*[@id=\"landing-page-app\"]/div/div[1]/div[3]/div[1]/div/div/div[1]/div/div/span")
    private WebElement logoTitle;

    @FindBy(xpath = "//*[@id=\"explore\"]/div/div/div[2]/div/a/p")
    private WebElement explorePage;
    @FindBy(xpath = "//*[@class=\"title explore-page-title\"]")
    private WebElement titleExplore ;

    @FindBy(xpath = "//*[@id=\"leetcode-navbar\"]/div[1]/ul/li[4]")
    private WebElement navbarDiscuss;

    @FindBy(xpath = "//*[@id=\"discuss-container\"]/div/div/div[3]/div[1]/div/div[2]/div/span/input")
    private WebElement inputDiscuss;

    @FindBy(xpath = "//*[@class=\"topic-list-content__3Win\"]")
    private WebElement listDiscuss;

    @FindBy(xpath = "//*[@id=\"__next\"]/div[1]/div[4]/div[2]/div[1]/div[4]/div[3]/div")
    private WebElement filterCount;

    @FindBy(xpath = "//*[@id=\"__next\"]/div[1]/div[4]/div[2]/div[1]/div[4]/div[1]/div[1]/div[2]")
    private WebElement filterDifficulty;

    public WebElement getProblemSetPage(){
        return problemSetPage;
    }
    public WebElement getNavbarProblems(){
        return navbarProblems;
    }
    public WebElement getInputProblems(){
        return inputProblems;
    }
    public WebElement getProblem(){
        return problem;
    }
    public WebElement getProblemTitle(){
        return problemTitle;
    }
    public WebElement getLogo(int c){
        switch (c){
            case (1):
                return driver.findElement(By.xpath("//*[@id=\"leetcode-navbar\"]/div[1]/a/span[1]"));
            case (2):
                return driver.findElement(By.xpath("//*[@id=\"leetcode-navbar\"]/div[1]/a/span[2]"));
            case (3):
                return driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div/div/div[3]/nav/div/div/div[1]/ul/a/div/div[1]/img"));
        }
        return null;
    }
    public WebElement getLogoTitle(){
        return logoTitle;
    }
    public WebElement getExplorePage(){
        return explorePage;
    }
    public WebElement getTitleExplore(){
        return titleExplore;
    }
    public WebElement getNavbarDiscuss(){
        return navbarDiscuss;
    }
    public WebElement getInputDiscuss(){
        return inputDiscuss;
    }
    public WebElement getListDiscuss(){
        return listDiscuss;
    }
    public WebElement getFilterCount(){
        return filterCount;
    }
    public WebElement getProblemsList(){
        return driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[4]/div[2]/div[1]/div[4]/div[2]/div/div/div[2]"));
    }
    public WebElement getFilterDifficulty(){
        return filterDifficulty;
    }
}
