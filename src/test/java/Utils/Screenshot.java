package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshot {

    WebDriver driver;

    public Screenshot(WebDriver driver) {
        this.driver = driver;  // Initialize the driver
    }

    public void takeScreenshot(String screenshotName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destinationPath = System.getProperty("user.home") + "/Downloads/" + screenshotName + ".png";
            FileUtils.copyFile(src, new File(destinationPath));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save screenshot");
        }
    }

}
