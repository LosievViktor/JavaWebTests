package com.playwrighttestexamples.pages;

public final class Locators {

    private Locators() {
    }

    // Main page locator
    public static final String HEADER_TAG = "h3";

    // Sample App page locators
    public static final String TXT_LOGIN = "[name='UserName']";
    public static final String TXT_PASSWORD = "[name='Password']";
    public static final String BTN_LOGIN = "#login";
    public static final String LBL_STATUS = "#loginstatus";

    // Progress Bar page locators
    public static final String BTN_START = "#startButton";
    public static final String BTN_STOP = "#stopButton";
    public static final String PROGRESS_BAR = "#progressBar";
    public static final String PROGRESS_BAR_VALUE = "aria-valuenow";

    // File Upload page locators
    public static final String IFRAME = "iframe";
    public static final String INPUT_FILE = "#browse";
}
