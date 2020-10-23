package tests;

import base.PageBase;
import enums.LoginInfo;
import org.junit.Test;
import pages.HomePage;
import util.Description;

public class CoreTest extends PageBase {

    @Test
    @Description("Anasayfanın açıldığı onaylanıyor.")
    public void assertHomePageTest() throws Throwable {
        HomePage homePage = new HomePage(driver);
        homePage.closedPopUpbButton()
                 .assertHomePageIsDisplayed();
    }

     @Test
    @Description("Login ekranı açılıp, bir kullanıcı ile login olunuyor.")
    public void successLoginTest() throws Throwable {
         HomePage homePage = new HomePage(driver);
         homePage.closedPopUpbButton()
                 .assertHomePageIsDisplayed()
                 .clickBtnSignIn()
                 .assertSignInPage()
                 .clickFacebook()
                 .assertFacebookPopup()
                 .typeFacebookEmail(LoginInfo.CORRECT_MAIL)
                 .typeFacebookPassword(LoginInfo.CORRECT_PASSWORD)
                 .clickFacebookSignIn()
                 .switchCurrentWindow()
                 .assertHomePageIsDisplayed()
                 .clearSearchData()
                 .typeSearchData()
                 .clickSearchButton()
                 .assertProductListPageIsLoad()
                 .assertProductListDisplayed()
                 .clickPagination(2)
                 .assertPageNumber(2)
                 .clickSearchProduct()
                 .clickfavoritePage()
                 .assertFollowProductDisplayed()
                 .clickFollowDetailPage()
                 .assertFollowProductDetailDisplayed()
                 .deleteFollowProduct()
                 .deleteFollowProductPopup()
                 .notFoundFollowProductDetailPage();

     }

}