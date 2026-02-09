# QA Automation Framework (Java + Selenium + JUnit)

This project is a Page Object Model (POM) based automation framework built using Java, Selenium 4, JUnit Jupiter, and Maven.

## Features
- Java 17
- Selenium 4.40.0
- JUnit Jupiter 6.x
- Page Object Model architecture
- Centralized WebDriver management
- Configurable test execution

## Project Structure
```
qa-automation-framework/
 pom.xml
 src/
   main/java/core/
   main/java/pages/
  test/java/test/
 target/
```

## Running Tests
```
mvn test
```

## Run with custom parameters
```
mvn test -Dbrowser=chrome -Dheadless=false -DbaseUrl=https://the-internet.herokuapp.com/login -Dusername=tomsmith -Dpassword=SuperSecretPassword!
```
