package task5;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfProperties;
import utils.Driver;
import utils.ScreenshotsOnFailure;

public class WebTest extends BaseTest{
    @Rule
    public ScreenshotsOnFailure failure = new ScreenshotsOnFailure(Driver.getDriver());

    @BeforeClass
    public static void setUp(){
        BaseTest.setUp();
        requestSpecification.basePath("");
        Driver.setup();
        Driver.setDriverUrl(ConfProperties.getProperty("reqres"));
        webPage = new WebPage(Driver.getDriver());
    }

    @Test
    public void checkUsers(){
        String response =  checkStatusCodeGet("/users/2", 200).extract().body().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"users-single\"]")).click();
        WebDriverWait wait = Driver.createWait(100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }

    @Test
    public void checkUser(){
        String response =  checkStatusCodeGet("/users/2", 200).extract().body().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"users-single\"]")).click();
        WebDriverWait wait = Driver.createWait(100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }

    @Test
    public void checkEmptyUser(){
        String response =  checkStatusCodeGet("/users/22", 404).extract().body().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"users-single-not-found\"]")).click();
        WebDriverWait wait = Driver.createWait(100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }

    @Test
    public void checkNewUser(){
        PostUser user = new PostUser("morpheus", "leader");
        String response =  checkStatusCodeGet("/users", user, 201).extract().body().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"post\"]")).click();
        WebDriverWait wait = Driver.createWait(100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }

    @Test
    public void checkPutUpdate(){
        PostUser user = new PostUser("morpheus", "zion resident");
        String response =  checkStatusCodePut("/users/2", user, 200).extract().body().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"put\"]")).click();
        WebDriverWait wait = Driver.createWait(100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }

    @Test
    public void checkPatchUpdate(){
        PostUser user = new PostUser("morpheus", "zion resident");
        String response =  checkStatusCodePatch("/users/2", user, 200).extract().response().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"patch\"]")).click();
        WebDriverWait wait = Driver.createWait(100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }

    @Test
    public void checkDelete(){
        String response =  checkStatusCodeDelete("/users/2", 204).extract().body().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"delete\"]")).click();
        WebDriverWait wait = Driver.createWait(300);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }

    @Test
    public void checkDelay(){
        String response =  checkStatusCodeGet("/users?delay=3", 200).extract().body().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"delay\"]")).click();
        WebDriverWait wait = Driver.createWait(4000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }

    @Test
    public void checkRegister(){
        AuthUser user = new AuthUser("eve.holt@reqres.in", "pistol");
        String response =  checkStatusCodeGet("/register", user, 200).extract().response().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"register-successful\"]")).click();
        WebDriverWait wait = Driver.createWait(100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }

    @Test
    public void checkRegisterUnsuccessful(){
        AuthUserUnsuccess user = new AuthUserUnsuccess("sydney@fife");
        String response =  checkStatusCodeGet("/register", user, 400).extract().body().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"register-unsuccessful\"]")).click();
        WebDriverWait wait = Driver.createWait(300);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }

    @Test
    public void checkLogin(){
        AuthUser user = new AuthUser("eve.holt@reqres.in", "cityslicka");
        String response =  checkStatusCodeGet("/login", user, 200).extract().body().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"login-successful\"]")).click();
        WebDriverWait wait = Driver.createWait(100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }

    @Test
    public void checkLoginUnsuccessful(){
        AuthUserUnsuccess user = new AuthUserUnsuccess("peter@klaven");
        String response =  checkStatusCodeGet("/login", user, 400).extract().body().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"login-unsuccessful\"]")).click();
        WebDriverWait wait = Driver.createWait(100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }

    @Test
    public void checkListResource(){
        String response =  checkStatusCodeGet("/unknown", 200).extract().body().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"unknown\"]")).click();
        WebDriverWait wait = Driver.createWait(100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }
    @Test
    public void checkResource(){
        String response =  checkStatusCodeGet("/unknown/2",200).extract().body().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"unknown-single\"]")).click();
        WebDriverWait wait = Driver.createWait(100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }

    @Test
    public void checkEmptyResource(){
        String response =  checkStatusCodeGet("/unknown/23",404).extract().body().asString().replaceAll(" ", "");
        webPage.getButtons().findElement(By.xpath(".//li[@data-id=\"unknown-single-not-found\"]")).click();
        WebDriverWait wait = Driver.createWait(100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-key=\"output-response\"]")));
        Assert.assertEquals(response, webPage.getOutput().getText().replaceAll(" ", "").replaceAll("\n", ""));
    }
}
