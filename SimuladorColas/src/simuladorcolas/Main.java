package simuladorcolas;

/**
 *
 * @author Fabián Guevara
 * @date 16-6-10
 * @version 0.1
 */
public class Main {

    public static void main(String[] args) {
        GeneradorAleatorios generador = new GeneradorAleatorios();
        double[] ran = new double[10];
        for(int i = 0; i < 10; ++i)
        {
            ran[i] = generador.nextExponencial(10);
        }
        int[] aleatorio = new int[100];
        for(int i = 0; i < aleatorio.length; ++i)
        {
            aleatorio[i] = 0;
        }
        for(int i = 0; i < aleatorio.length; ++i)
        {
            ++aleatorio[(int)(10*generador.nextExponencial(10.0))];
        }
        for(int i = 0; i < aleatorio.length; ++i)
        {
            System.out.println(aleatorio[i]);
        }



        Simulador sim = new Simulador(2, 3, 4, 8.5, 100);
        sim.simular();
        System.out.println("Tamaño promedio de la cola: " + sim.getLongitudColaEsperada());
        System.out.println("Ttiempo promedio de espera para clientes tipo 1: " + sim.getTiempoEsperaMedio1());
        System.out.println("Ttiempo promedio de espera para clientes tipo 2: " + sim.getTiempoEsperaMedio2());
        System.out.println("Ttiempo promedio de espera para clientes tipo 3: " + sim.getTiempoEsperaMedio3());
        Interfaz pantalla = new Interfaz();
        pantalla.setVisible(true);
    }

}
