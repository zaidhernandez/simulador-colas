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
 * @author a52566
 */
public class GeneradorAleatoriosTest {

    public GeneradorAleatoriosTest() {
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
     * Test of nextUniforme method, of class GeneradorAleatorios.
     */
    @Test
    public void testNextUniforme_0args() {
        System.out.println("nextUniforme");
        GeneradorAleatorios instance = new GeneradorAleatorios();
        //double[] resultados = new double[1000];
        double suma = 0;
        for (int i = 0; i < 1000; ++i) {
            suma += instance.nextUniforme();
        }
        suma /= 1000;
        System.out.println("diferencia de media real y teórica (debe tender a cero): " + Math.abs(suma - 1.0 / 2));
        assertTrue(Math.abs(suma - 1.0 / 2) < 2.0 / 12); // la media menos miy (0.5) debe ser menor que dos veces la varianza
    }

    /**
     * Test of nextUniforme method, of class GeneradorAleatorios.
     */
    @Test
    public void testNextUniforme_int() {
        System.out.println("nextUniforme");
        GeneradorAleatorios instance = new GeneradorAleatorios();
        // TODO review the generated test code and remove the default call to fail.
        int[] resultados = new int[1000];
        int salio;
        for (int i = 0; i < 1000; ++i) {
            salio = instance.nextUniforme(1000);
            resultados[salio]++;
            System.out.println("Salió un: " + salio);
        }
    }

    /**
     * Test of nextExponencial method, of class GeneradorAleatorios.
     */
    @Test
    public void testNextExponencial() {
        System.out.println("nextExponencial");
        GeneradorAleatorios instance = new GeneradorAleatorios();
        double expResult = 0.0;
        int[] aleatorio = new int[100];
        for (int i = 0; i < aleatorio.length; ++i) {
            aleatorio[i] = 0;
        }
        for (int i = 0; i < aleatorio.length; ++i) {
            ++aleatorio[(int) (10 * instance.nextExponencial(10.0))];
        }
        for (int i = 0; i < aleatorio.length; ++i) {
            System.out.println(aleatorio[i]);
        }
        //double result = instance.nextExponencial();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");

    }

    /**
     * Test of setSemilla method, of class GeneradorAleatorios.
     */
    @Test
    public void testSetSemilla() {
        System.out.println("setSemilla");
        long s = 0L;
        GeneradorAleatorios.setSemilla(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextUniforme method, of class GeneradorAleatorios.
     */
    @Test
    public void testNextUniforme_0args_1() {
        System.out.println("nextUniforme");
        double expResult = 0.0;
        double result = GeneradorAleatorios.nextUniforme();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextUniforme method, of class GeneradorAleatorios.
     */
    @Test
    public void testNextUniforme_int_1args() {
        System.out.println("nextUniforme");
        int tope = 0;
        int expResult = 0;
        int result = GeneradorAleatorios.nextUniforme(tope);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextExponencial method, of class GeneradorAleatorios.
     */
    @Test
    public void testNextExponencial_double() {
        System.out.println("nextExponencial");
        double lambda = 0.0;
        double expResult = 0.0;
        double result = GeneradorAleatorios.nextExponencial(lambda);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
