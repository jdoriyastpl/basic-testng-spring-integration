
package test.java.com.site.tests;

import test.java.com.site.framework.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 * CLASS / TYPE DESCRIPTION GOES HERE.
 *
 * @author jdoriya 10-Jul-2016
 *
 */
public abstract class TestBase {

    protected WebDriver driver;
    private ApplicationContext applicationContext;

    @BeforeSuite(alwaysRun = true)
    public void setApplicationContext() {
        initializeSystemProperties();
    }

    /**
     *
     */
    private void initializeSystemProperties() {

        applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);

    }


    @BeforeMethod(alwaysRun = true)
    public WebDriver setUp() {
        driver = WebDriverManager.getWebDriver();
        return driver;
    }

}
