rm -rf ../pragmatach-site/*
cd site
mvn clean package
cp -R target/site/* ../../pragmatach-site/
cd ..
cd pragmatach-framework
mvn javadoc:javadoc
cp -R target/site/apidocs ../../pragmatach-site/
cd ..
mvn clean package deploy
cp -R repo ../pragmatach-site/