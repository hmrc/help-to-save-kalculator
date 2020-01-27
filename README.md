
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

### iOS

Each release tag includes a Carthage binary dependency specification. To use the Carthage binary:
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
      "0.3.7": "https://github.com/hmrc/tax-kalculator/releases/download/0.3.7/HelpToSaveKalculator.framework.zip"
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

Add the mobile-releases bintray repository to your top-level `build.gradle`:

```groovy
repositories {
    maven {
        url  "https://hmrc.bintray.com/mobile-releases" 
    }
}
```

Add the dependency in the `build.gradle` of the module:

```groovy
dependencies {
    implementation "uk.gov.hmrc:tax-kalculator-jvm:x.y.z"
}
```

## Development

To run unit tests and checks:

`./gradlew check`

To update the README badges:

`./gradlew cleanBuildTestCoverage`

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
