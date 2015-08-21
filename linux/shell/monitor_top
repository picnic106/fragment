#!/bin/bash

proname="apache-tomcat"
datetime=`date +%Y-%m-%d`

rootPath="/home/songyingjie/shscript"
dateFile="${rootPath}/datetime-mem.txt"
memFile="${rootPath}/mem-$datetime.log"
newFile="${rootPath}/mem-now.txt"


if [ ! -f "$dateFile" ]; then
 touch "$dateFile"
fi

if [ ! -f "$memFile" ]; then
 touch "$memFile"
fi

if [ ! -f "$newFile" ]; then
 touch "$newFile"
fi



while true ; do  

tomcatpid=`ps -ef | grep -i ${proname} | grep -v "grep" |awk '{print $2}'`
date +%Y-%m-%d\ %T >  $dateFile
top -b -n 1 | grep  14151 |awk '{print $1,$5,$6,$7,$9,$10,$11,$12}' > $newFile
paste  $dateFile   $newFile  >> $memFile 

sleep 1m

done
