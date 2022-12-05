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
            url: "https://github.com/hmrc/help-to-save-kalculator/releases/download/0.7.1/HelpToSaveKalculator.xcframework.zip",
            checksum: "034750c0e2b72b855322dce9ef3c3008f250a75a8db516580f6031518fcff0fc"
        ),
    ]
)
