package com.playwrighttestexamples.tests;

import com.playwrighttestexamples.pages.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class MainPageTests extends BaseTest {

    static Stream<String> linkProvider() {
        return Strings.LINKS.stream();
    }

    @ParameterizedTest(name = "Main page link: {0}")
    @MethodSource("linkProvider")
    @DisplayName("This test goes to web site and visits each chapter link.")
    void mainPageLinksTest(String pageName) {
        loadPage(pageName);
    }

    @Test
    @DisplayName("This test checks main page Title.")
    void mainPageAttributes() {
        loadMainPage();
        assertThat(page).hasTitle(Strings.PAGE_TITLE);
    }
}
