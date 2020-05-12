# Selenium Workspace

[![N|Solid](https://www.selenium.dev/images/selenium_logo_large.png)](https://www.selenium.dev/)

This is a fully functional Selenium workspace for testing and reuse.

# Features!

  - Selenium + Java
  - Data driven testing using excel sheet for inputs
  - TestNG framework
  - SureFire plugin
  - Customized reports (see webtesting\reports)
  - Screenshot on failure and attaching the screenshot to the report
  - Page file
  - Maven framework
  - HTTP Response saved in xml file (Testcase008)
  - CI ready (run maven commands)

### Test cases

* Testcase001 - Testing failed sign up for empty data fields (-ve testing)
* Testcase002 - Testing failed sign up for invalid first name (-ve testing)
* Testcase003 - Testing failed sign up for invalid last name (-ve testing)
* Testcase004 - Testing failed sign up for invalid password name (-ve testing)
* Testcase005 - Testing failed sign up for invalid email (-ve testing)
* Testcase006 - Testing failed sign up for invalid phone (-ve testing)
* Testcase007 - Testing successful sign up for valid data (+ve testing)
* Testcase008 - Saving HTTP response into xml file

### Installation

Everything you need is in the pom.xml no prerequisites needed

Install the dependencies using this command

```sh
$ mvn dependency:sources
```

For javadoc run this command (optional)

```sh
$ mvn dependency:resolve -DClassifier=javadoc
```

### Plugins

SureFire is added in the pom file


### Running test cases

First clean the /target directory using this command
```sh
$ mvn clean
```

You can then install the dependencies from the above commands

Use this command for the RegTest suite
```sh
$ mvn test -PRegTest
```

### Reports
Report can be found in "webtesting\reports"

### HTTP Response
Response can be found in "webtesting\HTTPResponses"