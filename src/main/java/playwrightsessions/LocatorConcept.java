package playwrightsessions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.util.List;

public class LocatorConcept {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        BrowserContext browserContext = browser.newContext();
        Page page = browserContext.newPage();
        page.navigate("https://www.orangehrm.com/en/30-day-free-trial");

        //Locators:
        //Single element
        Locator cookiesAccept = page.locator("text = Allow all").first();
        cookiesAccept.click();

        //Clicking on Contact Sales button
        Locator contactSales = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Contact Sales"));
        //To get count of 'Contact Sales' locators
        int totalButtons = contactSales.count();
        System.out.println(totalButtons);
        contactSales.click();

        //Multiple elements
        Locator countries = page.locator("select#Form_getForm_Country");
        System.out.println(countries.count());

        //1. To print select class dropdown - countries using textContent.
//        for(int i=0; i<countries.count(); i++){
//            String countiesList = countries.nth(i).textContent();
//            System.out.println(countiesList);
//        }

        //2. Using allTextContents.
        List<String> countiesList = countries.allTextContents();
        for(String c : countiesList){
            System.out.println(c);
        }

        // -------- Cleanup --------
        page.close();
        browserContext.close();

        browser.close();
        playwright.close();
    }
}
