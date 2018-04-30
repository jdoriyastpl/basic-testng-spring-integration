
package test.java.com.site.html;

import test.java.com.site.config.AppConfig;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * This class represents a library of html component ids for the various screens in the XYZ application. It is designed to be used
 * in acceptance test projects to increase productivity in authoring browser interactions and reduce the duplication of effort
 * across projects and sprints. Any id added to this class should be added according to the following structure :-
 *
 * <pre>
*     class ComponentIds.
*               static class [XYZ screen].
*                                         static class [section of screen].
*                                                                          static field [html component]
* </pre>
 *
 * <XYZ screen>, <section of screen> (sub-sections can also be added where applicable) and <html component> should describe the of
 * classes and fields should be capitalized and word-separated with underscores; and html component field names should be visual
 * constructs which are apparent to an end user, not programmatic constructs in the corresponding Application source. All names
 * prefixed with their type in lower case to ease indentification. At present the pool of types is :
 *
 * <pre>
*     image, radio, textbox, dropdown, menu, textarea, ui,
*     list, label, button, span, element_group, input, link, tab
* </pre>
 *
 * This can be added to as neccessary, but care must be taken to avoid duplication and ensure consistency. This should be
 * accomplished by manually examining the id in the appropriate html browser source,adding a new field definition in the
 * appropriate structure within ComponentIds (or creating said structure if it doesn't exist). For the value of each field, a
 * string containing the id is not appropriate; the id must be overrideable from a properties file :-
 *
 * To understand the reccomended use of this class compare the following examples:
 *
 * <pre>
*     waitForIdThenType("message","username", "1300000");
*     waitForIdThenType("message", ComponentIds.LOGIN....input_USER_NAME, "1300000");
* </pre>
 *
 * In the first example, the id for the component to be modified is hard-coded. Regardless of whether another test in another
 * project already interacts with this component, the developer will have to inspect the webpage manually, copy the id and paste
 * it into the source code of his test. This effort may be repeated an arbitrary number of times, which is very time consuming. In
 * the second example, the developer is using the ComponentIds class to retrieve the id required. Assuming a previous developer
 * has already interacted with this component and saved its id, the developer of this new test can simply use auto-complete to
 * drill down through the nested structures. It is more than likely the webpage might never need to be opened, never mind
 * examined, while authoring his test. If we examine the drill-down, we can see the logical structure detailed above :-
 *
 * <pre>
*     ComponentIds.LOGIN....input_USER_NAME
*     ComponentIds.[  Screen ].[   Section    ].[          Component            ]
* </pre>
 *
 * The preceding example assumes tha another developer has already added the required id. Of course, this will not always be the
 * case; once you are sure the id does not currently exist in ComponentIds, it should be added.
 */
public class ComponentIds {

    private static Properties overrides;
    private static final Logger LOG = Logger.getLogger(ComponentIds.class);


    static {
        try {
            init(new AppConfig());
        } catch (FileNotFoundException e) {
            LOG.warn("Appway config file not found.", e);
        } catch (IOException e) {
            LOG.warn("Appway config file could not be read.", e);
        }
    }

    /**
     * NOTE: this method is called by the static block when the class is loaded but is in its own method for testability reasons.
     * Calling this method after the class is loaded will have NO EFFECT.
     */
    protected static void init(AppConfig appwayConfig) {
        overrides = new Properties();
        try {
            String overrideLocation = appwayConfig.getProperty("ComponentIdsOverrideFile");
            InputStream stream = null;

            if (overrideLocation != null && overrideLocation.length() > 0) {
                stream = ComponentIds.class.getClassLoader().getResourceAsStream(overrideLocation);
                try {
                    overrides.load(stream);
                } finally {
                    stream.close();
                }
            }

        } catch (IOException ex) {
            LOG.warn("Appway config file could not be read.", ex);
        }
    }


    public static class HOMEPAGE {

        public static final String button_APPLY_FOR_A_LOAN =
                overrides.getProperty("HOMEPAGE.button_APPLY_FOR_A_LOAN", "//*[@data-q='homepage-check-rate-button']");
    }
}
