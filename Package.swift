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
            url: "https://github.com/hmrc/help-to-save-kalculator/releases/download/0.4.1/HelpToSaveKalculator.xcframework.zip"
        ),
    ]
)
