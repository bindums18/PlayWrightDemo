package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class TestngClass {

    WebDriver driver;

    PageFactory locators;

    Screenshot screenshot;

    @BeforeTest
    public void loginHomePage() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bindu MS\\IdeaProjects\\SeleniumProject\\Chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();

        // Create instance of the pageFactory
        locators = new PageFactory(driver);
        // Create instance of the Screenshot
        screenshot = new Screenshot(driver);

    }

    //@Test(groups = {"smoke"})
    @Test
    public void few_actions_verified() {
        WebElement twoTabSearchtextbox = driver.findElement(By.id("twotabsearchtextbox"));
        twoTabSearchtextbox.clear();
        Actions a = new Actions(driver);
        a.moveToElement(twoTabSearchtextbox).click().keyDown(Keys.SHIFT)
                .sendKeys("women clothing").build().perform();

    }

    //@Test(dependsOnMethods = {"scrollVerticallyDownwardsVerified"})
    @Test(enabled = false)
    public void footerSectionBrokenLinksAreVerified() throws MalformedURLException {

        SoftAssert a = new SoftAssert();
        WebElement navFooter = driver.findElement(By.id("navFooter"));
        List<WebElement> links = navFooter.findElements(By.tagName("a"));

        for(WebElement link : links){

            String url = link.getAttribute("href");
            // Skip the link if href is empty or null
            if (url == null || url.isEmpty()) {
                System.out.println("Skipping invalid link: " + link.getText());
                continue;
            }

            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("HEAD");
                conn.connect();
                int responseCode = conn.getResponseCode();
                System.out.println(responseCode);
                if(responseCode>=400){
                    System.out.println("broken link is:" + url);
                }
                a.assertTrue(responseCode>=400, "Link is a broken link");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        a.assertAll();
    }

    @Test
    public void scrollVerticallyDownwardsVerified() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
        WebDriverWait wdWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navBackToTop")));
        Thread.sleep(3000);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
