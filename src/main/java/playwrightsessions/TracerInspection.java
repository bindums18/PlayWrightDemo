package playwrightsessions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class TracerInspection {
    public static void main(String[] args) {

        // Refer below for better understanding.
        // https://playwright.dev/java/docs/trace-viewer-intro

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                            .setChannel("msedge")
                            .setHeadless(false));

            BrowserContext context = browser.newContext();

            // Start tracing before creating / navigating a page.
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));

            Page page = context.newPage();

            page.navigate("https://www.booking.com/?chal_t=1766395575159&force_referer=");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Dismiss sign-in info.")).click();

            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Where are you going?")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Bangalore India")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sa 27 December")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Su 28 December")).click();
            page.getByTestId("occupancy-config").click();
            page.locator(".de576f5064.b46cd7aad7.e26a59bb37.c295306d66.c7a901b0e7.aaf9b6e287.dc8366caa6").first().click();
            page.locator(".de576f5064.b46cd7aad7.e26a59bb37.c295306d66.c7a901b0e7.aaf9b6e287.dc8366caa6").first().click();
            page.locator(".de576f5064.b46cd7aad7.e26a59bb37.c295306d66.c7a901b0e7.aaf9b6e287.dc8366caa6").first().click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Done")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();

            String url = page.url();
            System.out.println("Page URL: " + url);

            // Stop tracing and export it into a zip archive.
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));
        }
    }
}
