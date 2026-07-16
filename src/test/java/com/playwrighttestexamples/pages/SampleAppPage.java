package com.playwrighttestexamples.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public final class SampleAppPage extends BasePage {

    public SampleAppPage(Page page) {
        super(page);
    }

    private Locator loginTextField() {
        return page.locator(Locators.TXT_LOGIN);
    }

    private Locator passwordTextField() {
        return page.locator(Locators.TXT_PASSWORD);
    }

    private Locator loginButton() {
        return page.locator(Locators.BTN_LOGIN);
    }

    public Locator statusLabel() {
        return page.locator(Locators.LBL_STATUS);
    }

    public void login(String user, String pass) {
        loginTextField().fill(user);
        passwordTextField().fill(pass);
        loginButton().click();
    }
}
