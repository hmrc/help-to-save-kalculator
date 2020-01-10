
# help-to-save-kalculator

##### code coverages
![LINE](https://img.shields.io/badge/line--coverage-92%25-brightgreen.svg)
![BRANCH](https://img.shields.io/badge/branch--coverage-89%25-brightgreen.svg)
![COMPLEXITY](https://img.shields.io/badge/complexity-1.59-brightgreen.svg)

## Calculate help to save bonus

```kotlin
Calculator.run(
                regularPayment = 50,                // Required (must be between 1 and 50)   
                currentBalance = null,              // Optional (Int) (Default: null)
                currentFirstPeriodBonus = null,     // Optional (Double) (Default: null)
                currentSecondPeriodBonus = null,    // Optional (Double) (Default: null)
                accountStartDate = null             // Optional (DateTime) (Default: null)
)          
```

 > `currentBalance`, `currentFirstPeriodBonus`, `currentSecondPeriodBonus`, `currentSecondPeriodBonus` and `accountStartDate` must either be ALL provided or all null.

> The default values are working in Android (and other JVM) but currently do not seem to be present in iOS, so pass in the default values for now.

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
