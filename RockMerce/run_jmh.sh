#!/usr/bin/env zsh
# Script per eseguire benchmark JMH nel progetto RockMerce
# Usage:
#   ./run_jmh.sh cart      # esegue CartServiceAdapterBenchmark
#   ./run_jmh.sh login     # esegue LoginBenchmark
#   ./run_jmh.sh ".*MyBenchmark.*"  # esegue la regex fornita
# Default JMH args possono essere sovrascritti impostando JMH_ARGS env var.

set -euo pipefail
PROJECT_ROOT=$(cd "$(dirname "$0")" && pwd)
cd "$PROJECT_ROOT"

if [[ $# -lt 1 ]]; then
  echo "Usage: $0 <cart|login|<regex>>"
  exit 1
fi

KEY=$1
case "$KEY" in
  cart)
    REGEX=".*CartServiceAdapterBenchmark.*"
    ;;
  login)
    REGEX=".*LoginBenchmark.*"
    ;;
  *)
    REGEX="$KEY"
    ;;
esac

# Default JMH args (override passing env JMH_ARGS string)
if [[ -z "${JMH_ARGS:-}" ]]; then
  JMH_ARGS_ARR=( -wi 5 -i 10 -f 1 -t 1 -bm thrpt )
else
  # split JMH_ARGS into array on whitespace
  read -A JMH_ARGS_ARR <<< "$JMH_ARGS"
fi

# Ensure classes are compiled and build classpath
./mvnw -DskipTests test-compile dependency:build-classpath -Dmdep.outputFile=cp.txt
CP="target/classes:target/test-classes:$(cat cp.txt)"

echo "Running JMH with regex: $REGEX"
echo "Classpath first entries:"
echo "  target/classes"
echo "  target/test-classes"

echo "java -cp $CP org.openjdk.jmh.Main \"$REGEX\" ${JMH_ARGS_ARR[@]}"

# Execute JMH
java -cp "$CP" org.openjdk.jmh.Main "$REGEX" "${JMH_ARGS_ARR[@]}"
