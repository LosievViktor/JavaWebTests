package com.playwrighttestexamples.tests;

import com.playwrighttestexamples.pages.SampleAppPage;
import com.playwrighttestexamples.pages.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class SampleAppTests extends BaseTest {

    @Test
    @DisplayName("This test goes to web site and tests login page in Positive case.")
    void loginFormPositiveTest() {
        SampleAppPage sampleAppPage = new SampleAppPage(page);

        loadPage(Strings.SAMPLE_APP);

        sampleAppPage.login(params.login, params.password);

        assertThat(sampleAppPage.statusLabel())
                .hasText(Strings.WELCOME_USER_MESSAGE + " " + params.login + "!");
    }

    @Test
    @DisplayName("This test goes to web site and tests login page in Negative case.")
    void loginFormNegativeTest() {
        SampleAppPage sampleAppPage = new SampleAppPage(page);

        loadPage(Strings.SAMPLE_APP);

        sampleAppPage.login(params.login, params.wrongPassword);

        assertThat(sampleAppPage.statusLabel()).hasText(Strings.WRONG_PASSWORD_MESSAGE);
    }
}
