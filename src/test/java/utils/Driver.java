package utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {
    private static EventFiringWebDriver driver;

    public static void setup(){
        ChromeOptions options = new ChromeOptions();
        ArrayList<String> opArgList = new ArrayList<>();
        opArgList.add("--no-sandbox");
        opArgList.add("--disable-dev-shm-usage");
        String[] opArg = opArgList.toArray(new String[0]);
        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.geolocation", -1);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments(opArg);

        driver = new EventFiringWebDriver(new ChromeDriver(options));
        driver.register(new LogsForAllure());

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static EventFiringWebDriver getDriver(){
        return driver;
    }
    public static void setDriverUrl(String url){
        driver.get(url);
    }

    public static Actions createAction(){
        return new Actions(driver);
    }
    public static WebDriverWait createWait(int ms){
        return new WebDriverWait(driver, Duration.ofMillis(ms));
    }
    public static String switchTo(int page){
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[page]);
        return driver.getTitle();
    }
    public static void refresh(){
        driver.navigate().refresh();
    }
}
