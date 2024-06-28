package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.TreeMap;

public class Lambda {
    WebDriver driver;
    public Lambda(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    TreeMap<String, WebElement> elements = new TreeMap<>();

    @FindBy(xpath = "/html/body/div/div/h2")
    private WebElement title;

    @FindBy(xpath = "/html/body/div/div/div/span")
    private WebElement span;

    @FindBy(xpath = "/html/body/div/div/div/ul/li[1]")
    private WebElement first;

    @FindBy(xpath = "/html/body/div/div/div/ul")
    private WebElement list;

    @FindBy(xpath = "//*[@id=\"sampletodotext\"]")
    private WebElement input;

    @FindBy(xpath = "//*[@id=\"addbutton\"]")
    private WebElement button;

    public WebElement getTitle(){
        return title;
    }
    public WebElement getSpan(){
        return span;
    }
    public WebElement getFirst(){
        return first;
    }
    public TreeMap<String, WebElement> getElements(){
        for(WebElement element: list.findElements(By.xpath("li"))){
            elements.put(element.getText(), element);
        }
        return elements;
    }
    public WebElement getInput(){return input;}
    public WebDriverWait createWait(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));
        return wait;
    }

}
