# WebTests (Java)
UI automation tests for web site http://uitestingplayground.com/, built with Playwright for Java,
JUnit 5, and Maven.

## How to launch

1. Install the Playwright browser binaries (one-time, same role as the C#
   project's `playwright.ps1 install` step):

   ```
   mvn compile exec:java -e -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install"
   ```

2. Run the tests:

   ```
   mvn test
   ```
