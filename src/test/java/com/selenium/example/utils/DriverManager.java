package com.selenium.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.Properties;

public class DriverManager {
 OptionsManager optionsManager;
    public Properties properties;
    private static final String PATH = "/config.properties";
    public static  ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver initDriver(String browser) {
        System.out.println("Browser value is : " + browser);
        optionsManager = new OptionsManager(properties);

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        }

        else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
        }

        else {
            System.out.println("Please enter the right browser name : " + browser);
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return driver.get();
    }


    public Properties initProp() {
      final String propertyFilePath= "src/test/java/config/config.properties";

            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(propertyFilePath));
                properties = new Properties();
                try {
                    properties.load(reader);
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
            }
        return properties;
    }

}
