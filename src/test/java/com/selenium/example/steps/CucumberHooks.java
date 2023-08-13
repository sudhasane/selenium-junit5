package com.selenium.example.steps;

import com.selenium.example.utils.DriverUtility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.selenium.example.utils.DriverManager;

import java.io.IOException;
import java.util.Properties;


public class CucumberHooks {
    WebDriver driver;
    Properties properties;
    DriverManager driverManager = new DriverManager();

    @Before
    public void setup() {

        properties = driverManager.initProp();
        driver = driverManager.initDriver("chrome");
        driver.get("https://demo.applitools.com");
    }


    @After
    public void closeBrowser(Scenario scenario) throws InterruptedException, IOException {
        if (scenario.isFailed()) {
            byte[] src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", scenario.getName() + ".png");
        }
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
