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
    @FindBy(xpath = "/html/body/header/nav/div[1]/div[2]/div[1]/div/ul/li[3]/a/i")
    private WebElement scheduleBtn;

    @FindBy(xpath = "//*[@id=\"bx_3777608605_2811\"]/div[3]/div/div[1]/a")
    private WebElement goToSiteBtn;

    @FindBy(xpath = "/html/body/div/div[1]/div[1]/div[3]/input[1]")
    private WebElement groupInput;

    @FindBy(xpath = "/html/body/div/div[1]/div[2]")
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
