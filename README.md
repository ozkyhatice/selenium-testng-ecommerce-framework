# E-commerce Test Automation

This is a test automation project for an e-commerce website. It covers both UI testing with Selenium and API testing with REST Assured. I built this as a reference implementation to show how to structure a real test automation project that can run in parallel and integrate with CI/CD pipelines.

## What this project does

The project tests the automationexercise.com website, which is a practice e-commerce site. It includes tests for common user flows like login, product search, adding items to cart, and checkout. There's also a set of API tests that verify endpoints for products, search, and user operations.

Tests run in parallel using TestNG, which helps keep execution time reasonable when the test suite grows. There's a simple retry mechanism for flaky tests and a custom listener that logs test execution details.

## Project structure

```
src/test/java/com/automation/
├── api/              # API test classes
├── base/             # Base classes for test setup
├── driver/           # WebDriver factory with ThreadLocal for parallel execution
├── listeners/        # TestNG listeners for logging and reporting
├── pages/            # Page Object classes
├── tests/            # UI test classes
└── utils/            # Configuration reader and retry logic

src/test/resources/
├── config.properties      # Environment configuration
└── productsSchema.json    # JSON schema for API validation
```

The `driver` package uses ThreadLocal to manage WebDriver instances. This is important for parallel test execution - each thread gets its own browser instance, so tests don't interfere with each other.

Page objects live in the `pages` package and extend BasePage, which has common methods like waiting for elements. Test classes extend BaseTest, which handles driver initialization and cleanup.

## Technologies used

**Java 17** - I'm using Java 17 because it's the current LTS version. The project doesn't use any Java 17-specific features, so it should work fine on Java 11 if needed.

**Selenium 4.20.0** - For browser automation. Selenium 4 has better support for modern web features and doesn't require separate driver executables anymore (uses the built-in driver manager). I also use the new headless mode (`--headless=new`) which is more stable than the old one.

**TestNG 7.10.2** - Test framework. I picked TestNG over JUnit because of its better support for parallel execution, test configuration through XML, and built-in retry mechanisms. The `@Parameters` annotation makes it easy to run the same test on different browsers.

**REST Assured 5.4.0** - For API testing. It has a readable syntax for making HTTP requests and validating responses. The json-schema-validator module lets you validate API responses against a schema, which is better than writing assertions for every field.

**Maven** - Build tool and dependency management. The surefire plugin is configured to run tests through `testng.xml`, which gives you control over test execution order and parallelization.

## Configuration

The `config.properties` file has basic settings:

```
baseUrl=https://automationexercise.com
browser=chrome
headless=true
apiBaseUrl=https://automationexercise.com/api
```

You can override the headless setting from the command line, which is useful when debugging:

```bash
mvn test -Dheadless=false
```

## How to run tests

Make sure you have Java 17 and Maven installed.

Clone the repository and navigate to the project directory:

```bash
git clone <repository-url>
cd ecommerce-test-automation
```

Run all tests:

```bash
mvn clean test
```

Run with browser visible (not headless):

```bash
mvn clean test -Dheadless=false
```

Run specific test class:

```bash
mvn test -Dtest=LoginTest
```

The `testng.xml` file is set up to run tests in parallel with 3 threads. You can adjust this by changing the `thread-count` attribute.

## Test execution flow

When you run `mvn test`:

1. Maven compiles the test code
2. Surefire plugin picks up `testng.xml`
3. TestNG initializes tests based on the suite configuration
4. For each test, `BaseTest.setUp()` creates a WebDriver instance
5. Test methods execute
6. `BaseTest.tearDown()` closes the browser
7. TestListener logs results

Tests run in parallel at the "test" level (not at the method level). This means each `<test>` block in `testng.xml` runs in its own thread with its own browser instance.

## API tests

API tests don't need a browser, so they extend `ApiBaseTest` instead of `BaseTest`. They use REST Assured to make HTTP requests and validate responses.

The `ApiSchemaTest` class uses JSON schema validation to verify the structure of API responses. The schema file is in `src/test/resources/productsSchema.json`. This is more maintainable than writing assertions for every field, especially for large JSON responses.


