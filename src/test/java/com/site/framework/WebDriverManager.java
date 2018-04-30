
package test.java.com.site.framework;

import test.java.com.site.config.AppConfig;
import test.java.com.site.config.BrowserConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverManager {

    static Logger logger = Logger.getLogger(WebDriverManager.class);
    public static WebDriver driver;


    /*
     * public static final String MODE_KEY="mode"; public static final String HUB_URL_KEY="hubUrl"; public static final String
     * DESIRED_PLATFORM_KEY="platform"; public static final String DESIRED_BROWSER_VERSION="browserVersion"; public static final
     * String BROSER_KEY="browser";
     */

    public static WebDriver getWebDriver() {

        try {

            String browser = new BrowserConfig().getProperty("browserType");
            // String browserVersion= getProperty(DESIRED_BROWSER_VERSION);
            // DesiredCapabilities caps;

            if ("firefox".equalsIgnoreCase(browser)) {
                /* JavaScriptError.addExtension(ffProfile); */
                driver = new FirefoxDriver();

                getEnvUrl(driver);
                return driver;

            } else if ("ie".equalsIgnoreCase(browser)) {
                File file = new File(System.getProperty("user.dir") + "src/test/resources/IEDriverServer.exe");
                // get the path of webdriver exe
                System.setProperty("webdriver.ie.driver",
                        file.getAbsolutePath());

                DesiredCapabilities capabilities = DesiredCapabilities
                        .internetExplorer();
                capabilities.setCapability(" ignoreZoomSetting", true);
                capabilities
                        .setCapability(
                                InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                                true);
                driver = new InternetExplorerDriver(capabilities);
                getEnvUrl(driver);
                return driver;

            } else if ("chrome".equalsIgnoreCase(browser)) {
                File file = new File(
                        new BrowserConfig().getProperty("ChromeServerPath"));
                System.setProperty("webdriver.chrome.driver",
                        file.getAbsolutePath());
                driver = new ChromeDriver();
                getEnvUrl(driver);
                return driver;
            }
        } catch (Exception ex) {
            logger.info("Inside Get Web Driver: " + ex.getMessage());
        }
        return driver;
    }

    // Based Upon Environment specified In config Webdriver will get the
    // URL to run Automation Suite
    public static void getEnvUrl(WebDriver driver) throws FileNotFoundException, IOException {
        driver.manage().timeouts().pageLoadTimeout(BasePage.PAGE_LOAD_TIME_OUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get(new AppConfig().getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }
}
