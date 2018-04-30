
package test.java.com.site.framework;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CLASS / TYPE DESCRIPTION GOES HERE.
 *
 * @author jdoriya 10-Jul-2016
 *
 */
public abstract class BasePage {

    protected static final Logger LOG = Logger.getLogger(BasePage.class);
    private WebDriver webDriver;
    public static final int PAGE_LOAD_TIME_OUT_SECONDS = 60;
    private int defaultWaitTime = PAGE_LOAD_TIME_OUT_SECONDS;
    private int pollingNanoseconds = PAGE_LOAD_TIME_OUT_SECONDS * 1000;
    @Autowired
    private WaitFactory waitFactory;

    private SelectFactory selectFactory;
    private List<WebDriverException> exceptionList;


    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        waitFactory = new WaitFactory();
        PageFactory.initElements(webDriver, this);

    }

    public WebDriver getWebDriver() {

        return webDriver;
    }

    public void quitWebDriver() {
        webDriver.quit();
    }

    public WebDriverWait getWait() {
        return waitFactory.newWebDriverWait(webDriver, defaultWaitTime);
    }

    public void setDefaultWaitTime(final int timeoutSeconds) {
        this.defaultWaitTime = timeoutSeconds;
    }

    public WebDriverWait defaultWait() {
        return this.waitFactory.newWebDriverWait(webDriver, defaultWaitTime);
    }

    public FluentWait<WebDriver> customWait(String message) {
        return this.waitFactory.newFluentWait(webDriver, message, defaultWaitTime, pollingNanoseconds);
    }

    public void waitForId(String message, String id) {
        defaultWait().withMessage(message);
        waitForElementToBePresent(By.id(id), message, defaultWaitTime, pollingNanoseconds);
        // defaultWait().until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    public void waitForIdThenClick(String message, String id) {
        try {

            waitForId(message, id);
            webDriver.findElement(By.id(id)).click();
        } catch (TimeoutException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(id, "");
            exceptionList.add(e);

        } catch (NoSuchElementException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(id, "");
            exceptionList.add(e);

        }
    }

    public void waitForIdThenType(String message, String id, String data) {
        try {

            waitForId(message, id);
            webDriver.findElement(By.id(id)).sendKeys(data);
        } catch (TimeoutException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(id, "");
            exceptionList.add(e);

        } catch (NoSuchElementException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(id, "");
            exceptionList.add(e);

        }
    }

    public void waitForIdClearThenType(String message, String id, String data) {
        try {

            waitForId(message, id);
            webDriver.findElement(By.id(id)).clear();
            webDriver.findElement(By.id(id)).sendKeys(data);
        } catch (TimeoutException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(id, "");
            exceptionList.add(e);

        } catch (NoSuchElementException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(id, "");
            exceptionList.add(e);

        }
    }

    public void waitForIdThenSendKeys(String message, String id, Keys keys) {
        try {

            waitForId(message, id);
            getWebDriver().findElement(By.id(id)).sendKeys(keys);
        } catch (TimeoutException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(id, "");
            exceptionList.add(e);

        } catch (NoSuchElementException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(id, "");
            exceptionList.add(e);

        }
    }

    public void waitForXPathThenType(String message, String xpath, String selectString) {
        try {

            waitForXPath(message, xpath);
            waitForXPathThenClick(message, xpath);
            webDriver.findElement(By.xpath(xpath)).sendKeys(selectString);
        } catch (TimeoutException e) {

            //
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(xpath, "");
            exceptionList.add(e);

        } catch (NoSuchElementException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(xpath, "");
            exceptionList.add(e);

        }
    }

    public void waitForXPathThenClick(String message, String xpath) {
        try {

            waitForXPath(message, xpath);
            webDriver.findElement(By.xpath(xpath)).click();
        } catch (TimeoutException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(xpath, "");
            exceptionList.add(e);

        } catch (NoSuchElementException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(xpath, "");
            exceptionList.add(e);

        }
    }

    private void waitForXPath(String message, String xpath) {
        try {

            defaultWait().withMessage(message);
            waitForElementToBePresent(By.xpath(xpath), message, defaultWaitTime, pollingNanoseconds);
        } catch (TimeoutException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(xpath, "");
            exceptionList.add(e);

        } catch (NoSuchElementException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(xpath, "");
            exceptionList.add(e);

        }
    }

    public void waitForElementToAppear(final By selector, final String message, final int timeoutSeconds,
                                       final int pollingNanoseconds) {
        try {

            waitFactory.newFluentWait(webDriver, message, timeoutSeconds, pollingNanoseconds)
                    .until(ExpectedConditions.visibilityOfElementLocated(selector));
        } catch (TimeoutException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(selector.toString(), "");
            exceptionList.add(e);

        } catch (NoSuchElementException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(selector.toString(), "");
            exceptionList.add(e);

        }
    }

    public void waitForElementToBePresent(final By selector, final String message, final int timeoutSeconds,
                                          final int pollingNanoseconds) {
        try {

            waitFactory.newFluentWait(webDriver, message, timeoutSeconds, pollingNanoseconds)
                    .until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (TimeoutException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(selector.toString(), "");
            exceptionList.add(e);

        } catch (NoSuchElementException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(selector.toString(), "");
            exceptionList.add(e);

        }
    }

    public void waitForElementToNotBePresent(final By selector, final String message, final int timeoutSeconds,
                                             final int pollingNanoseconds) {

        ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {

            public Boolean apply(final WebDriver webDriver) {
                try {
                    webDriver.findElement(selector);
                    return false;
                } catch (NoSuchElementException e) {
                    return true;
                }
            }
        };
        waitFactory.newFluentWait(webDriver, message, timeoutSeconds, pollingNanoseconds).until(condition);

    }

    public void waitForIdThenSelect(String message, String id, String selectString) {
        try {

            waitForId(message, id);
            Select select = selectFactory.createNewSelect(webDriver.findElement(By.id(id)));
            select.selectByVisibleText(selectString);
        } catch (TimeoutException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(id, "");
            exceptionList.add(e);

        } catch (NoSuchElementException e) {
            LOG.warn("Exception while trying to locate element.", e);
            e.addInfo(id, "");
            exceptionList.add(e);

        }
    }


    public class SelectFactory {

        public Select createNewSelect(WebElement webElement) {
            return new Select(webElement);
        }
    }
}
