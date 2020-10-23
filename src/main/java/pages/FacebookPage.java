package pages;

import base.TestBase;
import enums.LoginInfo;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;

import static base.PageBase.WAIT_TIME;

public class FacebookPage extends TestBase {

    public FacebookPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIME), this);
    }
    RemoteWebDriver driver;

    @FindBy(id ="homelink")
    private WebElement facebookHomeLink;

    @FindBy(id = "email")
    private WebElement facebookEmail;

    @FindBy(id="pass")
    private WebElement facebookPass;

    @FindBy(id ="loginbutton")
    private WebElement facebookLoginButton;

    public FacebookPage assertFacebookPopup() {
        System.out.println(" Facebook giriş yap sayfası açıldı mı ?");
        Assert.assertTrue(facebookLoginButton.isDisplayed());
        return this;
    }

    public FacebookPage typeFacebookEmail(LoginInfo eMail) throws IOException {
        System.out.println("mail adresi giriliyor mu ?");
       facebookEmail.sendKeys(eMail.getLoginInfo());
        return this;
    }

    public FacebookPage typeFacebookPassword(LoginInfo passwordd) throws IOException {
        System.out.println("şifre giriliyor mu ?");
        facebookPass.sendKeys(passwordd.getLoginInfo());
        return this;
    }

    public HomePage clickFacebookSignIn() throws InterruptedException {
        System.out.println("Facebook giriş yap butonuna tıklıyor mu ?");
        //facebookLoginButton.click();
        clickElementWithJS2(driver,"#loginbutton");
        return new HomePage(driver);
    }


    public static class FollowProductPage extends TestBase {
        RemoteWebDriver driver;

        public FollowProductPage(RemoteWebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIME), this);
        }

        @FindBy (css = ".accTitle")
        public WebElement assertFollowPage;

        @FindBy (css = ".listItemTitle")
        public WebElement listItemWrap;

        @FindBy ( css = ".columnContent ")
        public WebElement columnContent;

        @FindBy (xpath = "//h2[contains(text(),'Favorilerim')]")
        public WebElement followProductPage;

        public FollowProductPage assertFollowProdcutPage() {
            System.out.println("favorilerim sayfası açıldı mı?");
            Assert.assertEquals("Favorilerim / Listelerim", assertFollowPage.getText());
            return this;
        }

        public FollowProductPage clickFollowProduct(){
            System.out.println("Favoriye alınan ürüne tıklıyor mu ?");
            listItemWrap.click();
            return this;
        }
        public FollowProductPage assertFollowProdcutPageDetails() {
            System.out.println("favorilerim detay sayfası açıldı mı?");
            Assert.assertEquals("Favorilerim / Listelerim", followProductPage.getText());
            return this;

        }


    }
}


