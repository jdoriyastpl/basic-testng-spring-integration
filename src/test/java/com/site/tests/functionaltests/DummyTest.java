
package test.java.com.site.tests.functionaltests;

import test.java.com.site.pages.HomePage;
import test.java.com.site.pages.RegistrationPage;
import test.java.com.site.tests.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * CLASS / TYPE DESCRIPTION GOES HERE.
 *
 * @author jdoriya 09-Jul-2016
 *
 */
public class DummyTest extends TestBase {

    protected static final Logger LOG = Logger.getLogger(DummyTest.class);
    @Test
    void test() {
        LOG.info("Test Started Now");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToProsperHomePage();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.test();
    }

}
