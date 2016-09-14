#!/bin/ksh
######### api/java_lang/instrument/Instrumentation/index.html#Instrumentation

cd `dirname $0`
TCK_HOME=${TCK_HOME:-`cd ../../../../..; pwd`}


export CLASSPATH="$TCK_HOME/classes"

"$JAVA_HOME/bin/java" -javaagent:$TCK_HOME/lib/agent1.jar=file:///$TCK_HOME/tests/api/java_lang/instrument/Instrumentation/index.html#Instrumentation,opt1 -javaagent:$TCK_HOME/lib/agent2.jar=file:///$TCK_HOME/tests/api/java_lang/instrument/Instrumentation/index.html#Instrumentation,opt2 javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.InstrumentationTests -TestURL file:///$TCK_HOME/tests/api/java_lang/instrument/Instrumentation/index.html#Instrumentation -TestCaseID ALL -TestURL file:///$TCK_HOME/tests/api/java_lang/instrument/Instrumentation/index.html#Instrumentation 
if [ $? != 95 ] ; then echo "-- failed "; exit 1 ; fi

echo "-- passed"; exit 0
