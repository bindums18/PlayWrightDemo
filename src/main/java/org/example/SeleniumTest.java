//package org.example;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//public class SeleniumTest {
//
//    WebDriver driver;
//
//    @BeforeClass
//    public void setup() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bindu MS\\IdeaProjects\\SeleniumProject\\Chromedriver\\chromedriver.exe");
//        // Initialize ChromeDriver (browser)
//        driver = new ChromeDriver();
//    }
//
//    // Test method to verify the title of the page
//    @Test
//    public void verifyTitle() {
//        driver.get("https://flipkart.com/");
//        driver.manage().window().maximize();
//        System.out.println(driver.getTitle());
//        System.out.println(driver.getCurrentUrl());
//    }
//
//    // This method is executed once after all the test methods
//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}
