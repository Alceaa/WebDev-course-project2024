package task3;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.TreeMap;
import utils.ConfProperties;
import utils.Driver;
import utils.ScreenshotsOnFailure;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class YandexTest {
    private static Yandex yandexPage;
    private final TreeMap<String, String> productMap = new TreeMap<>();
    private WebElement product;
    @Rule
    public ScreenshotsOnFailure failure = new ScreenshotsOnFailure(Driver.getDriver());

    @BeforeClass
    public static void setup() {
        Driver.setup();
        Driver.setDriverUrl(ConfProperties.getProperty("yandex"));
        yandexPage = new Yandex(Driver.getDriver());
    }

    @Test
    public void checkYandex() throws InterruptedException {
        check1Access();
        check2GoToCategory();
        check3Log5Products();
        check4Save1Product();
        check5AddToWishlist();
        check6GoToWishlist();
        check7DeleteFromWishlist();
        check8Refresh();
    }
    @Step("Проверка доступа")
    void check1Access(){
        Assert.assertEquals(RestAssured.get(ConfProperties.getProperty("yandex")).getStatusCode(), 200);
    }

    @Step("Перейти на страницу каталога, на страницу игровых приставок")
    void check2GoToCategory() throws InterruptedException {
        try {
            yandexPage.getCategoryBtn().click();
            Thread.sleep(1000);
            Actions action = Driver.createAction();
            action.moveToElement(yandexPage.getSubCategory()).perform();
            action.moveToElement(yandexPage.getCategory()).click().perform();
        }catch (Exception e){
            System.out.println("Введите капчу");
        }
    }

    @Step("Сохранить 5 первых товаров")
    void check3Log5Products(){
        try {
            int c = 0;
            for (WebElement product : yandexPage.getProductsList().findElements(By.xpath("//div[@data-apiary-widget-name=\"@marketfront/SerpEntity\"]"))) {
                Driver.createAction().moveToElement(product).build().perform();
                try {
                    String title = product.findElement(By.xpath(".//*[@data-auto=\"snippet-title\"]")).getText();
                    String price = product.findElement(By.xpath(".//*[@data-auto=\"snippet-price-current\"]")).findElement(By.xpath("span")).getText();
                    price = price.replaceAll("\\D+", "");
                    System.out.println(title + " " + price);
                } catch (NoSuchElementException e) {
                    continue;
                }
                if (c++ > 3) {
                    break;
                }
            }
        }catch (Exception e){
            System.out.println("Введите капчу");
        }
    }

    @Step("Сохранить первый товар")
    void check4Save1Product(){
        try {
            int k = 0;
            String title = "";
            String price = "";
            for (WebElement product : yandexPage.getProductsList().findElements(By.xpath("div"))) {
                if (k != 0) {
                    Driver.createAction().moveToElement(product).build().perform();
                    title = product.findElement(By.xpath(".//*[@data-auto=\"snippet-title\"]")).getText();
                    price = product.findElement(By.xpath(".//*[@data-auto=\"snippet-price-current\"]")).findElement(By.xpath("span")).getText();
                    price = price.replaceAll("\\D+", "");
                    this.productMap.put(title, price);
                    this.product = product;
                    break;
                }
                k++;
            }
            System.out.println(title + " " + price);
        }catch (Exception e){
            System.out.println("Введите капчу");
        }
    }

    @Step("Добавить товар в избранное")
    void check5AddToWishlist(){
        try {
            WebElement favBtn = yandexPage.getProductsList().findElement(By.xpath("//div")).findElement(By.xpath("//*[@title='Добавить в избранное']"));
            favBtn.click();
            Assert.assertEquals(favBtn.getAttribute("title"), "Удалить из избранного");
            Assert.assertEquals(yandexPage.getNotification().getText(), "Товар добавлен в избранное. Нажмите, чтобы перейти к списку.");
        }catch (Exception e){
            System.out.println("Введите капчу");
        }
    }

    @Step("Перейти в избранное")
    void check6GoToWishlist(){
        try{
        yandexPage.getWishlistPage().click();
        Assert.assertEquals(yandexPage.getWishlist().findElements(By.xpath("div")).size(), 1);
        WebElement product = yandexPage.getWishlist().findElement(By.xpath("div"));
        Assert.assertTrue(this.productMap.containsKey(product.findElement(By.xpath("//h3[@data-auto='snippet-title']")).getText()));
        Assert.assertTrue(this.productMap.containsValue(product.findElement(By.xpath("//span[@data-auto='snippet-price-current']")).findElement(By.xpath("span")).getText()));
        }catch (Exception e){
            System.out.println("Ввойдите в аккаунт");
        }
    }

    @Step("Удалить из избранного")
    void check7DeleteFromWishlist(){
        try{
        this.product.findElement(By.xpath("button[@title=\"Удалить из избранного\"]")).click();
        Assert.assertEquals(yandexPage.getNotification().findElement(By.xpath("a")).getText(),
                productMap.firstKey() + " удалён из избранного");
        }catch (Exception e){
            System.out.println("Войдите в аккаунт");
        }
    }

    @Step("Обновить страницу")
    void check8Refresh(){
        try {
            Driver.refresh();
            Assert.assertEquals(yandexPage.getEmptyState().getText(), "Войдите в аккаунт");
        }catch (Exception e){
            System.out.println("Войдите в аккаунт");
        }
    }
    @AfterClass
    public static void tearDown() {
        Driver.getDriver().quit();
    }
}