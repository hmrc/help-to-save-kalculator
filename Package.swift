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
            url: "https://github.com/hmrc/help-to-save-kalculator/releases/download/0.6.0/HelpToSaveKalculator.xcframework.zip",
            checksum: "bdbea185b5e9ab83db39c04dd42604a491f48633e869caa0442ddfeea6617fcc"
        ),
    ]
)
