#!/bin/sh

rm -rf ../pragmatach-site/*
mkdir ../pragmatach-site
cd site
mvn clean package
cp -R target/site/* ../../pragmatach-site/
cd ..
mvn javadoc:javadoc
mkdir ../pragmatach-site/apidocs
cp -R target/site/apidocs/* ../pragmatach-site/apidocs
mvn clean package deploy
cp -R repo ../pragmatach-site/