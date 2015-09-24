#!/bin/bash

proname="apache-tomcat"

rootPath="/home/songyingjie/shscript/mem"
dateFile="${rootPath}/datetime-jstack.txt"
newFile_jstack="${rootPath}/jstack-now.txt"




datetime=`date +%Y-%m-%d`

date +%Y-%m-%d\ %T >  $dateFile

jstackFile="${rootPath}/jstack-$datetime.log"


tomcatpid=`ps -ef | grep -i ${proname} | grep -v "grep" |awk '{print $2}'`

if [ ! $tomcatpid ]; then

echo `date +%Y-%m-%d\ %T`---'error not tomatpid' >> ${rootPath}/error_jstack.log


else 

deadlockname=`jstack -l $tomcatpid |grep deadlock`
if [ ! $deadlockname ]; then
    jstack -l $tomcatpid >$newFile_jstack
	paste -d"\n"  $dateFile   $newFile_jstack  >> $jstackFile

fi

fi
