package com.playwrighttestexamples.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.playwrighttestexamples.pages.Locators;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.options.AriaRole.LINK;

/**
 * Equivalent of the C# project's BaseTest (Microsoft.Playwright.NUnit.PageTest). Playwright's
 * Java bindings don't ship a JUnit base class, so this manages the Playwright/Browser lifecycle
 * once per test class (static, via @BeforeAll/@AfterAll) and gives every test method its own
 * isolated BrowserContext + Page (via @BeforeEach/@AfterEach) - the same isolation PageTest
 * gives you per-test in .NET.
 * <p>
 * Note: since playwright/browser/params are static fields declared on this shared superclass,
 * this pattern assumes test classes run sequentially (Surefire's default) rather than in
 * parallel forks - see the README for details if you want to parallelize.
 */
public abstract class BaseTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected static TestParameters params;

    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    static void launchBrowser() {
        params = TestParameters.load();
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(params.headless));
    }

    @AfterAll
    static void closeBrowser() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        if (context != null) {
            context.close();
        }
    }

    protected void loadMainPage() {
        page.navigate(params.environment);
    }

    private void clickLinkByText(String linkText) {
        page.getByRole(LINK, new Page.GetByRoleOptions().setName(linkText).setExact(true)).click();
    }

    private void assertPageLoaded(String headerText) {
        assertThat(page.locator(Locators.HEADER_TAG)).hasText(headerText);
    }

    protected void loadPage(String chapter) {
        loadMainPage();
        clickLinkByText(chapter);
        assertPageLoaded(chapter);
    }
}
