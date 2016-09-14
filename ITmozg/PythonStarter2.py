print 42 + 8
print type(154535151554151515)
print type(-154535151554151515)
print 0b1101
print 0b110
print 0o12312345
print 0x123456789ABcd
stroka = '15'
print type(stroka)
print int(stroka)
bool1 = True
bool2 = False
print(bool1)
print(bool2)
print(3.2e-3)
print(5.2E28)



./jdk-9/bin/java -addmods jdk.xml.ws,jdk.xml.bind,java.se.ee -XaddExports:jdk.xml.bind/com.sun.tools.internal.xjc=ALL-UNNAMED -XaddExports:jdk.xml.bind/com.sun.tools.internal.jxc=ALL-UNNAMED -XaddExports:jdk.xml.ws/com.sun.tools.internal.ws=ALL-UNNAMED -Djck.testOrderReversed=true  -Xmx1000m -jar ./JCK-runtime-9/lib/jtjck.jar  -jtoptions:"-set jck.env.runtime.agent.passiveServiceCommand @{java.home}/bin/java\ -addmods\ jdk.xml.ws,jdk.xml.bind,java.se.ee\ -cp\ /net/zenit/export/users/izlatkin/hudson/headless_device/lib/javatest.jar\ -Djava.security.policy\=/net/zenit/export/users/izlatkin/hudson/headless_device/lib/jck.policy\ -Djava.rmi.activation.port\=@{rmi.port}\ -Djavatest.security.allowPropertiesAccess\=true\ com.sun.javatest.agent.AgentMain\ -passive\ -passivePort\ @{port}\ -map\ /net/zenit/export/users/izlatkin/hudson/map.txt" -v:non-pass -nosound -mode:single -networkModeHost:zenit.ru.oracle.com -jdk:/set/java/re/jdk/9/promoted/latest/binaries/solaris-x64 -networkModeSuite:/net/zenit/export/users/izlatkin/hudson/headless_device -removeRemoteResources:'files and parents' -vmoptions/a:"-client -Dmultitest.testcaseOrder=reverseSorted" -w:/export/users/hudson/workspace/dev_nightly_runtime_linux_headless_izlatkin/workdir  -exclude:./JCK-extra-9/jtx/jck9master.jtx -jtoptions/a:"-kfl ../JCK-extra-9/kfl/jck9.kfl" -jtoptions/a:"-writeReport ./workdir/jtReport"  -headless  api/xsl/conf/lre/lre04.html#lre04


bash-4.1$ ./jdk-9/bin/java -addmods jdk.xml.ws,jdk.xml.bind,java.se.ee -XaddExports:jdk.xml.bind/com.sun.tools.internal.xjc=ALL-UNNAMED -XaddExports:jdk.xml.bind/com.sun.tools.internal.jxc=ALL-UNNAMED -XaddExports:jdk.xml.ws/com.sun.tools.internal.ws=ALL-UNNAMED -Djck.testOrderReversed=true  -Xmx1000m -jar ./JCK-runtime-9/lib/jtjck.jar  -v:non-pass -nosound -mode:single -networkModeHost:zenit.ru.oracle.com -jdk:/set/java/re/jdk/9/promoted/latest/binaries/solaris-x64 -networkModeSuite:/net/zenit/export/users/izlatkin/hudson/headless_device -removeRemoteResources:'files and parents' -vmoptions/a:"-client -Dmultitest.testcaseOrder=reverseSorted" -exclude:./JCK-extra-9/jtx/jck9master.jtx -jtoptions/a:"-kfl ../JCK-extra-9/kfl/jck9.kfl" -jtoptions/a:"-writeReport ./workdir/jtReport"  -headless  api/xsl/conf/lre/lre04.html#lre04
Aug 12, 2016, 7:39:41 PM Harness starting test run with configuration "jck_runtime_solaris"...
ERROR: api/xsl/conf/lre/lre04.html#lre04
Aug 12, 2016, 7:40:45 PM Finished executing all tests, wait for cleanup...
    Aug 12, 2016, 7:40:45 PM Harness done with cleanup from test run.
Total time = 63s
Setup time = 0s
Cleanup time = 0s
Test results: error: 1
Results written to /export/users/hudson/workspace/dev_nightly_runtime_linux_headless_izlatkin/JCK-runtime-9_b43-work.
Error: Some tests did not pass
Report written to /export/users/hudson/workspace/dev_nightly_runtime_linux_headless_izlatkin/workdir/jtReport
bash-4.1$ cat /net/zenit/export/users/izlatkin/hudson/headless_device/dynmap.txt
/export/users/hudson/workspace/dev_nightly_runtime_linux_headless_izlatkin/JCK-runtime-9 /net/zenit/export/users/izlatkin/hudson/headless_device
/export/users/hudson/workspace/dev_nightly_runtime_linux_headless_izlatkin/JCK-runtime-9_b43-work /net/zenit/export/users/izlatkin/hudson/headless_device
bash-4.1$ ./jdk-9/bin/java -addmods jdk.xml.ws,jdk.xml.bind,java.se.ee -XaddExports:jdk.xml.bind/com.sun.tools.internal.xjc=ALL-UNNAMED -XaddExports:jdk.xml.bind/com.sun.tools.internal.jxc=ALL-UNNAMED -XaddExports:jdk.xml.ws/com.sun.tools.internal.ws=ALL-UNNAMED -Djck.testOrderReversed=true  -Xmx1000m -jar ./JCK-runtime-9/lib/jtjck.jar -jtoptions:"-set jck.env.runtime.agent.passiveServiceCommand @{java.home}/bin/java\ -addmods\ jdk.xml.ws,jdk.xml.bind,java.se.ee\ -cp\ /net/zenit/export/users/izlatkin/hudson/headless_device/lib/javatest.jar\ -Djava.security.policy\=/net/zenit/export/users/izlatkin/hudson/headless_device/lib/jck.policy\ -Djava.rmi.activation.port\=@{rmi.port}\ -Djavatest.security.allowPropertiesAccess\=true\ com.sun.javatest.agent.AgentMain\ -passive\ -passivePort\ @{port}\ -map\ /net/zenit/export/users/izlatkin/hudson/headless_device/dynmap.txt" -v:non-pass -nosound -mode:single -networkModeHost:zenit.ru.oracle.com -jdk:/set/java/re/jdk/9/promoted/latest/binaries/solaris-x64 -networkModeSuite:/net/zenit/export/users/izlatkin/hudson/headless_device -removeRemoteResources:'files and parents' -vmoptions/a:"-client -Dmultitest.testcaseOrder=reverseSorted" -exclude:./JCK-extra-9/jtx/jck9master.jtx -jtoptions/a:"-kfl ../JCK-extra-9/kfl/jck9.kfl" -jtoptions/a:"-writeReport ./workdir/jtReport"  -headless  api/xsl/conf/lre/lre04.html#lre04
Aug 12, 2016, 7:42:40 PM Harness starting test run with configuration "jck_runtime_solaris"...
Aug 12, 2016, 7:42:45 PM Finished executing all tests, wait for cleanup...
    Aug 12, 2016, 7:42:45 PM Harness done with cleanup from test run.
Total time = 5s
Setup time = 0s
Cleanup time = 0s
Test results: passed: 1
Results written to /export/users/hudson/workspace/dev_nightly_runtime_linux_headless_izlatkin/JCK-runtime-9_b43-work.
Report written to /export/users/hudson/workspace/dev_nightly_runtime_linux_headless_izlatkin/workdir/jtReport
