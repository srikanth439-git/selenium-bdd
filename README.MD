## NHS Jobs Searching portal

### selenium-cucumber: Automation Testing Using Java

selenium-cucumber is a behavior-driven development (BDD) approach to writing automation test scripts to test web applications. It enables you to write and execute automated acceptance/unit tests. It is cross-platform, open source, and free. Automate your test cases with minimal coding.

### Installation (Pre-requisites)
- JDK 17 (Make sure Java classpath is set)
- Maven (Make sure .m2 classpath is set)
- Eclipse or IntelliJ
- Must have chrome or firefox browser

### Tools and Technologies
- Framework model: BDD-Cucumber with Page object Model
- Selenium version: 4.20.0
- JUnit version: 4.13.2
- Maven

### Running Tests through Command Line
mvn -Dtest=TestRunner test
### HTML Report
To generate an HTML report, use:
mvn test -Dcucumber.options="–plugin html:target/result.html"

Reports will be generated in the directory: `\target\cucumber-reports`, and you can view them locally.
### JSON Report
To generate a JSON report, use:
mvn test -Dcucumber.options="–plugin json:target/result.json"