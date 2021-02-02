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
            url: "https://github.com/hmrc/help-to-save-kalculator/releases/download/0.4.0/HelpToSaveKalculator.xcframework.zip",
            checksum: "a0ea982423ca636477afe614a13c01d84f02644a18ef5bbb146654684f0ec5a4"
        ),
    ]
)
