
package test.java.com.site.framework;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

/**
 * CLASS / TYPE DESCRIPTION GOES HERE.
 *
 * @author jdoriya 10-Jul-2016
 *
 */
@Component
public class WaitFactory {

    public FluentWait<WebDriver> newFluentWait(WebDriver webDriver,
                                               final String message, final int timeoutSeconds,
                                               final int pollingNanoseconds) {
        return new FluentWait<WebDriver>(webDriver)
                .withTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .pollingEvery(pollingNanoseconds, TimeUnit.NANOSECONDS)
                .withMessage(message).ignoring(NoSuchElementException.class);
    }

    public WebDriverWait newWebDriverWait(WebDriver webDriver,
                                          final int timeoutSeconds) {
        return new WebDriverWait(webDriver, timeoutSeconds);
    }
}
