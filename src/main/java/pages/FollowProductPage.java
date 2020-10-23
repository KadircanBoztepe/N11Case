package pages;

import base.TestBase;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static base.PageBase.WAIT_TIME;

public class FollowProductPage extends TestBase {
    RemoteWebDriver driver;


    public FollowProductPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIME), this);
    }

    @FindBy (css =".accTitle")
    public WebElement assertFollowPage;

    @FindBy(css=".listItemTitle")
    public WebElement followDetailPage;



    public FollowProductPage assertFollowProductDisplayed() {
        System.out.println("Aratılan ürün için listeleme ekranı geldi mi ?");
        Assert.assertTrue(assertFollowPage.isDisplayed());
        return this;
    }

    public FollowProdcutDetailPage clickFollowDetailPage(){
        System.out.println("Favorim listesindeki ürün için favori detay sayfasına tıklıyor mu ?");
        followDetailPage.click();
        return new FollowProdcutDetailPage(driver);
    }


}
