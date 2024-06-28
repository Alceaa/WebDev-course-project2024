package task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Mospolytech {
    WebDriver driver;
    public Mospolytech(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//a[@title=\"Расписание\"]")
    private WebElement scheduleBtn;

    @FindBy(xpath = "//a[@href=\"https://rasp.dmami.ru/\"]")
    private WebElement goToSiteBtn;

    @FindBy(xpath = "//input[@placeholder=\"группа ...\"]")
    private WebElement groupInput;

    @FindBy(xpath = "//div[@class=\"found-groups row not-print\"]")
    private WebElement groupList;

    public WebElement getScheduleBtn(){
        return scheduleBtn;
    }
    public WebElement getGoToSiteBtn(){
        return goToSiteBtn;
    }
    public WebElement getGroupInput(){
        return groupInput;
    }
    public WebElement getGroupList(){
        return groupList;
    }
    public WebElement getToday(){
        return driver.findElement(By.className("schedule-day_today"));
    }
}
