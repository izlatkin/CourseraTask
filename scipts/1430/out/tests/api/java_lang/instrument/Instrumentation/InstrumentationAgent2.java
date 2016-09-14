/*
 * Copyright (c) 2003, 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

 /*
 * @(#)InstrumentationAgent2.java 08/01/22Cheryl Stocks, Oleg Oleinik, Dmitry Bessonov, Yuri Gaevsky
 */

/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 *
 * Instrumentation tests. JPLIS agent.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.UnmodifiableClassException;
import javasoft.sqe.javatest.Status;


public class InstrumentationAgent2 extends InstrumentationMultiTest {

    private static boolean fromPremain    = false;
    private static boolean fromAgentmain  = false;

    public static String testClassName = packageName + "."
            + "InstrumentationAgent2";

    
    public static void premain(String agentOptString, Instrumentation inst) {
        InstrumentationMultiTest test = new InstrumentationAgent2();
        fromPremain = true;
        test.instrumentation = inst;
        test.agentOptString = agentOptString;
        test.opts = AgentOptionsParser.parseAgentOpts(agentOptString);

        JplisAgentInstrumentation.setAgent2Instrumentation(inst);

        // testSuiteRootURL should always be the first option set by JCKScript
        // or manually in command-line (singleJVM)
        test.setResourceDirURL(test.opts[0]);

        test.run(new String[0], System.err, System.out);
    }

    public static void agentmain(String agentOptString, Instrumentation inst) {
        InstrumentationMultiTest test = new InstrumentationAgent2();
        fromAgentmain = true;
        test.instrumentation = inst;
        test.agentOptString = agentOptString;
        test.opts = AgentOptionsParser.parseAgentOpts(agentOptString);

        JplisAgentInstrumentation.setAgent2Instrumentation(inst);

        // testSuiteRootURL should always be the first option set by JCKScript
        // or manually in command-line (singleJVM)
        test.setResourceDirURL(test.opts[0]);

        test.run(new String[0], System.err, System.out);
    }

    /**
     * Assertion testing
     * This switch may be repeated multiple times on the same command line,
     * thus creating multiple agents. Each premain method will be called in the
     * order the -javaagent switches appeared on the command line.
     * <br><b>Expected results</b>: The first agent registers tranasformer,
     * the second agent checks that the first transformer is registered, loads
     * class and checks that class is transformed by the transformer registered
     * in the first agent, then removes both transformers.
     */
    public Status Instrumentation050() {
        String testCaseID = "Instrumentation050";
        String testCaseID2 = "Instrumentation049";
        String classToLoadInAgent = "ClassQ1";

        String originalStr    = " T r a n s f o r m e d    string   , ClassQ1";
        String transformedStr = "One more time transformed string   , ClassQ1";

        ClassFileTransformer transformer2 = new TestClassFileTransformerP("ClassQ1",
                                                                         originalStr, transformedStr);
        instrumentation.addTransformer(transformer2);
        ClassLoader cl = null;
        try {
            try {
                cl = new LocalDirClassLoader(classesDirURL, this.getClass().getClassLoader());
            } catch (Exception e){
                setStatus(Status.FAILED, testCaseID, testClassName + ": can not create LocalDirClassLoader " + e);
                return Status.passed("Testing result passed to test");
            }
            Class c;
            try {
                c = cl.loadClass(classToLoadInAgent);
            } catch (Exception e){
                setStatus(Status.FAILED, testCaseID, testClassName + ": class " + classToLoadInAgent +
                                                                     " can not be loaded " + e);
                return Status.passed("Testing result passed to test");
            }
            BaseClass obj = null;
            try {
                obj = (BaseClass) c.newInstance();
            } catch (Exception e){
                setStatus(Status.FAILED, testCaseID, testClassName + ": loaded class " + classToLoadInAgent +
                             " can not be instantiated, thrown exception: " + e);
                return Status.passed("Testing result passed to test");
            }
            if (!transformedStr.equals(obj.getString())){
                setStatus(Status.FAILED, testCaseID, "The check of the class " + classToLoadInAgent
                                      + " failed (the class was not transformed(?)): " + obj.getString());
                return Status.passed("Testing result passed to test");
            }
        } finally {
            if (transformer2 != null){
                instrumentation.removeTransformer(transformer2);
            }
            ClassFileTransformer transformer1 = (ClassFileTransformer)getObject(testCaseID2);
            if (transformer1 != null){
                instrumentation.removeTransformer(transformer1);
            } else {
                setStatus(Status.FAILED, testCaseID, testClassName + ": no transformer set in the first " +
                                                                     "agent (first agent not invoked (?))");
                return Status.passed("Testing result passed to test");
            }
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, first agent is called before the second");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing
     * for public static void premain(String agentArgs, Instrumentation inst),
     * Each agent has its own classname and options. Each agent receives its own options as a single string.
     * <br><b>Expected results</b>: options passed to the second agent are as expected
     */
    public Status Instrumentation100() {
        String testCaseID = "Instrumentation100";

        if (opts.length != 2 || "".equals(opts[0].trim()) || !"opt2".equals(opts[1])){
            setStatus(Status.FAILED, testCaseID, "wrong options string was passed " +
                                                 "to the agent:\"" + agentOptString +
                                                 "\" instead of: <testDirURL>,opt2");
            return Status.passed("Testing result passed to test");
        }
        setStatus(Status.PASSED, testCaseID, "OKAY, correct options are passed to the second agent");
        return Status.passed("Testing result passed to test");
    }

    /**
     * Assertion testing for
     *     public static void premain(String agentArgs, Instrumentation inst).
     * The JVM first attempts to invoke  premain(String agentArgs, Instrumentation inst)
     * method on the agent class. The agent class may also have an agentmain method for
     * use when the agent is started after VM startup. When the agent is started using
     * a command-line option, the agentmain method is not invoked.
     * <br><b>Expected results</b>: only long form of premain method is invoked as expected
     */
    public Status Instrumentation200() {
        String testCaseID = "Instrumentation200";

        if ((!fromPremain) || fromAgentmain) {
            setStatus(Status.FAILED, testCaseID,
                      "FAIL, wrong method is invoked from the agent: \""
		      + agentOptString + "\"");
            return Status.passed("premain method is not invoked");
        }
        setStatus(Status.PASSED, testCaseID,
                 "OKAY, only premain(String agentArgs, Instrumentation inst) "
               + "method is invoked as expected");
        return Status.passed("Testing result passed to test");
    }

    //==========================================================================

    /**
     * Assertion testing for
     * for public boolean isModifiableClass(Class theClass).
     * Verifying that this method throws NullPointerException
     * if the specified class is null.
     */
    public Status Instrumentation216() {
        final String testCaseID = "Instrumentation216";

        try {
            instrumentation.isModifiableClass(null);
            setStatus(Status.FAILED, testCaseID,
                    "NullPointerException is not thrown");
            return Status.passed("Testing result passed to test");
        } catch (NullPointerException ok) {
            // expected
        }

        setStatus(Status.PASSED, testCaseID,
                "OKAY, NullPointerException is thrown as expected");
        return Status.passed("OKAY");
    }

    /**
     * Assertion testing for
     * for public boolean isModifiableClass(Class theClass).
     * Verifying that primitive classes and array classes are never modifiable.
     */
    public Status Instrumentation217() {
        final String testCaseID = "Instrumentation217";
        StringBuffer errorMessages = new StringBuffer();

        Class[] classesArray = new Class[] {
            byte.class, boolean.class, char.class, short.class,
            int.class, long.class, float.class, double.class,
            new Object[0].getClass()
        };

        String[] classNamesStr = new String[] {
            "byte.class", "boolean.class", "char.class", "short.class",
            "int.class",  "long.class", "float.class", "double.class",
            "Object[0].getClass()"
        };

        for (int i = 0; i < classesArray.length; i++) {
            if (instrumentation.isModifiableClass(classesArray[i])) {
                errorMessages.append(classNamesStr[i] + " is unexpectedly " +
                        "modifiable;  ");
            }
        }

        if (errorMessages.length() > 0) {
            setStatus(Status.FAILED, testCaseID, errorMessages.toString());
            return Status.passed("Testing result passed to test");
        }

        setStatus(Status.PASSED, testCaseID, "OKAY, primitive classes and " +
                "array classes are not modifiable");
        return Status.passed("OKAY");
    }

    //==========================================================================

    /**
     * Assertion testing for
     *     public static void agentmain(String agentArgs, Instrumentation inst).
     * The JVM first attempts to invoke
     *   agentmain(String agentArgs, Instrumentation inst)
     * method on the agent class.
     * The agent class may also have an premain method for use when the agent is
     * started using a command-line option. When the agent is started after VM
     * startup the premain method is not invoked.
     * <br><b>Expected results</b>: only long form of agentmain method is
     * invoked as expected
     */
    public Status Instrumentation250() {
        String testCaseID = "Instrumentation250";

        if ((!fromAgentmain) || fromPremain) {
            setStatus(Status.FAILED, testCaseID,
                      "FAIL, wrong method is invoked from the agent: \""
		      + agentOptString + "\"");
            return Status.passed("agentmain method is not invoked");
        }
        setStatus(Status.PASSED, testCaseID,
                 "OKAY, only agentmain(String agentArgs, Instrumentation inst)"
               + " method is invoked as expected");
        return Status.passed("Testing result passed to test");
    }
}
