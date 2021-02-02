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
            checksum: "f69a1fd6747c85813c62f5252bc0ac96b3e1d66bdb8ff2e3ccb9f1b6af89416a"
        ),
    ]
)
