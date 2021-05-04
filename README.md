
# help-to-save-kalculator

## Status
[![Build Status](https://app.bitrise.io/app/6a0a30b884ce6131/status.svg?token=q0pKDUFK3Qfa6sXfy66vog&branch=master)](https://app.bitrise.io/app/6a0a30b884ce6131)
![LINE](https://img.shields.io/badge/line--coverage-98%25-brightgreen.svg)
![BRANCH](https://img.shields.io/badge/branch--coverage-82%25-brightgreen.svg)
![COMPLEXITY](https://img.shields.io/badge/complexity-1.64-brightgreen.svg)
[![Github](https://img.shields.io/github/release/hmrc/help-to-save-kalculator.svg)](https://gitHub.com/hmrc/help-to-save-kalculator/releases/)  
![swift-pm](https://img.shields.io/badge/SwiftPM-Compatible-success.svg)
## Calculate help to save bonus

## For new users
```kotlin
Calculator.run(regularPayment = 50.0) // Must be between 1 and 50)          
```
## For users with existing accounts
```kotlin
Calculator.run(
        regularPayment = 50.0, // Must be between 1 and 50  
        currentBalance = 100.0,             
        currentPeriod1Bonus = 50.0, 
        currentPeriod2Bonus = 0.0,  
        accountStartDate = DateTime()
)          
```

### Response
This will returns an object of type `CalculatorResponse`.  This provides headline figures that are the results at the end of the scheme. However, if a monthly breakdown is needed a cumulative breakdown is provided in `monthlyBreakdown`

* `monthlyPayments: Double`
* `monthlyBreakdown: List<MonthlyBreakdown>`
    * `monthNumber: Int`
    * `savingsToDate: Double`
    * `period1Bonus: Double`
    * `period2Bonus: Double`
    * `bonusToDate: Double`
* `endOfSchemeBonus: Double`
* `endOfSchemeSavings: Double`
* `endOfSchemeTotal: Double`
* `endOfPeriod1Bonus: Double`
* `endOfPeriod1Savings: Double`
* `endOfPeriod1Total: Double`
* `endOfPeriod2Bonus: Double`
* `endOfPeriod2Savings: Double`
* `endOfPeriod2Total: Double`

## For existing accounts in first term
```kotlin
FirstBonusTermCalculator.runFirstBonusCalculator(input)
```
Where `input` is of the type `FirstBonusInput` with the following parameters:
```
regularPayment: Double,                 // 25.0
currentBalance: Double,                 // 25.0
paidInThisMonth: Double,                // 50.0
thisMonthEndDate: YearMonthDayInput,    // YearMonthDayInput(2020, 3, 31)
firstTermEndDate: YearMonthDayInput,    // YearMonthDayInput(2022, 2, 28)
secondTermEndDate: YearMonthDayInput,   // YearMonthDayInput(2024, 2, 28)
balanceMustBeMoreThanForBonus: Double   // 50.0
```

### Response
This will returns an object of type `FirstBonusCalculatorResponse`. 
* `totalProjectedSavingsIncludingBonuses: Double`
* `totalProjectedSavings: Double`
* `totalProjectedBonuses: Double`
* `projectedSavingsFirstBonusPeriod: Double`
* `projectedFirstBonus: Double`
* `projectedAdditionalSavingsFinalBonusPeriod: Double`
* `projectedFinalBonus: Double`

## For existing accounts in final term
```kotlin
FinalBonusTermCalculator.runFinalBonusCalculator(input)
```
Where `input` is of the type `FinalBonusInput` with the following parameters:
```
regularPayment: Double,                 // 25.0
currentBalance: Double,                 // 25.0
paidInThisMonth: Double,                // 50.0
canPayInThisMonth: Double,              // 0.0
thisMonthEndDate: YearMonthDayInput,    // YearMonthDayInput(2022, 3, 31)
secondTermEndDate: YearMonthDayInput,   // YearMonthDayInput(2024, 2, 28)
balanceMustBeMoreThanForBonus: Double,  // 50.0
secondTermBonusEstimate: Double         // 25.0
```

### Response
This will returns an object of type `FinalBonusCalculatorResponse`. 
* `totalProjectedSavingsIncludingBonuses: Double`
* `totalProjectedSavings: Double`
* `totalProjectedBonuses: Double`
* `finalBonusStatus: FinalBonusStatus`

## Validation

To validate the monthly contributions:
```kotlin
val isValidRegularPayments        = RegularPaymentValidators.isValidRegularPayments(1000.0)      // true
val isAboveMinimumRegularPayments = RegularPaymentValidators.isAboveMinimumRegularPayments(0.0)  // false
val isBelowMaximumRegularPayments = RegularPaymentValidators.isBelowMaximumRegularPayments(50.0) // true
```

## Installation

### iOS
#### Swift Package Manager
- From version `0.5.0` onwards, the use of `Swift Package Manager` is required.
- Note: Because this operates as a closed source, binary dependency, Swift PM will only work with tagged releases and not branches.
```swift
https://github.com/hmrc/help-to-save-kalculator
```

#### Carthage
- For all versions below `0.5.0`, the use of `Carthage` is required.
- Each release tag includes a Carthage binary dependency specification. To use the Carthage binary:
* In the same directory as your Cartfile, add a directory.
```shell script
$ mkdir Carthage-Binaries
``` 
* Add a JSON file that holds your Carthage binary specifications.
```shell script
$ touch Carthage-Binaries/HelpToSaveKalculator.json
```
* Point to the latest release in your JSON file.
```json
    {
      "0.3.7": "https://github.com/hmrc/help-to-save-kalculator/releases/download/0.3.7/HelpToSaveKalculator.framework.zip"
    }
```
* List the dependency in your Cartfile
```shell script
    ...
    binary "Carthage-Binaries/HelpToSaveKalculator.json" == 0.3.7
    ...
```
* Update your Carthage dependencies as per your requirements.

#### Simulator Architectures: 
* Most Carthage users will include a Carthage [copy-frameworks](https://www.raywenderlich.com/416-carthage-tutorial-getting-started) build step that removes unwanted architectures for 
distribution builds.
* If you don't use Carthage and just download and link the framework in your project, it'll be necessary to strip unwanted architectures in a build step.
You may want to implement something like [this](http://ikennd.ac/blog/2015/02/stripping-unwanted-architectures-from-dynamic-libraries-in-xcode/).

### Android or JVM

Add the Github Package repository to your top-level `build.gradle`, along with a Github username and access token (no permissions required).

```groovy
repositories {
  maven {
    url = "https://maven.pkg.github.com/hmrc/help-to-save-kalculator"
    credentials {
      username = System.getenv("GITHUB_USER_NAME")
      password = System.getenv("GITHUB_TOKEN")
    }
  }
}
```

Add the dependency in the `build.gradle` of the module:

```groovy
dependencies {
    implementation "uk.gov.hmrc:help-to-save-kalculator-jvm:x.y.z"
}
```

## Development

To run unit tests and checks:

`./gradlew check`

To update the README badges:

`./gradlew cleanBuildTestCoverage`

### Release process

The CI tool has been set up to trigger a build and publish to GitHub packages when a tag is created on a build.

Recommended flow:
- Raise PR
- Approved PR
- Merge
- Wait for Bitrise to build and test
- Tag for release
- Apps update to new version

You need to:
* Have a valid Bitrise access token saved in your path under the variable name `BITRISE_TOKEN`. See [Bitrise docs](https://devcenter.bitrise.io/api/authentication).
* Two environment variables, `HTS_KALC_APP_SLUG` & `HTS_KALC_RELEASE_WORKFLOW_ID` will also need to be included in your bash/ZSH profile. Speak with [Chris](https://github.com/chrisob55) to obtain these values.

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
