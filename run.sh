#!/bin/bash

mkdir -p out/production/BKV
javac -d out/production/BKV src/*.java src/network/*.java
java -cp out/production/BKV Main
