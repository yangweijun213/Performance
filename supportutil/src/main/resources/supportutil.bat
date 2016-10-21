@setlocal
@echo off

rem the path of colline home directory
set COLLINE_HOME=..\..

pushd %COLLINE_HOME%\bin
set CLASS_PATH=

if exist databasetype.bat (
	call databasetype.bat
)

rem colline.properties or oracle.properties/sqlserver.properties
if exist colline.properties (
	set DATABASE_PROPERTIES=colline.properties
) else (
	set DATABASE_PROPERTIES=%DATABASE_TYPE%.properties
)

rem get LRSClient.jar, LRS3rdParty.jar, database driver libs like ojdbc.jar jtds.jar
pushd %COLLINE_HOME%\bin
for %%F in (*.jar) do call set "CLASS_PATH=%%CLASS_PATH%%;%COLLINE_HOME%\bin\%%F"
popd

for %%F in (*.jar) do call set "CLASS_PATH=%%CLASS_PATH%%;%%F"

java -cp %CLASS_PATH% com.lombardrisk.colline.supportutil.Main supportutil.properties %COLLINE_HOME%\bin\%DATABASE_PROPERTIES%