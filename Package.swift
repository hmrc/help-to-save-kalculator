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
            url: "https://github.com/hmrc/help-to-save-kalculator/releases/download/0.8.0/HelpToSaveKalculator.xcframework.zip",
            checksum: "97db3b9821ea7b100c5e4705446be686ae270e0751625f2a2f52ac73e0718c11"
        ),
    ]
)
