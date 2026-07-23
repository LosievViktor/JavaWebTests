# WebTests (Java)

JAVA code examples for Web UI Automation Tests made with Playwright library.

## How to launch

1. Install the Playwright browser binaries (one-time, same role as the C# project's `playwright.ps1 install` step):
   
   ...
   mvn compile exec:java -e -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install"
   ...
   
3. Run the tests:

   ...
   mvn test
   ...
