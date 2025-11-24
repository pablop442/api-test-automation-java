#!/bin/bash
set -e

TIMESTAMP=$(date +"%Y-%m-%d_%H-%M-%S")
RESULTS_DIR="allure-results"
REPORT_DIR="allure-report-$TIMESTAMP"

echo "Running tests..."

rm -rf "$RESULTS_DIR"
mkdir -p "$RESULTS_DIR"

mvn clean test -Dallure.results.directory="$RESULTS_DIR"

allure generate "$RESULTS_DIR" -o "$REPORT_DIR" --clean

allure open "$REPORT_DIR"
