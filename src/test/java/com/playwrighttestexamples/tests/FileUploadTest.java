package com.playwrighttestexamples.tests;

import com.playwrighttestexamples.pages.FileUploadPage;
import com.playwrighttestexamples.pages.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

class FileUploadTest extends BaseTest {

    private static final Path FILE_PATH = Paths.get("src", "test", "resources", "file.txt");

    @Test
    @DisplayName("This test goes to web site and tests File Upload via Drag and Drop.")
    void uploadFileDragAndDropTest() {
        loadPage(Strings.FILE_UPLOAD);

        FileUploadPage uploadPage = new FileUploadPage(page);

        uploadPage.upload(FILE_PATH.toAbsolutePath());
        uploadPage.assertUploaded();
    }
}
