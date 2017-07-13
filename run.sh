#!/bin/bash

mkdir -p out/production/BKV
javac -cp lib/json-simple-1.1.jar -d out/production/BKV src/*.java src/network/*.java
java -cp lib/json-simple-1.1.jar:out/production/BKV Main
