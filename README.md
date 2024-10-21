
# help-to-save-kalculator

## Status
[![Build Status](https://app.bitrise.io/app/6a0a30b884ce6131/status.svg?token=q0pKDUFK3Qfa6sXfy66vog&branch=master)](https://app.bitrise.io/app/6a0a30b884ce6131)
[![Github](https://img.shields.io/github/release/hmrc/help-to-save-kalculator.svg)](https://gitHub.com/hmrc/help-to-save-kalculator/releases/)  
![swift-pm](https://img.shields.io/badge/SwiftPM-Compatible-success.svg)

# Contents
* [Bonus for new users](#bonus-for-new-users)
* [Bonus for existing accounts](#bonus-for-existing-accounts)
* [Accounts in first term](#accounts-in-first-term)
* [Existing accounts in final term](#existing-accounts-in-final-term)
* [Usage](#usage)
* [Release process](#release-process)
* [License](#license)

## Bonus for new users
#### Android
```kotlin
Calculator.run(regularPayment = 50.0) // Must be between 1 and 50)          
```
#### iOS
```swift
Calculator().run(regularPayment: 50.0) // Must be between 1 and 50)          
```
## Bonus for existing accounts
#### Android
```kotlin
Calculator.run(
    regularPayment = 50.0, // Must be between 1 and 50  
    currentBalance = 100.0,             
    currentPeriod1Bonus = 50.0, 
    currentPeriod2Bonus = 0.0,  
    accountStartDate = DateTime()
)          
```
#### iOS
```swift
Calculator().run(
    regularPayment: 50.0, // Must be between 1 and 50  
    currentBalance: 100.0,             
    currentPeriod1Bonus: 50.0, 
    currentPeriod2Bonus: 0.0,  
    accountStartDate: Date()
)          
```

### Response structure
This will return an object of type `CalculatorResponse`.  This provides headline figures that are the results at the end of the scheme. However, if a monthly breakdown is needed a cumulative breakdown is provided in `monthlyBreakdown`

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

## Accounts in first term
#### Android
```kotlin
FirstBonusTermCalculator.runFirstBonusCalculator(input)
```
```swift
FirstBonusTermCalculator().runFirstBonusCalculator(input)
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

## Existing accounts in final term
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

## Usage

### iOS
#### Swift Package Manager
> Because this operates as a closed source, binary dependency, Swift PM will only work with tagged releases and not branches.
```swift
https://github.com/hmrc/help-to-save-kalculator
```

#### Simulator Architectures:
* If the framework is downloaded and linked in the project, it'll be necessary to strip unwanted architectures in a build step.
  * For example, you may want to implement something like [this](http://ikennd.ac/blog/2015/02/stripping-unwanted-architectures-from-dynamic-libraries-in-xcode/).

### Android & JVM

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

## Release process

```shell
bundle exec fastlane tag_release
```

### Required
* A valid Bitrise access token saved in your path under the variable name `BITRISE_TOKEN`. See [Bitrise docs](https://devcenter.bitrise.io/api/authentication).
* Two environment variables, `HTS_KALC_APP_SLUG` & `HTS_KALC_RELEASE_WORKFLOW_ID` will also need to be included in your bash/ZSH profile. These can be found in Bitwarden.

### Steps executed
* Ensure git status is clean
* Ensure `main` branch
* Through the interactive shell, select the tag version using semantic versioning.
* Locally executes `build_xcframework.sh`:
  * Creates an XCFramework
  * Computes and updates the checksum in the Swift Package declaration.
* Stamps the changelog
* Commit and push the updated `Package.swift` and `CHANGELOG.md`
* Upload release artifacts to tagged Github release
* Executes `release.sh` to start the CI pipeline on CI.

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
