# CoverGo Interview

The purpose of this project is to show the coverGo interviewers some tests for a web application.

The web application used to perform the tests is https://imedical.asiainsurance.hk/. I have chosen this application because it is a coverGo application for one of his clients.

## Used tools
For this project, I have used the following tools:
- IntelliJ IDEA CE
- Selenium 4.11.0
- Junit 5.10.0
- Cucumber 7.13.0
- Maven 3.6.2
- Java 11

## Requirements

It´s important to keep in mind that the followings tools should be installed to execute the tests:
- Java JDK
- A browser (Chrome, Firefox, Edge, Chromium, Safari)

## Usage/Examples
This project uses maven to execute the tests with the following command:

If you have Maven locally installed:
```bash
mvn clean test
```
If you don´t have Maven installed and you are on Linux, you can use:
```bash
./mvnw clean test
```

Or if you are on Windows:
```bash
mvnw.cmd clean test
```

To control how the tests will be executed, I have some properties that we can use to modify the execution of the tests. The properties are:
- browser
- headless

### Property browser
With this property, we can select the browser that we want to use in our tests. The values that admit this property are:
- chrome
- firefox
- edge
- safari
- chromium
- opera
- iexplorer

If we execute the tests without informing this property, the browser used will be Chrome.

Examples:

```bash
mvn clean test -Dbrowser=chrome
# or you can use
# Linux:
./mvnw clean test -Dbrowser=chrome
# Windows
mvnw.cmd clean test -Dbrowser=chrome
```

```bash
mvn clean test -Dbrowser=safari
# or you can use
# Linux:
./mvnw clean test -Dbrowser=safari

```

```bash
mvn clean test -Dbrowser=firefox
# or you can use
# Linux:
./mvnw clean test -Dbrowser=firefox
# Windows
mvnw.cmd clean test -Dbrowser=firefox
```

### Property headless
With this property, we can control if the browser will run the tests with GUI or not (headless mode).

Note: This behaviour can´t be implemented in all browsers. The browsers that can be executed with this property are:
- chrome
- chromium
- edge
- firefox

If we execute the tests without informing this property, the browser GUI will be visible.

Examples:

```bash
mvn clean test -Dbrowser=chrome -Dheadless
# or you can use
# Linux:
./mvnw clean test -Dbrowser=chrome -Dheadless
# Windows
mvnw.cmd clean test -Dbrowser=chrome -Dheadless
```

### Parallel execution
This projects is configured to execute the test in parallel. The parallel configuration is in file junit-platform.properties.
The main properties defined in this file are:
```text
# Enables parallel execution for cucumber
cucumber.execution.parallel.enabled=true

# Define a custom strategy for parallelism
cucumber.execution.parallel.config.strategy=custom

# Set the custom parallel strategy class due fixed strategy not works in JAVA 9+.
cucumber.execution.parallel.config.custom.class=com.palmadelvalle.cucumber.runner.CustomParallelStrategy
```

By default, the project the tests are executed in maximum 5 pool. This is controlled with the variable FIXED_PARALLELISM in the Constants class.


### Filtering by tags
In this project, I have defined some tags in the scenarios to control the execution. With maven and this tags, we can define explicitly which test we want to be executed.

Examples:
Having a feature or scenario with tag @card_details, we can control the execution of only this scenario or feature with the following command:

```bash
mvn clean test -Dbrowser=chrome -Dheadless -Dgroups="card_details"
# or you can use
# Linux:
./mvnw clean test -Dbrowser=chrome -Dheadless -Dgroups="card_details"
# Windows
mvnw.cmd clean test -Dbrowser=chrome -Dheadless -Dgroups="card_details"
```


## Reporting

Once the tests have been executed, all the reports generated during the execution will be in the directory "target/cucumber-reports"
Three differents reports will be generated:
- Cucumber JSON report: Cucumber.html
- Cucumber HTML report: Cucumber.json
- JUnit XML report: Cucumber.xml

You can find examples of this files in the following link [CoverGo tests execution](https://github.com/palmaDelValle/covergo-interview/actions/runs/5940663212)

## Authors

- Francisco Javier Palma del Valle [@palmadelvalle](https://github.com/palmaDelValle)
