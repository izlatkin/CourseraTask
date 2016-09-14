#!/bin/ksh
######### api/java_lang/instrument/Instrumentation/attach.html#InstrumentationAttach

cd `dirname $0`
TCK_HOME=${TCK_HOME:-`cd ../../../../..; pwd`}

PATHSEP=":" ; if [ `uname -s |grep ^Windows` ] ;  then PATHSEP=";" ; fi

export CLASSPATH="$TCK_HOME/classes${PATHSEP}${JAVA_HOME}/lib/tools.jar"

"$JAVA_HOME/bin/java" javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.InstrumentationAttachTests -attachClassName javasoft.sqe.jck.lib.attach.JPLISAttachConnector -agent1 $TCK_HOME/lib/agent1.jar -agent1opts file:///$TCK_HOME/tests/api/java_lang/instrument/Instrumentation/attach.html#InstrumentationAttach,opt1 -agent2 $TCK_HOME/lib/agent2.jar -agent2opts file:///$TCK_HOME/tests/api/java_lang/instrument/Instrumentation/attach.html#InstrumentationAttach,opt2 -TestCaseID ALL -TestURL file:///$TCK_HOME/tests/api/java_lang/instrument/Instrumentation/attach.html#InstrumentationAttach 
if [ $? != 95 ] ; then echo "-- failed "; exit 1 ; fi

echo "-- passed"; exit 0
