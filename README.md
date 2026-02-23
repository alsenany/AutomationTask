# QA Automation Framework (Java + Selenium +JUnit)

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
   main/java/core/BasePage
   main/java/core/Main   
   main/java/pages/LoginPage
   main/java/pages/RegistrationPage
  
   test/java/test package/test
   test/java/test package/Registrationtest

 target/
```

## Import into IntelliJ IDEA

1- Open IntelliJ IDEA
2- Click File → New → Project from Existing Sources
3- Select your project folder (qa-automation-framework)
4- Choose Maven when IntelliJ asks for project type
5- Make sure “Import Maven projects automatically” is enabled
6- IntelliJ will automatically download:
    Selenium
    JUnit
    WebDriver 

## How to Add/Update Dependencies (Maven)
- Add a new dependency

1- Open pom.xml.
2- Inside the <dependencies> ... </dependencies> block, add the dependency snippet.
3- Reload Maven in your IDE (IntelliJ: Maven tool window → Reload All Maven Projects) or run mvn clean install.

- Update an existing dependency

1- Change the <version> in pom.xml to the desired release.
2- Reload Maven (or run mvn -U clean install to force update checks).
3- If IntelliJ still shows red underlines, 
Update Maven Indexes (Alt+Enter on the dependency → Update Maven Indexes) and try again.

## Note

- We expose a DriverManager interface and one implementation per browser 
to follow SOLID principles (SRP, OCP, DIP), improve testability and parallel safety