#!/usr/bin/env bash
# Inspired by https://cuneyt.aliustaoglu.biz/en/creating-standalone-macos-app-dmg-from-javafx/

#Variables
NAME="Synchro Calculator"
ICON_PATH="../CI-tools/general/logo.icns"
RUNNER_PATH="../CI-tools/mac_os/Runner.sh"
BUNDLE_DIR_NAME=bundle_macOS
BUNDLE_PATH="./Synchro Calculator.app/Contents/MacOS"
JLINK_IMAGE_PATH="../target/SynchroCalculator/"
APPIFY_PATH="../CI-tools/mac_os/appify.sh"

# Creates the Directory that will contain the Bundled app
mkdir $BUNDLE_DIR_NAME
# Go into the directory
cd $BUNDLE_DIR_NAME
# Makes the Runner.sh used by appify an executable script
chmod +x "$RUNNER_PATH"
# Uses appify to make the bundle
$APPIFY_PATH -i "$ICON_PATH" -n "$NAME" -s "$RUNNER_PATH"
# Copies the image generated with javafx:jlink plugin in the bundle
cp -R "$JLINK_IMAGE_PATH" "$BUNDLE_PATH"