package simuladorcolas;

import java.util.ArrayList;


/**
 *
 * @author Fabián Guevara
 * @date 6-7-10
 * @version 1.0
 * La clase simulador contiene toda la lógica para una simulación de colas con
 * un solo servidor, 3 tipos de clientes con tiempos de atención
 * exponencialmente distribuido con media ajustable (igual para todos), y
 * tiempos de llegada también exponencialmente distribuidos, con media ajustable
 * para cada cliente individualmente. También el tiempo de la simulación es
 * ajustable.
 */
public class Simulador
{
    /**
     * Tiempo de llegada esperado de los clientes tipo 1
     */
    private final double lambda1;
    /**
     * Tiempo de llegada esperado de los clientes tipo 2
     */
    private final double lambda2;
    /**
     * Tiempo de llegada esperado de los clientes tipo 3
     */
    private final double lambda3;
    /**
     * Tiempo de atención esperado de los 3 tipos de cliente
     */
    private final double miu;
    /**
     * En cada momento de la simulación, indica el tiempo actual
     */
    private double horaSimulacion; //TM
    /**
     * Indica el tiempo que falta para la siguiente llegada (en cierta unidad)
     */
    private double tiempoProximaLlegada; //AT
    /**
     * Indica el tiempo faltante para la siguiente salida (en la misma unidad
     * que tiempoProximaLlegada)
     */
    private double tiempoProximaSalida; //DT
    /**
     * Si true, el servidor puede atender de inmediato al primero en la cola
     * (o el próximo en llegar, si la cola está vacía). Si false, significa
     * que el servidor está ocupado. Mientras el servidor se mantenga así, todo
     * cliente que llegue tendrá que esperar
     */
    private boolean servidorDisponible; // SS
    /**
     * En cada momento de la simulación, indica el número de clientes en la cola
     */
    private int longitudCola; // WL
    /**
     * Tiempo en el que se acaba la simulación
     */
    private final double tiempoLimiteSimulacion; // MX
    /**
     * Arreglo de listas, una para cada tipo de cliente. La i-ésima lista
     * contiene el tiempo que esperó cada cliente tipo i en el sistema para ser
     * atendido
     */
    private ArrayList<Long>[] tiemposEspera;
    /**
     * Lista con la longitud de la cola al momento de cada llegada y cada salida
     * durante la simulación
     */
    private ArrayList<Integer> longitudesCola;
    /**
     * Lista con los clientes en la cola y en el sistema. La cabeza de la cola
     * representa al cliente siendo atendido.
     */
    private ArrayList<Cliente> cola;
    /**
     * Generador de números aleatorios
     */
    private final GeneradorAleatorios generador;

    /**
     * Crea un nuevo objeto Simulador indicando la media de los tiempos de
     * llegada para cada cliente, la media del tiempo de atención y el tiempo
     * de la simulación.
     * @param l1 tiempo esperado de llegada de los clientes de tipo 1
     * @param l2 tiempo esperado de llegada de los clientes de tipo 2
     * @param l3 tiempo esperado de llegada de los clientes de tipo 3
     * @param t tiempo que tardará la simulación
     */
    public Simulador(double l1, double l2, double l3, double m, double t)
    {
        this.lambda1 = l1;
        this.lambda2 = l2;
        this.lambda3 = l3;
        this.miu = m;
        //this.horaSimulacion = 0;
        this.tiempoProximaLlegada = 0;
        this.tiempoProximaSalida = Long.MAX_VALUE;
        this.servidorDisponible = true;
        this.longitudCola = 0;
        this.tiempoLimiteSimulacion = t;
        this.tiemposEspera = new ArrayList[3];
        for(int i = 0; i < 3; ++i)
            tiemposEspera[i] = new ArrayList<Long>();
        this.longitudesCola = new ArrayList<Integer>();
        this.cola = new ArrayList<Cliente>();
        this.generador = new GeneradorAleatorios();
        generador.setSemilla(System.currentTimeMillis());
    }
    /**
     * Inicia la simulación
     */
    public void simular()
    {
        //generar tiempos de llegada
        double llegada1 = this.generador.nextExponencial(this.lambda1);
        double llegada2 = this.generador.nextExponencial(this.lambda2);
        double llegada3 = this.generador.nextExponencial(this.lambda3);
        EL_MENOR menor = this.getMenor(llegada1, llegada2, llegada3);
        do
        {
            switch(menor)//decidir cuál es el próximo evento
            {
                case PROXIMA_SALIDA:
                    //TODO manejar lógica cuando hay una salida
                    break;
                case LLEGADA_CLIENTE1:
                    //TODO encargarse del evento de llegar un cliente tipo 1
                    break;
                case LLEGADA_CLIENTE2:
                    //TODO encargarse del evento de llegar un cliente tipo 2
                    break;
                case LLEGADA_CLIENTE3:
                    //TODO encargarse del evento de llegar un cliente tipo 3
                    break;
            }
        }while(this.horaSimulacion < this.tiempoLimiteSimulacion);
        //TODO lógica de la simulación
    }
    enum EL_MENOR {PROXIMA_SALIDA, LLEGADA_CLIENTE1, LLEGADA_CLIENTE2, LLEGADA_CLIENTE3};
    private EL_MENOR getMenor(double l1, double l2, double l3)
    {
        EL_MENOR resultado = EL_MENOR.PROXIMA_SALIDA;
        double menor = this.tiempoProximaSalida;
        if(l1 < menor)
        {
            menor = l1;
            resultado = EL_MENOR.LLEGADA_CLIENTE1;
        }
        if(l2 < menor)
        {
            menor = l2;
            resultado = EL_MENOR.LLEGADA_CLIENTE2;
        }
        if(l3 < menor)
        {
            resultado = EL_MENOR.LLEGADA_CLIENTE3;
        }
        return resultado;
    }
    /**
     * Esta clase abstrae las propiedades de un cliente. Básicamente el tiempo
     * que lleva en el sistema y el tipo. Para esta segunda propiedad, esta
     * clase define una enum pública llamada TIPO.
     */
    static class Cliente
    {
        public enum TIPO {UNO, DOS, TRES};
        TIPO tipo;
        private long tiempo;

        public Cliente(TIPO t)
        {
            this.tipo = t;
            this.tiempo = 0;
        }
        /**
         * Incrementa el tiempo del cliente
         * @param t incremento del tiempo del cliente
         */
        public void actualizarTiempo(long t)
        {
            this.tiempo += t;
        }
        public long getTiempo()
        {
            return this.tiempo;
        }
    }
}
