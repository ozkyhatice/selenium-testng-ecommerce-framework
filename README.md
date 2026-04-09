# E-commerce Test Automation

This is a test automation project for an e-commerce website. It covers both UI testing with Selenium and API testing with REST Assured. I built this as a reference implementation to show how to structure a real test automation project that can run in parallel and integrate with CI/CD pipelines.

---

## What this project does

The project tests the automationexercise.com website, which is a practice e-commerce site. It includes tests for common user flows like login, product search, adding items to cart, and checkout. There's also a set of API tests that verify endpoints for products, search, and user operations.

Tests run in parallel using TestNG, which helps keep execution time reasonable when the test suite grows. There's a simple retry mechanism for flaky tests and a custom listener that logs test execution details.

---

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

The `driver` package uses ThreadLocal to manage WebDriver instances. This is important for parallel test execution — each thread gets its own browser instance, so tests don't interfere with each other.

Page objects live in the `pages` package and extend BasePage, which has common methods like waiting for elements. Test classes extend BaseTest, which handles driver initialization and cleanup.

---

## API Testing & Automation

This project implements a **multi-layered API testing strategy** for `automationexercise.com`, combining both code-based automation and tool-based validation.

### 1. REST Assured (Framework Integrated)

Code-based API tests are located in:

```
src/test/java/com/automation/api/
```

Key capabilities:

* **POJO Modeling**
  Uses Jackson and Lombok to map JSON responses into Java objects (`Product`, `UserRequest`).

* **JSON Schema Validation**
  API responses are automatically validated against:

```
src/test/resources/productsSchema.json
```

* **Data-Driven Testing**
  Search scenarios run with multiple datasets using `testData.json`.

This approach keeps validations scalable and maintainable compared to field-by-field assertions.

---

### 2. Postman & Newman (CI/CD Integrated)

For exploratory testing and lightweight CI health checks, Postman collections are integrated into the project.

**Features:**

* Direct repository synchronization via `.postman/` metadata
* Reusable environments
* Automated execution inside CI pipelines using Newman

**Newman execution command:**

```bash
newman run postman/collections/AutomationExercise_API.postman_collection.json \
-e postman/environments/AutomationExercise.postman_environment.json \
--reporters cli,junit \
--reporter-junit-export target/newman-report.xml
```

Newman generates JUnit reports that can be consumed directly by CI tools for test reporting.

---

## Technologies used

**Java 17** — Current LTS version used for stability.

**Selenium 4.20.0** — Browser automation with built-in driver manager and modern headless mode (`--headless=new`).

**TestNG 7.10.2** — Enables parallel execution, XML configuration, and retry mechanisms.

**REST Assured 5.4.0** — API testing with readable request/response validation and JSON schema verification.

**Postman + Newman** — API exploration and CI/CD pipeline health checks.

**Maven** — Dependency management and test execution via Surefire plugin.

---

## Configuration

The `config.properties` file contains environment settings:

```
baseUrl=https://automationexercise.com
browser=chrome
headless=true
apiBaseUrl=https://automationexercise.com/api
```

Override headless mode when debugging:

```bash
mvn test -Dheadless=false
```

---

## How to run tests

Make sure Java 17 and Maven are installed.

Clone the repository:

```bash
git clone <repository-url>
cd ecommerce-test-automation
```

Run all tests:

```bash
mvn clean test
```

Run with visible browser:

```bash
mvn clean test -Dheadless=false
```

Run a specific test:

```bash
mvn test -Dtest=LoginTest
```

Parallel execution is configured via `testng.xml` (`thread-count=3`).

---

## Test execution flow

When running `mvn test`:

1. Maven compiles the project
2. Surefire loads `testng.xml`
3. TestNG initializes suites
4. `BaseTest.setUp()` creates WebDriver instances
5. Tests execute
6. `BaseTest.tearDown()` closes browsers
7. TestListener logs execution results

Tests run in parallel at the **test level**, ensuring isolated browser sessions.

---

## API tests

API tests extend `ApiBaseTest` and do not require browser initialization.

The `ApiSchemaTest` validates API response structures using JSON schema validation located at:

```
src/test/resources/productsSchema.json
```

Schema validation improves maintainability and reduces brittle assertions for large API responses.

---
