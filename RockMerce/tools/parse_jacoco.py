#!/usr/bin/env python3
"""
Semplice parser per target/site/jacoco/jacoco.xml
Stampa coverage summary: instructions, branches, lines, methods, classes
Uso:
    python3 parse_jacoco.py [--file PATH]
"""
import sys
import xml.etree.ElementTree as ET
from pathlib import Path
import argparse

DEFAULT_PATH = Path('target/site/jacoco/jacoco.xml')

parser = argparse.ArgumentParser(description='Parse JaCoCo XML report and print coverage summary')
parser.add_argument('--file', '-f', default=str(DEFAULT_PATH), help='Path to jacoco.xml (default: target/site/jacoco/jacoco.xml)')
args = parser.parse_args()

JACOCO_XML = Path(args.file)

if not JACOCO_XML.exists():
    print(f"jacoco.xml non trovato in {JACOCO_XML.resolve()}")
    sys.exit(2)

try:
    root = ET.parse(JACOCO_XML).getroot()
except ET.ParseError as e:
    print(f"Errore parsing XML: {e}")
    sys.exit(3)

# <report> -> contains <counter type="INSTRUCTION" missed="x" covered="y"/>
counters = {}
for counter in root.findall('.//counter'):
    t = counter.get('type')
    try:
        missed = int(counter.get('missed', '0'))
        covered = int(counter.get('covered', '0'))
    except (TypeError, ValueError):
        missed = 0
        covered = 0
    counters[t] = (missed, covered)

# helper
def pct(missed, covered):
    total = missed + covered
    if total == 0:
        return 0.0
    return covered * 100.0 / total

any_printed = False
for kind in ['INSTRUCTION','BRANCH','LINE','METHOD','CLASS']:
    if kind in counters:
        missed, covered = counters[kind]
        print(f"{kind}: {covered}/{missed+covered} covered -> {pct(missed,covered):.2f}%")
        any_printed = True
    else:
        print(f"{kind}: not present in report")

if not any_printed:
    # No counters found: possibly different XML schema
    print("Nessun counter trovato nel file jacoco.xml. Verifica che il file sia un report JaCoCo.")
    sys.exit(4)

sys.exit(0)
