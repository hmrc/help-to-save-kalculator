
# help-to-save-kalculator

##### code coverages
![LINE](https://img.shields.io/badge/line--coverage-98%25-brightgreen.svg)
![BRANCH](https://img.shields.io/badge/branch--coverage-86%25-brightgreen.svg)
![COMPLEXITY](https://img.shields.io/badge/complexity-1.48-brightgreen.svg)

## Calculate help to save bonus

### For new users
```kotlin
Calculator.run(
                regularPayment = 50             // Must be between 1 and 50   
)          
```
### For users with existing accounts
```kotlin
Calculator.run(
                regularPayment = 50,            // Must be between 1 and 50  
                currentBalance = 100,             
                currentFirstPeriodBonus = 50.0, 
                currentSecondPeriodBonus = 0.0,  
                accountStartDate = DateTime() 
)          
```

## Response
This will returns an object of type `CalculatorResponse`.  This provide headline figures that are the results at the end of the scheme. However, if a monthly breakdown is needed a cumulative breakdown is provided in `monthlyBreakdown`

* `monthlyPayments: Int`
* `monthlyBreakdown: List<MonthlyBreakdown>`
    * `monthNumber: Int`
    * `balance: Int`
    * `secondYearBonus: Double`
    * `fourthYearBonus: Double`
    * `totalBonusToDate: Double`
* `finalBalance: Int`
* `finalSecondYearBonus: Double`
* `finalFourthYearBonus: Double`

## Validation

To validate the monthly contributions:
```kotlin
val isValidRegularPayments        = RegularPaymentValidators.isValidRegularPayments(1000)      // true
val isAboveMinimumRegularPayments = RegularPaymentValidators.isAboveMinimumRegularPayments(0)  // false
val isBelowMaximumRegularPayments = RegularPaymentValidators.isBelowMaximumRegularPayments(50) // true
```

## Installation

TBC

> For JVM projects they can run `publishMavenLocal` and use in the project until this is added to Bintray etc.

## Development

To run unit tests and checks:

`./gradlew check`

To update the README badges:

`./gradlew cleanBuildTestCoverage`

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
