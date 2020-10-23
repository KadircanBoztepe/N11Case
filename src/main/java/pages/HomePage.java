package pages;

import base.TestBase;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import util.ReadTestData;

import java.io.IOException;

import static base.PageBase.WAIT_TIME;

public class HomePage extends TestBase {
    RemoteWebDriver driver;

    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIME), this);
    }

    @FindBy(css = ".topMainSlide")
    private WebElement homePageBanner;

    @FindBy(css = ".btnHolder")
    private WebElement buttonPopup;

    @FindBy(css = ".btnSignIn")
    private WebElement signInButton;

    @FindBy(css = ".user")
    private WebElement user;

    @FindBy(id = "searchData")
    private WebElement searchData;

    @FindBy(css = ".searchBtn")
    private WebElement searchButton;

    public HomePage assertHomePageIsLoad() throws IOException {

        Assert.assertTrue("home page not loaded", driver.getCurrentUrl().contains("n11.com"));
        assertHomePageIsDisplayed();
        return this;
    }

    public HomePage closedPopUpbButton() {
        System.out.println("Giriş sayfasındaki pop-up'ın tamam butonuna basıyor mu?");
        buttonPopup.click();
        return this;
    }

    public HomePage assertHomePageIsDisplayed() {
        System.out.println("giriş sayfası görüntülendi mi bakılıyor ?");
        Assert.assertTrue(homePageBanner.isDisplayed());
        return this;
    }

    public LoginPage clickBtnSignIn() {
        System.out.println("Giriş yap butonuna tıklıyor mu ?");
        signInButton.click();
        return new LoginPage(driver);
    }

    public HomePage clearSearchData() {
        System.out.println("arama inputun içerisi temizleniyor mu ?");
        searchData.clear();
        return this;
    }

    public HomePage typeSearchData() throws IOException {
        System.out.println("arama inputuna Samsung yazılıyor mu ?");
        ReadTestData readTestData = new ReadTestData();
        searchData.sendKeys(readTestData.getSearchKeyword());
        return this;
    }

    public ProductListPage clickSearchButton() {
        System.out.println("Arama butonuna tıklıyor mu ?");
        searchButton.click();
        return new ProductListPage(driver);
    }

    public HomePage switchCurrentWindow() {
        driver.switchTo().window(LoginPage.CURRENT_WINDOW_HANDLE);
        return this;
    }

}
