/*
 * Copyright (c) 2003, 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * @(#)InstrumentationMultiTest.java 08/01/22Cheryl Stocks, Oleg Oleinik, Dmitry Bessonov, Yuri Gaevsky
 */


/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 *
 * Instrumentation tests. JPLIS agent.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.lang.instrument.IllegalClassFormatException;
import java.util.Hashtable;
import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javasoft.sqe.javatest.Status;
import javasoft.sqe.javatest.lib.MultiTest;


public class InstrumentationMultiTest extends MultiTest {

    private static volatile String loadLibraryStatus = "";

    static {
        loadLib("jckjni");
    }

    private static boolean loadLib(String libName) {
        try {
            System.loadLibrary(libName);
            loadLibraryStatus = null;
            return true;
        } catch (SecurityException e) {
            loadLibraryStatus  += "loadLibrary(\"" + libName + "\") throws: "
                    + e + "\n";
        } catch (UnsatisfiedLinkError e) {
            loadLibraryStatus  +=  "loadLibrary(\"" + libName + "\") throws: "
                    + e + "\n";
        }
        return false;
    }

    public static String getLoadLibraryStatus() {
        return loadLibraryStatus;
    }

    public static String packageName = "javasoft.sqe.tests.api.java.lang.instrument.Instrumentation";

    public static String redefinePackageName = packageName + ".redefineKlass";
    public static String originalPackageName = packageName + ".redefineClass";

    public static String retransformedPackageName = packageName + ".retransformKlass";
    public static String toretransformPackageName = packageName + ".retransformClass";

    Instrumentation instrumentation = null;
    String[] opts = null;

    /* location of class files to load by LocalDirClassLoader */
    String classesDirURL = "";

    String agentOptString = "";

    /**
     * Location of jar files for addition to bootstrap/system
     * class loader search path
     */
    private String jarsDirURL = "";

    /**
     * Returns location of jar files for addition to bootstrap/system
     * class loader search path.
     */
    protected String getJarsDirURL() {
        return jarsDirURL;
    }

    /**
     * Sets locations of class/jar files used in the tests for
     * <code>java.lang.instrument.Instrumentation</code>.
     */
    public void setResourceDirURL(String testSuiteRootURL) {
        final String relativeDir = "api/java_lang/instrument/Instrumentation/";

        // retrieve testDirURL for class loading
        String sep = testSuiteRootURL.endsWith("/") ? "" : "/";

        // set location of class files to load by LocalDirClassLoader
        classesDirURL = testSuiteRootURL + sep + relativeDir + "stubclasses/";
        jarsDirURL = "jar:" + testSuiteRootURL + sep + relativeDir;
    }

    /**
     * Stores the result of testing (Status.PASSED=0 or Status.FAILED=2) in a static member
     * of a class for later retrieving in the stub-testclass.
     * @param status int value (Status.PASSED, Status.FAILED) reflecting passed or failed result.
     * @param testCaseID a String uniquely identifying a test case.
     * @param msg a String which says the reason of failure or passing.
     * @returns a Status object which reflects testcase passed/failed status in JavaTest manner.
     */
    void setStatus(int result, String testCaseID, String msg) {
        Status s = new Status(result, msg);
        JplisAgentResults.setResult(testCaseID, s);
    }

    void storeObject(Object obj, String id) {
         JplisAgentResults.storeObject(obj, id);
    }

    Object getObject(String id) {
         return JplisAgentResults.getObject(id);
    }

    /**
     * Creates an array of ClassDefinition objects for redefinition
     * by loading original class through CLass.forName() and loading
     * redefining class as resource.
     * <p>Loaded resource is transformed by fixing of class's class name
     * in class file's byte array to the original class's class name, so that
     * the byte array represents original class.
     *
     * @param   classNames
     *          an array of ClassNamePairs
     * @param   adjustPkgName
     *          a flag when true then class name in the loaded byte array
     *          is changed to correspond to the original class's name.
     *
     * @return  an array of ClassDefinition objects.
     */
    protected ClassDefinition[] createClassDefinitions(ClassNamePair[] classNames, boolean adjustPkgName)
            throws Exception {
        ClassDefinition[] classDefArray = new ClassDefinition[classNames.length];

        for (int i = 0; i < classNames.length; ++i) {
            String originalClassName = classNames[i].getFirstClassName();
            String redefineClassName = classNames[i].getSecondClassName();
            String redefineClassFileName = redefineClassName.replace('.','/') + ".class";

            byte[] ba = ByteCodeReader.loadClassBytes(redefineClassFileName);
            if (adjustPkgName) {
                ba = ByteCodeReader.transformBytes(ba, "redefineKlass", "redefineClass");
            }

            Class c = Class.forName(originalClassName);
            classDefArray[i] = new ClassDefinition(c, ba);
        }

        return classDefArray;
    }

}


class TestClassFileTransformer implements ClassFileTransformer {

    String className = null;
    String[] classNames = null;
    TransformationResult transfomationResult = null;

    public TestClassFileTransformer() {
    }

    public TestClassFileTransformer(String className) {
        this.className = transformClassName(className);
    }

    public TestClassFileTransformer(TransformationResult tr, String className) {
        this(className);
        transfomationResult = tr;
    }

    public TestClassFileTransformer(TransformationResult tr, String[] classNames) {
        this.classNames = new String[classNames.length];
        for (int i = 0; i < classNames.length; ++i) {
            this.classNames[i] = transformClassName(classNames[i]);
        }
        transfomationResult = tr;
    }


    String transformClassName(String className) {
        return className.replace('.', '/');
    }

    public byte[] transform(ClassLoader loader, String className,
                        Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                        byte[] classfileBuffer) throws IllegalClassFormatException {
        return null;
    }


    public byte[] transformBytes(byte[] classfileBuffer, String from, String to) {

        byte[] ba = new byte[classfileBuffer.length];
        System.arraycopy(classfileBuffer, 0, ba, 0, ba.length);
        try {
            ByteCodeReader.transformBytes(ba, from, to);
        } catch (Exception e) {
            if (transfomationResult != null) {
                transfomationResult.setResultString(
                        " classfileBuffer does not contain expected bytes: \"" + from + "\"");
            }
            return null;
        }
        if (transfomationResult != null) {
            transfomationResult.setResultString("OKAY");
        }
        return ba;
    }

    public boolean isExpectedClass(String className) {
        if (this.className != null && this.className.equals(className)) {
            return true;
        }
        if (this.classNames != null) {
            for (int i = 0; i < this.classNames.length; ++i) {
                if (this.classNames[i].equals(className)) {
                    return true;
                }
            }
        }
        return false;
    }
}



final class LocalDirClassLoader extends ClassLoader {

    private String baseDirURL = "";

    LocalDirClassLoader(String baseDirURL, ClassLoader parent) {
        super(parent);
        this.baseDirURL = baseDirURL;
    }

    LocalDirClassLoader(ClassLoader parent) {
        this("", parent);
    }

    public byte[] loadClassData(String path) throws ClassNotFoundException {
        InputStream s = null;

        try {
            String classURL = baseDirURL + path;
            URL u = new URL(classURL);
            s = u.openStream();
        } catch (Exception e) {
            throw new ClassNotFoundException(path, e);
        }

        byte[] buffer = new byte[0];
        byte[] chunk = new byte[4096];
        int count;

        try {
            while ((count = s.read(chunk)) >= 0) {
               byte[] t = new byte[buffer.length + count];
               System.arraycopy(buffer, 0, t, 0, buffer.length);
               System.arraycopy(chunk, 0, t, buffer.length, count);
               buffer = t;
            }
        } catch (IOException e) {
            throw new ClassNotFoundException(e + ": " + path);
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException ioe) {
                    throw new ClassNotFoundException(ioe + ": " + path);
                }
            }
        }
        return buffer;
    }

    public Class findClass(String className) throws ClassNotFoundException {
        Class c = null;
        try {
            String path = className.replace('.', '/') + ".class";
            byte data[] = loadClassData(path);
            c = defineClass(className, data, 0, data.length);
        } catch (ClassFormatError ex) {
            throw new ClassNotFoundException(className);
        }
        return c;
    }

    public Class loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, true);
    }

}


final class ByteCodeReader {

    public static byte[] loadClassBytes(String resourceName) throws Exception {
        byte[] redefClassBytes;
        InputStream in = null;
        ClassLoader cl = ByteCodeReader.class.getClassLoader();
        if (cl == null) {
            cl = new MyClassLoader();
        } else {
            cl = new MyClassLoader(cl);
        }

        try {
            in = cl.getResourceAsStream(resourceName);
            if (in == null) {
                throw new Exception("Class file \"" + resourceName + "\" not found");
            }
            redefClassBytes = readByteCode(in);
        } finally {
            try {
                if (in != null) {
                   in.close();
                }
            } catch (java.io.IOException ioe) {
            }
        }
        return redefClassBytes;
    }

     public static byte[] readByteCode(InputStream in) throws IOException {
         int len = 0;
         int size = 1000;
         byte data[] = new byte[size];
         for (int i = 0;;) {
             i = in.read(data, len, size - len);
             if (i == -1) {
                 break;
             }
             len += i;
             if (len == size) {
                 byte buf[] = new byte[size * 2];
                 System.arraycopy(data, 0, buf, 0, size);
                 size *= 2;
                 data = buf;
             }
         }
         byte[] res = new byte[len];
         System.arraycopy(data, 0, res, 0, len);
         return res;
     }

     public static byte[] transformBytes(byte[] b, String from, String to) throws Exception {

        CharSequence fromCS = from.subSequence(0, from.length());
        CharSequence toCS = to.subSequence(0, to.length());
        boolean found = false;

        for (int i = 0; i <= b.length - fromCS.length(); i++) {
            boolean failed = false;
            for (int j = 0; j < fromCS.length(); ++j) {
                if ((int)b[i + j] != (int)fromCS.charAt(j)) {
                    failed = true;
                    break;
                }
            }
            if (failed) {
                continue;
            }
            for (int j = 0; j < toCS.length(); ++j) {
                b[i + j] = (byte)toCS.charAt(j);
            }
            found = true;
            i += fromCS.length();
        }
        if (!found) {
            throw new Exception("Byte sequence corresponding to \"" + to + "\" string is " +
                                " not found in the transformed byte array");
        }
        return b;
    }
}


final class ClassNamePair {

    private String s1 = "";
    private String s2 = "";

    ClassNamePair(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    String getFirstClassName() {
        return s1;
    }

    String getSecondClassName() {
        return s2;
    }
}


final class TransformationResult {

    private volatile String resMsg = "";

    synchronized void setResultString(String res) {
        resMsg = res;
    }

    synchronized String getResultString() {
        return resMsg;
    }

    synchronized void cleanup() {
        resMsg = "";
    }

}

final class JplisAgentResults {

    private static Hashtable agentsResults = new Hashtable();
    private static Hashtable objects = new Hashtable();

    static synchronized void clearResults() {
         agentsResults = new Hashtable();
         objects = new Hashtable();
    }

    static synchronized void storeObject(Object obj, String id) {
        objects.put(id, obj);
    }

    static synchronized Object getObject(String id) {
        return objects.get(id);
    }

    static synchronized void setResult(String testCaseID, Status s) {
        agentsResults.put(testCaseID, s);
    }

    static synchronized Status getResult(String testCaseID) {
        return (Status)agentsResults.get(testCaseID);
    }
}

final class AgentOptionsParser {
    /**
     * Parses agent's option string using "," as the separator and transforms it
     * to array of string options.
     *
     * @param args
     *          a String which represents agent's options which are passed to
     *          the premain method.
     * @returns an array of options which are ","-separated tokens in passed
     *          options string.
     */
     public static String[] parseAgentOpts(String opts) {
        final String OPTS_SEPARATOR = ",";
        Vector optsVector = new Vector();
        int lastTokenSepIndex = 0;
        int nextTokenSepIndex = opts.indexOf(OPTS_SEPARATOR, lastTokenSepIndex);

        while (nextTokenSepIndex > 0) {
            optsVector.add(opts.substring(lastTokenSepIndex, nextTokenSepIndex));
            lastTokenSepIndex = nextTokenSepIndex + 1;
            nextTokenSepIndex = opts.indexOf(OPTS_SEPARATOR, lastTokenSepIndex);
        }

        optsVector.add(opts.substring(lastTokenSepIndex, opts.length()));
        return (String[]) optsVector.toArray(new String[0]);
    }
}

final class JplisAgentInstrumentation {
    private static Instrumentation inst1 = null;
    private static Instrumentation inst2 = null;

    public static synchronized Instrumentation getAgent1Instrumentation() {
         return inst1;
    }

    public static synchronized Instrumentation getAgent2Instrumentation() {
         return inst2;
    }

    public static synchronized void setAgent1Instrumentation(Instrumentation callback) {
         inst1 = callback;
    }

    public static synchronized void setAgent2Instrumentation(Instrumentation callback) {
         inst2 = callback;
    }

}
