package base;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PageBase {
    public RemoteWebDriver driver;
    public static int WAIT_TIME = 15;
    public static int IMPLICITLY_WAIT = 20;
    private static int PAGE_LOAD_TIMEOUT = 30;
    public static String BROWSER = System.getProperty("browser").toLowerCase();

    @Before

    public void setUp() throws IOException {
        if (BROWSER == null)
              BROWSER = "chrome";
            switch (BROWSER) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--ignore-certifcate-errors");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--disable-plugins");
                    chromeOptions.addArguments("--disable-plugins-discovery");
                    chromeOptions.addArguments("--disable-preconnect");
                    chromeOptions.addArguments("'--dns-prefetch-disable'");
                    //chromeOptions.addArguments("--disable-features=CookiesWithoutSameSiteMustBeSecure,SameSiteByDefaultCookies");
                    chromeOptions.setAcceptInsecureCerts(true);
                    chromeOptions.addArguments("test-type");
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver.exe");
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().window().maximize();


                    HashMap<String, Object> chromeLocalStatePrefs = new HashMap<String, Object>();
                    List<String> experimentalFlags = new ArrayList<String>();
                    experimentalFlags.add("same-site-by-default-cookies@2");
                    experimentalFlags.add("cookies-without-same-site-must-be-secure@2");
                    chromeLocalStatePrefs.put("browser.enabled_labs_experiments", experimentalFlags);
                    chromeOptions.setExperimentalOption("localState", chromeLocalStatePrefs);

                    break;

                case "firefox":
                    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
            }
        Configuration configuration = new Configuration();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
        driver.get(configuration.getBaseUrl());

    }

    @Rule
    public final TestRule watchman = new TestWatcher() {
        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }


        @Override
        protected void failed(Throwable e, Description description) {

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                File currPath = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\failImages\\" + driver.getSessionId() + "-screenshot.png");
                System.out.println("Screenshot at: " + currPath.toString());
                FileHandler.copy(scrFile, currPath);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        @AfterClass
        protected void finished(Description description) {
            if (driver != null)
                driver.quit();
            System.out.println("Test sonlandı.");
        }
    };

}
