/*
 * Copyright (c) 2003, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 *   @(#)Instrumentation.test.xml 13/05/07 19:52 Cheryl Stocks, Oleg Oleinik, Dmitry Bessonov, Yuri Gaevsky, Dmitry Miltsov
 * Generated from : api/java_lang/instrument/Instrumentation/Instrumentation.test.xml
 *
 * Testing of methods of java.lang.instrument.Instrumentation class.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation;

import java.io.PrintWriter;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import javasoft.sqe.javatest.Status;
import javasoft.sqe.jck.lib.ReadTest;


public class InstrumentationTests extends ReadTest {

    String getClassDirURL() {
        String sep = testdirurl.endsWith("/") ? "" : "/";
        return testdirurl + sep + "stubclasses/";
    }

    Status getAgentResult(String testCaseID){
        Status s = JplisAgentResults.getResult(testCaseID);
        if (s == null) {
            return Status.failed(
                    "Results of testing in Agent not found for testcase: "
                    + testCaseID);
        }
        return s;
    }

    /* standalone interface */
    public static void main(String argv[]) {
        InstrumentationTests test = new InstrumentationTests();
        test.run(argv, System.err, System.out).exit();
    }

    /**
     * Assertion testing
     * for public static void premain(String agentArgs, Instrumentation inst),
     * Each agent has its own classname and options. Each agent
     * receives its own options as a single string..
     * <br><b>Expected results</b>: options passed to the first agent are as expected
     */
    public Status Instrumentation001() {
        String testCaseID = "Instrumentation001";
            return getAgentResult(testCaseID);
    }

    /**
     * Equivalence class partitioning
     * with input values orientation
     * for public long getObjectSize(Object objectToSize),
     * <br><b>objectToSize</b>: non-null objects.
     * <br><b>Expected results</b>: positive values
     */
    public Status Instrumentation002() {
        String testCaseID = "Instrumentation002";
            return getAgentResult(testCaseID);
    }

    /**
     * Boundary value analysis
     * with input values orientation
     * for public long getObjectSize(Object objectToSize),
     * <br><b>objectToSize</b>: null.
     * <br><b>Expected results</b>: NullPointerException
     */
    public Status Instrumentation003() {
        String testCaseID = "Instrumentation003";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public Class[] getAllLoadedClasses(),
     * The method returns an array containing all the classes loaded
     * by the JVM, zero-length if there are none..
     * <br><b>Expected results</b>: returned non-null reference is an array with positive length,
     * contains test's class
     */
    public Status Instrumentation004() {
        String testCaseID = "Instrumentation004";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public Class[] getInitiatedClasses(ClassLoader loader),
     * <br><b>pre-conditions</b>: new MyClassLoader is created, no classes are loaded,
     * The method returns zero-length if there are no classes
     * for which loader is an initiating loader..
     * <br><b>Expected results</b>: non-null array is returned
     */
    public Status Instrumentation005() {
        String testCaseID = "Instrumentation005";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public Class[] getInitiatedClasses(ClassLoader loader),
     * <br><b>pre-conditions</b>: a class is loaded by the current class loader,
     * The method returns an array of all classes for
     * which loader is an initiating loader..
     * <br><b>Expected results</b>: returned non-null reference is an array with length greater than zero
     */
    public Status Instrumentation006() {
        String testCaseID = "Instrumentation006";
            return getAgentResult(testCaseID);
    }

    /**
     * Equivalence class partitioning
     * with input values orientation
     * for public Class[] getInitiatedClasses(ClassLoader loader),
     * <br><b>loader</b>: null.
     * <br><b>Expected results</b>: no exceptions, returned array is non-null
     */
    public Status Instrumentation007() {
        String testCaseID = "Instrumentation007";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public boolean isRedefineClassesSupported(),
     * During a single instantiation of a single JVM,
     * multiple calls to this method will always return the same answer..
     * <br><b>Expected results</b>: multiple method invocations return same result
     */
    public Status Instrumentation019() {
        String testCaseID = "Instrumentation019";
            return getAgentResult(testCaseID);
    }

    /**
     * Equivalence class partitioning
     * with input values orientation
     * for public void redefineClasses(ClassDefinition... definitions),
     * <br><b>definitions</b>: zero-length array.
     * <br><b>Expected results</b>: no exceptions if redefinition is supported, otherwise, UnsupportedOperationException
     */
    public Status Instrumentation020() {
        String testCaseID = "Instrumentation020";
            return getAgentResult(testCaseID);
    }

    /**
     * Boundary value analysis
     * with input values orientation
     * for public void redefineClasses(ClassDefinition... definitions),
     * <br><b>definitions</b>: null.
     * <br><b>Expected results</b>: NullPointerException if redefinition is supported,
     * otherwise, UnsupportedOperationException
     */
    public Status Instrumentation021() {
        String testCaseID = "Instrumentation021";
            return getAgentResult(testCaseID);
    }

    /**
     * Boundary value analysis
     * with input values orientation
     * for public void redefineClasses(ClassDefinition... definitions),
     * <br><b>definitions</b>: array with sinlge null element.
     * <br><b>Expected results</b>: NullPointerException if redefinition is supported,
     * otherwise, UnsupportedOperationException
     */
    public Status Instrumentation022() {
        String testCaseID = "Instrumentation022";
            return getAgentResult(testCaseID);
    }

    /**
     * Boundary value analysis
     * with input values orientation
     * for public void redefineClasses(ClassDefinition... definitions),
     * <br><b>definitions</b>: array with byte[0] class file.
     * <br><b>Expected results</b>: ClassFormatError if redefinition is supported,
     * otherwise, UnsupportedOperationException
     */
    public Status Instrumentation023() {
        String testCaseID = "Instrumentation023";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public boolean isRedefineClassesSupported(),
     * Returns whether or not the current JVM configuration supports
     * redefinition of classes..
     * <br><b>Expected results</b>: if isRedefineClassesSupported() returns true
     * then redefineClasses() does not throw UnsupportedOperationException
     */
    public Status Instrumentation024() {
        String testCaseID = "Instrumentation024";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public boolean isRedefineClassesSupported(),
     * Returns whether or not the current JVM configuration supports
     * redefinition of classes..
     * <br><b>Expected results</b>: if isRedefineClassesSupported() returns true
     * then redefineClasses() does not throw UnsupportedOperationException
     */
    public Status Instrumentation025() {
        String testCaseID = "Instrumentation025";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void redefineClasses(ClassDefinition... definitions),
     * <br><b>pre-conditions</b>: the instance method of to be defined class runs and falls into wait(),
     * If a redefined method has active stack frames, those
     * active frames continue to run the bytecodes of the original method. The redefined method will
     * be used on new invokes..
     * <br><b>Expected results</b>: after class redefinition the old method continues execution,
     * redefined method is executed on new invoke.
     */
    public Status Instrumentation026() {
        String testCaseID = "Instrumentation026";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void redefineClasses(ClassDefinition... definitions),
     * <br><b>pre-conditions</b>: the static method of to be defined class runs and falls into wait(),
     * If a redefined method has active stack frames,
     * those active frames continue to run the bytecodes of the original method. The redefined method
     * will be used on new invokes..
     * <br><b>Expected results</b>: after class redefinition the old method continues execution,
     * redefined method is executed on new invoke.
     */
    public Status Instrumentation027() {
        String testCaseID = "Instrumentation027";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void redefineClasses(ClassDefinition... definitions),
     * Redefining a class does not cause its
     * initializers to be run. The values of static variables will remain as they were
     * prior to the call..
     * <br><b>Expected results</b>: after class redefinition the values of static variables
     * did not change.
     */
    public Status Instrumentation028() {
        String testCaseID = "Instrumentation028";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public byte[] ClassFileTransformer.transform(ClassLoader loader, String className, Class classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer),
     * The method arguments are:
     * classBeingRedefined - if this is a redefine, the class being redefined,
     * classfileBuffer - the input byte buffer in class file format,
     * className - the name of the class in the internal form of fully qualified class name,.
     * <br><b>Expected results</b>: classBeingRedefined is as expected,
     * classfileBuffer is valid class file byte array, className corresponds to the redefined class name
     */
    public Status Instrumentation029() {
        String testCaseID = "Instrumentation029";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public byte[] ClassFileTransformer.transform(ClassLoader loader, String className, Class classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer),
     * If IllegalClassFormatException is thrown by
     * transformer then redefine will still be attempted.
     * <br><b>Expected results</b>: despite of transform() throws IllegalClassFormatException the class
     * is redefined
     */
    public Status Instrumentation030() {
        String testCaseID = "Instrumentation030";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void redefineClasses(ClassDefinition... definitions),
     * <br><b>pre-conditions</b>: instance/static method/field is renamed in redefining class,
     * The redefinition must not rename methods or fields. The methods
     * throws UnsupportedOperationException if the redefinition made unsupported changes..
     * <br><b>Expected results</b>: UnsupportedOperationException, if redefinition is supported
     */
    public Status Instrumentation031() {
        String testCaseID = "Instrumentation031";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void redefineClasses(ClassDefinition... definitions),
     * <br><b>pre-conditions</b>: redefinition with class with changed superclass / implementing another interface,
     * The redefinition must not change inheritance. The methods
     * throws UnsupportedOperationException if the redefinition made unsupported changes..
     * <br><b>Expected results</b>: UnsupportedOperationException, if redefinition is supported
     */
    public Status Instrumentation032() {
        String testCaseID = "Instrumentation032";
            return getAgentResult(testCaseID);
    }

    /**
     * Boundary value analysis
     * with input values orientation
     * for public void addTransformer(ClassFileTransformer transformer),
     * <br><b>transformer</b>: null.
     * <br><b>Expected results</b>: NullPointerException is thrown
     */
    public Status Instrumentation040() {
        String testCaseID = "Instrumentation040";
            return getAgentResult(testCaseID);
    }

    /**
     * Boundary value analysis
     * with input values orientation
     * for public boolean removeTransformer(ClassFileTransformer transformer),
     * <br><b>transformer</b>: null.
     * <br><b>Expected results</b>: NullPointerException is thrown
     */
    public Status Instrumentation041() {
        String testCaseID = "Instrumentation041";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public boolean removeTransformer(ClassFileTransformer transformer),
     * removeTransformer() returns true
     * if the transformer was found and removed, false if the transformer was not found.
     * <br><b>Expected results</b>: removement of unregistered
     * transformer returns false
     */
    public Status Instrumentation042() {
        String testCaseID = "Instrumentation042";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void addTransformer(ClassFileTransformer transformer),
     * All future class definitions will be seen by
     * the transformer, except definitions of classes upon which any registered transformer is dependent..
     * <br><b>Expected results</b>: the class loaded by transformer is not seen
     * by another transformer, the order of invocations is correct
     */
    public Status Instrumentation043() {
        String testCaseID = "Instrumentation043";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public byte[] ClassFileTransformer.transform(ClassLoader loader, String className, Class classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer),
     * The method arguments are:
     * classBeingRedefined - if this is a redefine, the class being redefined, otherwise null
     * classfileBuffer - the input byte buffer in class file format,
     * className - the name of the class in the internal form of fully qualified class name,.
     * <br><b>Expected results</b>: classBeingRedefined is null,
     * classfileBuffer is valid class file byte array
     */
    public Status Instrumentation044() {
        String testCaseID = "Instrumentation044";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public byte[] ClassFileTransformer.transform(ClassLoader loader, String className, Class classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer),
     * If the transformer believes the classFileBuffer
     * does not represent a validly formatted class file, it should throw an IllegalClassFormatException.
     * Subsequent transformers will still be called and the load or redefine will still be attempted..
     * <br><b>Expected results</b>: in a set of registered transformers,
     * the transformer throwing IllegalClassFormatException does not affect transformation
     */
    public Status Instrumentation045() {
        String testCaseID = "Instrumentation045";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public byte[] ClassFileTransformer.transform(ClassLoader loader, String className, Class classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer),
     * If the implementing method determines that no
     * transformations are needed, it should return null.
     * <br><b>Expected results</b>: in a set of registered transformers,
     * the transformer returning null does not affect transformation
     */
    public Status Instrumentation046() {
        String testCaseID = "Instrumentation046";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public boolean removeTransformer(ClassFileTransformer transformer),
     * Unregisters the supplied transformer. Future class
     * definitions will not be shown to the transformer..
     * <br><b>Expected results</b>: removed transformer is not invoked during future class definitions
     */
    public Status Instrumentation047() {
        String testCaseID = "Instrumentation047";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void addTransformer(ClassFileTransformer transformer),
     * <br><b>pre-conditions</b>: a transformer is registered in agent, class is loaded in test (application) class,
     * All future class definitions will be seen by the transformer..
     * <br><b>Expected results</b>: the test gets transformed class
     */
    public Status Instrumentation048() {
        String testCaseID = "Instrumentation048";
            String classToLoad = "ClassP1";
            String expectedString = " T r a n s f o r m e d    string   , ClassP1";

            Status s = getAgentResult(testCaseID);
            if (!s.isPassed()) {
                return s;
            }
            ClassLoader cl = null;
            try {
                cl = new LocalDirClassLoader(getClassDirURL(), this.getClass().getClassLoader());
            } catch (Exception e){
                return Status.failed("Unable to create LocalDirClassLoader, exception thrown: " + e);
            }

            BaseClass obj = null;

            Class c;
            try {
                c = cl.loadClass(classToLoad);
            } catch (Exception e){
                return Status.failed("Unable to load class " + classToLoad + ", exception thrown: " + e);
            }

            try {
                obj = (BaseClass) c.newInstance();
            } catch (Exception e){
                return Status.failed("Unable to instantiate class " + classToLoad + ", exception thrown: " + e);
            }

            if (!expectedString.equals(obj.getString())){
                return Status.failed("Unexpected string value: \"" + obj.getString() + "\", instead of expected: \"" +
                                     expectedString + " in the transformed class ClassP1");
            }
            return Status.passed("OKAY");

    }

    /**
     * Assertion testing
     * for public void addTransformer(ClassFileTransformer transformer),
     * Agent switch may be repeated multiple times on the
     * same command line, thus creating multiple agents. Each premain method will be called in the
     * order the -javaagent switches appeared on the command line..
     * <br><b>Expected results</b>: The first agent registers tranasformer,
     * the second agent checks that the first transformer is registered, registers own transformer, loads
     * class and checks that class is transformed by both registered transformers, then
     * removes both transformers.
     */
    public Status Instrumentation049() {
        String testCaseID = "Instrumentation049";
            String testCaseID2 = "Instrumentation050";

            Status s = getAgentResult(testCaseID);
            Status s2 = getAgentResult(testCaseID2);
            if (!s.isPassed()){
                 return s;
            }
            return s2;

    }

    /**
     * Assertion testing
     * for public static void premain(String agentArgs, Instrumentation inst),
     * Each agent has its own classname and options. Each agent
     * receives its own options as a single string..
     * <br><b>Expected results</b>: options passed to the second agent are as expected
     */
    public Status Instrumentation100() {
        String testCaseID = "Instrumentation100";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public static void premain(String agentArgs, Instrumentation inst),
     * The JVM first attempts to invoke the following method on the agent class:
     * <pre>
     * public static void premain(String agentArgs, Instrumentation inst);
     * </pre>
     * The agent class may also have an agentmain method for use when the agent is started after VM startup. When the agent is started
     * using a command-line option, the agentmain method is not invoked..
     * <br><b>Expected results</b>: the agentmain method is not invoked as expected
     */
    public Status Instrumentation200() {
        String testCaseID = "Instrumentation200";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for void appendToBootstrapClassLoaderSearch(JarFile jarfile),
     * The Java Virtual Machine Specification specifies that a subsequent attempt to resolve
     * a symbolic reference that the Java virtual machine has previously unsuccessfully attempted
     * to resolve always fails with the same error that was thrown as a result of the initial
     * resolution attempt..
     * <br><b>Expected results</b>: A subsequent attempt to resolve a symbolic reference that the JVM has previously unsuccessfully attempted
     * to resolve always fails with the same error that was thrown as a result of the initial resolution attempt.
     */
    public Status Instrumentation201() {
        String testCaseID = "Instrumentation201";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for void appendToBootstrapClassLoaderSearch(JarFile jarfile),
     * When the virtual machine's built-in class loader, known as the bootstrap class loader,
     * unsuccessfully searches for a class, the entries in the JAR file will be searched as well..
     * <br><b>Expected results</b>: The classes contained within given JAR file are loadable by bootstrap class loader
     */
    public Status Instrumentation202() {
        String testCaseID = "Instrumentation202";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for void appendToBootstrapClassLoaderSearch(JarFile jarfile),
     * throws: NullPointerException If jarfile is null.
     * <br><b>Expected results</b>: NullPointerException is thrown as expected
     */
    public Status Instrumentation203() {
        String testCaseID = "Instrumentation203";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for void appendToSystemClassLoaderSearch(JarFile jarfile),
     * The Java Virtual Machine Specification specifies that a subsequent attempt to resolve
     * a symbolic reference that the Java virtual machine has previously unsuccessfully attempted
     * to resolve always fails with the same error that was thrown as a result of the initial
     * resolution attempt..
     * <br><b>Expected results</b>: A subsequent attempt to resolve a symbolic reference that the JVM has previously unsuccessfully attempted
     * to resolve always fails with the same error that was thrown as a result of the initial resolution attempt.
     */
    public Status Instrumentation211() {
        String testCaseID = "Instrumentation211";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for void appendToSystemClassLoaderSearch(JarFile jarfile),
     * When the system class loader for delegation (see getSystemClassLoader()) unsuccessfully searches
     * for a class, the entries in the JarFile will be searched as well.
     * The system class loader supports adding a JAR file to be searched if it implements
     * a method named appendToClassPathForInstrumentation which takes a single parameter
     * of type java.lang.String.
     * throws: UnsupportedOperationException - If the system class loader does not support appending a JAR file to be searched..
     * <br><b>Expected results</b>: UnsupportedOperationException is thrown as expected if the system class loader does not support appending a JAR file to be searched
     * The classes contained within given JAR file are loadable by system class loader if one does support appending a JAR file to be searched
     */
    public Status Instrumentation212() {
        String testCaseID = "Instrumentation212";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for void appendToSystemClassLoaderSearch(JarFile jarfile),
     * throws:NullPointerException If jarfile is null.
     * <br><b>Expected results</b>: NullPointerException is thrown as expected
     */
    public Status Instrumentation213() {
        String testCaseID = "Instrumentation213";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for void appendToSystemClassLoaderSearch(JarFile jarfile),
     * This method does not change the value of java.class.path system property..
     * <br><b>Expected results</b>: The value of java.class.path system property is not changed as expected.
     */
    public Status Instrumentation215() {
        String testCaseID = "Instrumentation215";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public boolean isModifiableClass(Class theClass),
     * throws: NullPointerException - if the specified class is null.
     */
    public Status Instrumentation216() {
        String testCaseID = "Instrumentation216";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public boolean isModifiableClass(Class theClass),
     * Primitive classes and array classes are never modifiable..
     */
    public Status Instrumentation217() {
        String testCaseID = "Instrumentation217";
            return getAgentResult(testCaseID);
    }

    /**
     * Equivalence class partitioning
     * with input values orientation
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * <br><b>classes</b>: null object.
     * <br><b>Expected results</b>: NullPointerException should be thrown
     */
    public Status Instrumentation218() {
        String testCaseID = "Instrumentation218";
            return getAgentResult(testCaseID);
    }

    /**
     * Equivalence class partitioning
     * with input values orientation
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * <br><b>classes</b>: classes array with one null component.
     * <br><b>Expected results</b>: NullPointerException should be thrown
     */
    public Status Instrumentation219() {
        final Class[] classes = {null};
            final boolean isRetransformSupported =
                    JplisAgentInstrumentation.getAgent1Instrumentation().isRetransformClassesSupported();

            try {
                JplisAgentInstrumentation.getAgent1Instrumentation().retransformClasses(classes);
            } catch (NullPointerException ok) {
                return Status.passed(
                        "OKAY, NullPointerException is thrown as expected");
            } catch (UnsupportedOperationException uoe) {
                if (isRetransformSupported) {
                    return Status.failed(
                            "FAILED. Unexpected UnsupportedOperationException: "
                            + uoe.getMessage());
                } else {
                    return Status.passed(
                            "OKAY, UnsupportedOperationException is thrown");
                }
            } catch (UnmodifiableClassException uce) {
                return Status.failed(
                        "FAILED. Unexpected UnmodifiableClassException: "
                        + uce.getMessage());
            } catch (Throwable e) {
                return Status.failed("FAILED. Unexpected exception: " + e);
            }

            return Status.failed("FAILED. NullPointerException is not thrown");
    }

    /**
     * Assertion testing
     * for public boolean isModifiableClass(Class theClass),
     * Primitive classes (for example, java.lang.Integer.TYPE) and array classes are never modifiable..
     */
    public Status Instrumentation220() {
        String testCaseID = "Instrumentation220";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public boolean isRetransformClassesSupported(),
     * Retransformation will only be supported if the Can-Retransform-Classes manifest
     * attribute is set to true in the agent JAR file (as described in the package
     * specification) and the JVM supports this capability..
     */
    public Status Instrumentation221() {
        if (JplisAgentInstrumentation.getAgent2Instrumentation().isRetransformClassesSupported()) {
                return Status.failed("FAILED. isRetransformClassesSupported() unexpectedly "
                        + "returned true while the manifest attribute "
                        + "'Can-Retransform-Classes' for this agent is set to 'false'");
            }

            return Status.passed("OKAY, isRetransformClassesSupported() correctly "
                        + "returned 'false' when the Can-Retransform-Classes "
                        + "manifest attribute is set to 'false'");
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * This method does not cause any initialization except that which would occur
     * under the customary JVM semantics. In other words, redefining a class does not
     * cause its initializers to be run. The values of static variables will remain as
     * they were prior to the call. Instances of the retransformed class are not
     * affected..
     * <br><b>Expected results</b>: After retransformation of A1 class the value of static field did not change,
     * retransformed static and instance methods will return new values, and value of
     * instance field will have new value only on new instance.
     */
    public Status Instrumentation222() {
        String testCaseID = "Instrumentation222";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * This method does not cause any initialization except that which would occur
     * under the customary JVM semantics. In other words, redefining a class does not
     * cause its initializers to be run. The values of static variables will remain as
     * they were prior to the call. Instances of the retransformed class are not
     * affected. The retransformation may change method bodies, the constant pool and
     * attributes..
     * <br><b>Expected results</b>: After retransformation of A1 class the value of static field did not change,
     * retransformed static and instance methods will return new values, and value of
     * instance field will have new value only on new instance. The retransformation
     * may change method bodies, the constant pool and attributes.
     */
    public Status Instrumentation223() {
        String testCaseID = "Instrumentation223";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * The retransformation must not add, remove or rename fields or methods, change
     * the signatures of methods, or change inheritance..
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown because retransformation
     * must not remove field.
     */
    public Status Instrumentation224() {
        String testCaseID = "Instrumentation224";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * The retransformation must not add, remove or rename fields or methods, change
     * the signatures of methods, or change inheritance..
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown because retransformation
     * must not add field.
     */
    public Status Instrumentation225() {
        String testCaseID = "Instrumentation225";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * The retransformation must not add, remove or rename fields or methods, change
     * the signatures of methods, or change inheritance..
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown because retransformation
     * must not remove static field.
     */
    public Status Instrumentation226() {
        String testCaseID = "Instrumentation226";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * The retransformation must not add, remove or rename fields or methods, change
     * the signatures of methods, or change inheritance..
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown because retransformation
     * must not add static field.
     */
    public Status Instrumentation227() {
        String testCaseID = "Instrumentation227";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * The retransformation must not add, remove or rename fields or methods, change
     * the signatures of methods, or change inheritance..
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown because retransformation
     * must not remove method.
     */
    public Status Instrumentation228() {
        String testCaseID = "Instrumentation228";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * The retransformation must not add, remove or rename fields or methods, change
     * the signatures of methods, or change inheritance..
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown because retransformation
     * must not add method.
     */
    public Status Instrumentation229() {
        String testCaseID = "Instrumentation229";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * The retransformation must not add, remove or rename fields or methods, change
     * the signatures of methods, or change inheritance..
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown because retransformation
     * must not remove static method.
     */
    public Status Instrumentation230() {
        String testCaseID = "Instrumentation230";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * The retransformation must not add, remove or rename fields or methods, change
     * the signatures of methods, or change inheritance..
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown because retransformation
     * must not add static method.
     */
    public Status Instrumentation231() {
        String testCaseID = "Instrumentation231";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * The retransformation must not add, remove or rename fields or methods, change
     * the signatures of methods, or change inheritance..
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown because retransformation
     * must not change field access modifiers.
     */
    public Status Instrumentation232() {
        String testCaseID = "Instrumentation232";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * The retransformation must not add, remove or rename fields or methods, change
     * the signatures of methods, or change inheritance..
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown because retransformation
     * must not change field modifiers.
     */
    public Status Instrumentation233() {
        String testCaseID = "Instrumentation233";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * The retransformation must not add, remove or rename fields or methods, change
     * the signatures of methods, or change inheritance..
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown because retransformation
     * must not change method access modifiers.
     */
    public Status Instrumentation234() {
        String testCaseID = "Instrumentation234";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * The retransformation must not add, remove or rename fields or methods, change
     * the signatures of methods, or change inheritance..
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown because retransformation
     * must not change sychronized method modifier.
     */
    public Status Instrumentation235() {
        String testCaseID = "Instrumentation235";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * The retransformation must not add, remove or rename fields or methods, change
     * the signatures of methods, or change inheritance..
     * <br><b>Expected results</b>: UnsupportedOperationException should be thrown because retransformation
     * must not change class inheritance.
     */
    public Status Instrumentation236() {
        String testCaseID = "Instrumentation236";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * Throws: ClassFormatError - if the data did not contain a valid class.
     * <br><b>Expected results</b>: ClassFormatError should be thrown.
     */
    public Status Instrumentation237() {
        String testCaseID = "Instrumentation237";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * Throws: NoClassDefFoundError - if the name in the class file is not equal to the name of the class.
     * <br><b>Expected results</b>: NoClassDefFoundError should be thrown.
     */
    public Status Instrumentation238() {
        String testCaseID = "Instrumentation238";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * Throws: UnsupportedClassVersionError - if the class file version numbers are not supported.
     * <br><b>Expected results</b>: UnsupportedClassVersionError should be thrown.
     */
    public Status Instrumentation239() {
        String testCaseID = "Instrumentation239";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public boolean isNativeMethodPrefixSupported(),
     * Returns: true if the current JVM configuration supports setting a native method prefix, false if not.
     */
    public Status Instrumentation240() {
        String testCaseID = "Instrumentation240";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public boolean isNativeMethodPrefixSupported(),
     * Returns: true if the current JVM configuration supports setting a native method prefix, false if not.
     */
    public Status Instrumentation241() {
        String testCaseID = "Instrumentation241";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public boolean isNativeMethodPrefixSupported(),
     * Returns: true if the current JVM configuration supports setting a native method prefix, false if not.
     */
    public Status Instrumentation242() {
        String testCaseID = "Instrumentation242";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix),
     * For automatic resolution, the JVM will attempt:
     * <pre>    method(wrapped_foo) -> nativeImplementation(wrapped_foo)</pre>.
     * <br><b>Expected results</b>: The automatic resolution of:
     * <pre>    method(wrappednmpa1) -> nativeImplementation(wrappednmpa1)</pre>
     * is succeeded
     */
    public Status Instrumentation243() {
        String testCaseID = "Instrumentation243";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix),
     * When automatic resolution fails, the resolution will be retried with the
     * specified prefix deleted from the implementation name, yielding the correct
     * resolution:
     * <pre>    method(wrapped_foo) -> nativeImplementation(foo)</pre>.
     * <br><b>Expected results</b>: When automatic resolution fails, the resolution is retried with the 'wrapped'
     * prefix deleted from the native implementation name, yielding the correct
     * resolution:
     * <pre>    method(wrappednmpa2) -> nativeImplementation(nmpa2)</pre>
     */
    public Status Instrumentation244() {
        String testCaseID = "Instrumentation244";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix),
     * For explicit resolution with the JNI function RegisterNatives, the JVM will
     * attempt:
     * <pre>    method(foo) -> nativeImplementation(foo)</pre>.
     * <br><b>Expected results</b>: The explicit resolution with RegisterNatives succeeded the association:
     * <pre>    method(nmpa3) -> nativeImplementation(nmpa3)</pre>
     */
    public Status Instrumentation245() {
        String testCaseID = "Instrumentation245";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix),
     * When explicit resolution with the JNI function RegisterNatives fails,
     * the resolution will be retried with the specified prefix prepended
     * to the method name, yielding the correct resolution:
     * <pre>    method(wrapped_foo) -> nativeImplementation(foo)</pre>.
     * <br><b>Expected results</b>: When explicit resolution with RegisterNatives failed, the resolution is retried
     * with the <code>wrapped</code> prefix prepended to the <code>nmpa4</code> method
     * name, yielding the correct resolution:
     * <pre>    method(wrappednmpa4) -> nativeImplementation(nmpa4)</pre>
     */
    public Status Instrumentation246() {
        String testCaseID = "Instrumentation246";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix),
     * Since each <code>ClassFileTransformer</code> can do its own transformation of
     * the bytecodes, more than one layer of wrappers may be applied. Thus each
     * transformer needs its own prefix. Since transformations are applied in order,
     * the prefixes, if applied, will be applied in the same order(see addTransformer).
     * Thus if three transformers applied wrappers, <code>foo</code> might become
     * <code>$trans3_$trans2_$trans1_foo</code>..
     * <br><b>Expected results</b>: The prefixes are applied in the same order as transformation, so
     * <code>nmpa4</code> will become <code>prefix3prefix2prefix1nmpa4</code>.
     */
    public Status Instrumentation247() {
        String testCaseID = "Instrumentation247";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix),
     * But if, say, the second transformer did not apply a wrapper to <code>foo</code>
     * it would be just <code>$trans3_$trans1_foo</code>.
     * <br><b>Expected results</b>: The prefixes are applied in the same order as transformation and only when
     * a transformer did apply a native method prefix to code
     */
    public Status Instrumentation248() {
        String testCaseID = "Instrumentation248";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix),
     * To be able to efficiently determine the sequence of prefixes, an intermediate
     * prefix is only applied if its non-native wrapper exists..
     * <br><b>Expected results</b>: First and third prefixes are applied, but second prefix is not applied since
     * its non-native wrapper does not exist.
     */
    public Status Instrumentation249() {
        String testCaseID = "Instrumentation249";
            return getAgentResult(testCaseID);
    }

    /**
     * Assertion testing
     * for public void retransformClasses(Class&lt;?&gt;... classes) throws UnmodifiableClassException,
     * This method does not cause any initialization except that which would occur
     * under the customary JVM semantics. In other words, redefining a class does not
     * cause its initializers to be run. The values of static variables will remain as
     * they were prior to the call. Instances of the retransformed class are not
     * affected..
     * <br><b>Expected results</b>: After retransformation of classes A16 and A17 values of static fields did not change,
     * retransformed static and instance methods will return new values, and value of
     * instance fields will have new value only on new instances.
     */
    public Status Instrumentation251() {
        String testCaseID = "Instrumentation251";
            return getAgentResult(testCaseID);
    }
}
