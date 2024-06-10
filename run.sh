#!/bin/sh
javac -cp ".:sqlite-jdbc-3.46.0.0.jar" Main.java
java -cp ".:sqlite-jdbc-3.46.0.0.jar" Main
