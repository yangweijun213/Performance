#!/usr/bin/env bash

#the path of colline home directory
COLLINE_HOME=../..

CLASS_PATH="";
pushd ${COLLINE_HOME}/bin
#Load environment parameter
. ./env.sh

#Get database properties, either oracle.properties/sqlserver.properties or colline.properties
if [ -f databasetype.sh ]; then
        . ./databasetype.sh
fi

#colline.properties or oracle.properties/sqlserver.properties
if [ -f colline.properties ]; then
        DATABASE_PROPERTIES=colline.properties
else
        DATABASE_PROPERTIES=${DATABASE_TYPE}.properties
fi

#Get LRSClient.jar, LRS3rdParty.jar, database driver libs like ojdbc.jar jtds.jar
for JAR in `ls *.jar`
do
        CLASS_PATH="${COLLINE_HOME}/bin/${JAR}:${CLASS_PATH}"
done
popd

#Get supportutility.jar
for JAR in `ls *.jar`
do
        CLASS_PATH="${CLASS_PATH}:${JAR}"
done

java -cp ${CLASS_PATH} com.lombardrisk.colline.supportutil.Main supportutil.properties ${COLLINE_HOME}/bin/${DATABASE_PROPERTIES}