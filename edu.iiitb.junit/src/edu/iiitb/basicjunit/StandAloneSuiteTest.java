package edu.iiitb.basicjunit;

/**
 * Dummy interface for categorising the test cases.<br>
 * Currently test cases can be categorised into two specific test case types,
 * namely:
 * <ol>
 * <li>StandAloneSuiteTest: These test cases are individual test cases which are
 * run once. These can be part of the suite of test cases where each test case is run
 * once.</li>
 * <li>RegressionTest: These test cases are marked for regression testing.
 * Usually a single test case is marked for regression test. These are
 * categorised separately to prevent testing with the stand alone units.</li>
 * </ol>
 * <br>
 * <br>
 * <b>NOTE: New Categories can be added by introducing new interface to
 * represent those categories</br>
 * 
 * @author mohnish
 * 
 */
public interface StandAloneSuiteTest {

}
