package simuladorcolas;

/**
 *
 * @author Fabián Guevara
 * @date 16-6-10
 * @version 0.1
 */
public class Main {

    public static void main(String[] args) {
        GeneradorAleatorios.setSemilla(System.currentTimeMillis());
        double[] ran = new double[10];
        for (int i = 0; i < 10; ++i) {
            ran[i] = GeneradorAleatorios.nextUniforme();
        }
        int[] aleatorio = new int[100];
        for (int i = 0; i < aleatorio.length; ++i) {
            aleatorio[i] = 0;
        }
        for(int i = 0; i < aleatorio.length; ++i)
        {
            ++aleatorio[(int)(GeneradorAleatorios.nextExponencial(1.0/11))];
        }
        double media = 0;
        for(int i = 0; i < aleatorio.length; ++i)
        {
            System.out.println(aleatorio[i]);
            media += i*aleatorio[i];
        }
        media /= 1000;
        System.out.println("La media es: " + media);
        Interfaz pantalla = new Interfaz();
        pantalla.setVisible(true);
 
    }
}
