package stepDefinitions;

import Utils.PageFactory;
import Utils.Screenshot;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
//import org.testng.Assert;

public class StepDefinitions {

    WebDriver driver;

    PageFactory locators;

    Screenshot screenshot;

    @Given("I open Flipkart flight booking page")
    public void i_open_flipkart_flight_booking_page() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bindu MS\\IdeaProjects\\SeleniumProject\\Chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.makemytrip.com/flights/");
        driver.navigate().refresh();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Then("the page title is verified")
    public void the_page_title_is_verified() {
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);
    }

    @Then("the page URL and navigate pages are verified")
    public void the_page_url_and_navigate_pages_are_verified() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        //System.out.println("Current URL: " + driver.getPageSource());

        // To remove mob num login popup
        driver.findElement(By.className("commonModal__close")).click();

        //Move to Hotels
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div[2]/div/div/nav/ul/li[2]/span/a/span[2]")).click();
        //Navigate back to Flights
        driver.navigate().back();
        //Again Navigate forward to Hotels
        driver.navigate().forward();
        driver.quit();
    }

    @Given("I open Amazon home page")
    public void i_open_amazon_home_page() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bindu MS\\IdeaProjects\\SeleniumProject\\Chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();

        // Create instance of the pageFactory
        locators = new PageFactory(driver);
        // Create instance of the Screenshot
        screenshot = new Screenshot(driver);

    }

    @And("the static dropdowns are verified")
    public void the_static_dropdowns_are_verified() {
        locators.amazonSearchDropdownBox();
        screenshot.takeScreenshot("Mobiles");
    }


    @And("few Actions are performed and verified")
    public void few_actions_are_performed_and_verified() {
        locators.fashionSection();
        screenshot.takeScreenshot("WomenClothing");
    }

    @And("Scroll vertically downwards verified")
    public void scrollVerticallyDownwardsVerified() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
        WebDriverWait wdWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navBackToTop")));
        Thread.sleep(3000);
    }

    @And("footer section links are verified")
    public void footerSectionLinksAreVerified() {
        WebElement navFooter = driver.findElement(By.id("navFooter"));
        screenshot.takeScreenshot("FooterLinks");
        System.out.println("Number of Footer links: ");
        System.out.println(navFooter.findElements(By.tagName("a")).size());
    }


    @And("footer section broken links are verified")
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

    @Then("i closed the browser")
    public void i_closed_the_browser() {
        driver.quit();
    }


}
