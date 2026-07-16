package com.playwrighttestexamples.tests;

import com.playwrighttestexamples.pages.ProgressBarPage;
import com.playwrighttestexamples.pages.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProgressBarTest extends BaseTest {

    @Test
    @DisplayName("This test goes to web site and tests Progress Bar.")
    void progressTest() {
        loadPage(Strings.PROGRESS_BAR);

        ProgressBarPage progressBarPage = new ProgressBarPage(page);

        progressBarPage.startButton().click();

        while (progressBarPage.getValueOfProgressBar() < 75) {
            page.waitForTimeout(50);
        }

        progressBarPage.stopButton().click();

        Assertions.assertEquals(75, progressBarPage.getValueOfProgressBar());
    }
}
