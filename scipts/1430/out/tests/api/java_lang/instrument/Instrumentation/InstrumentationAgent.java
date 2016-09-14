/*
 * Copyright (c) 2003, 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * @(#)InstrumentationAgent.java 08/01/31Cheryl Stocks, Oleg Oleinik, Yuri Gaevsky
 */

/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 *
 * Instrumentation tests. JPLIS agent.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.Vector;
import javasoft.sqe.javatest.Status;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass.ClassE1;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass.ClassF1;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass.ClassG1;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass.ClassH1;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass.ClassI1;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass.ClassS1;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass.ClassS2;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass.ClassS3;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass.ClassS4;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass.ClassS10;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass.ClassS11;

import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A1;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A2;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A3;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A4;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A5;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A6;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A7;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A8;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A9;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A10;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A11;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A12;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A13;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A14;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A15;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.B1;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.C1;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.D1;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A16;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass.A17;

import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.snmp.NMPA1;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.snmp.NMPA2;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.snmp.NMPA3;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.snmp.NMPA4;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.snmp.NMPA5;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.snmp.NMPA6;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.snmp.NMPA7;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.util.jar.JarFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;


public class InstrumentationAgent extends InstrumentationMultiTest {

    public static String testClassName = packageName + "."
            + "InstrumentationAgent";

    public static void premain(String agentOptString, Instrumentation inst) {
        // clean-up testcases results storage (static)
        JplisAgentResults.clearResults();

        InstrumentationMultiTest test = new InstrumentationAgent();
        test.instrumentation = inst;
        test.agentOptString = agentOptString;
        test.opts = AgentOptionsParser.parseAgentOpts(agentOptString);

        JplisAgentInstrumentation.setAgent1Instrumentation(inst);

        // testSuiteRootURL should always be the first option set by JCKScript
        // or manually in command-line (singleJVM)
        test.setResourceDirURL(test.opts[0]);

        test.run(new String[0], System.err, System.out);
    }

    public static void agentmain(String agentOptString, Instrumentation inst) {
        // clean-up testcases results storage (static)
        JplisAgentResults.clearResults();

        InstrumentationMultiTest test = new InstrumentationAgent();
        test.instrumentation = inst;
        test.agentOptString = agentOptString;
        test.opts = AgentOptionsParser.parseAgentOpts(agentOptString);

        JplisAgentInstrumentation.setAgent1Instrumentation(inst);

        // testSuiteRootURL should always be the first option set by JCKScript
        // or manually in command-line (singleJVM)
        test.setResourceDirURL(test.opts[0]);

        test.run(new String[0], System.err, System.out);
    }

    /**
     * Assertion testing
     * for public static void premain(String agentArgs, Instrumentation inst),
     * Each agent has its own classname and options. Each agent receives its own options as a single string.
     * <br><b>Expected results</b>: options are as expected
     */
    public Status Instrumentation001() {
        String testCaseID = "Instrumentation001";

        if (opts.length != 2 || "".equals(opts[0].trim()) || !"opt1".equals(opts[1])) {
            setStatus(Status.FAILED, testCaseID, "wrong options string was passed " +
                                                 "to the agent:\"" + agentOptString +
                                                 "\" instead of: <testDirURL>,opt1");
            return Status.passed("Testing result passed to test");
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, correct options are passed to the agent");
        return Status.passed("Testing result passed to test");
    }


    /**
     * Equivalence class partitioning
     * with input values orientation
     * for public long getObjectSize(Object objectToSize),
     * <br><b>objectToSize</b>:  non-null objects
     * <br><b>Expected results</b>: positive values
     */
    public Status Instrumentation002() {
        String testCaseID = "Instrumentation002";
        boolean failed = false;
        Object[] objs = {new Object(), new int[0], new byte[10], new String[0], new Object[10]};
        for (int i = 0; i < objs.length; ++i) {
            long size = instrumentation.getObjectSize(objs[i]);
            if (size <= 0l) {
                setStatus(Status.FAILED, testCaseID, "getObjectSize(obj) for " + i +
                                                     "-th Object element is negative");
                failed = true;
                break;
            }
        }
        if (!failed) {
            setStatus(Status.PASSED, testCaseID, "OKAY");
        }
        return Status.passed("Testing result passed to test");
    }


    /**
     * Boundary value analysis
     * with input values orientation
     * for public long getObjectSize(Object objectToSize),
     * <br><b>objectToSize</b>:  null
     * <br><b>Expected results</b>: NullPointerException is thrown
     */
    public Status Instrumentation003() {
        String testCaseID = "Instrumentation003";
        try {
            instrumentation.getObjectSize(null);
            setStatus(Status.FAILED, testCaseID, "getObjectSize(null) does not throw NullPointerException");
        } catch (NullPointerException npe) {
            setStatus(Status.PASSED, testCaseID, "OKAY, getObjectSize(null) throws NullPointerException");
        }
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing
     * for public Class[] getAllLoadedClasses(),
     * returns an array containing all the classes loaded by the JVM, zero-length if there are none.
     * <br><b>Expected results</b>: returned non-null reference is an array with positive length, contains
     * test's class.
     */
    public Status Instrumentation004() {
        String testCaseID = "Instrumentation004";
        boolean found = false;

        Class[] classes = instrumentation.getAllLoadedClasses();
        if (classes == null) {
            setStatus(Status.FAILED, testCaseID, "getAllLoadedClasses() returned null");
        } else if (classes.length == 0) {
            setStatus(Status.FAILED, testCaseID, "getAllLoadedClasses() returned zero-length array, " +
                                                 " despite of the test's class");
        } else {
            for (int i = 0; i < classes.length; ++i) {
                if (testClassName.equals(classes[i].getName())) {
                    found = true;
                }
            }
            if (found) {
                setStatus(Status.PASSED, testCaseID, "OKAY, getAllLoadedClasses() returned non-empty, " +
                                                     "non-null array, with test's class in the array");
            } else {
                setStatus(Status.FAILED, testCaseID, "test's class could not be found in the array " +
                                                     "returned by getAllLoadedClasses()");
            }
        }
        return Status.passed("Testing result passed to test");
    }


    /**
     * Assertion testing
     * for public Class[] getInitiatedClasses(ClassLoader loader),
     * returns zero-length if there are no classes for which loader is an initiating loader.
     * <br><b>Expected results</b>: non-null array is returned for some newly created classloader.
     */
    public Status Instrumentation005() {
        String testCaseID = "Instrumentation005";

        ClassLoader cl = new MyClassLoader();
        Class[] classes = instrumentation.getInitiatedClasses(cl);

        if (classes == null) {
            setStatus(Status.FAILED, testCaseID, "getInitiatedClasses(MyClassLoader) returned null");
        } else {
            setStatus(Status.PASSED, testCaseID, "OKAY, getInitiatedClasses(MyClassLoader) " +
                                                 "returned zero-length array");
        }
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing
     * for public Class[] getInitiatedClasses(ClassLoader loader),
     * returns an array of all classes for which loader is an initiating loader.
     * <br><b>Precondition</b>: a class is loaded by the current class loader.
     * <br><b>Expected results</b>: returned non-null reference is an array with length greater than zero.
     */
    public Status Instrumentation006() {
        String testCaseID = "Instrumentation006";
        boolean found = false;

        Class[] classes = instrumentation.getInitiatedClasses(this.getClass().getClassLoader());

        if (classes == null) {
            setStatus(Status.FAILED, testCaseID, "getInitiatedClasses(current ClassLoader) returned null");
        } else if (classes.length == 0) {
            String msg = "getInitiatedClasses(current ClassLoader) returned array of 0 length";
            setStatus(Status.FAILED, testCaseID, msg);
        } else {
            for (int i = 0; i < classes.length; ++i) {
                if (testClassName.equals(classes[i].getName())) {
                    found = true;
                }
            }
            if (found) {
                setStatus(Status.PASSED, testCaseID, "OKAY, getInitiatedClasses(current ClassLoader)" +
                                                     " returned array containing test's class");
            } else {
                setStatus(Status.FAILED, testCaseID, "test's class could not be found in the array " +
                                                     "returned by getInitiatedClasses(current ClassLoader)");
            }
        }
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing
     * for public Class[] getInitiatedClasses(ClassLoader loader),
     * returns an array of all classes for which loader is an initiating loader.
     * If the supplied loader is null, classes initiated by the bootstrap class
     * loader are returned.
     * <br><b>Expected results</b>: no exceptions, returned reference is non-null
     */
    public Status Instrumentation007() {
        String testCaseID = "Instrumentation007";
        Class[] classes = instrumentation.getInitiatedClasses(null);
        if (classes == null) {
            setStatus(Status.FAILED, testCaseID, "getInitiatedClasses(null) returned null");
        } else {
            setStatus(Status.PASSED, testCaseID, "OKAY, getInitiatedClasses(null) returned non-null array");
        }
        return Status.passed("Testing result passed to test");
    }


    /**
     * Assertion testing
     * for public boolean isRedefineClassesSupported(),
     * During a single instantiation of a single JVM, multiple calls to this
     * method will always return the same answer.
     * <br><b>Expected results</b>: multiple method invocations return same result
     */
    public Status Instrumentation019() {
        String testCaseID = "Instrumentation019";

        boolean supported = instrumentation.isRedefineClassesSupported();
        for (int i = 0; i < 10; ++i) {
            if (instrumentation.isRedefineClassesSupported() != supported) {
                setStatus(Status.FAILED, testCaseID, i + "-th invocation of isRedefineClassesSupported()" +
                                                      " returned " + !supported + " instead of " + supported);
                return Status.passed("Testing result passed to test");
            }
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, multiple invocations of isRedefineClassesSupported()" +
                                             " return the same result: " + supported);
        return Status.passed("Testing result passed to test");
   }


    /**
     * Equivalence class partitioning
     * with input values orientation
     * for public void redefineClasses(ClassDefinition[] definitions),
     * <br><b>definitions</b>:  zero-length array
     * <br><b>Expected results</b>: no exceptions if redefinition is supported, otherwise, UnsupportedOperationException
     */
    public Status Instrumentation020() {
        String testCaseID = "Instrumentation020";
        try {
            if (instrumentation.isRedefineClassesSupported()) {
                instrumentation.redefineClasses(new ClassDefinition[0]);
                setStatus(Status.PASSED, testCaseID, "OKAY, redefineClasses(new ClassDefinition[0]) throws no exceptions");
            } else {
                try {
                    instrumentation.redefineClasses(new ClassDefinition[0]);
                    setStatus(Status.FAILED, testCaseID, "redefinition is not supported, " +
                        "redefineClasses(new ClassDefinition[0]) does not throw UnsupportedOperationException");
                } catch (UnsupportedOperationException ue) {
                    setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is not supported, " +
                        "redefineClasses(new ClassDefinition[0]) throws UnsupportedOperationException");
                }
            }
        } catch (ClassNotFoundException cfe) {
            setStatus(Status.FAILED, testCaseID, "redefineClasses(new ClassDefinition[0]) throws ClassNotFoundException");
        } catch (UnmodifiableClassException uce) {
            setStatus(Status.FAILED, testCaseID, "redefineClasses(new ClassDefinition[0]) throws UnmodifiableClassException");
        }
        return Status.passed("Testing result passed to test");
    }

    /**
     * Boundary value analysis
     * with input values orientation
     * for public void redefineClasses(ClassDefinition[] definitions),
     * <br><b>definitions</b>: null
     * <br><b>Expected results</b>: NullPointerException if redefinition is supported,
     * otherwise, UnsupportedOperationException
     */
    public Status Instrumentation021() {
        String testCaseID = "Instrumentation021";
        try {
            if (instrumentation.isRedefineClassesSupported()) {
                try {
                    instrumentation.redefineClasses((ClassDefinition[])null);
                    setStatus(Status.FAILED, testCaseID, "redefinition is supported, redefineClasses(null) " +
                                " does not throw NullPointerException");
                } catch (NullPointerException npe) {
                    setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is supported, redefineClasses(null) " +
                                " throws NullPointerException");
                }
            } else {
                try {
                    instrumentation.redefineClasses((ClassDefinition[])null);
                    setStatus(Status.FAILED, testCaseID, "redefinition is not supported, " +
                        "redefineClasses(null) does not throw UnsupportedOperationException");
                } catch (UnsupportedOperationException ue) {
                    setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is not supported, " +
                        "redefineClasses(null) throws UnsupportedOperationException");
                }
            }
        } catch (ClassNotFoundException cfe) {
            setStatus(Status.FAILED, testCaseID, "redefineClasses(null) throws ClassNotFoundException");
        } catch (UnmodifiableClassException uce) {
            setStatus(Status.FAILED, testCaseID, "redefineClasses(null) throws UnmodifiableClassException");
        }

        return Status.passed("Testing result passed to test");
    }

    /**
     * Boundary value analysis
     * with input values orientation
     * for public void redefineClasses(ClassDefinition[] definitions),
     * <br><b>definitions</b>: array with sinlge null element
     * <br><b>Expected results</b>: NullPointerException if redefinition is supported,
     * otherwise, UnsupportedOperationException
     */
    public Status Instrumentation022() {
        String testCaseID = "Instrumentation022";
        ClassDefinition[] classDefArray = {null};
        try {
            if (instrumentation.isRedefineClassesSupported()) {
                try {
                    instrumentation.redefineClasses(classDefArray);
                    setStatus(Status.FAILED, testCaseID, "redefinition is supported, redefineClasses({null}) " +
                                " does not throw NullPointerException");
                } catch (NullPointerException npe) {
                    setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is supported, redefineClasses({null}) " +
                                " throws NullPointerException");
                }
            } else {
                try {
                    instrumentation.redefineClasses(classDefArray);
                    setStatus(Status.FAILED, testCaseID, "redefinition is not supported, " +
                        "redefineClasses({null}) does not throw UnsupportedOperationException");
                } catch (UnsupportedOperationException ue) {
                    setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is not supported, " +
                        "redefineClasses({null}) throws UnsupportedOperationException");
                }
            }
        } catch (ClassNotFoundException cfe) {
            setStatus(Status.FAILED, testCaseID, "redefineClasses({null}) throws ClassNotFoundException");
        } catch (UnmodifiableClassException uce) {
            setStatus(Status.FAILED, testCaseID, "redefineClasses({null}) throws UnmodifiableClassException");
        }
        return Status.passed("Testing result passed to test");
    }


    /**
     * Boundary value analysis
     * with input values orientation
     * for public void redefineClasses(ClassDefinition[] definitions),
     * <br><b>definitions</b>: array with byte[0] class file
     * <br><b>Expected results</b>: ClassFormatError if redefinition is supported,
     * otherwise, UnsupportedOperationException
     */
    public Status Instrumentation023() {
        String testCaseID = "Instrumentation023";
        ClassDefinition cd = new ClassDefinition(new ClassB().getClass(), new byte[0]);
        ClassDefinition[] classDefArray = {cd};
        try {
            if (instrumentation.isRedefineClassesSupported()) {
                try {
                    instrumentation.redefineClasses(classDefArray);
                    setStatus(Status.FAILED, testCaseID, "redefinition is supported, redefineClasses(cd) " +
                                " does not throw ClassFormatError for empty class file in ClassDefinition");
                } catch (ClassFormatError cfe) {
                    setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is supported, redefineClasses(cd) " +
                                " with empty class file throws ClassFormatError");
                } catch (UnmodifiableClassException uce) {
                    setStatus(Status.PASSED, testCaseID, "redefinition of ClassB with empty class file in ClassDefinition throws UnmodifiableClassException");
                }
            } else {
                try {
                    instrumentation.redefineClasses(classDefArray);
                    setStatus(Status.FAILED, testCaseID, "redefinition is not supported, " +
                        "redefineClasses(cd) with empty class file in ClassDefinition does not throw UnsupportedOperationException");
                } catch (UnsupportedOperationException ue) {
                    setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is not supported, " +
                        "redefineClasses(cd) with empty class file in ClassDefinition throws UnsupportedOperationException");
                } catch (UnmodifiableClassException uce) {
                    setStatus(Status.FAILED, testCaseID, "redefinition is not supported, but redefinition of ClassB with " +
                        "empty class file in ClassDefinition throws UnmodifiableClassException");
                }
            }
        } catch (ClassNotFoundException cfe) {
            setStatus(Status.FAILED, testCaseID, "redefinition with empty class file in ClassDefinition throws ClassNotFoundException");
        }
        return Status.passed("Testing result passed to test");
    }


    /**
     * Boundary value analysis
     * with input values orientation
     * for public void redefineClasses(ClassDefinition[] definitions),
     * <br><b>definitions</b>: array with one element with class file of another class (ClassCC instead of ClassC)
     * <br><b>Expected results</b>: NoClassDefFoundError if redefinition is supported,
     * otherwise, UnsupportedOperationException
     */
    public Status Instrumentation024() {
        String testCaseID = "Instrumentation024";

        ClassDefinition[] classDefArray = null;
        try {
             ClassNamePair[] cnp = {new ClassNamePair(packageName + ".ClassCA", packageName + ".ClassCB")};
             classDefArray = createClassDefinitions(cnp, false);
        } catch (Exception e) {
            setStatus(Status.FAILED, testCaseID, "While loading class file an exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        }
        try {
            if (instrumentation.isRedefineClassesSupported()) {
                try {
                    instrumentation.redefineClasses(classDefArray);
                    setStatus(Status.FAILED, testCaseID, "redefinition is supported, redefineClasses(cd) " +
                                " does not throw NoClassDefFoundError for redefinition by a class of different name");
                } catch (NoClassDefFoundError ncdfe) {
                    setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is supported, redefineClasses(cd) " +
                                " throws NoClassDefFoundError for redefinition by a class of different name");
                } catch (UnmodifiableClassException uce) {
                    setStatus(Status.PASSED, testCaseID, "redefinition is supported, redefinition by a class of " +
                                "different name throws UnmodifiableClassException");
                }
            } else {
                try {
                    instrumentation.redefineClasses(classDefArray);
                    setStatus(Status.FAILED, testCaseID, "redefinition is not supported, " +
                        "redefinition by a class of different name does not throw UnsupportedOperationException");
                } catch (UnsupportedOperationException ue) {
                    setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is not supported, " +
                        "redefinition by a class of different name throws UnsupportedOperationException");
                } catch (UnmodifiableClassException uce) {
                    setStatus(Status.FAILED, testCaseID, "redefinition is not supported, UnmodifiableClassException" +
                         " instead of UnsupportedOperationException is thrown");
                }
            }
        } catch (ClassNotFoundException cfe) {
            setStatus(Status.FAILED, testCaseID, "redefinition by a class of different name throws ClassNotFoundException");
        }
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing
     * for public void redefineClasses(ClassDefinition[] definitions),
     * Redefine the supplied set of classes using the supplied class files. Operates on a set of classes.
     * Instances of the redefined class are not affected.
     * <br><b>Precondition</b>: instances of to be redefined class are created and verified that return
     * expected values
     * <br><b>Expected results</b>: if redefinition is supported: existing instances return same values
     * after redefinition but new instances return new values, otherwise, UnsupportedOperationException
     */
    public Status Instrumentation025() {
        String testCaseID = "Instrumentation025";

        String[] classes = {"ClassD1", "ClassD2"};
        ClassNamePair[] classNames = new ClassNamePair[classes.length];

        for (int i = 0; i < classes.length; ++i) {
            classNames[i] = new ClassNamePair(originalPackageName + "." + classes[i],
                                              redefinePackageName + "." + classes[i]);
        }

        ClassDefinition[] classDefArray = null;
        try {
            classDefArray = createClassDefinitions(classNames, true);
        } catch (Exception e) {
            setStatus(Status.FAILED, testCaseID, "While loading class file an exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        }

        ClassD[] objs = new ClassD[classNames.length];

        for (int i = 0; i < classNames.length; ++i) {
            Class c = null;
            try {
                c = Class.forName(classNames[i].getFirstClassName());
                objs[i] = (ClassD)c.newInstance();
            } catch (Exception e) {
                setStatus(Status.FAILED, testCaseID, "While loading class " + classNames[i].getFirstClassName() +
                             " an exception was thrown: " + e);
                return Status.passed("Testing result passed to test");
            }

            if (!ClassD.checkOldValues(objs[i])) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[i].getFirstClassName()
                           + " failed (not original class(?))");
                return Status.passed("Testing result passed to test");
            }
        }

        try {
            if (instrumentation.isRedefineClassesSupported()) {

                try {
                    instrumentation.redefineClasses(classDefArray);
                } catch (UnmodifiableClassException uce) {
                    setStatus(Status.PASSED, testCaseID, "redefinition is supported, " +
                                "redefinition throws UnmodifiableClassException");
                    return Status.passed("Testing result passed to test");
                }

                ClassD obj = null;
                for (int i = 0; i < classNames.length; ++i) {
                    Class c = null;
                    try {
                        c = Class.forName(classNames[i].getFirstClassName());
                        obj = (ClassD)c.newInstance();
                    } catch (Exception e) {
                        setStatus(Status.FAILED, testCaseID, "While loading class " + classNames[i].getFirstClassName() +
                             " an exception was thrown: " + e);
                        return Status.passed("Testing result passed to test");
                    }

                    if (!ClassD.checkNewValues(obj)) {
                        setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[i].getFirstClassName()
                                             + " failed (class not redefined(?))");
                        return Status.passed("Testing result passed to test");
                    }
                    if (!ClassD.checkOldValues(objs[i])) {
                        setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[i].getFirstClassName()
                                             + " failed (redefinition affected old instance(?))");
                        return Status.passed("Testing result passed to test");
                    }
                }
                setStatus(Status.PASSED, testCaseID, "OKAY, redefinition affects only new instances");
            } else {
                try {
                    instrumentation.redefineClasses(classDefArray);
                    setStatus(Status.FAILED, testCaseID, "redefinition is not supported, " +
                        "redefinition does not throw UnsupportedOperationException");
                } catch (UnsupportedOperationException ue) {
                    setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is not supported, " +
                        "redefinition throws UnsupportedOperationException");
                } catch (UnmodifiableClassException uce) {
                    setStatus(Status.FAILED, testCaseID, "redefinition is not supported, UnmodifiableClassException" +
                         " is thrown instead of UnsupportedOperationException");
                }
            }
        } catch (ClassNotFoundException cfe) {
            setStatus(Status.FAILED, testCaseID, "redefinition throws ClassNotFoundException " + cfe);
        }
        return Status.passed("Testing result passed to test");
    }


    /**
     * Assertion testing
     * for public void redefineClasses(ClassDefinition[] definitions),
     * If a redefined method has active stack frames, those active frames continue to run the
     * bytecodes of the original method. The redefined method will be used on new invokes.
     * <br><b>Precondition</b>: the instance method of to be defined class runs and falls into wait()
     * <br><b>Expected results</b>: after class redefinition the old method continues execution,
     * redefined method is executed on new invoke.
     */
    public Status Instrumentation026() {
        String testCaseID = "Instrumentation026";

        if (!instrumentation.isRedefineClassesSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is not supported");
            return Status.passed("Testing result passed to test");
        }

        ClassNamePair[] classNames = {new ClassNamePair(originalPackageName + "." + "ClassE1",
                                                        redefinePackageName + "." + "ClassE1")};

        ClassDefinition[] classDefArray = null;
        try {
            classDefArray = createClassDefinitions(classNames, true);
        } catch (Exception e) {
            setStatus(Status.FAILED, testCaseID, "While loading class " + classNames[0].getFirstClassName() +
                         " an exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        }

        ClassE obj = new ClassE1();

        int xOld = -1, xNew = 1;

        ClassE.flag = -1;

        try {
            synchronized(obj.synchThreadReady) {
                obj.start();
                while (ClassE.flag != 1) {
                    obj.synchThreadReady.wait();
                }
                instrumentation.redefineClasses(classDefArray);

                ClassE.flag = 2;
                obj.synchThreadReady.notifyAll();
                while (ClassE.flag != 3) {
                    obj.synchThreadReady.wait();
                }

                xOld = obj.getInt();

                ClassE.flag = 0;
                obj.synchThreadReady.notifyAll();
                while (ClassE.flag != 1) {
                     obj.synchThreadReady.wait();
                }
                ClassE.flag = 2;
                obj.synchThreadReady.notifyAll();

                while (ClassE.flag != 3) {
                    obj.synchThreadReady.wait();
                }
                obj.join();
                xNew = obj.getInt();
            }
        } catch (InterruptedException ie) {
            setStatus(Status.FAILED, testCaseID, "The test was interrupted");
            return Status.passed("Testing result passed to test");
        } catch (ClassNotFoundException cnfe) {
            setStatus(Status.FAILED, testCaseID, "redefinition throws ClassNotFoundException " + cnfe);
            return Status.passed("Testing result passed to test");
        } catch (UnmodifiableClassException uce) {
            setStatus(Status.PASSED, testCaseID, "redefinition is supported, UnmodifiableClassException is thrown");
            return Status.passed("Testing result passed to test");
        }
        if (!ClassE.checkOldValues(xOld)) {
            setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                  + " failed (redefinition affected being executed method(?))");
            return Status.passed("Testing result passed to test");
        }
        if (!ClassE.checkNewValues(xNew)) {
            setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                  + " failed (redefinition did not redefined method(?))");
            return Status.passed("Testing result passed to test");
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, redefinition affects only new instance methods invocations");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing
     * for public void redefineClasses(ClassDefinition[] definitions),
     * If a redefined method has active stack frames, those active frames continue to run the
     * bytecodes of the original method. The redefined method will be used on new invokes.
     * <br><b>Precondition</b>: the static method of to be defined class runs and falls into wait()
     * <br><b>Expected results</b>: after class redefinition the old method continues execution,
     * redefined method is executed on new invoke.
     */
    public Status Instrumentation027() {
        String testCaseID = "Instrumentation027";

        if (!instrumentation.isRedefineClassesSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is not supported");
            return Status.passed("Testing result passed to test");
        }

        ClassF obj = new ClassF1();

        ClassNamePair[] classNames = {new ClassNamePair(originalPackageName + "." + "ClassF1",
                                                        redefinePackageName + "." + "ClassF1")};

        ClassDefinition[] classDefArray = null;
        try {
            classDefArray = createClassDefinitions(classNames, true);
        } catch (Exception e) {
            setStatus(Status.FAILED, testCaseID, "While loading class " + classNames[0].getFirstClassName() +
                         " an exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        }

        int xOld = -1, xNew = 1;

        ClassF.flag = -1;

        try {
            synchronized(obj.synchThreadReady) {
                obj.start();

                while (ClassF.flag != 1) {
                    obj.synchThreadReady.wait();
                }
                instrumentation.redefineClasses(classDefArray);
                ClassF.flag = 2;
                obj.synchThreadReady.notifyAll();
                while (ClassF.flag != 3) {
                    obj.synchThreadReady.wait();
                }

                xOld = obj.getInt();

                ClassF.flag = 0;
                obj.synchThreadReady.notifyAll();
                while (ClassF.flag != 1) {
                     obj.synchThreadReady.wait();
                }
                ClassF.flag = 2;
                obj.synchThreadReady.notifyAll();
                while (ClassF.flag != 3) {
                    obj.synchThreadReady.wait();
                }

                obj.join();
                xNew = obj.getInt();
            }
        } catch (InterruptedException ie) {
            setStatus(Status.FAILED, testCaseID, "The test was interrupted");
            return Status.passed("Testing result passed to test");
        } catch (ClassNotFoundException cnfe) {
            setStatus(Status.FAILED, testCaseID, "redefinition throws ClassNotFoundException " + cnfe);
            return Status.passed("Testing result passed to test");
        } catch (UnmodifiableClassException uce) {
            setStatus(Status.PASSED, testCaseID, "redefinition is supported, UnmodifiableClassException is thrown");
            return Status.passed("Testing result passed to test");
        }
        if (!ClassF.checkOldValues(xOld)) {
            setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                  + " failed (redefinition affected being executed method(?))");
            return Status.passed("Testing result passed to test");
        }
        if (!ClassF.checkNewValues(xNew)) {
            setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                  + " failed (redefinition did not redefined method(?))");
            return Status.passed("Testing result passed to test");
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, redefinition affects only new static methods invocations");
        return Status.passed("Testing result passed to test");
    }


    /**
     * Assertion testing
     * for public void redefineClasses(ClassDefinition[] definitions),
     * Redefining a class does not cause its initializers to be run.
     * The values of static variables will remain as they were prior to the call.
     * <br><b>Expected results</b>: after class redefinition the values of static variables
     * did not change.
     */
    public Status Instrumentation028() {
        String testCaseID = "Instrumentation028";

        if (!instrumentation.isRedefineClassesSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is not supported");
            return Status.passed("Testing result passed to test");
        }

        ClassG obj = new ClassG1();

        ClassNamePair[] classNames = {new ClassNamePair(originalPackageName + "." + "ClassG1",
                                                        redefinePackageName + "." + "ClassG1")};

        ClassDefinition[] classDefArray = null;
        try {
            classDefArray = createClassDefinitions(classNames, true);
        } catch (Exception e) {
            setStatus(Status.FAILED, testCaseID, "While loading class " + classNames[0].getFirstClassName() +
                         " an exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        }

        if (!ClassG.checkOldValues(obj)) {
            setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                  + " failed (not an original static values(?))");
            return Status.passed("Testing result passed to test");
        }
        try {
            instrumentation.redefineClasses(classDefArray);
        } catch (ClassNotFoundException cnfe) {
            setStatus(Status.FAILED, testCaseID, "redefinition throws ClassNotFoundException " + cnfe);
            return Status.passed("Testing result passed to test");
        } catch (UnmodifiableClassException uce) {
            setStatus(Status.PASSED, testCaseID, "redefinition is supported, UnmodifiableClassException is thrown");
            return Status.passed("Testing result passed to test");
        }

        if (!ClassG.checkNewValues(obj)) {
            setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                  + " failed (redefinition changed static values(?))");
            return Status.passed("Testing result passed to test");
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, redefinition does not affect the values of static variables");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing
     * for public void redefineClasses(ClassDefinition[] definitions),
     * Registered transformers will be called before the redefine operation is applied.
     * <br><b>Expected results</b>: registered transformer receives not redefined class
     */
    public Status Instrumentation029() {
        String testCaseID = "Instrumentation029";

        String originalStr    = "This is a string for redefinition";
        String redefinedStr   = "Redefined string for transformation";
        String redefTransfStr = "Redefined and transformed string   ";

        if (!instrumentation.isRedefineClassesSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is not supported");
            return Status.passed("Testing result passed to test");
        }
        ClassH1 obj = new ClassH1();

        ClassNamePair[] classNames = {new ClassNamePair(originalPackageName + "." + "ClassH1",
                                                        redefinePackageName + "." + "ClassH1")};
        ClassDefinition[] classDefArray = null;
        try {
            classDefArray = createClassDefinitions(classNames, true);
        } catch (Exception e) {
            setStatus(Status.FAILED, testCaseID, "While loading class " + classNames[0].getFirstClassName() +
                         " an exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        }

        Class objClass = obj.getClass();
        TransformationResult res = new TransformationResult();
        ClassFileTransformer transformer = new ClassFileTransformerH(res, objClass, classNames[0].getFirstClassName(),
                                                                     redefinedStr, redefTransfStr);
        try {
            instrumentation.addTransformer(transformer);

            if (!originalStr.equals(obj.getString())) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                      + " failed (not the original class(?))");
                return Status.passed("Testing result passed to test");
            }
            try {
                instrumentation.redefineClasses(classDefArray);
            } catch (ClassNotFoundException cnfe) {
                setStatus(Status.FAILED, testCaseID, "redefinition throws ClassNotFoundException " + cnfe);
                return Status.passed("Testing result passed to test");
            } catch (UnmodifiableClassException uce) {
                setStatus(Status.PASSED, testCaseID, "redefinition is supported, UnmodifiableClassException is thrown");
                return Status.passed("Testing result passed to test");
            }
            if (!originalStr.equals(obj.getString())) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                      + " failed - registered transformer affected existing instance");
                return Status.passed("Testing result passed to test");
            }
            if ("".equals(res.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Transformer was not invoked for redefinition ");
                return Status.passed("Testing result passed to test");
            }
            if (!"OKAY".equals(res.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Transformer reports problem: " + res.getResultString());
                return Status.passed("Testing result passed to test");
            }
            ClassH1 newObj = new ClassH1();
            if (!redefTransfStr.equals(newObj.getString())) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                      + " failed - transformer did not change the class during redefinition");
                return Status.passed("Testing result passed to test");
            }
        } finally {
            instrumentation.removeTransformer(transformer);
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, transformer is called before class redefinition");
        return Status.passed("Testing result passed to test");
    }


    /**
     * Assertion testing
     * for public byte[] transform(ClassLoader loader,
     *                  String className,
     *                  Class classBeingRedefined,
     *                  ProtectionDomain protectionDomain,
     *                  byte[] classfileBuffer),
     * If IllegalClassFormatException is thrown by transformer then redefine will still be attempted
     * <br><b>Expected results</b>: despite of transform() throws IllegalClassFormatException the class
     * is redefined.
     */
    public Status Instrumentation030() {
        String testCaseID = "Instrumentation030";

        String originalStr    = "This is a string for redefinition, ClassI1";
        String redefinedStr   = "Redefined string for transformation, ClassI1";
        String redefTransfStr = "Redefined and transformed string   , ClassI1";

        if (!instrumentation.isRedefineClassesSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is not supported");
            return Status.passed("Testing result passed to test");
        }
        ClassI1 obj = new ClassI1();

        ClassNamePair[] classNames = {new ClassNamePair(originalPackageName + "." + "ClassI1",
                                                        redefinePackageName + "." + "ClassI1")};
        ClassDefinition[] classDefArray = null;
        try {
            classDefArray = createClassDefinitions(classNames, true);
        } catch (Exception e) {
            setStatus(Status.FAILED, testCaseID, "While loading class " + classNames[0].getFirstClassName() +
                         " an exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        }

        TransformationResult res = new TransformationResult();
        ClassFileTransformer transformer = new ClassFileTransformerI(res, classNames[0].getFirstClassName(),
                                                                     redefinedStr, redefTransfStr);
        try {
            instrumentation.addTransformer(transformer);

            if (!originalStr.equals(obj.getString())) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                      + " failed (not the original class(?)): " + obj.getString());
                return Status.passed("Testing result passed to test");
            }
            try {
                instrumentation.redefineClasses(classDefArray);
            } catch (ClassNotFoundException cnfe) {
                setStatus(Status.FAILED, testCaseID, "redefinition throws ClassNotFoundException " + cnfe);
                return Status.passed("Testing result passed to test");
            } catch (UnmodifiableClassException uce) {
                setStatus(Status.PASSED, testCaseID, "redefinition is supported, UnmodifiableClassException is thrown");
                return Status.passed("Testing result passed to test");
            }
            ClassI1 newObj = new ClassI1();
            if (!originalStr.equals(obj.getString())) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                      + " failed - registered transformer affected existing instance:" + obj.getString());
                return Status.passed("Testing result passed to test");
            }
            if ("".equals(res.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Transformer was not invoked for redefinition ");
                return Status.passed("Testing result passed to test");
            }
            if (!"OKAY".equals(res.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Transformer reports problem: " + res.getResultString());
                return Status.passed("Testing result passed to test");
            }
            if (!redefinedStr.equals(newObj.getString())) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                      + " failed - transformer changed the class during redefinition or  "
                                      + " IllegalClassFormatException affects redefinition: " + newObj.getString());
                return Status.passed("Testing result passed to test");
            }
        } finally {
            instrumentation.removeTransformer(transformer);
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, IllegalClassFormatException does not affect redefinition");
        return Status.passed("Testing result passed to test");
    }


    /**
     * Assertion testing
     * for public void redefineClasses(ClassDefinition[] definitions),
     * The redefinition must not rename methods or fields. The methods throws UnsupportedOperationException
     * if the redefinition made unsupported changes.
     * <br><b>Precondition</b>: instance/static method/field is renamed in redefining class
     * <br><b>Expected results</b>: UnsupportedOperationException, if redefinition is supported
     */
    public Status Instrumentation031() {
        String testCaseID = "Instrumentation031";

        if (!instrumentation.isRedefineClassesSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is not supported");
            return Status.passed("Testing result passed to test");
        }

        String[][] testCases = {{"renamed instance method", "ClassS1"}, {"renamed static method", "ClassS2"},
                                {"renamed instance field", "ClassS3"}, {"renamed static field", "ClassS4"}};

        for (int i = 0; i < testCases.length; ++i) {

            ClassNamePair[] classNames = {new ClassNamePair(originalPackageName + "." + testCases[i][1],
                                                            redefinePackageName + "." + testCases[i][1])};

            if (!ClassS.checkOldValues(createSObject(i))) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                                   + " failed (not original class(?))");
                return Status.passed("Testing result passed to test");
            }

            ClassDefinition[] classDefArray = null;
            try {
                classDefArray = createClassDefinitions(classNames, true);
            } catch (Exception e) {
                setStatus(Status.FAILED, testCaseID, "While loading class " + classNames[0].getFirstClassName() +
                             " an exception was thrown: " + e);
                return Status.passed("Testing result passed to test");
            }

            try {
                instrumentation.redefineClasses(classDefArray);
                setStatus(Status.FAILED, testCaseID, "redefinition by a class with " + testCases[i][0] +
                                                     " does not throw UnsupportedOperationException");
                return Status.passed("Testing result passed to test");
            } catch (ClassNotFoundException cnfe) {
                setStatus(Status.FAILED, testCaseID, "redefinition throws ClassNotFoundException " + cnfe);
                return Status.passed("Testing result passed to test");
            } catch (UnmodifiableClassException uce) {
                setStatus(Status.PASSED, testCaseID, "redefinition is supported, UnmodifiableClassException is thrown");
                return Status.passed("Testing result passed to test");
            } catch (UnsupportedOperationException uo) {
            }

            if (!ClassS.checkOldValues(createSObject(i))) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                                   + " failed (redefinition was applied despite of "
                                                   + " UnsupportedOperationException(?))");
                return Status.passed("Testing result passed to test");
            }
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, UnsupportedOperationException is thrown for " +
                                             "redefinition by a class with renamed instance/static methods/fields");
        return Status.passed("Testing result passed to test");
    }

    private ClassS createSObject(int i) {
        if (i == 0) {
             return new ClassS1();
        } else if (i == 1) {
             return new ClassS2();
        } else if (i == 2) {
             return new ClassS3();
        } else if (i == 3) {
             return new ClassS4();
        } else if (i == 10) {
             return new ClassS10();
        } else if (i == 11) {
             return new ClassS11();
        } else {
            return new ClassS();
        }
    }


    /**
     * Assertion testing
     * for public void redefineClasses(ClassDefinition[] definitions),
     * The redefinition must not change class inheritance. The methods throws UnsupportedOperationException
     * if the redefinition made unsupported changes.
     * <br><b>Precondition</b>: redefinition by a class extending another class, implementing another interface
     * <br><b>Expected results</b>: UnsupportedOperationException, if redefinition is supported
     */
    public Status Instrumentation032() {
        String testCaseID = "Instrumentation032";

        if (!instrumentation.isRedefineClassesSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, redefinition is not supported");
            return Status.passed("Testing result passed to test");
        }

        String[][] testCases = {{"different superclass", "ClassS10"},
                                {"implemented different interface", "ClassS11"}};

        for (int i = 0; i < testCases.length; ++i) {

            ClassNamePair[] classNames = {new ClassNamePair(originalPackageName + "." + testCases[i][1],
                                                            redefinePackageName + "." + testCases[i][1])};

            if (!ClassS.checkOldValues(createSObject(10 + i))) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                                   + " failed (not original class(?))");
                return Status.passed("Testing result passed to test");
            }

            ClassDefinition[] classDefArray = null;
            try {
                classDefArray = createClassDefinitions(classNames, true);
            } catch (Exception e) {
                setStatus(Status.FAILED, testCaseID, "While loading class " + classNames[0].getFirstClassName() +
                             " an exception was thrown: " + e);
                return Status.passed("Testing result passed to test");
            }

            try {
                instrumentation.redefineClasses(classDefArray);
                setStatus(Status.FAILED, testCaseID, "redefinition by a class with " + testCases[i][0] +
                                                     " does not throw UnsupportedOperationException");
                return Status.passed("Testing result passed to test");
            } catch (ClassNotFoundException cnfe) {
                setStatus(Status.FAILED, testCaseID, "redefinition throws ClassNotFoundException " + cnfe);
                return Status.passed("Testing result passed to test");
            } catch (UnmodifiableClassException uce) {
                setStatus(Status.PASSED, testCaseID, "redefinition is supported, UnmodifiableClassException is thrown");
                return Status.passed("Testing result passed to test");
            } catch (UnsupportedOperationException uo) {
            }

            if (!ClassS.checkOldValues(createSObject(10 + i))) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classNames[0].getFirstClassName()
                                                   + " failed (redefinition was applied despite of "
                                                   + " UnsupportedOperationException(?))");
                return Status.passed("Testing result passed to test");
            }
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, UnsupportedOperationException is thrown for " +
                                             "redefinition by a class with changed inheritance");
        return Status.passed("Testing result passed to test");
    }


    /**
     * Boundary value analysis
     * with input values orientation
     * for public void addTransformer(ClassFileTransformer transformer),
     * <br><b>transformer</b>:  null
     * <br><b>Expected results</b>: NullPointerException is thrown
     */
    public Status Instrumentation040() {
        String testCaseID = "Instrumentation040";
        try {
            instrumentation.addTransformer(null);
            setStatus(Status.FAILED, testCaseID, "addTransformer(null) does not throw NullPointerException");
        } catch (NullPointerException npe) {
            setStatus(Status.PASSED, testCaseID, "OKAY, addTransformer(null) throws NullPointerException");
        }
        return Status.passed("Testing result passed to test");
    }


    /**
     * Boundary value analysis
     * with input values orientation
     * public boolean removeTransformer(ClassFileTransformer transformer),
     * <br><b>transformer</b>:  null
     * <br><b>Expected results</b>: NullPointerException is thrown
     */
    public Status Instrumentation041() {
        String testCaseID = "Instrumentation041";
        try {
            instrumentation.removeTransformer(null);
            setStatus(Status.FAILED, testCaseID, "removeTransformer(null) does not throw NullPointerException");
        } catch (NullPointerException npe) {
            setStatus(Status.PASSED, testCaseID, "OKAY, removeTransformer(null) throws NullPointerException");
        }
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing
     * for public void addTransformer(ClassFileTransformer transformer),
     * Registers the supplied transformer. The same transformer may be added more than once.
     * removeTransformer() returns true if the transformer was found and removed, false if the
     * transformer was not found
     * <br><b>Expected results</b>: twice registered one transformer may be successively removed twice,
     * the third removement returns false, removement of unregistered transformer returns false
     */
    public Status Instrumentation042() {
        String testCaseID = "Instrumentation042";

        ClassFileTransformer transformer = new TestClassFileTransformer();
        ClassFileTransformer anotherTransformer = new TestClassFileTransformer();
        ClassFileTransformer unregisteredTransformer = new TestClassFileTransformer();

        instrumentation.addTransformer(transformer);
        instrumentation.addTransformer(transformer);
        instrumentation.addTransformer(anotherTransformer);

        if (!instrumentation.removeTransformer(anotherTransformer)) {
            setStatus(Status.FAILED, testCaseID, "removeTransformer(some registered transformer) returns false");
            return Status.passed("Testing result passed to test");
        }
        if (!instrumentation.removeTransformer(transformer)) {
            setStatus(Status.FAILED, testCaseID, "first removeTransformer(transformer) returns false");
            return Status.passed("Testing result passed to test");
        }
        if (!instrumentation.removeTransformer(transformer)) {
            setStatus(Status.FAILED, testCaseID, "second removeTransformer(transformer) returns false");
            return Status.passed("Testing result passed to test");
        }
        if (instrumentation.removeTransformer(transformer)) {
            setStatus(Status.FAILED, testCaseID, "third removeTransformer(transformer) returns true");
            return Status.passed("Testing result passed to test");
        }
        if (instrumentation.removeTransformer(unregisteredTransformer)) {
            setStatus(Status.FAILED, testCaseID, "removeTransformer(unregistered transformer) returns true");
            return Status.passed("Testing result passed to test");
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, transformer is registered and removed twice");
        return Status.passed("Testing result passed to test");
    }


    /**
     * Assertion testing
     * for public void addTransformer(ClassFileTransformer transformer),
     * All future class definitions will be seen by the transformer, except
     * definitions of classes upon which any registered transformer is dependent.
     * <br><b>Expected results</b>: the class loaded by transformer is not seen by another transformer,
     * the order of invocations is correct.
     */
    public Status Instrumentation043() {
        String testCaseID = "Instrumentation043";
        Vector v = new Vector();
        String classToLoadInTransformer = "ClassJ2";
        String classToLoadInAgent = "ClassJ1";
        String id1 = "transformer1";
        String id2 = "transformer2";
        ClassFileTransformer transformer2 = null;
        ClassFileTransformer transformer1 = null;

        ClassLoader cl = null;

        try {
            cl = new LocalDirClassLoader(classesDirURL, this.getClass().getClassLoader());
        } catch (Exception e) {
            setStatus(Status.FAILED, testCaseID, "Can not create LocalDirClassLoader " + e);
            return Status.passed("Testing result passed to test");
        }

        try {
            transformer1 = new TestClassFileTransformerJ1(id1, v, classToLoadInTransformer, classToLoadInAgent);
            instrumentation.addTransformer(transformer1);

            transformer2 = new TestClassFileTransformerJ2(id2, v, classToLoadInTransformer, classToLoadInAgent, cl);
            instrumentation.addTransformer(transformer2);

            try {
                Class c = cl.loadClass(classToLoadInAgent);
            } catch (Exception e) {
                setStatus(Status.FAILED, testCaseID, "Class " + classToLoadInAgent + " could not be loaded " + e);
                return Status.passed("Testing result passed to test");
            }

            if (v.size() != 2) {
                String resMsg = "two transformers were invoked " + v.size() + " time(s) instead of each once:\n";
                while(v.size() != 0) {
                    resMsg += (String) v.remove(0) + ",\n";
                }
                setStatus(Status.FAILED, testCaseID, resMsg);
                return Status.passed("Testing result passed to test");
            }

            String res = (String) v.remove(0);
            String expected = id1 + " on " + classToLoadInAgent + " loading";

            if (!expected.equals(res)) {
                setStatus(Status.FAILED, testCaseID, res + " was invoked instead of " + expected);
                return Status.passed("Testing result passed to test");
            }

            res = (String) v.remove(0);
            expected = id2 + " on " + classToLoadInAgent + " loading";

            if (!expected.equals(res)) {
                setStatus(Status.FAILED, testCaseID, res + " was invoked instead of " + expected);
                return Status.passed("Testing result passed to test");
            }
        } finally {
            if (transformer1 != null) {
                instrumentation.removeTransformer(transformer1);
            }
            if (transformer2 != null) {
                instrumentation.removeTransformer(transformer2);
            }
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, transformer does not see classes loaded by other transformers, " +
                                             " the order of invocation is correct");
        return Status.passed("Testing result passed to test");
    }


    /**
     * Assertion testing
     * for public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined,
     * ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException
     * The implementation of this method may transform the supplied class file and return a new
     * replacement class file.
     * If multiple transformers are registered, they will be called in the order added.
     * <br><b>Expected results</b>: first registered transformer changes class bytes, second expects
     * changed class bytes and changes the class once more.
     */
    public Status Instrumentation044() {
        String testCaseID = "Instrumentation044";

        String classToLoadInAgent = "ClassK1";
        ClassFileTransformer transformer1 = null;
        ClassFileTransformer transformer2 = null;

        String originalStr            = "This is a string for transformation, ClassK1";
        String transformedStrIntermed = " Partially transformed s t r i n g , ClassK1";
        String transformedStr         = " T r a n s f o r m e d    string   , ClassK1";

        TransformationResult res1 = new TransformationResult();
        TransformationResult res2 = new TransformationResult();

        ClassLoader cl = null;
        BaseClass obj = null;

        try {
            cl = new LocalDirClassLoader(classesDirURL, this.getClass().getClassLoader());
        } catch (Exception e) {
            setStatus(Status.FAILED, testCaseID, "Can not create LocalDirClassLoader " + e);
            return Status.passed("Testing result passed to test");
        }

        try {
            transformer1 = new TestClassFileTransformerK(res1, classToLoadInAgent, originalStr, transformedStrIntermed);
            transformer2 = new TestClassFileTransformerK(res2, classToLoadInAgent, transformedStrIntermed, transformedStr);
            instrumentation.addTransformer(transformer1);
            instrumentation.addTransformer(transformer2);

            Class c;
            try {
                c = cl.loadClass(classToLoadInAgent);
            } catch (Exception e) {
                setStatus(Status.FAILED, testCaseID, "Class " + classToLoadInAgent + " could not be loaded " + e);
                return Status.passed("Testing result passed to test");
            }

            if ("".equals(res1.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "First registered transformer was not invoked for transformation ");
                return Status.passed("Testing result passed to test");
            }

            if ("".equals(res2.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Second registered transformer was not invoked for transformation ");
                return Status.passed("Testing result passed to test");
            }

            if (!"OKAY".equals(res1.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "First registered transformer reports problem: " + res1.getResultString());
                return Status.passed("Testing result passed to test");
            }

            if (!"OKAY".equals(res2.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Second registered transformer reports problem: " + res2.getResultString());
                return Status.passed("Testing result passed to test");
            }

            try {
                obj = (BaseClass) c.newInstance();
            } catch (Exception e) {
                setStatus(Status.FAILED, testCaseID, "The loaded class " + classToLoadInAgent + ", can not be " +
                                                     " instantiated, thrown exception: " + e);
                return Status.passed("Testing result passed to test");
            }

            if (!transformedStr.equals(obj.getString())) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classToLoadInAgent
                                      + " failed (the class was not transformed(?)): " + obj.getString());
                return Status.passed("Testing result passed to test");
            }

        } finally {
            if (transformer1 != null) {
                instrumentation.removeTransformer(transformer1);
            }
            if (transformer2 != null) {
                instrumentation.removeTransformer(transformer2);
            }
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, class bytes were correctly transformed");
        return Status.passed("Testing result passed to test");
    }


    /**
     * Assertion testing
     * for public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined,
     * ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException
     * If the transformer believes the classFileBuffer does not represent a validly formatted class
     * file, it should throw an IllegalClassFormatException. Subsequent transformers will still
     * be called and the load or redefine will still be attempted.
     * <br><b>Expected results</b>: in a set of registered transformers, the transformer throwing
     * IllegalClassFormatException does not affect transformation.
     */
    public Status Instrumentation045() {
        String testCaseID = "Instrumentation045";

        String classToLoadInAgent = "ClassL1";
        ClassFileTransformer transformer1 = null;
        ClassFileTransformer transformer2 = null;
        ClassFileTransformer transformer3 = null;

        String originalStr             = "This is a string for transformation, ClassL1";
        String transformedStrIntermed  = " Partially transformed s t r i n g , ClassL1";
        String transformedStr          = " T r a n s f o r m e d    string   , ClassL1";

        TransformationResult res1 = new TransformationResult();
        TransformationResult res2 = new TransformationResult();
        TransformationResult res3 = new TransformationResult();

        ClassLoader cl = null;
        BaseClass obj = null;

        try {
            cl = new LocalDirClassLoader(classesDirURL, this.getClass().getClassLoader());
        } catch (Exception e) {
            setStatus(Status.FAILED, testCaseID, "Can not create LocalDirClassLoader " + e);
            return Status.passed("Testing result passed to test");
        }

        try {
            transformer1 = new TestClassFileTransformerLM(res1, classToLoadInAgent, originalStr, transformedStrIntermed);
            transformer2 = new TestClassFileTransformerL2(res2, classToLoadInAgent);
            transformer3 = new TestClassFileTransformerLM(res3, classToLoadInAgent, transformedStrIntermed, transformedStr);
            instrumentation.addTransformer(transformer1);
            instrumentation.addTransformer(transformer2);
            instrumentation.addTransformer(transformer3);

            Class c;
            try {
                c = cl.loadClass(classToLoadInAgent);
            } catch (Exception e) {
                setStatus(Status.FAILED, testCaseID, "Class " + classToLoadInAgent + " could not be loaded " + e);
                return Status.passed("Testing result passed to test");
            }

            if ("".equals(res1.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "First registered transformer was not invoked for transformation ");
                return Status.passed("Testing result passed to test");
            }

            if ("".equals(res2.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Second (throwing IllegalClassFormatException) registered transformer " +
                                                     " was not invoked for transformation ");
                return Status.passed("Testing result passed to test");
            }

            if ("".equals(res3.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Third registered transformer was not invoked for transformation ");
                return Status.passed("Testing result passed to test");
            }

            if (!"OKAY".equals(res1.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "First registered transformer reports problem: " + res1.getResultString());
                return Status.passed("Testing result passed to test");
            }

            if (!"OKAY".equals(res2.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Second registered transformer reports problem: " + res2.getResultString());
                return Status.passed("Testing result passed to test");
            }

            if (!"OKAY".equals(res3.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Third registered transformer reports problem: " + res3.getResultString());
                return Status.passed("Testing result passed to test");
            }

            try {
                obj = (BaseClass) c.newInstance();
            } catch (Exception e) {
                setStatus(Status.FAILED, testCaseID, "The loaded class " + classToLoadInAgent + ", can not be " +
                                                     " instantiated, thrown exception: " + e);
                return Status.passed("Testing result passed to test");
            }

            if (!transformedStr.equals(obj.getString())) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classToLoadInAgent
                                      + " failed (the class was not transformed(?)): " + obj.getString());
                return Status.passed("Testing result passed to test");
            }

        } finally {
            if (transformer1 != null) {
                instrumentation.removeTransformer(transformer1);
            }
            if (transformer2 != null) {
                instrumentation.removeTransformer(transformer2);
            }
            if (transformer3 != null) {
                instrumentation.removeTransformer(transformer3);
            }
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, IllegalClassFormatException thrown by one of the " +
                                             "transformers does not affect transformation");
        return Status.passed("Testing result passed to test");
    }


    /**
     * Assertion testing
     * for public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined,
     * ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException
     * If the implementing method determines that no transformations are needed, it should return null
     * <br><b>Expected results</b>: in a set of registered transformers, the transformer returning
     * null does not affect transformation.
     */
    public Status Instrumentation046() {
        String testCaseID = "Instrumentation046";

        String classToLoadInAgent = "ClassM1";
        ClassFileTransformer transformer1 = null;
        ClassFileTransformer transformer2 = null;
        ClassFileTransformer transformer3 = null;

        String originalStr             = "This is a string for transformation, ClassM1";
        String transformedStrIntermed  = " Partially transformed s t r i n g , ClassM1";
        String transformedStr          = " T r a n s f o r m e d    string   , ClassM1";

        TransformationResult res1 = new TransformationResult();
        TransformationResult res2 = new TransformationResult();
        TransformationResult res3 = new TransformationResult();

        ClassLoader cl = null;
        BaseClass obj = null;

        try {
            cl = new LocalDirClassLoader(classesDirURL, this.getClass().getClassLoader());
        } catch (Exception e) {
            setStatus(Status.FAILED, testCaseID, "Can not create LocalDirClassLoader " + e);
            return Status.passed("Testing result passed to test");
        }

        try {
            transformer1 = new TestClassFileTransformerLM(res1, classToLoadInAgent, originalStr, transformedStrIntermed);
            transformer2 = new TestClassFileTransformerM2(res2, classToLoadInAgent);
            transformer3 = new TestClassFileTransformerLM(res3, classToLoadInAgent, transformedStrIntermed, transformedStr);
            instrumentation.addTransformer(transformer1);
            instrumentation.addTransformer(transformer2);
            instrumentation.addTransformer(transformer3);

            Class c;
            try {
                c = cl.loadClass(classToLoadInAgent);
            } catch (Exception e) {
                setStatus(Status.FAILED, testCaseID, "Class " + classToLoadInAgent + " could not be loaded " + e);
                return Status.passed("Testing result passed to test");
            }

            if ("".equals(res1.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "First registered transformer was not invoked for transformation ");
                return Status.passed("Testing result passed to test");
            }

            if ("".equals(res2.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Second (throwing IllegalClassFormatException) registered transformer " +
                                                     " was not invoked for transformation ");
                return Status.passed("Testing result passed to test");
            }

            if ("".equals(res3.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Third registered transformer was not invoked for transformation ");
                return Status.passed("Testing result passed to test");
            }

            if (!"OKAY".equals(res1.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "First registered transformer reports problem: " + res1.getResultString());
                return Status.passed("Testing result passed to test");
            }

            if (!"OKAY".equals(res2.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Second registered transformer reports problem: " + res2.getResultString());
                return Status.passed("Testing result passed to test");
            }

            if (!"OKAY".equals(res3.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Third registered transformer reports problem: " + res3.getResultString());
                return Status.passed("Testing result passed to test");
            }

            try {
                obj = (BaseClass) c.newInstance();
            } catch (Exception e) {
                setStatus(Status.FAILED, testCaseID, "The loaded class " + classToLoadInAgent + ", can not be " +
                                                     " instantiated, thrown exception: " + e);
                return Status.passed("Testing result passed to test");
            }

            if (!transformedStr.equals(obj.getString())) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classToLoadInAgent
                                      + " failed (the class was not transformed(?)): " + obj.getString());
                return Status.passed("Testing result passed to test");
            }

        } finally {
            if (transformer1 != null) {
                instrumentation.removeTransformer(transformer1);
            }
            if (transformer2 != null) {
                instrumentation.removeTransformer(transformer2);
            }
            if (transformer3 != null) {
                instrumentation.removeTransformer(transformer3);
            }
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, null returned by one of the " +
                                             "transformers does not affect transformation");
        return Status.passed("Testing result passed to test");
    }


    /**
     * Assertion testing
     * for public boolean removeTransformer(ClassFileTransformer transformer),
     * Unregisters the supplied transformer. Future class definitions will not be shown to the transformer.
     * <br><b>Precondition</b>: register two transformers, one is removed
     * <br><b>Expected results</b>: removed transformer is not invoked during class definition.
     */
    public Status Instrumentation047() {
        String testCaseID = "Instrumentation047";

        String[] classesToLoadInAgent = {"ClassN1", "ClassN2"};
        ClassFileTransformer transformer1 = null;
        ClassFileTransformer transformer2 = null;

        String originalStr             = "This is a string for transformation, ClassN";
        String transformedStrIntermed  = " Partially transformed s t r i n g , ClassN";
        String transformedStr          = " T r a n s f o r m e d    string   , ClassN";

        TransformationResult res1 = new TransformationResult();
        TransformationResult res2 = new TransformationResult();

        ClassLoader cl = null;
        BaseClass obj = null;

        try {
            cl = new LocalDirClassLoader(classesDirURL, this.getClass().getClassLoader());
        } catch (Exception e) {
            setStatus(Status.FAILED, testCaseID, "Can not create LocalDirClassLoader " + e);
            return Status.passed("Testing result passed to test");
        }

        try {
            transformer1 = new TestClassFileTransformerN(res1, classesToLoadInAgent, originalStr, transformedStrIntermed);
            transformer2 = new TestClassFileTransformerN(res2, classesToLoadInAgent, transformedStrIntermed, transformedStr);
            instrumentation.addTransformer(transformer1);
            instrumentation.addTransformer(transformer2);

            Class c;
            try {
                c = cl.loadClass(classesToLoadInAgent[0]);
            } catch (Exception e) {
                setStatus(Status.FAILED, testCaseID, "Class " + classesToLoadInAgent[0] + " could not be loaded " + e);
                return Status.passed("Testing result passed to test");
            }
            res1.cleanup();
            res2.cleanup();
            instrumentation.removeTransformer(transformer2);
            try {
                c = cl.loadClass(classesToLoadInAgent[1]);
            } catch (Exception e) {
                setStatus(Status.FAILED, testCaseID, "Class " + classesToLoadInAgent[1] + " could not be loaded " + e);
                return Status.passed("Testing result passed to test");
            }

            if ("".equals(res1.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "First registered transformer was not invoked for " +
                                                     "transformation of " + classesToLoadInAgent[1]);
                return Status.passed("Testing result passed to test");
            }
            if (!"OKAY".equals(res1.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "First registered transformer reports problem: " + res1.getResultString());
                return Status.passed("Testing result passed to test");
            }

            if ("OKAY".equals(res2.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Second (removed) transformer was invoked for " +
                                                     " transformation of " + classesToLoadInAgent[1]);
                return Status.passed("Testing result passed to test");
            }
            if (!"".equals(res2.getResultString())) {
                setStatus(Status.FAILED, testCaseID, "Second (removed) transformer was invoked for " +
                                                     " transformation of " + classesToLoadInAgent[1] +
                                                     " and reports problem: " + res2.getResultString());
                return Status.passed("Testing result passed to test");
            }
            try {
                obj = (BaseClass) c.newInstance();
            } catch (Exception e) {
                setStatus(Status.FAILED, testCaseID, "The loaded class " + classesToLoadInAgent[1] + " can not be " +
                                                     " instantiated, thrown exception: " + e);
                return Status.passed("Testing result passed to test");
            }

            if (!transformedStrIntermed.equals(obj.getString())) {
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classesToLoadInAgent[1]
                                      + " failed (the class was not transformed(?)): " + obj.getString());
                return Status.passed("Testing result passed to test");
            }

        } finally {
            if (transformer1 != null) {
                instrumentation.removeTransformer(transformer1);
            }
            if (transformer2 != null) {
                instrumentation.removeTransformer(transformer2);
            }
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, removed transformer is not invoked for transformation");
        return Status.passed("Testing result passed to test");
    }


    /**
     * Assertion testing
     * for public void addTransformer(ClassFileTransformer transformer),
     * All future class definitions will be seen by the transformer.
     * <br><b>Precondition</b>: a transformer is registered, class is loaded in test (application) class
     * <br><b>Expected results</b>: the test gets transformed class
     */
    public Status Instrumentation048() {
        String testCaseID = "Instrumentation048";

        String originalStr    = "This is a string for transformation, ClassP1";
        String transformedStr = " T r a n s f o r m e d    string   , ClassP1";

        ClassFileTransformer transformer = new TestClassFileTransformerP("ClassP1",
                                                                         originalStr, transformedStr);
        instrumentation.addTransformer(transformer);

        setStatus(Status.PASSED, testCaseID, "OKAY, transformer is successfully registered");
        return Status.passed("Tranformer is registered");
    }

    /**
     * Assertion testing
     * This switch may be repeated multiple times on the same command line,
     * thus creating multiple agents. Each premain method will be called in the
     * order the -javaagent switches appeared on the command line.
     * <br><b>Expected results</b>: The first agent registers tranasformer,
     * the second agent checks that the first transformer is registered, loads
     * class and checks that class is transformed by the transformer registered in the
     * first agent, then removes both transformers.
     */
    public Status Instrumentation049() {
        String testCaseID = "Instrumentation049";

        String originalStr    = "This is a string for transformation, ClassQ1";
        String transformedStr = " T r a n s f o r m e d    string   , ClassQ1";

        ClassFileTransformer transformer = new TestClassFileTransformerP("ClassQ1",
                                                                         originalStr, transformedStr);
        instrumentation.addTransformer(transformer);

        storeObject(transformer, testCaseID);
        setStatus(Status.PASSED, testCaseID, "OKAY, transformer is successfully registered");
        return Status.passed("Tranformer is registered");
    }

    //----------------------------------------------------------------
    /**
     * Assertion testing
     * for public void appendToBootstrapClassLoaderSearch(JarFile jarfile).
     * Verifying that attempt to resolve a symbolic reference that was
     * unsuccesfull still remains unsuccessfull after appending JAR file
     * with searched class.
     */
    public Status Instrumentation201() {
        final String testCaseID = "Instrumentation201";

        final String clname = "ClassToResolve";
        final String bootjar = getJarsDirURL() + "toresolve.jar" + "!/";

        // Test case setup
        if (tryToResolveClass()) {
            setStatus(Status.FAILED, testCaseID, "Should not be able to resolve a reference "
                    + "on the \"" + clname + "\" class");
            return Status.passed("Testing result passed to test");
        }

        JarFile jf = loadJarFromURL(bootjar, testCaseID);
        if(jf == null) {
            setStatus(Status.FAILED, testCaseID, "jar is null");
            return Status.passed("Testing result passed to test");
        }
        // end of Test case setup

        instrumentation.appendToBootstrapClassLoaderSearch(jf);

        if (tryToResolveClass()) {
            setStatus(Status.FAILED, testCaseID, "Reference on the \"" + clname
                    + "\" class is unexpectedly resolved");
            return Status.passed("Testing result passed to test");
        }

        setStatus(Status.PASSED, testCaseID, "OKAY, subsequent attempt to resolve a symbolic reference "
                + "is failed as expected");
        return Status.passed("OKAY");
    }

    /**
     * Assertion testing
     * for public void appendToBootstrapClassLoaderSearch(JarFile jarfile).
     * Verifying that specified jar is being added to bootstrap classloader
     * search.
     */
    public Status Instrumentation202() {
        final String testCaseID = "Instrumentation202";

        final String clname = packageName + "." + "ClassToLoad1";
        final String bootjar = getJarsDirURL() + "toload1.jar" + "!/";

        // Test case setup
        try {
            Class.forName(clname);
            setStatus(Status.FAILED, testCaseID, "Should not be able to create an instance of the \""
                     + clname + "\" class");
            return Status.passed("Testing result passed to test");
        } catch (ClassNotFoundException cnfe) {
            // OKAY
        }

        JarFile jf = loadJarFromURL(bootjar, testCaseID);
        if(jf == null) {
            setStatus(Status.FAILED, testCaseID, "jar is null");
            return Status.passed("Testing result passed to test");
        }
        // end of Test case setup

        instrumentation.appendToBootstrapClassLoaderSearch(jf);

        try {
            if (!checkLoadedByLoader(testCaseID, clname, null)) {
                return Status.passed("Testing result passed to test");
            }
        } catch (SecurityException se) {
            return checkGetClassLoader(testCaseID);
        }

        setStatus(Status.PASSED, testCaseID, "OKAY, toload1.jar is added to bootstrap classloader search");
        return Status.passed("OKAY");
    }

    /**
     * Assertion testing
     * for public void appendToBootstrapClassLoaderSearch(JarFile jarfile).
     * Verifying that NullPointerException is being thrown if jarfile is null.
     */
    public Status Instrumentation203() {
        final String testCaseID = "Instrumentation203";

        JarFile jf = null;
        try {
            instrumentation.appendToBootstrapClassLoaderSearch(jf);
        } catch (NullPointerException npe) {
            setStatus(Status.PASSED, testCaseID, "OKAY, NullPointerException is thrown as expected");
            return Status.passed("OKAY");
        }

        setStatus(Status.FAILED, testCaseID, "NullPointerException is not thrown");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing
     * for public void appendToSystemClassLoaderSearch(JarFile jarfile).
     * Verifying that attempt to resolve a symbolic reference that was
     * unsuccesfull still remains unsuccessfull after appending JAR file
     * with searched class.
     */
    public Status Instrumentation211() {
        String testCaseID = "Instrumentation211";

        final String clname = packageName + "." + "ClassToResolve2";
        final String systemjar = getJarsDirURL() + "toresolve2.jar" + "!/";

        // Test case setup
        if (tryToResolveClass2()) {
            setStatus(Status.FAILED, testCaseID, "Should not be able to resolve a reference on the \""
                    + clname + "\" class");
            return Status.passed("Testing result passed to test");
        }

        JarFile jf = loadJarFromURL(systemjar, testCaseID);
        if(jf == null) {
            setStatus(Status.FAILED, testCaseID, "jar is null");
            return Status.passed("Testing result passed to test");
        }
        // end of Test case setup

        if (!isJarFileAppendingSupportedBySCL()) {
            try {
                instrumentation.appendToSystemClassLoaderSearch(jf);
                setStatus(Status.FAILED, testCaseID, "UnsupportedOperationException is not thrown");
                return Status.passed("Testing result passed to test");
            } catch(UnsupportedOperationException uoe) {
                // OKAY
            }
        } else {
            try {
                instrumentation.appendToSystemClassLoaderSearch(jf);
            } catch(UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID, "Unexpected UnsupportedOperationException is thrown: " + uoe);
                return Status.passed("Testing result passed to test");
            }
        }

        if (tryToResolveClass2()) {
            setStatus(Status.FAILED, testCaseID, "Reference on the \"" + clname + "\" class is unexpectedly resolved");
            return Status.passed("Testing result passed to test");
        }

        setStatus(Status.PASSED, testCaseID, "OKAY, subsequent attempt to resolve a symbolic reference failed as expected");
        return Status.passed("OKAY");
    }

    /**
     * Assertion testing
     * for public void appendToSystemClassLoaderSearch(JarFile jarfile).
     * Verifying that if possible, specified jar file is being added to
     * system classloader search. The system class loader supports adding
     * a JAR file to be searched if it implements a method named
     * appendToClassPathForInstrumentation which takes a single parameter
     * of type java.lang.String.
     */
    public Status Instrumentation212() {
        final String testCaseID = "Instrumentation212";

        final String clname = packageName + "." + "ClassToLoad2";
        final String systemjar = getJarsDirURL() + "toload2.jar" + "!/";

        // Test case setup
        try {
            Class.forName(clname);
            setStatus(Status.FAILED, testCaseID, "Should not be able to create an instance of the \"" + clname + "\" class");
            return Status.passed("Testing result passed to test");
        } catch (ClassNotFoundException cnfe) {
            // OKAY
        }

        JarFile jf = loadJarFromURL(systemjar, testCaseID);
        if(jf == null) {
            setStatus(Status.FAILED, testCaseID, "jar is null");
            return Status.passed("Testing result passed to test");
        }
        // end of Test case setup

        if (!isJarFileAppendingSupportedBySCL()) {
            try {
                instrumentation.appendToSystemClassLoaderSearch(jf);
                setStatus(Status.FAILED, testCaseID, "UnsupportedOperationException is not thrown");
                return Status.passed("Testing result passed to test");
            } catch(UnsupportedOperationException uoe) {
                setStatus(Status.PASSED, testCaseID, "UnsupportedOperationException is thrown as expected. "
                          + "The system class loader doesn't support appending a JAR file to be searched");
                return Status.passed("Testing result passed to test");
            }
        } else {
            try {
                instrumentation.appendToSystemClassLoaderSearch(jf);
            } catch(UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID, "Unexpected UnsupportedOperationException is thrown: " + uoe);
                return Status.passed("Testing result passed to test");
            }
        }

        try {
            if (!checkLoadedByLoader(testCaseID, clname, ClassLoader.getSystemClassLoader())) {
                return Status.passed("Testing result passed to test");
            }
        } catch (SecurityException se) {
            return checkGetClassLoader(testCaseID);
        }

        setStatus(Status.PASSED, testCaseID, "OKAY, toload2.jar is added to system classloader search");
        return Status.passed("OKAY");
    }

    /**
     * Assertion testing
     * for public void appendToSystemClassLoaderSearch(JarFile jarfile).
     * Verifying that NullPointerException is being thrown if jarfile is null.
     */
    public Status Instrumentation213() {
        final String testCaseID = "Instrumentation213";

        JarFile jf = null;
        try {
            if (!isJarFileAppendingSupportedBySCL()) {
                try {
                    instrumentation.appendToSystemClassLoaderSearch(jf);
                } catch(UnsupportedOperationException uoe) {
                    setStatus(Status.PASSED, testCaseID, "UnsupportedOperationException is thrown. "
                              + "The system class loader doesn't support appending a JAR file");
                    return Status.passed("OKAY");
                }
            } else {
                instrumentation.appendToSystemClassLoaderSearch(jf);
            }
        } catch (NullPointerException npe) {
            setStatus(Status.PASSED, testCaseID, "OKAY, NullPointerException is thrown as expected");
            return Status.passed("OKAY");
        }

        setStatus(Status.FAILED, testCaseID, "NullPointerException is not thrown");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing
     * for public void appendToSystemClassLoaderSearch(JarFile jarfile).
     * Verifying that this method does not change the value of java.class.path
     * system property.
     */
    public Status Instrumentation215() {
        final String testCaseID = "Instrumentation215";

        final String key = "java.class.path";
        final String systemjar = getJarsDirURL() + "toload3.jar" + "!/";

        // Test case setup
        String oldClassPath = null;
        try {
            oldClassPath = System.getProperty(key);
        } catch (SecurityException se) {
            return checkGetPropertyAccess(key, testCaseID);
        }

        JarFile jf = loadJarFromURL(systemjar, testCaseID);
        if(jf == null) {
            setStatus(Status.FAILED, testCaseID, "jar is null");
            return Status.passed("Testing result passed to test");
        }
        // end of Test case setup

        if (!isJarFileAppendingSupportedBySCL()) {
            try {
                instrumentation.appendToSystemClassLoaderSearch(jf);
                setStatus(Status.FAILED, testCaseID,
                        "UnsupportedOperationException is not thrown");
            } catch(UnsupportedOperationException uoe) {
                // OKAY
            }
        } else {
            try {
                instrumentation.appendToSystemClassLoaderSearch(jf);
            } catch(UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException is thrown: "
                        + uoe);
                return Status.passed("Testing result passed to test");
            }
        }

        String newClassPath = null;
        try {
            newClassPath = System.getProperty(key);
        } catch (SecurityException se) {
            return checkGetPropertyAccess(key, testCaseID);
        }

        if (!equalsClassPath(oldClassPath, newClassPath)) {
            setStatus(Status.FAILED, testCaseID, " \"" + key + "\" system property is unexpectedly changed: "
                    + "oldClassPath=" + oldClassPath + " "
                    + "newClassPath=" + newClassPath);
            return Status.passed("Testing result passed to test");
        }

        setStatus(Status.PASSED, testCaseID,"OKAY, \"" + key + "\" system property is not changed as expected");
        return Status.passed("OKAY");
    }

    //==========================================================================

    /**
     * Assertion testing for
     * void retransformClasses(Class<?>... classes).
     * Verifying that this method throws NullPointerException
     * if the supplied classes array is null.
     */
    public Status Instrumentation218() {
        final String testCaseID = "Instrumentation218";
        final Class[] classes = null;
        final boolean isRetransformSupported =
                instrumentation.isRetransformClassesSupported();

        try {
            instrumentation.retransformClasses(classes);
        } catch (NullPointerException ok) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, NullPointerException is thrown as expected");
            return Status.passed("OKAY");
        } catch (UnsupportedOperationException uoe) {
            if (isRetransformSupported) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected exception: " + uoe.getMessage());
                return Status.passed("Testing result passed to test");
            } else {
                setStatus(Status.PASSED, testCaseID,
                        "OKAY, UnsupportedOperationException is thrown");
                return Status.passed("OKAY");
            }
        } catch (UnmodifiableClassException uce) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected exception: " + uce.getMessage());
            return Status.passed("Testing result passed to test");
        } catch (Throwable e) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected exception: " + e);
            return Status.passed("Testing result passed to test");
        }

        setStatus(Status.FAILED, testCaseID,
                "NullPointerException is not thrown");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing for
     * void retransformClasses(Class<?>... classes)
     * Verifying that this method throws UnmodifiableClassException
     * if a specified class cannot be modified.
     */
    public Status Instrumentation220() {
        final String testCaseID = "Instrumentation220";
        final Class[] classesArray = new Class[] {
            byte.class, boolean.class, char.class, short.class,
            int.class, long.class, float.class, double.class,
            new Object[0].getClass()
        };
        final String[] classNamesStr = new String[] {
            "byte.class", "boolean.class", "char.class", "short.class",
            "int.class",  "long.class", "float.class", "double.class",
            "Object[0].getClass()"
        };
        final boolean isRetransformSupported =
                instrumentation.isRetransformClassesSupported();
        boolean isUOEthrown = false;

        StringBuffer errorMessages = new StringBuffer();

        for (int i = 0; i < classesArray.length; i++) {
            try {
                instrumentation.retransformClasses(classesArray[i]);
                errorMessages.append("retransformClasses() does not "
                        + "throw UnmodifiableClassException for "
                        + "unmodifiable class " + classNamesStr[i] + "; ");
            } catch (UnsupportedOperationException ex) {
                if (isRetransformSupported) {
                    errorMessages.append("Unexpected exception: " + ex + "; ");
                } else {
                    isUOEthrown = true;
                }
            } catch (UnmodifiableClassException ok) {
                //expected
            } catch (Throwable e) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected exception: " + e);
                return Status.passed("Testing result passed to test");
            }
        }

        if (errorMessages.length() > 0) {
            setStatus(Status.FAILED, testCaseID, errorMessages.toString());
            return Status.passed("Testing result passed to test");
        }

        if (isUOEthrown) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, UnsupportedOperationException is thrown");
        } else {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, UnmodifiableClassException is thrown as expected");
        }
        return Status.passed("OKAY");
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that:
     *  1) the transformer is called when A1 class is retransformed
     *  2) retransformation of a class does not cause its initializers to be run
     *  3) the value of static field will remain as it was prior to the call
     *  4) instance of the retransformed class is not affected.
     * <br><b>Expected results</b>: after class retransformation the value of static field
     * did not change, retransformed static and instance methods will return new values,
     * and value of instance field will have new value only on new instance.
     */
    public Status Instrumentation222() {
        final String testCaseID = "Instrumentation222";

        // check if VM support retransformation
        if (!instrumentation.isRetransformClassesSupported()) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, the current JVM configuration does not "
                    + "support retransformation of classes");
            return Status.passed("Testing result passed to test");
        }

        // create instance of original class
        A1 obj = new A1();

        // check if the A1 class modifiable
        if (!instrumentation.isModifiableClass(obj.getClass())) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, the A1 class is not modifiable");
            return Status.passed("Testing result passed to test");
        }

        // create transformer
        ClassFileTransformer transformer = new ARetransformer(
                retransformedPackageName + "." + "A1");

        try {
            // add transformer
            try {
                instrumentation.addTransformer(transformer, true);
            } catch (UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException while "
                        + "adding transformer: " + uoe);
                return Status.passed("Testing result passed to test");
            }

            // make retransformation
            try {
                instrumentation.retransformClasses(obj.getClass());
            } catch (UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException while "
                        + "retransformation: " + uoe);
                return Status.passed("Testing result passed to test");
            } catch (UnmodifiableClassException uce) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnmodifiableClassException while "
                        + "retransformation: " + uce);
                return Status.passed("Testing result passed to test");
            } catch (Throwable e) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected exception while retransformation: " + e);
                return Status.passed("Testing result passed to test");
            }
        } finally {
            // do not forget to remove transformer
            instrumentation.removeTransformer(transformer);
        }

        // check retransformation results for initial object
        //   - instanceField and staticField should have initial values
        //   - instanceMethod() and staticMethod() should return new values
        if (obj.instanceField != 22 || obj.staticField != 34
                || obj.instanceMethod() != 4 || obj.staticMethod() != 5) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected result of retransformation for the A1 class: "
                    + "obj.instanceField=" + obj.instanceField + "  "
                    + "A1.staticField=" + A1.staticField + "  "
                    + "obj.instanceMethod()=" + obj.instanceMethod() + "  "
                    + "A1.staticMethod()=" + A1.staticMethod());
            return Status.passed("Testing result passed to test");
        }

        // create new instance after retransformation
        A1 newObj = new A1();

        // check retransformation results for new instance
        //   - staticField should have initial value
        //   - instanceField should have new value
        //   - instanceMethod() and staticMethod() should return new values
        if (newObj.instanceField != 44 || A1.staticField != 34
                || newObj.instanceMethod() != 4 || A1.staticMethod() != 5) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected result of retransformation for the A1 class: "
                    + "newObj.instanceField=" + newObj.instanceField + "  "
                    + "A1.staticField=" + A1.staticField + "  "
                    + "newObj.instanceMethod()=" + newObj.instanceMethod() + "  "
                    + "A1.staticMethod()=" + A1.staticMethod());
            return Status.passed("Testing result passed to test");
        }

        setStatus(Status.PASSED, testCaseID,
                "OKAY, retransformation of the A1 class completed with expected result");
        return Status.passed("OKAY");
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that:
     *  1) the transformer is called when A2 class is retransformed
     *  2) the retransformation may change method bodies, the constant pool and attributes
     *  3) the value of static field will remain as it was prior to the call
     *  4) instance of the retransformed class is not affected.
     * <br><b>Expected results</b>: after class retransformation the value of static field
     * did not change, retransformed static and instance methods will return new values,
     * and value of instance field will have new value only on new instance.
     */
    public Status Instrumentation223() {
        final String testCaseID = "Instrumentation223";

        // check if VM support retransformation
        if (!instrumentation.isRetransformClassesSupported()) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, the current JVM configuration does not "
                    + "support retransformation of classes");
            return Status.passed("Testing result passed to test");
        }

        // create instance of original class
        A2 obj = new A2();

        // check if the A2 class modifiable
        if (!instrumentation.isModifiableClass(obj.getClass())) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, the A2 class is not modifiable");
            return Status.passed("Testing result passed to test");
        }

        // create transformer
        ClassFileTransformer transformer = new ARetransformer(
                retransformedPackageName + "." + "A2");

        try {
            // add transformer
            try {
                instrumentation.addTransformer(transformer, true);
            } catch (UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException while "
                        + "adding transformer: " + uoe);
                return Status.passed("Testing result passed to test");
            }

            // make retransformation
            try {
                instrumentation.retransformClasses(obj.getClass());
            } catch (UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException while "
                        + "retransformation: " + uoe);
                return Status.passed("Testing result passed to test");
            } catch (UnmodifiableClassException uce) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnmodifiableClassException while "
                        + "retransformation: " + uce);
                return Status.passed("Testing result passed to test");
            } catch (Throwable e) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected exception while retransformation: " + e);
                return Status.passed("Testing result passed to test");
            }
        } finally {
            // do not forget to remove transformer
            instrumentation.removeTransformer(transformer);
        }

        // check retransformation results for initial object
        //   - instanceField and staticField should have initial values
        //   - instanceMethod() and staticMethod() should return new values
        if (obj.instanceField != 22 || obj.staticField != 34
                || obj.instanceMethod() != 4 || obj.staticMethod() != 5) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected result of retransformation for the A2 class: "
                    + "obj.instanceField=" + obj.instanceField + "  "
                    + "A2.staticField=" + A2.staticField + "  "
                    + "obj.instanceMethod()=" + obj.instanceMethod() + "  "
                    + "A2.staticMethod()=" + A2.staticMethod());
            return Status.passed("Testing result passed to test");
        }

        // create new instance after retransformation
        A2 newObj = new A2();

        // check retransformation results for new instance
        //   - staticField should have initial value
        //   - instanceField should have new value
        //   - instanceMethod() and staticMethod() should return new values
        if (newObj.instanceField != 44 || A2.staticField != 34
                || newObj.instanceMethod() != 4 || A2.staticMethod() != 5) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected result of retransformation for the A2 class: "
                    + "newObj.instanceField=" + newObj.instanceField + "  "
                    + "A2.staticField=" + A2.staticField + "  "
                    + "newObj.instanceMethod()=" + newObj.instanceMethod() + "  "
                    + "A2.staticMethod()=" + A2.staticMethod());
            return Status.passed("Testing result passed to test");
        }

        setStatus(Status.PASSED, testCaseID,
                "OKAY, retransformation of the A2 class completed with expected result");
        return Status.passed("OKAY");
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * must not remove field.
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown.
     */
    public Status Instrumentation224() {
        final String testCaseID = "Instrumentation224";

        // create instance of original class
        A3 obj = new A3();

        return checkUnallowedRetransformation(obj, testCaseID);
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * must not add field.
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown.
     */
    public Status Instrumentation225() {
        final String testCaseID = "Instrumentation225";

        // create instance of original class
        A4 obj = new A4();

        return checkUnallowedRetransformation(obj, testCaseID);
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * must not remove static field.
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown.
     */
    public Status Instrumentation226() {
        final String testCaseID = "Instrumentation226";

        // create instance of original class
        A5 obj = new A5();

        return checkUnallowedRetransformation(obj, testCaseID);
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * must not add static field.
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown.
     */
    public Status Instrumentation227() {
        final String testCaseID = "Instrumentation227";

        // create instance of original class
        A6 obj = new A6();

        return checkUnallowedRetransformation(obj, testCaseID);
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * must not remove method.
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown.
     */
    public Status Instrumentation228() {
        final String testCaseID = "Instrumentation228";

        // create instance of original class
        A7 obj = new A7();

        return checkUnallowedRetransformation(obj, testCaseID);
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * must not add method.
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown.
     */
    public Status Instrumentation229() {
        final String testCaseID = "Instrumentation229";

        // create instance of original class
        A8 obj = new A8();

        return checkUnallowedRetransformation(obj, testCaseID);
    }


    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * must not remove static method.
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown.
     */
    public Status Instrumentation230() {
        final String testCaseID = "Instrumentation230";

        // create instance of original class
        A9 obj = new A9();

        return checkUnallowedRetransformation(obj, testCaseID);
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * must not add static method.
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown.
     */
    public Status Instrumentation231() {
        final String testCaseID = "Instrumentation231";

        // create instance of original class
        A10 obj = new A10();

        return checkUnallowedRetransformation(obj, testCaseID);
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * must not change field access modifiers.
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown.
     */
    public Status Instrumentation232() {
        final String testCaseID = "Instrumentation232";

        // create instance of original class
        A11 obj = new A11();

        return checkUnallowedRetransformation(obj, testCaseID);
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * must not change field modifiers.
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown.
     */
    public Status Instrumentation233() {
        final String testCaseID = "Instrumentation233";

        // create instance of original class
        A12 obj = new A12();

        return checkUnallowedRetransformation(obj, testCaseID);
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * must not change method access modifiers.
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown.
     */
    public Status Instrumentation234() {
        final String testCaseID = "Instrumentation234";

        // create instance of original class
        A13 obj = new A13();

        return checkUnallowedRetransformation(obj, testCaseID);
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * must not change sychronized method modifier.
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown.
     */
    public Status Instrumentation235() {
        final String testCaseID = "Instrumentation235";

        // create instance of original class
        A14 obj = new A14();

        return checkUnallowedRetransformation(obj, testCaseID);
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * must not change class inheritance.
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown.
     */
    public Status Instrumentation236() {
        final String testCaseID = "Instrumentation236";

        // create instance of original class
        A15 obj = new A15();

        return checkUnallowedRetransformation(obj, testCaseID);
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * fails if the retransformation data did not contain a valid class.
     * <br><b>Expected results</b>: ClassFormatError should be thrown.
     */
    public Status Instrumentation237() {
        final String testCaseID = "Instrumentation237";

        // check if VM support retransformation
        if (!instrumentation.isRetransformClassesSupported()) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, the current JVM configuration does not "
                    + "support retransformation of classes");
            return Status.passed("Testing result passed to test");
        }

        // create instance of original class
        B1 obj = new B1();

        // check if the B1 class modifiable
        if (!instrumentation.isModifiableClass(obj.getClass())) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, the B1 class is not modifiable");
            return Status.passed("Testing result passed to test");
        }

        // create transformer
        ClassFileTransformer transformer = new BRetransformer(
                retransformedPackageName + "." + "B1");

        try {
            // add transformer
            try {
                instrumentation.addTransformer(transformer, true);
            } catch (UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException while "
                        + "adding transformer: " + uoe);
                return Status.passed("Testing result passed to test");
            }

            // make retransformation
            try {
                instrumentation.retransformClasses(obj.getClass());

                setStatus(Status.FAILED, testCaseID,
                        "ClassFormatError wasn't thrown");
                return Status.passed("Testing result passed to test");
            } catch (ClassFormatError ok) {
                // expected
            } catch (UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException while "
                        + "retransformation: " + uoe);
                return Status.passed("Testing result passed to test");
            } catch (UnmodifiableClassException uce) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnmodifiableClassException while "
                        + "retransformation: " + uce);
                return Status.passed("Testing result passed to test");
            } catch (Throwable e) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected exception while retransformation: " + e);
                return Status.passed("Testing result passed to test");
            }
        } finally {
            // do not forget to remove transformer
            instrumentation.removeTransformer(transformer);
        }

        setStatus(Status.PASSED, testCaseID,
                "OKAY, retransformation of the B1 class correctly failed " +
                "with ClassFormatError");
        return Status.passed("OKAY");
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * fails if the name in the class file is not equal to the name of the class.
     * <br><b>Expected results</b>: NoClassDefFoundError should be thrown.
     */
    public Status Instrumentation238() {
        final String testCaseID = "Instrumentation238";

        // check if VM support retransformation
        if (!instrumentation.isRetransformClassesSupported()) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, the current JVM configuration does not "
                    + "support retransformation of classes");
            return Status.passed("Testing result passed to test");
        }

        // create instance of original class
        C1 obj = new C1();

        // check if the C1 class modifiable
        if (!instrumentation.isModifiableClass(obj.getClass())) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, the C1 class is not modifiable");
            return Status.passed("Testing result passed to test");
        }

        // create transformer
        ClassFileTransformer transformer = new CRetransformer(
                retransformedPackageName + "." + "C1");

        try {
            // add transformer
            try {
                instrumentation.addTransformer(transformer, true);
            } catch (UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException while "
                        + "adding transformer: " + uoe);
                return Status.passed("Testing result passed to test");
            }

            // make retransformation
            try {
                instrumentation.retransformClasses(obj.getClass());

                setStatus(Status.FAILED, testCaseID,
                        "NoClassDefFoundError wasn't thrown");
                return Status.passed("Testing result passed to test");
            } catch (NoClassDefFoundError ok) {
                // expected
            } catch (UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException while "
                        + "retransformation: " + uoe);
                return Status.passed("Testing result passed to test");
            } catch (UnmodifiableClassException uce) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnmodifiableClassException while "
                        + "retransformation: " + uce);
                return Status.passed("Testing result passed to test");
            } catch (Throwable e) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected exception while retransformation: " + e);
                return Status.passed("Testing result passed to test");
            }
        } finally {
            // do not forget to remove transformer
            instrumentation.removeTransformer(transformer);
        }

        setStatus(Status.PASSED, testCaseID,
                "OKAY, retransformation of the C1 class correctly failed " +
                "with NoClassDefFoundError");
        return Status.passed("OKAY");
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     * The test registers the supplied transformer and verifies that retransformation
     * fails if the class file version numbers are not supported.
     * <br><b>Expected results</b>: UnsupportedClassVersionError should be thrown.
     */
    public Status Instrumentation239() {
        final String testCaseID = "Instrumentation239";

        // check if VM support retransformation
        if (!instrumentation.isRetransformClassesSupported()) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, the current JVM configuration does not "
                    + "support retransformation of classes");
            return Status.passed("Testing result passed to test");
        }

        // create instance of original class
        D1 obj = new D1();

        // check if the D1 class modifiable
        if (!instrumentation.isModifiableClass(obj.getClass())) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, the D1 class is not modifiable");
            return Status.passed("Testing result passed to test");
        }

        // create transformer
        ClassFileTransformer transformer = new DRetransformer(
                retransformedPackageName + "." + "D1");

        try {
            // add transformer
            try {
                instrumentation.addTransformer(transformer, true);
            } catch (UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException while "
                        + "adding transformer: " + uoe);
                return Status.passed("Testing result passed to test");
            }

            // make retransformation
            try {
                instrumentation.retransformClasses(obj.getClass());

                setStatus(Status.FAILED, testCaseID,
                        "UnsupportedClassVersionError wasn't thrown");
                return Status.passed("Testing result passed to test");
            } catch (UnsupportedClassVersionError ok) {
                // expected
            } catch (UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException while "
                        + "retransformation: " + uoe);
                return Status.passed("Testing result passed to test");
            } catch (UnmodifiableClassException uce) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnmodifiableClassException while "
                        + "retransformation: " + uce);
                return Status.passed("Testing result passed to test");
            } catch (Throwable e) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected exception while retransformation: " + e);
                return Status.passed("Testing result passed to test");
            }
        } finally {
            // do not forget to remove transformer
            instrumentation.removeTransformer(transformer);
        }

        setStatus(Status.PASSED, testCaseID,
                "OKAY, retransformation of the D1 class correctly failed " +
                "with UnsupportedClassVersionError");
        return Status.passed("OKAY");
    }

    /**
     * Assertion testing for
     *   void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix)
     *
     * NullPointerException - if passed a null transformer.
     *
     * <br><b>Expected results</b>: NullPointerException should be thrown.
     */
    public Status Instrumentation240() {
        final String testCaseID = "Instrumentation240";

        try {
            instrumentation.setNativeMethodPrefix(
                    (ClassFileTransformer) null, "someprefix");

            setStatus(Status.FAILED, testCaseID,
                    "NullPointerException was NOT thrown");
            return Status.passed("Testing result passed to test");
        } catch (UnsupportedOperationException uoe) {
            if (!instrumentation.isNativeMethodPrefixSupported()) {
                setStatus(Status.PASSED, testCaseID,
                        "OKAY, the current JVM configuration does not "
                        + "allow setting a native method prefix");
                return Status.passed("Testing result passed to test");
            } else {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException: " + uoe);
                return Status.passed("Testing result passed to test");
            }
        } catch (NullPointerException ok) {
            // expected
        } catch (Throwable e) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        }

        setStatus(Status.PASSED, testCaseID, "OKAY");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing for
     *   void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix)
     *
     * UnsupportedOperationException - if the current configuration of the JVM
     * does not allow setting a native method prefix (isNativeMethodPrefixSupported()
     * is false).
     *
     * <br><b>Expected results</b>: if setting a native method prefix is not
     * allowed then UnsupportedOperationException must be thrown; no exception
     * should be thrown otherwise.
     */
    public Status Instrumentation241() {
        final String testCaseID = "Instrumentation241";

        ClassFileTransformer tf = null;
        try {
            tf = new TestClassFileTransformer();
            instrumentation.addTransformer(tf);
            instrumentation.setNativeMethodPrefix(tf, "someprefix");

            if (!instrumentation.isNativeMethodPrefixSupported()) {
                setStatus(Status.FAILED, testCaseID,
                        "UnsupportedOperationException was NOT thrown "
                        + "when setting a native method prefix is NOT "
                        + "allowed");
                return Status.passed("Testing result passed to test");
            }
        } catch (UnsupportedOperationException uoe) {
            if (instrumentation.isNativeMethodPrefixSupported()) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException when setting"
                        + " a native method prefix is allowed: " + uoe);
                return Status.passed("Testing result passed to test");
            }
        } catch (Throwable e) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        } finally {
            if (tf != null) {
                instrumentation.removeTransformer(tf);
            }
        }

        setStatus(Status.PASSED, testCaseID, "OKAY");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing for
     *   void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix)
     *
     * IllegalArgumentException - if the transformer is not registered.
     *
     * <br><b>Expected results</b>: IllegalArgumentException should be thrown
     */
    public Status Instrumentation242() {
        final String testCaseID = "Instrumentation242";

        ClassFileTransformer tf = null;
        try {
            tf = new TestClassFileTransformer();
            instrumentation.setNativeMethodPrefix(tf, "someprefix");

            setStatus(Status.FAILED, testCaseID,
                    "IllegalArgumentException was NOT thrown");
            return Status.passed("Testing result passed to test");
        } catch (IllegalArgumentException ok) {
            // expected
        } catch (UnsupportedOperationException uoe) {
            if (!instrumentation.isNativeMethodPrefixSupported()) {
                setStatus(Status.PASSED, testCaseID,
                        "OKAY, the current JVM configuration does not "
                        + "allow setting a native method prefix");
                return Status.passed("Testing result passed to test");
            } else {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException: " + uoe);
                return Status.passed("Testing result passed to test");
            }
        } catch (Throwable e) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        }

        setStatus(Status.PASSED, testCaseID, "OKAY");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing for
     *   void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix)
     *
     *  For automatic resolution, the JVM will attempt:<br>
     *   <code>method(wrapped_foo) -> nativeImplementation(wrapped_foo)</code>.
     */
    public Status Instrumentation243() {
        final String testCaseID = "Instrumentation243";

        if (!instrumentation.isNativeMethodPrefixSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, skipping the test "
                    + "because the current JVM configuration does NOT allow "
                    + "setting a native method prefix");
            return Status.passed("Testing result passed to test");
        }

        final String loadLibStatus = getLoadLibraryStatus();
        if (loadLibStatus != null) {
            setStatus(Status.FAILED, testCaseID, loadLibStatus);
            return Status.passed("Testing result passed to test");
        }

        final String clsName = "NMPA1";
        final String methName = "nmpa1";
        final String prefix = "wrapped";

        ClassFileTransformer tf = null;
        try {
            tf = new TestClassFileTransformer(clsName);
            instrumentation.addTransformer(tf);
            instrumentation.setNativeMethodPrefix(tf, prefix);

            NMPA1 obj = new NMPA1();

            final int expValue = 11; // from native wrappednmpa1
            final int retValue = obj.nmpa1();
            if (retValue != expValue) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected returned value from method '" + methName
                        + "': " + retValue + " (expected " + expValue + ")");
                return Status.passed("Testing result passed to test");
            }
        } catch (Throwable e) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        } finally {
            if (tf != null) {
                instrumentation.removeTransformer(tf);
            }
        }

        setStatus(Status.PASSED, testCaseID, "OKAY");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing for
     *   void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix)
     *
     * When automatic resolution fails, the resolution will be retried with the
     * specified prefix deleted from the implementation name, yielding the correct
     * resolution:<br>
     *    <code>method(wrapped_foo) -> nativeImplementation(foo)</code>
     */
    public Status Instrumentation244() {
        final String testCaseID = "Instrumentation244";

        if (!instrumentation.isNativeMethodPrefixSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, skipping the test "
                    + "because the current JVM configuration does NOT allow "
                    + "setting a native method prefix");
            return Status.passed("Testing result passed to test");
        }

        final String loadLibStatus = getLoadLibraryStatus();
        if (loadLibStatus != null) {
            setStatus(Status.FAILED, testCaseID, loadLibStatus);
            return Status.passed("Testing result passed to test");
        }

        final String clsName = "NMPA2";
        final String methName = "nmpa2";
        final String prefix = "wrapped";

        ClassFileTransformer tf = null;
        try {
            tf = new TestClassFileTransformer(clsName);
            instrumentation.addTransformer(tf);
            instrumentation.setNativeMethodPrefix(tf, prefix);

            NMPA2 obj = new NMPA2();

            final int expValue = 2;
            final int retValue = obj.nmpa2();
            if (retValue != expValue) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected returned value from method '" + methName
                        + "': " + retValue + " (expected " + expValue + ")");
                return Status.passed("Testing result passed to test");
            }
        } catch (Throwable e) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        } finally {
            if (tf != null) {
                instrumentation.removeTransformer(tf);
            }
        }

        setStatus(Status.PASSED, testCaseID, "OKAY");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing for
     *   void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix)
     *
     * For explicit resolution with the JNI function RegisterNatives, the JVM
     * will attempt:<br>
     *   <code>method(foo) -> nativeImplementation(foo)</code>.
     */
    public Status Instrumentation245() {
        final String testCaseID = "Instrumentation245";

        if (!instrumentation.isNativeMethodPrefixSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, skipping the test "
                    + "because the current JVM configuration does NOT allow "
                    + "setting a native method prefix");
            return Status.passed("Testing result passed to test");
        }

        final String loadLibStatus = getLoadLibraryStatus();
        if (loadLibStatus != null) {
            setStatus(Status.FAILED, testCaseID, loadLibStatus);
            return Status.passed("Testing result passed to test");
        }

        final String clsName = "NMPA3";
        final String methName = "nmpa3";
        final String prefix = "wrapped";
        ClassFileTransformer tf = null;

        try {
            tf = new TestClassFileTransformer(clsName);
            instrumentation.addTransformer(tf);
            instrumentation.setNativeMethodPrefix(tf, prefix);

            NMPA3 obj = new NMPA3();

            if (!NMPA3.regMeth()) {
                setStatus(Status.FAILED, testCaseID, "explicit resolution with "
                        + "RegisterNatives failed the association: method("
                        + methName + ") -> nativeImplementation(" + methName
                        + ")");
                return Status.passed("Testing result passed to test");
            }

            final int expValue = 3;
            final int retValue = obj.nmpa3();
            if (retValue != expValue) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected returned value from method '" + methName
                        + "': " + retValue + " (expected " + expValue + ")");
                return Status.passed("Testing result passed to test");
            }
        } catch (Throwable e) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        } finally {
            if (tf != null) {
                instrumentation.removeTransformer(tf);
            }
        }

        setStatus(Status.PASSED, testCaseID, "OKAY");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing for
     *   void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix)
     *
     * When explicit resolution with the JNI function RegisterNatives fails,
     * the resolution will be retried with the specified prefix prepended
     * to the method name, yielding the correct resolution:<br>
     *   <code>method(wrapped_foo) -> nativeImplementation(foo)</code>
     */
    public Status Instrumentation246() {
        final String testCaseID = "Instrumentation246";

        if (!instrumentation.isNativeMethodPrefixSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, skipping the test "
                    + "because the current JVM configuration does NOT allow "
                    + "setting a native method prefix");
            return Status.passed("Testing result passed to test");
        }

        final String loadLibStatus = getLoadLibraryStatus();
        if (loadLibStatus != null) {
            setStatus(Status.FAILED, testCaseID, loadLibStatus);
            return Status.passed("Testing result passed to test");
        }

        final String clsName = "NMPA4";
        final String methName = "nmpa4";
        final String prefix = "wrapped";
        ClassFileTransformer tf = null;

        try {
            tf = new TestClassFileTransformer(clsName);
            instrumentation.addTransformer(tf);
            instrumentation.setNativeMethodPrefix(tf, prefix);

            NMPA4 obj = new NMPA4();

            if (!NMPA4.regMeth()) {
                setStatus(Status.FAILED, testCaseID, "The resolution was NOT "
                        + "retried with the '"+ prefix + "' prefix prepended "
                        + "to the '" + methName + "' method name");
                return Status.passed("Testing result passed to test");
            }

            final int expValue = 4;
            final int retValue = obj.nmpa4();
            if (retValue != expValue) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected returned value from method '" + methName
                        + "': " + retValue + " (expected " + expValue + ")");
                return Status.passed("Testing result passed to test");
            }
        } catch (Throwable e) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        } finally {
            if (tf != null) {
                instrumentation.removeTransformer(tf);
            }
        }

        setStatus(Status.PASSED, testCaseID, "OKAY");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing for
     *   void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix)
     *
     * Since each <code>ClassFileTransformer</code> can do its own transformation of
     * the bytecodes, more than one layer of wrappers may be applied. Thus each
     * transformer needs its own prefix. Since transformations are applied in order,
     * the prefixes, if applied, will be applied in the same order (see addTransformer).
     * Thus if three transformers applied wrappers, <code>foo</code> might become
     * <code>$trans3_$trans2_$trans1_foo</code>.
     */
    public Status Instrumentation247() {
        final String testCaseID = "Instrumentation247";

        if (!instrumentation.isNativeMethodPrefixSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, skipping the test "
                    + "because the current JVM configuration does NOT allow "
                    + "setting a native method prefix");
            return Status.passed("Testing result passed to test");
        }

        final String loadLibStatus = getLoadLibraryStatus();
        if (loadLibStatus != null) {
            setStatus(Status.FAILED, testCaseID, loadLibStatus);
            return Status.passed("Testing result passed to test");
        }

        final String clsName = "NMPA5";
        final String methName = "nmpa5";
        final String prefix1 = "prefix1";
        final String prefix2 = "prefix2";
        final String prefix3 = "prefix3";

        ClassFileTransformer tf1 = null;
        ClassFileTransformer tf2 = null;
        ClassFileTransformer tf3 = null;
        try {
            tf1 = new TestClassFileTransformer(clsName);
            instrumentation.addTransformer(tf1);

            tf2 = new TestClassFileTransformer(clsName);
            instrumentation.addTransformer(tf2);

            tf3 = new TestClassFileTransformer(clsName);
            instrumentation.addTransformer(tf3);

            // an order of applying the prefixes is defined by addTransformer()
            // only, therefore different order of setNativeMethodPrefix calls
            // below should not have any effect on it
            instrumentation.setNativeMethodPrefix(tf2, prefix2);
            instrumentation.setNativeMethodPrefix(tf3, prefix3);
            instrumentation.setNativeMethodPrefix(tf1, prefix1);

            NMPA5 obj = new NMPA5();

            final int expValue = 5;
            final int retValue = obj.nmpa5();
            if (retValue != expValue) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected returned value from method '" + methName
                        + "': " + retValue + " (expected " + expValue + ")");
                return Status.passed("Testing result passed to test");
            }
        } catch (Throwable e) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        } finally {
            if (tf1 != null) {
                instrumentation.removeTransformer(tf1);
            }
            if (tf2 != null) {
                instrumentation.removeTransformer(tf2);
            }
            if (tf3 != null) {
                instrumentation.removeTransformer(tf3);
            }
        }

        setStatus(Status.PASSED, testCaseID, "OKAY");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing for
     *   void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix)
     *
     * But if, say, the second transformer did not apply a wrapper to foo it would
     * be just $trans3_$trans1_foo.
     */
    public Status Instrumentation248() {
        final String testCaseID = "Instrumentation248";

        if (!instrumentation.isNativeMethodPrefixSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, skipping the test "
                    + "because the current JVM configuration does NOT allow "
                    + "setting a native method prefix");
            return Status.passed("Testing result passed to test");
        }

        final String loadLibStatus = getLoadLibraryStatus();
        if (loadLibStatus != null) {
            setStatus(Status.FAILED, testCaseID, loadLibStatus);
            return Status.passed("Testing result passed to test");
        }

        final String clsName = "NMPA6";
        final String methName = "nmpa6";
        final String prefix1 = "First";
        final String prefix3 = "Third";

        ClassFileTransformer tf1 = null;
        ClassFileTransformer tf2 = null;
        ClassFileTransformer tf3 = null;
        try {
            tf1 = new TestClassFileTransformer(clsName);
            instrumentation.addTransformer(tf1);
            instrumentation.setNativeMethodPrefix(tf1, prefix1);

            // Note that second transformer DOES NOT apply a wrapper
            tf2 = new TestClassFileTransformer(clsName);
            instrumentation.addTransformer(tf2);

            tf3 = new TestClassFileTransformer(clsName);
            instrumentation.addTransformer(tf3);
            instrumentation.setNativeMethodPrefix(tf3, prefix3);

            NMPA6 obj = new NMPA6();

            final int expValue = 6;
            final int retValue = obj.nmpa6();
            if (retValue != expValue) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected returned value from method '" + methName
                        + "': " + retValue + " (expected " + expValue + ")");
                return Status.passed("Testing result passed to test");
            }
        } catch (Throwable e) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        } finally {
            if (tf1 != null) {
                instrumentation.removeTransformer(tf1);
            }
            if (tf2 != null) {
                instrumentation.removeTransformer(tf2);
            }
            if (tf3 != null) {
                instrumentation.removeTransformer(tf3);
            }
        }

        setStatus(Status.PASSED, testCaseID, "OKAY");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing for
     *   void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix)
     *
     * To be able to efficiently determine the sequence of prefixes,
     * an intermediate prefix is only applied if its non-native wrapper exists.
     */
    public Status Instrumentation249() {
        final String testCaseID = "Instrumentation249";

        if (!instrumentation.isNativeMethodPrefixSupported()) {
            setStatus(Status.PASSED, testCaseID, "OKAY, skipping the test "
                    + "because the current JVM configuration does NOT allow "
                    + "setting a native method prefix");
            return Status.passed("Testing result passed to test");
        }

        final String loadLibStatus = getLoadLibraryStatus();
        if (loadLibStatus != null) {
            setStatus(Status.FAILED, testCaseID, loadLibStatus);
            return Status.passed("Testing result passed to test");
        }

        final String clsName = "NMPA7";
        final String methName = "nmpa7";
        final String prefix1 = "pref1";
        final String prefix2 = "pref2";
        final String prefix3 = "pref3";

        ClassFileTransformer tf1 = null;
        ClassFileTransformer tf2 = null;
        ClassFileTransformer tf3 = null;
        try {
            tf1 = new TestClassFileTransformer(clsName);
            instrumentation.addTransformer(tf1);
            instrumentation.setNativeMethodPrefix(tf1, prefix1);

            tf2 = new TestClassFileTransformer(clsName);
            instrumentation.addTransformer(tf2);
            instrumentation.setNativeMethodPrefix(tf2, prefix2);

            tf3 = new TestClassFileTransformer(clsName);
            instrumentation.addTransformer(tf3);
            instrumentation.setNativeMethodPrefix(tf3, prefix3);

            NMPA7 obj = new NMPA7();

            final int expValue = 7;
            final int retValue = obj.nmpa7();
            if (retValue != expValue) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected returned value from method '" + methName
                        + "': " + retValue + " (expected " + expValue + ")");
                return Status.passed("Testing result passed to test");
            }
        } catch (Throwable e) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected exception was thrown: " + e);
            return Status.passed("Testing result passed to test");
        } finally {
            if (tf1 != null) {
                instrumentation.removeTransformer(tf1);
            }
            if (tf2 != null) {
                instrumentation.removeTransformer(tf2);
            }
            if (tf3 != null) {
                instrumentation.removeTransformer(tf3);
            }
        }

        setStatus(Status.PASSED, testCaseID, "OKAY");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing for
     *   void retransformClasses(Class<?>... classes)
     *   void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
     *
     * The test registers the supplied transformer and verifies that:
     *  1) the transformer is called when test's classes A16 and A17 are retransformed
     *  2) retransformation of test's classes does not cause theirs initializers to be run
     *  3) the value of static fields will remain as it was prior to the call
     *
     * <br><b>Expected results</b>: after class retransformation the value of static fields
     * did not change, retransformed static and instance methods will return new values,
     * and value of instance fields will have new value only on new instances.
     */
    public Status Instrumentation251() {
        final String testCaseID = "Instrumentation251";

        // check if VM support retransformation
        if (!instrumentation.isRetransformClassesSupported()) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, the current JVM configuration does not "
                    + "support retransformation of classes");
            return Status.passed("Testing result passed to test");
        }

        // create instances of original classes
        A16 obj  = new A16();
        A17 obj2 = new A17();

        // check that the A16 and A17 classes are modifiable
        if (!instrumentation.isModifiableClass(obj.getClass())
                || !instrumentation.isModifiableClass(obj2.getClass())) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, the A16 or/and A17 class not modifiable");
            return Status.passed("Testing result passed to test");
        }

        ClassNamePair[] cnpArray = {
            new ClassNamePair(toretransformPackageName + "." + "A16",
                              retransformedPackageName + "." + "A16"),
            new ClassNamePair(toretransformPackageName + "." + "A17",
                              retransformedPackageName + "." + "A17")
        };

        // create transformer
        ClassFileTransformer transformer =
                new MultiClassRetransformer(cnpArray);

        try {
            // add transformer
            try {
                instrumentation.addTransformer(transformer, true);
            } catch (UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException while "
                        + "adding transformer: " + uoe);
                return Status.passed("Testing result passed to test");
            }

            // make retransformation
            try {
                instrumentation.retransformClasses(
                        obj.getClass(), obj2.getClass());
            } catch (UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException while "
                        + "retransformation: " + uoe);
                return Status.passed("Testing result passed to test");
            } catch (UnmodifiableClassException uce) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnmodifiableClassException while "
                        + "retransformation: " + uce);
                return Status.passed("Testing result passed to test");
            } catch (Throwable e) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected exception while retransformation: " + e);
                return Status.passed("Testing result passed to test");
            }
        } finally {
            // do not forget to remove transformer
            instrumentation.removeTransformer(transformer);
        }

        // check retransformation results for initial instances
        //   - instanceField and staticField should have initial values
        //   - instanceMethod() and staticMethod() should return new values
        if (obj.instanceField != 22 || obj.staticField != 34
                || obj.instanceMethod() != 4 || obj.staticMethod() != 5) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected result of retransformation for the A16 class: "
                    + "obj.instanceField=" + obj.instanceField + "  "
                    + "A16.staticField=" + A16.staticField + "  "
                    + "obj.instanceMethod()=" + obj.instanceMethod() + "  "
                    + "A16.staticMethod()=" + A16.staticMethod());
            return Status.passed("Testing result passed to test");
        } else if (obj2.instanceField != 2222 || obj2.staticField != 3334
                || obj2.instanceMethod() != 444 || obj2.staticMethod() != 555) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected result of retransformation for the A17 class: "
                    + "obj2.instanceField=" + obj2.instanceField + "  "
                    + "A17.staticField=" + A17.staticField + "  "
                    + "obj2.instanceMethod()=" + obj2.instanceMethod() + "  "
                    + "A17.staticMethod()=" + A17.staticMethod());
            return Status.passed("Testing result passed to test");
        }

        // create new instances after retransformation
        A16 newObj  = new A16();
        A17 newObj2 = new A17();

        // check retransformation results for new instances
        //   - staticField should have initial value
        //   - instanceField should have new value
        //   - instanceMethod() and staticMethod() should return new values
        if (newObj.instanceField != 44 || A16.staticField != 34
                || newObj.instanceMethod() != 4 || A16.staticMethod() != 5) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected result of retransformation for the A16 class: "
                    + "newObj.instanceField=" + newObj.instanceField + "  "
                    + "A16.staticField=" + A16.staticField + "  "
                    + "newObj.instanceMethod()=" + newObj.instanceMethod()
                    + "  " + "A16.staticMethod()=" + A16.staticMethod());
            return Status.passed("Testing result passed to test");
        } else if (newObj2.instanceField != 4444 || A17.staticField != 3334
                || newObj2.instanceMethod() != 444 || A17.staticMethod() != 555) {
            setStatus(Status.FAILED, testCaseID,
                    "Unexpected result of retransformation for the A17 class: "
                    + "newObj2.instanceField=" + newObj2.instanceField + "  "
                    + "A17.staticField=" + A17.staticField + "  "
                    + "newObj2.instanceMethod()=" + newObj2.instanceMethod()
                    + "  " + "A17.staticMethod()=" + A17.staticMethod());
            return Status.passed("Testing result passed to test");
        }

        setStatus(Status.PASSED, testCaseID,
                "OKAY, retransformations of classes A16 and A17 completed "
                + "with expected result");
        return Status.passed("Testing result passed to test");
    }

    //==========================================================================

    private JarFile loadJarFromURL(String urlname, String testCaseID) {
        JarFile jf = null;
        URL url = null;
        JarURLConnection c = null;

        try {
            url = new URL(urlname);
        } catch(MalformedURLException e) {
            setStatus(Status.FAILED, testCaseID, " parsing of URL failed unexpectedly: "
                    + urlname + "\n" + e);
            return jf;
        }
        try {
            c = (JarURLConnection) url.openConnection();
        } catch(IOException e) {
            setStatus(Status.FAILED, testCaseID, " openConnection() failed unexpectedly: "
                    + urlname + "\n" + e);
            return jf;
        }
        try {
            c.connect();
        } catch(IOException e) {
            setStatus(Status.FAILED, testCaseID, " connect() failed unexpectedly: "
                    + urlname + "\n" + e);
            return jf;
        }
        try {
            jf = c.getJarFile();
        } catch (IOException e) {
            setStatus(Status.FAILED, testCaseID, " getJarFile() failed unexpectedly: "
                    + urlname + "\n" + e);
            return jf;
        }

        if(jf == null) {
            setStatus(Status.FAILED, testCaseID, " getJarFile() returned unexpected null "
                    + "for URL = \""  + urlname +"\"");
            return jf;
        }
        return jf;
    }

    // check that the security manager really does not allow access
    // to the specified system property
    private Status checkGetPropertyAccess(String key, String testCaseID) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            try {
                security.checkPropertyAccess(key);
            } catch (SecurityException se) {
                setStatus(Status.PASSED, testCaseID,
                        "OKAY, security manager does not allow"
                      + "access to the system property: \""  + key + "\"");
                return Status.passed("PASSED");
            }
        }
        setStatus(Status.FAILED, testCaseID,
                "checkGetPropertyAccess: Unexpected SecurityException is thrown");
        return Status.passed("Testing result passed to test");
    }

    // check that the security manager really does not allow to get ClassLoader
    private Status checkGetClassLoader(String testCaseID) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            try {
                security.checkPermission(
                        new RuntimePermission("getClassLoader"));
            } catch (SecurityException se) {
                setStatus(Status.PASSED, testCaseID,
                        "OKAY, security manager does not allow to get ClassLoader");
                return Status.passed("PASSED");
            }
        }
        setStatus(Status.FAILED, testCaseID,
                "checkGetClassLoader: Unexpected SecurityException is thrown");
        return Status.passed("Testing result passed to test");
    }

    // check that the given class is loaded by the given loader
    private boolean checkLoadedByLoader(String testCaseID, final String name, final ClassLoader loader) {
        boolean correctly = false;
        try {
            Class cl = Class.forName(name);
            ClassLoader actual = cl.getClassLoader();
            String loaderName = (actual == null) ? "bootstrap"
                                                 : actual.toString();
            if (actual != loader) {
                setStatus(Status.FAILED, testCaseID, "\"" + name + "\" is incorrectly loaded by "
                        + loaderName + " class loader");
            } else {
                setStatus(Status.PASSED, testCaseID, "\"" + name + "\" is correctly loaded by "
                        + loaderName + " class loader");
                correctly = true;
            }
        } catch (Exception x) {
            setStatus(Status.FAILED, testCaseID, "checkLoadedByLoader unables to load " + name
                    + ", unexpected exception is thrown: " + x);
        }
        return correctly;
    }


    private boolean tryToResolveClass() {
        try {
            new ClassToResolve();
            return true;
        } catch (NoClassDefFoundError e) {
            return false;
        }
    }

    private boolean tryToResolveClass2() {
        try {
            new ClassToResolve2();
            return true;
        } catch (NoClassDefFoundError e) {
            return false;
        }
    }

    private boolean isJarFileAppendingSupportedBySCL() {
        try {
            ClassLoader.getSystemClassLoader().getClass().getDeclaredMethod(
                    "appendToClassPathForInstrumentation", String.class);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private boolean equalsClassPath(String s1, String s2) {
        if (s1 == null) {
            return (s2 == null);
        }
        if (s2 == null) {
            return false;
        }
        return s1.equals(s2);
    }

    /**
     * Checks that the retransformClasses method throws UnsupportedOperationException
     * when retransformation of the class tries to perform unallowed changes in the class
     * (for example, adding/removing/renaming fields or methods, changing the signatures
     * of methods, changing inheritance and so on).
     */
    private Status checkUnallowedRetransformation(Object initialObj, String testCaseID) {

        // check if current VM configuration supports retransformation
        if (!instrumentation.isRetransformClassesSupported()) {
            setStatus(Status.PASSED, testCaseID,
                    "OKAY, the current JVM configuration does not "
                    + "support retransformation of classes");
            return Status.passed("Testing result passed to test");
        }
        String className = initialObj.getClass().getSimpleName();

        // create transformer
        ClassFileTransformer transformer = new ARetransformer(
                retransformedPackageName + "." + className);

        try {
            // add transformer
            try {
                instrumentation.addTransformer(transformer, true);
            } catch (UnsupportedOperationException uoe) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnsupportedOperationException while "
                        + "adding transformer: " + uoe);
                return Status.passed("Testing result passed to test");
            }

            // make retransformation
            try {
                instrumentation.retransformClasses(initialObj.getClass());

                setStatus(Status.FAILED, testCaseID,
                        "UnsupportedOperationException wasn't thrown");
                return Status.passed("Testing result passed to test");
            } catch (UnsupportedOperationException ok) {
                // expected
            } catch (UnmodifiableClassException uce) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected UnmodifiableClassException while "
                        + "retransformation: " + uce);
                return Status.passed("Testing result passed to test");
            } catch (Throwable e) {
                setStatus(Status.FAILED, testCaseID,
                        "Unexpected exception while retransformation: " + e);
                return Status.passed("Testing result passed to test");
            }
        } finally {
            // do not forget to remove transformer
            instrumentation.removeTransformer(transformer);
        }

        setStatus(Status.PASSED, testCaseID,
                "OKAY, retransformation of the " + className +
                " class correctly failed with UnsupportedOperationException");
        return Status.passed("OKAY");
    }

} // end of InstrumentationAgent


class TestClassFileTransformerP extends TestClassFileTransformer {

    private String from = "";
    private String to = "";

    public TestClassFileTransformerP(String className, String from, String to) {
         super(className);
         this.from = from;
         this.to = to;
    }

    public byte[] transform(ClassLoader loader, String className,
                        Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                        byte[] classfileBuffer) throws IllegalClassFormatException {

        if (!isExpectedClass(className)) {
            return null;
        }
        byte[] ba = transformBytes(classfileBuffer, from, to);
        return ba;
    }
}


class TestClassFileTransformerN extends TestClassFileTransformer {

    private String from = "";
    private String to = "";

    public TestClassFileTransformerN(TransformationResult tr, String[] classNames,
                                     String from, String to) {
         super(tr, classNames);
         this.from = from;
         this.to = to;
    }

    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {

        if (!isExpectedClass(className)) {
            return null;
        }

        byte[] ba = transformBytes(classfileBuffer, from, to);

        return ba;
    }
}


class TestClassFileTransformerLM extends TestClassFileTransformer {

    private String from = "";
    private String to = "";

    public TestClassFileTransformerLM(TransformationResult tr, String clsName,
            String from, String to) {
         super(tr, clsName);
         this.from = from;
         this.to = to;
    }

    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {

        if (!isExpectedClass(className)) {
            return null;
        }

        byte[] ba = transformBytes(classfileBuffer, from, to);

        return ba;
    }
}



class TestClassFileTransformerM2 extends TestClassFileTransformer {

    public TestClassFileTransformerM2(TransformationResult tr, String clsName) {
         super(tr, clsName);
    }

    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {

        if (!isExpectedClass(className)) {
            return null;
        }

        transfomationResult.setResultString("OKAY");

        return null;
    }
}


class TestClassFileTransformerL2 extends TestClassFileTransformer {

    public TestClassFileTransformerL2(TransformationResult tr, String clsName) {
         super(tr, clsName);
    }

    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {

        if (!isExpectedClass(className)) {
            return null;
        }

        transfomationResult.setResultString("OKAY");

        throw new IllegalClassFormatException();
    }
}



class TestClassFileTransformerK extends TestClassFileTransformer {

    private String from = "";
    private String to = "";

    public TestClassFileTransformerK(TransformationResult tr, String clsName,
            String from, String to) {
         super(tr, clsName);
         this.from = from;
         this.to = to;
    }

    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {

        if (!isExpectedClass(className)) {
            return null;
        }

        if (classBeingRedefined != null) {
            transfomationResult.setResultString("classBeingRedefined is not null");
            return null;
        }

        if (classfileBuffer == null || classfileBuffer.length <= 4) {
            transfomationResult.setResultString(
                    " classfileBuffer is either null or has the length less "
                    + "than 4 bytes");
            return null;
        }

        byte[] ba = transformBytes(classfileBuffer, from, to);

        return ba;
    }
}



class TestClassFileTransformerJ1 extends TestClassFileTransformer {

    Vector v = null;
    String classNameUnexpected = "";
    String id = "";

    public TestClassFileTransformerJ1(String id, Vector v,
            String clsNameUnexpected, String clsNameExpected) {
         super(clsNameExpected);
         this.id = id;
         this.v = v;
         this.classNameUnexpected = transformClassName(clsNameUnexpected);
    }

    public byte[] transform(ClassLoader loader, String className,
                        Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                        byte[] classfileBuffer) throws IllegalClassFormatException {

        if (isExpectedClass(className) || classNameUnexpected.equals(className)) {
            v.add(id + " on " + className + " loading");
        }

        return null;
    }
}


class TestClassFileTransformerJ2 extends TestClassFileTransformerJ1 {

    private String classNameToLoad = "";
    private ClassLoader cl = null;

    public TestClassFileTransformerJ2(String id, Vector v,
            String classNameUnexpected, String classNameExpected,
            ClassLoader cl) {
         super(id, v, classNameUnexpected, classNameExpected);
         this.classNameToLoad = classNameUnexpected;
         this.cl = cl;
    }

    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {
         super.transform(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);

         if (isExpectedClass(className)) {
              try {
                  cl.loadClass(classNameUnexpected);
              } catch (ClassNotFoundException cnfe) {
                  v.add("ClassNotFoundException: " + id + " on " + className + " loading");
              }
         }

        return null;
    }
}


class ClassFileTransformerI extends TestClassFileTransformer {

    private String from = "";
    private String to = "";

    public ClassFileTransformerI(TransformationResult tr, String clsName,
            String from, String to) {
        super(tr, clsName);
        this.from = from;
        this.to = to;
    }

    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!isExpectedClass(className)) {
            return null;
        }

        byte[] ba = transformBytes(classfileBuffer, from, to);

        throw new IllegalClassFormatException();
    }
}


class ClassFileTransformerH extends TestClassFileTransformer {

    Class redefClass;
    String from;
    String to;

    public ClassFileTransformerH(TransformationResult tr, Class c,
            String clsName, String from, String to) {
        super(tr, clsName);
        this.redefClass = c;
        this.from = from;
        this.to = to;
    }

    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {

        if (!isExpectedClass(className)) {
            return null;
        }

        if (classBeingRedefined == null) {
            transfomationResult.setResultString("classBeingRedefined is null");
            return null;
        }

        String redefinedClassName = transformClassName(
                classBeingRedefined.getName());

        if (!this.className.equals(redefinedClassName)) {
            transfomationResult.setResultString(
                    "redefined Class's name: \"" + redefinedClassName
                    + "\" differs from expected: \"" + this.className + "\"");
            return null;
        }

        if (this.redefClass != classBeingRedefined) {
            transfomationResult.setResultString(
                    " Class instance to be redefined (passed in arguments) "
                    + " is not equal to the expected Class instance");
            return null;
        }

        if (classfileBuffer == null || classfileBuffer.length <= 4) {
            transfomationResult.setResultString(
                    " classfileBuffer is either null or has the length less "
                    + "than 4 bytes");
            return null;
        }

        byte[] ba = transformBytes(classfileBuffer, from, to);
        return ba;
    }
}


class ARetransformer implements ClassFileTransformer {
    private String toClassName;

    public ARetransformer(String toClassName) {
        this.toClassName = toClassName;
    }

    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {

        byte[] newBytes = null;
        // load new class bytes from class file
        try {
            newBytes = ByteCodeReader.loadClassBytes(
                    toClassName.replace('.','/') + ".class");
        } catch (Exception e) {
            throw new IllegalClassFormatException(e.getMessage());
        }

        // replace bytes in new class bytes array
        try {
            newBytes = ByteCodeReader.transformBytes(
                    newBytes, "retransformKlass", "retransformClass");
        } catch (Exception e) {
            throw new IllegalClassFormatException(e.getMessage());
        }

        return newBytes;
    }
}

class MultiClassRetransformer implements ClassFileTransformer {
    private ClassNamePair[] cnpArray;

    public MultiClassRetransformer(ClassNamePair[] cnpArray) {
        this.cnpArray = cnpArray;
    }

    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {

        byte[] newBytes = null;

        for (ClassNamePair cnp : cnpArray) {
            if (className.equals(cnp.getFirstClassName().replace('.','/'))) {
                // load new class bytes from class file
                try {
                    newBytes = ByteCodeReader.loadClassBytes(
                            cnp.getSecondClassName().replace('.','/')
                            + ".class");
                } catch (Exception e) {
                    throw new IllegalClassFormatException(e.getMessage());
                }
                // replace bytes in new class bytes array
                try {
                    newBytes = ByteCodeReader.transformBytes(
                            newBytes, "retransformKlass", "retransformClass");
                } catch (Exception e) {
                    throw new IllegalClassFormatException(e.getMessage());
                }
            }
        }

        return newBytes;
    }
}

class BRetransformer implements ClassFileTransformer {
    private String toClassName;

    public BRetransformer(String toClassName) {
        this.toClassName = toClassName;
    }

    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {

        byte[] newBytes = null;
        // load new class bytes from class file
        try {
            newBytes = ByteCodeReader.loadClassBytes(
                    toClassName.replace('.','/') + ".class");
        } catch (Exception e) {
            throw new IllegalClassFormatException(e.getMessage());
        }

        // replace bytes in new class bytes array
        try {
            newBytes = ByteCodeReader.transformBytes(
                    newBytes, "retransformKlass", "retransformClass");
        } catch (Exception e) {
            throw new IllegalClassFormatException(e.getMessage());
        }

        // truncating first byte of the class making it malformed
        byte[] result = new byte[newBytes.length-1];
        System.arraycopy(newBytes, 1, result, 0, newBytes.length-1);

        return result;
    }
}

class CRetransformer implements ClassFileTransformer {
    private String toClassName;

    public CRetransformer(String toClassName) {
        this.toClassName = toClassName;
    }

    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {

        byte[] newBytes = null;
        // load new class bytes from class file
        try {
            newBytes = ByteCodeReader.loadClassBytes(
                    toClassName.replace('.','/') + ".class");
        } catch (Exception e) {
            throw new IllegalClassFormatException(e.getMessage());
        }

        // replace bytes in new class bytes array with invalid package name
        try {
            newBytes = ByteCodeReader.transformBytes(
                    newBytes, "retransformKlass", "retransformClazz");
        } catch (Exception e) {
            throw new IllegalClassFormatException(e.getMessage());
        }

        return newBytes;
    }
}

class DRetransformer implements ClassFileTransformer {
    private String toClassName;

    public DRetransformer(String toClassName) {
        this.toClassName = toClassName;
    }

    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {

        byte[] newBytes = null;
        // load new class bytes from class file
        try {
            newBytes = ByteCodeReader.loadClassBytes(
                    toClassName.replace('.','/') + ".class");
        } catch (Exception e) {
            throw new IllegalClassFormatException(e.getMessage());
        }

        // replace bytes in new class bytes array
        try {
            newBytes = ByteCodeReader.transformBytes(
                    newBytes, "retransformKlass", "retransformClass");
        } catch (Exception e) {
            throw new IllegalClassFormatException(e.getMessage());
        }

        newBytes[7] = 99; // set invalid major version number

        return newBytes;
    }
}

class ClassA {
    String s = "some class A";
}

class ClassB {
    String s = "some class B";
}

class ClassCA {
    String s = "some class C";
}

class ClassCB {
    String s = "some class C";
}

class MyClassLoader extends ClassLoader {

    public MyClassLoader() {
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }
}


