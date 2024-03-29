#!/usr/bin/env bash

SEMVER_REGEX="^([0-9]|[1-9][0-9]*)\.([0-9]|[1-9][0-9]*)\.([0-9]|[1-9][0-9]*)(?:-([0-9A-Za-z-]+(?:\.[0-9A-Za-z-]+)*))?(?:\+[0-9A-Za-z-]+)?$"
if [ -z "$GITHUB_TOKEN" ]; then
  echo "ERROR: Please provide a Github token"
else
  if echo $1 | grep -Eq $SEMVER_REGEX; then
    # Run tests and build xcframework
    if ./gradlew check && ./gradlew assembleHelpToSaveKalculatorXCFramework; then
      DIRECTORY="swiftpm"
      ZIP="HelpToSaveKalculator.xcframework.zip"
      if [ -d "$DIRECTORY" ]; then
        rm -rf $DIRECTORY
      fi
      if [ -f "$ZIP" ]; then
        rm $ZIP
      fi
      mkdir $DIRECTORY
      echo "INFO: Copying artifact"
      cp -r build/XCFrameworks/release/HelpToSaveKalculator.xcframework $DIRECTORY
      echo "INFO: Zipping"
      cd $DIRECTORY
      zip -r $ZIP .

      if CHECK_SUM=$(swift package compute-checksum HelpToSaveKalculator.xcframework.zip); then
        echo "INFO: $(date +'%c') - Updating Package.swift with checksum ${CHECK_SUM}"
        sed -i .bak "s/checksum.*/checksum: \"${CHECK_SUM}\"/g" ../Package.swift

        sed -i .bak \
        "s/url: \"https:\/\/github.com\/hmrc\/help-to-save-kalculator\/releases.*/url: \"https\:\/\/github.com\/hmrc\/help-to-save-kalculator\/releases\/download\/${1}\/HelpToSaveKalculator.xcframework.zip\",/g" \
        ../Package.swift
      else
        echo "ERROR: $(date +'%c') - Failed to create checksum"
      fi
    else
      echo "ERROR: $(date +'%c') - Build failed"
    fi
  else
    echo "ERROR: Please provide a valid git tag"
  fi
fi
