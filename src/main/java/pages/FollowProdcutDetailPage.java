package pages;

import base.TestBase;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static base.PageBase.WAIT_TIME;

public class FollowProdcutDetailPage extends TestBase {
    RemoteWebDriver driver;

    public FollowProdcutDetailPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIME), this);
    }

    @FindBy (xpath="//h2[contains(text(),'Favorilerim')]")
    public WebElement assertFollowProductDetailPage;

    @FindBy (xpath ="//span[contains(text(),'Sil')]")
    public WebElement deleteProductButton;

    @FindBy (xpath= "//span[contains(text(),'Tamam')]")
    public WebElement deletePopUpOkeyButton;

    @FindBy (xpath="//div[@id='watchList']")
    public WebElement notFoundFollowProductDetailPage;

    public FollowProdcutDetailPage assertFollowProductDetailDisplayed() {
        System.out.println("Aratılan ürün için listeleme ekranı geldi mi ?");
        Assert.assertTrue(assertFollowProductDetailPage.isDisplayed());;
        return this;
    }

    public FollowProdcutDetailPage deleteFollowProduct(){
        System.out.println("Favorilere alınan ürün silindi mi ?");
        deleteProductButton.click();
        return this;
    }

    public FollowProdcutDetailPage deleteFollowProductPopup(){
        System.out.println("Ürünü silince çıkan pop-up'ta tamam butonuna tıklıyor mu?");
        deletePopUpOkeyButton.click();
        return this;
    }

    public FollowProdcutDetailPage notFoundFollowProductDetailPage(){
        System.out.println("Favorilerim boş mu kontrol ediliyor ?");
        Assert.assertTrue(notFoundFollowProductDetailPage.isDisplayed());
        return this;
    }


}
