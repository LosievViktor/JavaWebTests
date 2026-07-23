package com.playwrighttestexamples.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestParameters {

    public final String environment;
    public final String login;
    public final String password;
    public final String wrongPassword;
    public final boolean headless;

    private TestParameters(String environment, String login, String password, String wrongPassword, boolean headless) {
        this.environment = environment;
        this.login = login;
        this.password = password;
        this.wrongPassword = wrongPassword;
        this.headless = headless;
    }

    public static TestParameters load() {
        Properties fileProps = new Properties();
        try (InputStream in = TestParameters.class.getClassLoader().getResourceAsStream("test.properties")) {
            if (in != null) {
                fileProps.load(in);
            }
        } catch (IOException ignored) {
            // Fall back to system properties / defaults below.
        }

        return new TestParameters(
                resolve("environment", fileProps, "http://uitestingplayground.com/"),
                resolve("login", fileProps, "Viktor"),
                resolve("password", fileProps, "pwd"),
                resolve("wrongPassword", fileProps, "pwd123"),
                Boolean.parseBoolean(resolve("headless", fileProps, "false"))
        );
    }

    private static String resolve(String key, Properties fileProps, String defaultValue) {
        String fromSystem = System.getProperty(key);
        if (fromSystem != null && !fromSystem.isEmpty()) {
            return fromSystem;
        }

        String fromFile = fileProps.getProperty(key);
        if (fromFile != null && !fromFile.isEmpty()) {
            return fromFile;
        }

        return defaultValue;
    }
}
