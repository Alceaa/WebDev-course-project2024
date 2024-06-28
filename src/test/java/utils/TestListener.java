package utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;

public class TestListener implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.getLifecycle().addAttachment(
                "Скриншот падения теста: ", "image/png", "png",
                ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES)
        );
        Allure.addAttachment("Логи теста: ", String.valueOf(Driver.getDriver().manage().logs().get(LogType.BROWSER).getAll()));
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        Allure.addAttachment("Логи теста: ", String.valueOf(Driver.getDriver().manage().logs().get(LogType.BROWSER).getAll()));
    }
}
