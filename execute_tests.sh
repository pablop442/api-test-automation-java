#!/bin/bash
set -e

#Profile checking
if [ -z "$1" ]; then
    echo "Executing: $0 <profile_name>..."
    exit 1
fi

PROFILE_NAME=$1
TIMESTAMP=$(date +"%Y-%m-%d_%H-%M-%S")
RESULTS_DIR="allure-results"
REPORT_DIR="allure-report-$TIMESTAMP"

echo "Running tests with profile: $PROFILE_NAME"

rm -rf "$RESULTS_DIR"
mkdir -p "$RESULTS_DIR"

mvn clean test -P"$PROFILE_NAME" -Dallure.results.directory="$RESULTS_DIR"

allure generate "$RESULTS_DIR" -o "$REPORT_DIR" --clean

allure open "$REPORT_DIR"
