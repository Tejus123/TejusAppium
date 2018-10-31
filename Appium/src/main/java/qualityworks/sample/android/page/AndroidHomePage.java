package qualityworks.sample.android.page;

import static qualityworks.sample.Helpers.element;

import java.util.Objects;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;


public class AndroidHomePage {

    /** Verify the home page has loaded **/
    public void loaded() {

        MobileElement headingLabel = element(MobileBy.AccessibilityId("Heading"));
        String labelText = headingLabel.getAttribute("text");
        String labelTextCorrect = "Quality Works Appium Android Sample";
        Assert.assertTrue(labelText + " should be " + labelTextCorrect, Objects.equals(labelText, labelTextCorrect));
    }
}
