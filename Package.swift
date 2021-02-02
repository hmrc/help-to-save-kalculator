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
            url: "https://github.com/hmrc/help-to-save-kalculator/releases/download/0.5.0/HelpToSaveKalculator.xcframework.zip",
            checksum: "a6fa980dde3ebd7eccb763804a8ee0cb0aeb6ee31641b5db0b745505cb3b6059"
        ),
    ]
)
