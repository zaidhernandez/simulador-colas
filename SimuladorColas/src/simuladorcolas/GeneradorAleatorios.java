package simuladorcolas;

/**
 *
 * @author Fabián Guevara
 * @date 16-6-10
 * @version 0.1
 *
 * Esta es una clase de utilería con funciones estadísticas generales para
 * producir números aleatorios con diferentes distribuciones
 */
public class GeneradorAleatorios {
    /**
     * Semilla a partir de la cual se inician las secuencias aleatorias
     * producidas por los diferentes métodos de esta clase
     */
    private static long semilla = 4657382;
    static java.util.Random generador;
    public GeneradorAleatorios()
    {
        generador = new java.util.Random();
        generador.setSeed(semilla);
    }

    /**
     * Ajusta un nuevo valor como semilla
     * @param s nueva semilla
     */
    public static void setSemilla(long s)
    {
        semilla = s;
    }
    /**
     * Genera un nuevo número pseudo-aleatorio uniforme en [0, 1[
     * @return un número pseudo-aleatorio uniforfe en [0, 1[
     */
    public static double nextUniforme()
    {
        //java.util.Random generador = new java.util.Random();
        //generador.setSeed(semilla);
        return generador.nextDouble();
        //TODO meter nuestro propio algoritmo para generar aleatorios aquí
        /*double resultado = 0;
        //código para generar un random uniforme en [0, 1[
        return resultado;*/
    }
    /**
     * Genera un nuevo entero pseudo-aleatorio uniforme en [0, tope[
     * @param cota superior al valor devuelto por este método
     * @return un entero pseudo-aleatorio uniforfe en [0, tope[
     */
    public static int nextUniforme(int tope)
    {
        return (int)(GeneradorAleatorios.nextUniforme()*tope);
    }
    /**
     * Genera un nuevo número pseudo-aleatorio exponencial en [0, 1[
     * @param lambda valor esperado (media)
     * @return un número pseudo-aleatorio uniforfe en [0, 1[
     */
    public double nextExponencial(double lambda)
    {
        return -1*Math.log(GeneradorAleatorios.nextUniforme())/lambda;
    }

}
