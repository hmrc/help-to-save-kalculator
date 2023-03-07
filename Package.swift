// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "HelpToSaveKalculator",
    platforms: [
        .iOS(.v13)
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
            url: "https://github.com/hmrc/help-to-save-kalculator/releases/download/0.8.1/HelpToSaveKalculator.xcframework.zip",
            checksum: "cec72b55c6c2db86cb67aa6a65e78a1f4cbc4ce89a1b09ac2564bfcd70df95eb"
        ),
    ]
)
