package playwrightsessions;

import com.microsoft.playwright.*;

public class BrowserContextConcept {

    public static void main(String[] args) {

        // Create Multiple Browser Contexts in PlayWright.
        // BrowserContext in Playwright represents an isolated browser session, similar to an incognito window.
        // Creating multiple browser contexts allows testing multiple applications or users independently within the same browser instance.

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        // -------- Browser Context 1 --------
        BrowserContext brcx1 = browser.newContext();
        Page p1 = brcx1.newPage();
        p1.navigate("https://www.amazon.com");
        p1.fill("#twotabsearchtextbox", "Mobiles");
        System.out.println("Page 1 Title: " + p1.title());

        // -------- Browser Context 2 --------
        BrowserContext brcx2 = browser.newContext();
        Page p2 = brcx2.newPage();
        p2.navigate("https://www.makemytrip.com/");
        System.out.println("Page 2 Title: " + p2.title());

        // -------- Browser Context 3 --------
        BrowserContext brcx3 = browser.newContext();
        Page p3 = brcx3.newPage();
        p3.navigate("https://www.google.com/");
        System.out.println("Page 3 Title: " + p3.title());

        // -------- Cleanup --------
        p1.close();
        brcx1.close();

        p2.close();
        brcx2.close();

        p3.close();
        brcx3.close();

        browser.close();
        playwright.close();
    }
}
