package com.playwrighttestexamples.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public final class FileUploadPage extends BasePage {

    public FileUploadPage(Page page) {
        super(page);
    }

    private FrameLocator iFrame() {
        return page.frameLocator(Locators.IFRAME);
    }

    private Locator fileInput() {
        return iFrame().locator(Locators.INPUT_FILE);
    }

    public void upload(String filePath) {
        assertThat(fileInput()).isAttached();
        fileInput().setInputFiles(Paths.get(filePath));
    }

    public void upload(Path filePath) {
        assertThat(fileInput()).isAttached();
        fileInput().setInputFiles(filePath);
    }

    public void assertUploaded() {
        Locator uploadedMessage = iFrame().locator("p",
                new FrameLocator.LocatorOptions().setHasText(Strings.MESSAGE_OF_UPLOAD));
        assertThat(uploadedMessage).isVisible();
    }
}
