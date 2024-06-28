package task3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Yandex {
    WebDriver driver;

    public Yandex(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@data-zone-name=\"catalog\"]/button")
    private WebElement categoryBtn;

    @FindBy(xpath = "//a[@href=\"/catalog--geiming/41813350\"]")
    private WebElement category;

    @FindBy(xpath = "//a[@text()=\"Игровые приставки\"]")
    private WebElement subCategory;

    @FindBy(xpath = "//*[@data-auto=\"SerpList\"]")
    private WebElement productsList;

    @FindBy(xpath = "//*[@id=\"/content/notification\"]/div/div/a")
    private WebElement notification;

    @FindBy(xpath = "//*[@id=\"/content/header/header/wishlistButton\"]/a")
    private WebElement wishlistPage;
    @FindBy(id = "searchSection")
    private WebElement wishlist;
    @FindBy(xpath = "//span[@data-auto=\"emptyStateHeader\"]")
    private WebElement emptyState;

    public WebElement getCategoryBtn(){
        return categoryBtn;
    }
    public WebElement getCategory(){
        return category;
    }
    public WebElement getSubCategory(){
        return subCategory;
    }
    public WebElement getProductsList(){
        return productsList;
    }
    public WebElement getNotification(){
        return notification;
    }
    public WebElement getWishlistPage(){
        return wishlistPage;
    }
    public WebElement getWishlist(){
        return wishlist;
    }
    public WebElement getEmptyState(){
        return emptyState;
    }
}
