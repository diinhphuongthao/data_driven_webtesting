# README #

This README provides instructions on setting up and running a Java + Selenium automation framework for various automation tasks. The framework includes test scripts for the following scenarios:

## Scenario 1: Automation Testing with Data from Excel and JSON on Guru99

1. Open the website [https://demo.guru99.com/v4/](https://demo.guru99.com/v4/).

2. Write automation test scripts that use data from an Excel file (`TestCaseSuite_v2.xlsx`) for various test scenarios.

3. Execute the test scripts with different data sets from the Excel file and validate the results.

## Scenario 2: Getting Text from Tooltip 

1. Open the website [https://demoqa.com/tool-tips](https://demoqa.com/tool-tips).

2. Write code to extract text from tooltips on the webpage.

3. Execute the code and verify the results.

## Scenario 3: Selecting Date and Time on Calendar 

1. Open the website [https://demoqa.com/date-picker](https://demoqa.com/date-picker).

2. Write a function that selects a date and time on the calendar with the input format "YYYY/MM/DD HH:mm."

3. Execute the function with different date and time inputs and verify the selections.

## Scenario 4: Uploading Files

1. Open the website [https://demoqa.com/upload-download](https://demoqa.com/upload-download).

2. Explore and implement different solutions for uploading files to the website.

3. Execute the file upload operations and validate the results.

### What is this repository for? ###

* Quick summary: This project is an automation framework for web testing using Java and Selenium.
* Version: 1.0

### How do I get set up? ###

* Summary of set up:

  Make sure you have installed all of the following prerequisites on development machine:

    * **JDK 11** - [Download and install JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
    * **Maven** - [Download & Install Maven](https://maven.apache.org/download.cgi)
    * **IntelliJ IDEA** - [Download & Install IntelliJ Community](https://www.jetbrains.com/idea/download/#section=windows)

* Configuration:

  Clone this repository to local machine:

  ```bash
  git clone <repository_url>

* Configuring Maven Settings

  In the Maven configuration directory, in the file "setting.xml," located in the maven/config package, you need to make the following adjustments:

   ```<?xml version="1.0" encoding="UTF-8"?>
   <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <localRepository>/_(your project path)_/maven/repo</localRepository>
    <interactiveMode>true</interactiveMode>
    <usePluginRegistry>false</usePluginRegistry>
    <offline>false</offline>
   </settings>
   ```

  Replace _(your project path)_ with the actual path to your project on your local machine.

* Building and Installing the Project

  To build and install the project with the customized Maven settings, you can use the following command:

  ```bash 
  mvn clean install -s /_(your project path)_/maven/config/settings.xml

* Dependencies

  Add the necessary dependencies to your Maven project's pom.xml file. For Selenium WebDriver, you can include the following:

```<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>Excel_Test_Data_Repository</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <maven.compiler.version>3.8.1</maven.compiler.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <log4j.version>1.2.12</log4j.version>
        <slf4j.verion>1.7.36</slf4j.verion>
        <selenium.version>4.11.0</selenium.version>
        <webdrivermanager.version>5.4.0</webdrivermanager.version>
        <testng.version>7.8.0</testng.version>
        <allure-testng.version>2.23.0</allure-testng.version>
        <aspectj.version>1.9.6</aspectj.version>
        <maven-surefire-plugin-version>3.0.0-M5</maven-surefire-plugin-version>
        <json-path.version>2.8.0</json-path.version>
        <cucumber-java.version>7.13.0</cucumber-java.version>
        <allure.cucumber7.jvm.version>2.23.0</allure.cucumber7.jvm.version>
        <cucumber.gherkin.version>26.2.0</cucumber.gherkin.version>

        <allure-results.dir>${project.basedir}/target</allure-results.dir>

    </properties>

    <dependencies>
        <!-- https://mvnrepository.com/artifact/log4j/log4j -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>${log4j.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4j.verion}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-reload4j -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-reload4j</artifactId>
            <version>${slf4j.verion}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>${webdrivermanager.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.testng/testng -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>${testng.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.qameta.allure/allure-testng -->
        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-testng</artifactId>
            <version>${allure-testng.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.jayway.jsonpath/json-path -->
        <dependency>
            <groupId>com.jayway.jsonpath</groupId>
            <artifactId>json-path</artifactId>
            <version>${json-path.version}</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber-java.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-testng -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-testng</artifactId>
            <version>${cucumber-java.version}</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-picocontainer -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-picocontainer</artifactId>
            <version>${cucumber-java.version}</version>
        </dependency>


        <!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-compiler-plugin -->
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>${maven.compiler.version}</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.qameta.allure/allure-cucumber7-jvm -->
        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-cucumber7-jvm</artifactId>
            <version>${allure.cucumber7.jvm.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.cucumber/gherkin -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>gherkin</artifactId>
            <version>${cucumber.gherkin.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>5.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>5.0.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>11</source> <!--For JAVA 8 use 1.8-->
                    <target>11</target> <!--For JAVA 8 use 1.8-->
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${maven-surefire-plugin-version}</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>${project.basedir}/src/main/resources/test/${testsuite}.xml</suiteXmlFile>
                    </suiteXmlFiles>
                    <argLine>
                        -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
                        -Dcucumber.options="--plugin io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
                    </argLine>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>org.aspectj</groupId>
                        <artifactId>aspectjweaver</artifactId>
                        <version>${aspectj.version}</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>


</project>
```

### How to Run Tests

There are multiple ways to execute the test suite in this project:

#### Method 1: Running via IDE (Recommended)

1. Open your preferred Integrated Development Environment (IDE), such as IntelliJ IDEA.

2. Locate the `EditCustomerTest.xml` file within project's directory structure.

3. Right-click on `EditCustomerTest.xml` and select "Run" or "Run As" to execute the test suite.

#### Method 2: Running via Command Line

1. Open a terminal or command prompt.

2. Navigate to the root directory of project.

3. Run the following command to execute the test suite:

   ```bash
   mvn --settings ./maven/config/settings.xml test -Dtestsuite=EditCustomerTest

Ensure that you have added the required configuration to project's pom.xml file. Inside the <build> section, include the following code:

   ```<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>${maven-surefire-plugin-version}</version>
        <configuration>
            <suiteXmlFiles>
                <suiteXmlFile>${project.basedir}/src/main/resources/test/${testsuite}.xml</suiteXmlFile>
            </suiteXmlFiles>
        </configuration>
    </plugin>
</plugins>
```

### Deployment instructions

### Contribution guidelines

* Writing tests
* Code review
* Other guidelines

### Who do I talk to?

* Phuong Thao diinhphuongthao@gmail.com
* (Mentor) Luu Minh Tuyen



