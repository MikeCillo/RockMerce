#!/usr/bin/env bash
set -euo pipefail

MVN_CMD="./mvnw"
JACOCO_GOAL="org.jacoco:jacoco-maven-plugin:0.8.14:report"
LOGFILE="tools/coverage.log"
TEST_PATTERN="${TEST_PATTERN:-*ServletTest}"


echo "Running coverage script. Logs -> $LOGFILE"
echo "Working dir: $(pwd)" > "$LOGFILE"

echo "Using test pattern: $TEST_PATTERN" | tee -a "$LOGFILE"

if [ ! -x "$MVN_CMD" ]; then
  echo "Making wrapper executable: $MVN_CMD" | tee -a "$LOGFILE"
  chmod +x "$MVN_CMD"
fi

# Build the mvn command dynamically
MVN_FULL_CMD=("$MVN_CMD" "-DskipTests=false")
if [ "$TEST_PATTERN" != "" ] && [ "$TEST_PATTERN" != "ALL" ]; then
  MVN_FULL_CMD+=("-Dtest=$TEST_PATTERN")
fi
MVN_FULL_CMD+=("clean" "test" "$JACOCO_GOAL")

# Show and run
echo "Running: ${MVN_FULL_CMD[*]}" | tee -a "$LOGFILE"
"${MVN_FULL_CMD[@]}" 2>&1 | tee -a "$LOGFILE"

# Check for jacoco.xml
JACOCO_XML="target/site/jacoco/jacoco.xml"
if [ ! -f "$JACOCO_XML" ]; then
  echo "ERROR: $JACOCO_XML not found. See $LOGFILE for mvn output." | tee -a "$LOGFILE"
  exit 2
fi

# Run parser
echo "Parsing $JACOCO_XML" | tee -a "$LOGFILE"
python3 tools/parse_jacoco.py -f "$JACOCO_XML" 2>&1 | tee -a "$LOGFILE"

echo "Done. See $LOGFILE for the full logs." | tee -a "$LOGFILE"
