package pages;

import base.TestBase;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.Set;

import static base.PageBase.WAIT_TIME;

public class LoginPage extends TestBase {
    RemoteWebDriver driver;
    public static String CURRENT_WINDOW_HANDLE;

    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIME), this);
    }


    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    @FindBy(css = " div.simple.login:nth-child(3) div.container.container_1180 div.form.registerFormFieldset.loginPage:nth-child(3) div:nth-child(1) div.fieldset div.leftBlock div.blockWrap > div.facebook_large.medium.facebookBtn.btnLogin:nth-child(6)")
    private WebElement facebooklogin;


    public LoginPage assertSignInPage() {
        System.out.println("giriş yap sayfası açıldı mı ?");
        Assert.assertEquals("Giriş Yap", loginButton.getText());
        return this;
    }


    public FacebookPage clickFacebook() throws InterruptedException {
        System.out.println("Facebook ile giriş yap butonuna tıklıyor mu ?");
        CURRENT_WINDOW_HANDLE = driver.getWindowHandle();
        facebooklogin.click();
        Set<String> winHandle = driver.getWindowHandles();
        for (String windHnd : winHandle) {
            driver.switchTo().window(windHnd);
        }
        return new FacebookPage(driver);
    }


}




