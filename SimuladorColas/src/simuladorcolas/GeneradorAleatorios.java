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
    //static java.util.Random generador;
    private static int mt_index;
    private static int[] mt_buffer = new int[624];

    private static double convertir(int num) {
        
        if(Integer.signum(num)!=1){
            num*=-1;
        }
        String valor = num+"";
        valor = "0."+valor;
        return Double.parseDouble(valor);
    }

    public GeneradorAleatorios() {
        java.util.Random r = new java.util.Random();
        for (int i = 0; i < 624; i++) {
            mt_buffer[i] = r.nextInt();
        }
        mt_index = 0;

        //generador = new java.util.Random();
        //generador.setSeed(semilla);
    }

    /**
     * Ajusta un nuevo valor como semilla
     * @param s nueva semilla
     */
    public static void setSemilla(long s) {
        semilla = s;
    }

    /**
     * Genera un nuevo número pseudo-aleatorio uniforme en [0, 1[
     * @return un número pseudo-aleatorio uniforme en [0, 1[
     */
    public static double nextUniforme() {
        //TODO meter nuestro propio algoritmo para generar aleatorios aquí
       int seed[] = new int[256];
        Rand x = new Rand(seed);
        for (int i = 0; i < 2; ++i) {
            x.Isaac();
        }

        //código para generar un random uniforme en [0, 1[
        if (mt_index == 624) {
            mt_index = 0;
            int i = 0;
            int s;
            for (; i < 624 - 397; i++) {
                s = (mt_buffer[i] & 0x80000000) | (mt_buffer[i + 1] & 0x7FFFFFFF);
                mt_buffer[i] = mt_buffer[i + 397] ^ (s >> 1) ^ ((s & 1) * 0x9908B0DF);
            }
            for (; i < 623; i++) {
                s = (mt_buffer[i] & 0x80000000) | (mt_buffer[i + 1] & 0x7FFFFFFF);
                mt_buffer[i] = mt_buffer[i - (624 - 397)] ^ (s >> 1) ^ ((s & 1) * 0x9908B0DF);
            }

            s = (mt_buffer[623] & 0x80000000) | (mt_buffer[0] & 0x7FFFFFFF);
            mt_buffer[623] = mt_buffer[396] ^ (s >> 1) ^ ((s & 1) * 0x9908B0DF);
        }

        int num = mt_buffer[mt_index++];
        if (Integer.signum(num) != 1) {
            num *= -1;
        }

        double valor = convertir(num);
        return valor;
        /*
        java.util.Random generador = new java.util.Random();
        generador.setSeed(semilla);
        return generador.nextDouble();
         */
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
    public double nextExponencial(double lambda) {
        return -1 * Math.log(GeneradorAleatorios.nextUniforme()) / lambda;
    }
}
