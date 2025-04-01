package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import stepDefinitions.StepDefinitions;
import org.openqa.selenium.Keys;

import java.time.Duration;

public class PageFactory extends StepDefinitions {

    WebDriver driver;

    @FindBy(id = "searchDropdownBox")
    public WebElement amazonSearchDropdownBox;

    @FindBy(id = "twotabsearchtextbox")
    public WebElement twoTabSearchtextbox;

    //locator operation methods
    public void amazonSearchDropdownBox() {
        Select dropdown = new Select(amazonSearchDropdownBox);
        dropdown.selectByVisibleText("Amazon Fashion");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
        twoTabSearchtextbox.sendKeys("Mobiles");
    }

    public void fashionSection() {
        twoTabSearchtextbox.clear();
        Actions a = new Actions(driver);
       //a.moveToElement(twoTabSearchtextbox).click().sendKeys("women clothing").doubleClick().build().perform();
        a.moveToElement(twoTabSearchtextbox).click().keyDown(Keys.SHIFT)
                .sendKeys("women clothing").build().perform();
    }

    // Constructor to initialize the PageFactory
    public PageFactory(WebDriver driver) {
       this.driver = driver;
       org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

}





