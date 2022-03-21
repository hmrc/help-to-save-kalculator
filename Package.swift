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
            url: "https://github.com/hmrc/help-to-save-kalculator/releases/download/0.7.0/HelpToSaveKalculator.xcframework.zip",
            checksum: "66888dd0d7580d6e78d5dff96c544086e241dae62d45352253a9c224254c8cd6"
        ),
    ]
)
