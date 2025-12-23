package playwrightsessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightBasics {

    public static void main(String[] args) {

        // Create Playwright
        Playwright playwright = Playwright.create();

        // Launch browser
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        //.setChannel("msedge")
                        .setHeadless(false)
        );

        // Create page
        Page page = browser.newPage();

        // Navigate to URL
        page.navigate("https://www.amazon.com");

        // Get title and URL
        String title = page.title();
        System.out.println("Page Title: " + title);

        String url = page.url();
        System.out.println("Page URL: " + url);

        // Close browser and playwright
        browser.close();
        playwright.close();
    }
}
