package task5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebPage {
    WebDriver driver;

    public WebPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@data-key=\"output-response\"]")
    private WebElement output;

    @FindBy(xpath = "//*[@id=\"console\"]/div[1]/ul")
    private WebElement buttons;

    public WebElement getOutput(){
        return output;
    }
    public WebElement getButtons(){
        return buttons;
    }
}
