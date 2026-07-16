package com.playwrighttestexamples.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public final class ProgressBarPage extends BasePage {

    public ProgressBarPage(Page page) {
        super(page);
    }

    public Locator startButton() {
        return page.locator(Locators.BTN_START);
    }

    public Locator stopButton() {
        return page.locator(Locators.BTN_STOP);
    }

    private Locator bar() {
        return page.locator(Locators.PROGRESS_BAR);
    }

    public int getValueOfProgressBar() {
        String value = bar().getAttribute(Locators.PROGRESS_BAR_VALUE);
        return value != null ? Integer.parseInt(value) : 0;
    }
}
