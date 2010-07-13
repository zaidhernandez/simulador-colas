/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simuladorcolas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Propietario
 */
public class RandTest {

    public RandTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of Isaac method, of class Rand.
     */
    @Test
    public void testIsaac() {
        System.out.println("Isaac");
        Rand instance = new Rand();
        instance.Isaac();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Init method, of class Rand.
     */
    @Test
    public void testInit() {
        System.out.println("Init");
        boolean flag = false;
        Rand instance = new Rand();
        instance.Init(flag);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of val method, of class Rand.
     */
    @Test
    public void testVal() {
        System.out.println("val");
        Rand instance = new Rand();
        int expResult = 0;
        int result = instance.val();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}