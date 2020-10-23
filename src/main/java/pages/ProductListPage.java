package pages;

import base.TestBase;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import util.ReadTestData;

import java.io.IOException;
import java.util.List;

import static base.PageBase.WAIT_TIME;

public class ProductListPage extends TestBase {
    RemoteWebDriver driver;

    public static String PRODUCT_NAME;


    public ProductListPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIME), this);
    }

    @FindBy(css = ".resultListGroup")
    public WebElement productListGroup;

    @FindBy(css = ".pagination a")
    public List<WebElement> pagination;

    @FindBy(css = ".resultListGroup div[id].columnContent")
    public List<WebElement> productList;

    @FindBy(xpath = "//*[@id='view']/ul/li[3]//*[@title='Favorilere ekle']")
    public WebElement followProduct;

    @FindBy(xpath="//*[@id='view']/ul/li[3]")
    public WebElement followProduct2;

    @FindBy (xpath ="//a[contains(text(),'Hesabım')]")
    public WebElement clickFollowProduct;

    @FindBy(xpath = "//*[@title='Favorilerim / Listelerim']")
    public WebElement clickFollow;


    public ProductListPage assertProductListDisplayed() {
        System.out.println("Aratılan ürün için listeleme ekranı geldi mi ?");
        Assert.assertTrue(productListGroup.isDisplayed());
        return this;
    }

    public ProductListPage assertProductListPageIsLoad() throws IOException, InterruptedException {
        System.out.println("aratılan ürün için doğru sayfaya yönledirildi mi ?");
        ReadTestData readTestData = new ReadTestData();
            Thread.sleep(1500);
        Assert.assertTrue("link hatalı; " + driver.getCurrentUrl(), driver.getCurrentUrl().contains("arama?q=" + readTestData.getSearchKeyword()));
        assertProductListDisplayed();
        return this;
    }

    public ProductListPage clickPagination(int pageNumber) throws InterruptedException {
        System.out.println("istenilen sayfaya tıklıyor mu?");
        moveElement(driver, pagination.get(pageNumber - 1));
        pagination.get(pageNumber - 1).click();
        return this;
    }

    public ProductListPage assertPageNumber(int pageNumber) throws InterruptedException {
        System.out.println("istenilen sayfa doğru geldi mi ?");
        for (WebElement pageIndexElm : pagination) {
            if (pageIndexElm.getAttribute("class").contains("active")) {
                Assert.assertEquals(pageIndexElm.getText(), String.valueOf((pageNumber)));
                Assert.assertEquals(pageIndexElm.getAttribute("href"), driver.getCurrentUrl());
            }
        }
        return this;
    }

    public ProductListPage clickSearchProduct(){
        System.out.println("üstten 3. ürünün favorilere ekle butonuna tıklıyor mu ?");
        followProduct.click();
        return this;
    }

    public FollowProductPage clickfavoritePage(){
        Actions a = new Actions(driver);
        System.out.println("sayfanın üzerinde yer alan favorilerim alanına tıklıyor mu ?");
        scrollToElement(driver,clickFollowProduct);
        a.moveToElement(clickFollowProduct).build().perform();
        a.moveToElement(clickFollow).click().perform();
        return new FollowProductPage(driver);
    }


}
