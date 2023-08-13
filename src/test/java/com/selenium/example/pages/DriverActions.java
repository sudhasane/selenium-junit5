package com.selenium.example.pages;

import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.selenium.example.utils.DriverManager;

import java.time.Duration;
import java.util.*;

public class DriverActions {

   WebDriver driver = DriverManager.getDriver();

    public  final long FIVE_SECOND_PAUSE = 5000;

    Actions action = new Actions(driver);

    public  WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public  List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public  String getElementText(By locator) {
        return findElement(locator).getText();
    }

    public  boolean isElementDisplayed(By locator) {
        return findElement(locator).isDisplayed();
    }

    public  boolean isElementEnabled(By locator) {
        return findElement(locator).isEnabled();
    }

    public  void clearText(By locator) {
        findElement(locator).clear();
    }

    public  void sendKeys(By locator, String text) {
        findElement(locator).sendKeys(text);
    }

    public  void clickOnElement(By locator) {
        findElement(locator).click();
    }

    public  void clickOnElementByIndex(By locator, int index) {
        findElements(locator).get(index).click();
    }


    public  void clickWithJavaScript(By locator) {
        WebElement element = findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public  void refreshPage(By locator) {
        driver.navigate().refresh();
        //pageWait();
        waitForelementVisible(locator, Duration.ofSeconds(10));
    }
    public  void refresh(){
        driver.navigate().refresh();
       // pageWait();
    }

    public  String getElementAttribute(By locator, String atrName) {
        return findElement(locator).getAttribute(atrName);
    }

    public  String getCSSValue(By locator, String atrName) {
        return findElement(locator).getCssValue(atrName);
    }

    public WebDriverWait waitForElement() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait;
    }

    public  <T> ExpectedCondition<T> refreshed(final ExpectedCondition<T> condition) {
        return new ExpectedCondition<T>() {
            @Override
            public T apply(WebDriver driver) {
                try {
                    return condition.apply(driver);
                } catch (StaleElementReferenceException | UnhandledAlertException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("%s", condition);
            }
        };
    }

    private  void wait(ExpectedCondition<?> expCondition, Duration seconds) {
        for (int i = 1; i < 4; i++) {
            try {
                new WebDriverWait(driver, seconds).until(refreshed(expCondition));
                return;
            } catch (TimeoutException | UnreachableBrowserException te) {
                throw te;
            } catch (WebDriverException wde) {
                //                logWarning("Caught WebDriverException " + i + " times");
                waitForMilliSeconds(FIVE_SECOND_PAUSE);
            }
        }
        throw new RuntimeException("Webdriver exception failed more than 3 times");
    }

    public  void waitForMilliSeconds(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public  ExpectedCondition<WebElement> elementVisible(final By locator) {
        return ExpectedConditions.visibilityOfElementLocated(locator);
    }

    public  void waitForelementVisible(By locator, Duration waitTime) {
        wait(elementVisible(locator), waitTime);
    }

    public  void waitForelementClickable(By locator, Duration waitTime) {
        wait(elementClickable(locator), waitTime);
    }

    public  ExpectedCondition<WebElement> elementClickable(final By locator) {
        return ExpectedConditions.elementToBeClickable(locator);
    }

    public void doKeyBoardClick(String locator) {
        action.keyDown(Keys.LEFT_CONTROL).click((driver.findElement(By.xpath(locator)))).keyUp(Keys.LEFT_CONTROL).build().perform();
    }

    public void getWindowHandle(String windowName) {
        driver.switchTo().defaultContent();
        Set<String> tabs = driver.getWindowHandles();
        Iterator<String> iterator = tabs.iterator();
        driver.switchTo().window(iterator.next());
        driver.get(windowName);
    }

    public void actionMoveToElement(By locator) {
        action.moveToElement(findElement(locator)).click();
    }

    public void actionMoveToElementHover(By locator) {
        action.moveToElement(findElement(locator)).perform();
    }

    public boolean jsExecute(By locator) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth >0", findElement(locator));
    }

    public String getPageTitle() throws InterruptedException {
        Thread.sleep(500);
        return driver.getTitle();
    }

    public  boolean isElementPresent(By locator) {
        if (findElements(locator).size() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public  void waitForTitleIsVisible(String title, int waitTime) {
        title(title);
    }

    public  void scrollElementToView(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", findElement(locator));
    }

    public  ExpectedCondition<Boolean> title(String title) {
        return ExpectedConditions.titleIs(title);
    }

    public  void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public  void declineAlert() {
        driver.switchTo().alert().dismiss();
    }

    public  String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public  Boolean isClickable(By locator) {
        String str = findElement(locator).getAttribute("class");
        if (str.equals("disabled")) {
            return true;
        } else {
            return false;
        }

    }

    public  Boolean isButtonEnabled(By locator) {
        return findElement(locator).isEnabled();
    }

    public  void scrollDownThePage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    public  Boolean isColumnNameDisplayed(By locator) {
        return findElement(locator).isDisplayed();
    }

//    public  void pageWait() {
//        new WebDriverWait(driver, WAIT_FOR_30_SECS).until(
//                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public  String getSessionTokenFromCookie() {
        Set<Cookie> cookies = driver.manage().getCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String cookiesString = (String) js.executeScript("return document.cookie");
        cookies = parseBrowserCookies(cookiesString);
        for (Cookie st : cookies) {
            if (st.getName().equals("sessionToken")) {
                return st.getValue();
            }

        }
        return null;
    }

    public int getNumberOfelements(By locator) {
        return findElements(locator).size();
    }

    private  Set<Cookie> parseBrowserCookies(String cookiesString) {
        Set<Cookie> cookies = new HashSet<>();

        if (StringUtils.isBlank(cookiesString)) {
            return cookies;
        }
        Arrays.asList(cookiesString.split("; ")).forEach(cookie -> {
            String[] splitCookie = cookie.split("=", 2);
            cookies.add(new Cookie(splitCookie[0], splitCookie[1], "/"));
        });

        return cookies;
    }

    public void selectDropDownElement(By locator, String visibleText) {
        select(locator).selectByVisibleText(visibleText);
    }

    Select select(By locator) {
        return new Select(findElement(locator));
    }

    public  void waitForelementNotVisible(By locator, Duration waitTime) {
        wait(ExpectedConditions.invisibilityOfElementLocated(locator), waitTime);
    }
}
