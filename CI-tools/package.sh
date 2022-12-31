#!/bin/bash

# Path for the icon is given as argument when calling the script (different format based on OS)
ICON_PATH=$1

jpackage \
 --name "Synchro Calculator" \
 --input target \
 --main-jar SynchroCalculator.jar \
 --main-class alrapal.App \
 --runtime-image target/SynchroCalculator \
 --java-options "--add-exports javafx.base/com.sun.javafx.event=org.controlsfx.controls" \
 --icon "$ICON_PATH"
