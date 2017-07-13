#!/bin/bash

mkdir -p out/production/BKV
javac -d out/production/BKV src/*
java -cp out/production/BKV Main
