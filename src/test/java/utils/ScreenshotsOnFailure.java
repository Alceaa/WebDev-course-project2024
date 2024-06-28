package utils;

import java.io.File;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import org.junit.rules.MethodRule;

import org.junit.runners.model.FrameworkMethod;

import org.junit.runners.model.Statement;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

public class ScreenshotsOnFailure implements MethodRule {

    private WebDriver driver;

    public ScreenshotsOnFailure(WebDriver driver){
        this.driver = driver;
    }

    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {
                    captureScreenShot(frameworkMethod.getName());
                    throw t;
                }
            }

            public void captureScreenShot(String fileName) throws IOException {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                fileName += new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()).toString();
                File targetFile = new File("./Screenshots/" + fileName + ".png");
                FileUtils.copyFile(scrFile, targetFile);
            }
        };
    }
}