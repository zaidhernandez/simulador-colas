/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simuladorcolas;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import simuladorcolas.Simulador.Cliente;

/**
 *
 * @author Propietario
 */
public class SimuladorTest {

    public SimuladorTest() {
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
     * Test of simular method, of class Simulador.
     */
    @Test
    public void testSimular() throws Exception {
        System.out.println("simular");
        Simulador instance = null;
        instance.simular();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLongitudColaEsperada method, of class Simulador.
     */
    @Test
    public void testGetLongitudColaEsperada() {
        System.out.println("getLongitudColaEsperada");
        Simulador instance = null;
        double expResult = 0.0;
        double result = instance.getLongitudColaEsperada();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTiempoEsperaMedio1 method, of class Simulador.
     */
    @Test
    public void testGetTiempoEsperaMedio1() {
        System.out.println("getTiempoEsperaMedio1");
        Simulador instance = null;
        double expResult = 0.0;
        double result = instance.getTiempoEsperaMedio1();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTiempoEsperaMedio2 method, of class Simulador.
     */
    @Test
    public void testGetTiempoEsperaMedio2() {
        System.out.println("getTiempoEsperaMedio2");
        Simulador instance = null;
        double expResult = 0.0;
        double result = instance.getTiempoEsperaMedio2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTiempoEsperaMedio3 method, of class Simulador.
     */
    @Test
    public void testGetTiempoEsperaMedio3() {
        System.out.println("getTiempoEsperaMedio3");
        Simulador instance = null;
        double expResult = 0.0;
        double result = instance.getTiempoEsperaMedio3();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCola method, of class Simulador.
     */
    @Test
    public void testGetCola() {
        System.out.println("getCola");
        Simulador instance = null;
        ArrayList<Cliente> expResult = null;
        ArrayList<Cliente> result = instance.getCola();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of repintar method, of class Simulador.
     */
    @Test
    public void testRepintar() {
        System.out.println("repintar");
        ArrayList<Cliente> cola = null;
        Simulador instance = null;
        instance.repintar(cola);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class Simulador.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        Simulador instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}