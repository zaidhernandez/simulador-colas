package simuladorcolas;

import java.util.Random;

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

    static java.util.Random generador;

    //private static int[] bufferAleatorios = new int[624];

    /**
     * Dado un entero n, obtiene un número real que toma a 0 como parte entera y
     * a los dígitos de n como parte flotante
     * @param num número entero a partir del cuál se construye el número real
     * @return un número real que usa a los dígitos de num como parte flotante
     * y a 0 como parte entera. Es decir, el número devuelto es igual a 0.num
     */
    private static double convertir(int num) {
        if(Integer.signum(num)!=1){
            num*=-1;
        }
        String valor = num+"";
        valor = "0."+valor;
        return Double.parseDouble(valor);
    }
    /**
     * Ajusta un nuevo valor como semilla
     * @param s nueva semilla
     */
    public static void setSemilla(long s) {
        /*GeneradorAleatorios.generador = new java.util.Random();
        GeneradorAleatorios.generador.setSeed(s);
        for (int i = 0; i < 624; i++)
        {
            bufferAleatorios[i] = GeneradorAleatorios.generador.nextInt();
        }
        indiceDelBuffer = 0;*/
    }

    /**
     * Genera un nuevo número pseudo-aleatorio uniforme en [0, 1[ usando el
     * método de Mersenne Twister
     * @see http://www.qbrundage.com/michaelb/pubs/essays/random_number_generation.html
     * @return un número pseudo-aleatorio uniforme en [0, 1[
     */
    public static double nextUniforme() {
        //código para generar un random uniforme en [0, 1[
        int seed[] = new int[256];
        Random aleatorio = new Random();
        for (int i = 0; i < seed.length; i++) {
            seed[i] = (int)(aleatorio.nextFloat()*10000);
        }
        Rand x = new Rand(seed);
        for (int i = 0; i < 2; ++i) {
            x.Isaac();
        }
        double alea = convertir(x.rsl[x.SIZE-1*x.SIZE]);
        double valor = convertir(x.rsl[(int)alea]);
        //System.out.println(valor);
        return valor;
       // return generador.nextDouble();
    }

    /**
     * Genera un nuevo entero pseudo-aleatorio uniforme en [0, tope[
     * @param cota superior al valor devuelto por este método
     * @return un entero pseudo-aleatorio uniforfe en [0, tope[
     */
    public static int nextUniforme(int tope) {
        return (int) (GeneradorAleatorios.nextUniforme() * tope);
    }

    /**
     * Genera un nuevo número pseudo-aleatorio exponencial en [0, 1[
     * @param lambda valor esperado (media)
     * @return un número pseudo-aleatorio uniforfe en [0, 1[
     */
    public static double nextExponencial(double lambda)
    {
        return -1*Math.log(GeneradorAleatorios.nextUniforme())/lambda;
    }
}
