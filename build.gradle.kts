import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Properties
import org.gradle.api.tasks.GradleBuild
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript{
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/hmrc/mobile-gradle-plugins")
            credentials {
                username = System.getenv("GITHUB_USER_NAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    dependencies {
        classpath("uk.gov.hmrc.gradle:spotless:1.0.0")
    }
}


/***********************************************************************************************************************
 * Project Gradle Config
 ***********************************************************************************************************************/
apply(plugin = "uk.gov.hmrc.spotless")

group = "uk.gov.hmrc"
description = "Multiplatform Help To Save Calculator library"
version = System.getenv("BITRISE_GIT_TAG") ?: ("SNAPSHOT-" + getDate())

plugins {
    `maven-publish`
    kotlin("multiplatform").version("1.4.10")
    jacoco
    java
    id("com.github.dawnwords.jacoco.badge").version("0.1.0")
    id("io.gitlab.arturbosch.detekt").version("1.1.1")
    id("com.chromaticnoise.multiplatform-swiftpackage").version("2.0.3")
}

repositories {
    mavenCentral()
    jcenter()
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
}

/***********************************************************************************************************************
 * Declarations
 ***********************************************************************************************************************/

val artifactId = "help-to-save-kalculator"
val frameworkName = "HelpToSaveKalculator"
val licenseString = """/*
 * Copyright ${getYear()} HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
"""

/***********************************************************************************************************************
 * Kotlin Configuration
 ***********************************************************************************************************************/

kotlin {

    jvm()
    val iosX64 = iosX64("ios")
    val iosArm32 = iosArm32()
    val iosArm64 = iosArm64() //Simulator

    targets{
        configure(listOf(iosX64, iosArm32, iosArm64)) {
            binaries.framework {
                baseName = frameworkName
                embedBitcode("disable")
            }
        }
    }

    sourceSets.all {
        languageSettings.useExperimentalAnnotation("kotlin.Experimental")
    }

    sourceSets {
        val klockVersion = "2.0.1"
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("com.soywiz.korlibs.klock:klock:$klockVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("com.soywiz.korlibs.klock:klock-jvm:$klockVersion")
                implementation(kotlin("stdlib"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }

        val iosTest by getting {
        }

        val iosArm32Main by sourceSets.getting
        val iosArm64Main by sourceSets.getting

        configure(listOf(iosArm32Main, iosArm64Main)) {
            dependsOn(iosMain)
        }

        val iosArm32Test by sourceSets.getting
        val iosArm64Test by sourceSets.getting
        configure(listOf(iosArm32Test, iosArm64Test)) {
            dependsOn(iosTest)
        }
    }
}

/***********************************************************************************************************************
 * Swift Package Manager Configuration
 ***********************************************************************************************************************/

multiplatformSwiftPackage {
    swiftToolsVersion("5.3")
    targetPlatforms {
        iOS { v("11") }
    }
    outputDirectory(File(projectDir, "build/xcframework"))
}

/***********************************************************************************************************************
 * Other Task Configuration
 ***********************************************************************************************************************/

configurations {
    compileClasspath
}

jacocoBadgeGenSetting {

    jacocoReportPath = "$buildDir/reports/jacoco/commonUnitTestCoverage/commonUnitTestCoverage.xml"
}

jacoco {
    toolVersion = "0.8.4"
}

detekt {
    input = files("src/commonMain/kotlin")
    config = files("detekt-config.yml")
    reports {
        html {
            enabled = true
            destination = file("build/reports/detekt/index.html")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "$group.$artifactId"
            artifactId = artifactId
            version = version
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/hmrc/help-to-save-kalculator")
            credentials {
                username = System.getenv("GITHUB_USER_NAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

tasks.jacocoTestCoverageVerification {
    group = project.name

    violationRules {
        rule {
            limit {
                minimum = "0.87".toBigDecimal()
            }
        }
    }
    val excludes = listOf("**/*Test*.*")
    val coverageSourceDirs = listOf("src/commonMain/kotlin")
    sourceDirectories.setFrom(files(coverageSourceDirs))
    classDirectories.setFrom(fileTree("${project.buildDir}/classes/kotlin/jvm/").exclude(excludes))
    executionData.setFrom(files("${project.buildDir}/jacoco/jvmTest.exec"))
}


/***********************************************************************************************************************
 * Custom Functions
 **********************************************************************************************************************/
fun getYear(): String {
    return Calendar.getInstance().get(Calendar.YEAR).toString()
}

fun getDate(): String {
    val date = Date()
    val format = "yyyyMMddHHmm"
    return SimpleDateFormat(format).format(date).toString()
}


/***********************************************************************************************************************
 * Custom Tasks
 ***********************************************************************************************************************/

tasks.register<JacocoReport>("commonUnitTestCoverage") {
    group = project.name
    description = "Generate Jacoco coverage reports on the common module build."

    this.dependsOn("allTests")
    val excludes = listOf("**/*Test*.*")
    val coverageSourceDirs = listOf("src/commonMain/kotlin")
    executionData(files("${project.buildDir}/jacoco/jvmTest.exec"))

    reports {
        xml.isEnabled = true
        html.isEnabled = true
        sourceDirectories.setFrom(files(coverageSourceDirs))
        classDirectories.setFrom(fileTree("${project.buildDir}/classes/kotlin/jvm/").exclude(excludes))
    }
}

tasks.register<GradleBuild>("cleanBuildTestCoverage") {
    group = project.name

    tasks = listOf(
            "clean",
            "cleanAllTests",
            "build",
            "commonUnitTestCoverage",
            "generateJacocoBadge",
            "jacocoTestCoverageVerification"
    )
}
