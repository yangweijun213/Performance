The tools is used to exact support data

To get this utility to work

Prerequisite
1)java command already add to PATH environment parameter, and execute 'java -version' can show the java version.

Steps
1)Extract supportutilty-xxxx.zip to ${COLLINE_HOME}/bin, you NEED to get the file structure like ${COLLINE_HOME}/bin/supportutility-xxx/supportutil.sh,otherwise won't work.
2)Configure support.properties if you like.
3)Run suportutil.sh or suportutil.bat,
4)The support data will generated at the current folder


Notes
1)run dos2unix and grant execute permission to supportutil.sh if necessary.
2)The support data have the same file structure like Colline deployment, and additional support data(like application server and database server information) can be found in ${SUPPORT_DATA}/bin/supportutil-xxx/
3)You only need to follow the step 2-4 for future usage.
