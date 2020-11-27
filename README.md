# Serenity Test Automation -  Vending Machine and Bike API Tests

Serenity test framework combining acceptance tests in a single report.

### Pre Request

* Jdk 8
* Maven 3.5+
* IDE (IntelliJ/Eclipse etc)
* Git

### IDE setup

Enable Annotation Processing in IntelliJ settings and build the project. 

### Running the test with different Maven profiles

**Running Unit test**
```sh
$  mvn clean -Punit_tests test
```

**Running the Serenity Test with Cucumber Tag**
```sh
$ mvn clean -Ptests verify -DCucumber.options="--tags @Regression"
```

### Running the test from IDE

* Running from Serenity Runner file for API test

  Location: src/test/java/uk/co/api/runner/TestSuite.java
  
* Running from Serenity Runner file for Vending Machine test

  Location: src/test/java/uk/co/machine/runner/TestSuite.java

### Viewing the test report

```sh
target\site\reports\serenity\index.html
```


Functionalites | Tags | 
--- | --- |
Regression | @Regression | 
Vending Machine | @VendingMachine| 
City Bike | @CityBike | 

## Serenity Report sample

