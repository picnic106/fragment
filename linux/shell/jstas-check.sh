#!/bin/bash

proname="apache-tomcat"
#datetime=`date +%Y-%m-%d`

rootPath="/home/songyingjie/shscript"
dateFile="${rootPath}/datetime-jstas.txt"
#memFile="${rootPath}/jstas-$datetime.log"
newFile="${rootPath}/mem-now2.txt"


if [ ! -f "$dateFile" ]; then
 touch "$dateFile"
fi

#if [ ! -f "$memFile" ]; then
# touch "$memFile"
#fi

if [ ! -f "$newFile" ]; then
 touch "$newFile"
fi



while true ; do  

datetime=`date +%Y-%m-%d`

memFile="${rootPath}/jstas-$datetime.log"

if [ ! -f "$memFile" ]; then
 touch "$memFile"
fi

tomcatpid=`ps -ef | grep -i $proname | grep -v "grep" |awk '{print $2}'`
date +%Y-%m-%d\ %T >  $dateFile
jstat -gcutil $tomcatpid | awk 'NR>1' > $newFile
paste  $dateFile   $newFile  >> $memFile 

sleep 1s

done
