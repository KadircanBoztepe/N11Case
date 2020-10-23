package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {
    public JavascriptExecutor javascriptExecutor(RemoteWebDriver driver) {
        return driver;
    }

    public void clickElementWithJS(RemoteWebDriver driver, WebElement element) {

        javascriptExecutor(driver).executeScript("arguments[0].click();", element);
    }

    public void clickElementWithJS2(RemoteWebDriver driver, String element) {

        javascriptExecutor(driver).executeScript("document.querySelector('" + element + "').click();");
    }

    public void moveElement(RemoteWebDriver driver, WebElement webElement) throws InterruptedException {
        Actions actions = new Actions(driver);
        try {
            scrollToOfSet(driver, webElement.getLocation().x, webElement.getLocation().y);
            Thread.sleep(500);
            actions.moveToElement(webElement).build().perform();
        } catch (MoveTargetOutOfBoundsException me) {
            scrollToElement(driver, webElement);
            Thread.sleep(500);
            actions.moveByOffset(webElement.getLocation().x, webElement.getLocation().y).build().perform();
        }
        Thread.sleep(1000);
    }

    public void scrollToElement(RemoteWebDriver driver, WebElement webElement) {
        javascriptExecutor(driver).executeScript("window.scrollTo(" + webElement.getLocation().x + "," + webElement.getLocation().y + ");");
    }

    public void scrollToOfSet(RemoteWebDriver driver, int x, int y) {
        javascriptExecutor(driver).executeScript("window.scrollTo(" + x + "," + y + ");");
    }


}
