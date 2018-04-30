
package test.java.com.site.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.stereotype.Component;

/**
 * CLASS / TYPE DESCRIPTION GOES HERE.
 *
 * @author jdoriya 09-Jul-2016
 *
 */
@Component
public class BrowserConfig extends Properties {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public BrowserConfig() throws FileNotFoundException, IOException {

        InputStream is = this.getClass().getClassLoader().getResourceAsStream("browser.win.properties");
        load(is);

    }

    public BrowserConfig(String fileLocation) throws FileNotFoundException, IOException {

        load(new FileInputStream(fileLocation));

    }
}
