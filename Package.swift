// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "HelpToSaveKalculator",
    platforms: [
        .iOS(.v11)
    ],
    products: [
        .library(
            name: "HelpToSaveKalculator",
            targets: ["HelpToSaveKalculator"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "HelpToSaveKalculator",
            url: "https://github.com/hmrc/help-to-save-kalculator/releases/download/0.4.1/HelpToSaveKalculator.xcframework.zip",
            checksum: "24f71d23d4586a9b9e67c484984a1bd2b9e6313d4a1fcc7eaa41b4b1ebe52a76"
        ),
    ]
)
