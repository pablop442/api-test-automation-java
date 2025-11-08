#!/bin/bash
# Exit immediately if a command exits with a non-zero status
set -e

# --- Configuration ---
REPORT_DIR="allure-report"
RESULTS_DIR="allure-results"
HISTORY_DIR="$REPORT_DIR/history"

# --- Execution ---
echo "Starting API tests with Allure history preservation..."

# 1. Clean up old results directory and prepare for history copy
rm -rf "$RESULTS_DIR"
mkdir -p "$RESULTS_DIR"
echo "Cleaned previous test results from $RESULTS_DIR."

# 2. Copy the history from the previously generated report
if [ -d "$HISTORY_DIR" ]; then
    cp -r "$HISTORY_DIR" "$RESULTS_DIR/"
    echo "Successfully copied previous report history from $HISTORY_DIR to $RESULTS_DIR/history."
else
    echo "History directory $HISTORY_DIR not found. Generating a fresh report without history."
fi

# 3. Run the tests
mvn -fae clean test
echo "API tests completed. Results are in $RESULTS_DIR."

# 4. Generate the new report
allure generate --clean "$RESULTS_DIR" -o "$REPORT_DIR"
echo "Allure report successfully generated to $REPORT_DIR."

# 5. Open the report in the default browser
allure open
echo "Allure report generated and opened in browser."
