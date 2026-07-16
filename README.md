# WebTests (Java)

Java port of the C# `PlaywrightTestExamples` project - the same UI automation
suite against http://uitestingplayground.com/, built with Playwright for Java,
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

## Configuration

Runtime parameters mirror `live.runsettings` and are read from
`src/test/resources/test.properties`, overridable with `-D` system
properties (defaults shown):

| Property        | Default                              |
|------------------|---------------------------------------|
| `environment`    | `http://uitestingplayground.com/`     |
| `login`          | `Viktor`                              |
| `password`       | `pwd`                                 |
| `wrongPassword`  | `pwd123`                              |
| `headless`       | `false`                               |

Example:

```
mvn test -Denvironment=http://uitestingplayground.com/ -Dlogin=Viktor -Dpassword=pwd -DwrongPassword=pwd123 -Dheadless=true
```

## Project layout

- `src/test/java/.../pages/` - Page Object Model classes (mirrors the C#
  `Pages/` folder).
- `src/test/java/.../tests/` - JUnit 5 test classes (mirrors the C# `Tests/`
  folder), plus:
  - `BaseTest.java` - equivalent of the C# `BaseTest` (`Microsoft.Playwright.NUnit.PageTest`).
    Playwright's Java bindings don't ship a JUnit base class, so this manages
    one shared `Playwright`/`Browser` per test class (`@BeforeAll`/`@AfterAll`)
    and gives every test method its own isolated `BrowserContext`/`Page`
    (`@BeforeEach`/`@AfterEach`) - the same per-test isolation `PageTest`
    gives you in .NET. This assumes test classes run sequentially (Maven
    Surefire's default); see the note in `BaseTest.java` if you want to
    parallelize test classes across JVM forks.
  - `TestParameters.java` - equivalent of `TestContext.Parameters` /
    `live.runsettings`.
- `src/test/resources/` - `file.txt` (upload test fixture) and
  `test.properties` (default run parameters).

## Notes on the port

- `MainPageLinksTest`'s `[TestCaseSource]` becomes a JUnit 5
  `@ParameterizedTest` + `@MethodSource` over `Strings.LINKS`.
- The Progress Bar test's busy-wait (`while (...) Thread.Sleep(1)`) becomes a
  `while` loop polling every 50ms via `page.waitForTimeout(50)` - functionally
  identical, just less CPU-spinny.
- Playwright's Java assertions (`assertThat(...).hasText(...)`,
  `.isVisible()`, `.isAttached()`, `.hasTitle(...)`) are the direct
  equivalents of the C# `Assertions.Expect(...)` calls used throughout.
